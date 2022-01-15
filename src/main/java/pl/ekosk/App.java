package pl.ekosk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.ekosk.greetings.Greeter;
import pl.ekosk.productcatalog.DatabaseProductStorage;
import pl.ekosk.productcatalog.ProductCatalog;
import pl.ekosk.productcatalog.InMemoryProductStorage;
import pl.ekosk.productcatalog.ProductStorage;

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
        return new ProductCatalog(productStorage);
    }

    @Bean
    ProductStorage provideProductStorate() {
        return crateproductStoage();
    }

    DatabaseProductStorage createDbProductSotrage() {
        return new DatabaseProductStorage();
    }

    InMemoryProductStorage crateproductStoage() {
        return new InMemoryProductStorage();
    }
}
