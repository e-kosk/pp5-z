package pl.ekosk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.ekosk.greetings.Greeter;
import pl.ekosk.productcatalog.*;

import java.math.BigDecimal;
import java.util.UUID;

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
        productCatalog.publish("product-1");

        return productCatalog;
    }

    @Bean
    ProductStorage createDbProductStorage(ProductRepository productRepository) {
        return new DatabaseProductStorage(productRepository);
    }

}
