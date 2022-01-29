package pl.ekosk.payment;

public class RegistrationResponse {

    public static final String STATUS_OK = "SUCCESS";

    private PaymentStatus status;
    private String redirectUri;
    private String orderId;

    public RegistrationResponse() {}

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public boolean isSuccess() {
        return getStatus().getStatusCode().equals(STATUS_OK);
    }

    class PaymentStatus {
        private String statusCode;

        public PaymentStatus() {}

        public String getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(String statusCode) {
            this.statusCode = statusCode;
        }
    }
}
