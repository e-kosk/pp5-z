package pl.ekosk.sales;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    List<Product> products;

    public Cart() {
        this.products = new ArrayList<>();
    }

    public static Cart empty() {
        return new Cart();
    }

    public int getProductsCount() {
        return products.size();
    }

    public void addProduct(Product product) {
        products.add(product);
    }
}