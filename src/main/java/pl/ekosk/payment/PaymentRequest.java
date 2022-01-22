package pl.ekosk.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class PaymentRequest {
    private String notifyUrl;
    private String customerIp;
    private String merchantPosId;
    private String description;
    private String currencyCode;
    private Integer totalAmount;
    private Buyer buyer;
    private List<Product> products;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    static
    class Buyer {
        private String email;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    class Product {
        private String name;
    }
}
