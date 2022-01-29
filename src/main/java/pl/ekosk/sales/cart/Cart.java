package pl.ekosk.sales.cart;

import pl.ekosk.sales.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cart {

    List<Product> products;

    public Cart() {
        this.products = new ArrayList<>();
    }

    public static Cart empty() {
        return new Cart();
    }

    public int getProductsCount() {
        return (int) products.stream()
                .map(Product::getProductId)
                .collect(Collectors.groupingBy(w -> w))
                .entrySet()
                .stream()
                .map(v -> new CartItem(v.getKey(), v.getValue().size()))
                .count();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<CartItem> getItems() {
        return products.stream()
                .map(Product::getProductId)
                .collect(Collectors.groupingBy(w -> w))
                .entrySet()
                .stream()
                .map(v -> new CartItem(v.getKey(), v.getValue().size()))
                .collect(Collectors.toList());
    }
}
