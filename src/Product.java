import sun.plugin2.perf.Plugin2Rollup;

public class Product {
    private double price;
    private String type;
    private String name;
    private String size;
    private double weight;
    private double tax;

    public Product(double price, String type, String name, String size, double weight, double tax) {
        this.price = price;
        this.type = type;
        this.name = name;
        this.size = size;
        this.weight = weight;
        this.tax = tax;
    }

    public double getBruttoP(double price, double tax) {
        return price * (1 + tax);
    }

    public double getNettoP(double price) {
        return price;
    }

    public ProductDetails addProduct(Product product, double quantity) {
        ProductDetails newProduct = new ProductDetails(product, quantity);
        System.out.println("Dodano produkt do sklepu");
        return newProduct;
    }
}
