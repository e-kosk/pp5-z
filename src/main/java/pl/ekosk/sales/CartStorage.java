package pl.ekosk.sales;

import java.util.Optional;

public class CartStorage {
    public Optional<Cart> loadForCustomer(String customerId) {
        return Optional.empty();
    }

    public void save(String customerId, Cart cart) {

    }
}