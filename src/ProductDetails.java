public class ProductDetails {

    public Product product;
    public double quantity;

    public ProductDetails(Product product, double quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public ProductDetails(Product product, int quantity) {
        this(product, (double) quantity);
    }
}