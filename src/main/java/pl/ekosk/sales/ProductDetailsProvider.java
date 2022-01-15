package pl.ekosk.sales;

interface ProductDetailsProvider {
    Product getDetails(String productId);
}