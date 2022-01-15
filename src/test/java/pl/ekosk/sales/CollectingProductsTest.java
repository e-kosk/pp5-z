package pl.ekosk.sales;

import org.junit.jupiter.api.Test;

public class CollectingProductsTest {

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
        Cart cart = cartStorage.loadForCustomer(customerId);
        assertEquals(productsCount, cart.getProductsCount());
    }

    private SalesFacade thereIsSalesModule() {
        return new SalesFacade();
    }

    private String thereIsProduct(String productId) {
        return null;
    }

    private String thereIsCustomer(String customerId) {
        return customerId;
    }
}