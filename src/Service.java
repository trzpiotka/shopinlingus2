public class Service {
public static int nextId = 0;

    public int id;
    public String name;
    private double price;
    private int durationInMinutes;

    public Service(String name, double price, int duration) {
        this.id = Service.nextId++;
        this.name = name;
        this.price = price;
        this.durationInMinutes = duration;
    }

    public double getBruttoP(double price, double tax) {
        return price * (1 + tax);
    }

    public double getNettoP(double price) {
        return price;
    }
}
