import sun.plugin2.perf.Plugin2Rollup;

public class Product {
    public static int nextId = 0;

    public int id;
    private double price;
    public String type;
    public String name;
    private String size;
    private double weight;
    private double tax;

    public Product(double price, String type, String name, String size, double weight, double tax) {
        this.id = Product.nextId++;
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

}
