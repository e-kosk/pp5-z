package pl.ekosk.sales;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CollectingProductsTest {

    private DummyProductDetailsProvider productDetailsProvider;
    private InMemoryCartStorage cartStorage;

    @BeforeEach
    void initializeSharedObjects() {
        productDetailsProvider = new DummyProductDetailsProvider();
        cartStorage = new InMemoryCartStorage();
    }

    @Test
    void itAllowsToAddProductsToCart() {
        //Arrange
        String customerId = thereIsCustomer("Eryk");
        String productId = thereIsProduct("product-1");
        SalesFacade sales = thereIsSalesModule();

        //Act
        sales.addToCart(customerId, productId);

        //Assert
        thereIsXProductInCustomersCart(1, customerId);
    }

    private void thereIsXProductInCustomersCart(int productsCount, String customerId) {
        Optional<Cart> optionalCart = cartStorage.loadForCustomer(customerId);
        assertTrue(optionalCart.isPresent(), "There is no cart available");

        Cart cart = optionalCart.get();
        assertEquals(productsCount, cart.getProductsCount());
    }

    private SalesFacade thereIsSalesModule() {

        return new SalesFacade(cartStorage, productDetailsProvider);
    }

    private String thereIsProduct(String productId) {
        productDetailsProvider.products.put(
                productId,
                new Product(productId, productId.toUpperCase(), BigDecimal.valueOf(10.10)));

        return productId;
    }

    private String thereIsCustomer(String customerId) {
        return customerId;
    }

    class DummyProductDetailsProvider implements ProductDetailsProvider {
        HashMap<String, Product> products;

        public DummyProductDetailsProvider() {
            this.products = new HashMap<>();
        }

        @Override
        public Product getDetails(String productId) {
            return products.get(productId);
        }
    }
}
