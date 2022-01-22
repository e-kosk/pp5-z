package pl.ekosk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.ekosk.greetings.Greeter;
import pl.ekosk.productcatalog.*;
import pl.ekosk.productcatalog.Product;
import pl.ekosk.sales.*;
import pl.ekosk.sales.cart.InMemoryCartStorage;
import pl.ekosk.sales.offerting.OfferMaker;

import java.math.BigDecimal;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        Greeter greeter = new Greeter();
        System.out.println(greeter.hello("Eryk"));
    }

    @Bean
    Greeter createGreater() {
        return new Greeter();
    }

    @Bean
    ProductCatalog createCatalog(ProductStorage productStorage) {
        ProductCatalog productCatalog = new ProductCatalog(productStorage);

        productCatalog.addProduct(
                "product-1",
                "my nice picture",
                "very nice"
        );

        productCatalog.updatePrice("product-1", BigDecimal.valueOf(10.10));
        productCatalog.assignImage("product-1", "https://picsum.photos/id/103/200/300");
        productCatalog.publish("product-1");

        productCatalog.addProduct(
                "product-2",
                "my nice picture 2",
                "bla bla"
        );

        productCatalog.updatePrice("product-2", BigDecimal.valueOf(20.10));
        productCatalog.assignImage("product-2", "https://picsum.photos/id/102/200/300");
        productCatalog.publish("product-2");

        productCatalog.addProduct(
                "product-3",
                "My Nice Picture 3",
                "Bla bla"
        );

        productCatalog.updatePrice("product-3", BigDecimal.valueOf(30.10));
        productCatalog.assignImage("product-3", "https://picsum.photos/id/1062/200/300");
        productCatalog.publish("product-3");

        return productCatalog;
    }

    @Bean
    ProductStorage createDbProductStorage(ProductRepository productRepository) {
        return new DatabaseProductStorage(productRepository);
    }

    @Bean
    SalesFacade createSalesFacade(ProductCatalog productCatalog) {
        ProductDetailsProvider productDetailsProvider = (ProductDetailsProvider) (productId) -> {
            Product loadedProduct = productCatalog.loadProduct(productId);

            return new pl.ekosk.sales.Product(
                    loadedProduct.getProductId(),
                    loadedProduct.getName(),
                    loadedProduct.getPrice()
            );
        };

        return new SalesFacade(
                new InMemoryCartStorage(),
                productDetailsProvider,
                new OfferMaker(productDetailsProvider)
        );
    }
}
