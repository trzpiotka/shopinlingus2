public class Service {

    private String name;
    private double price;
    private int durationInMinutes;

    public Service(String name, double price, int duration) {
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

    public Service addService(Service service) {
        System.out.println("Dodano usługę do sklepu");
        return service;
    }

    public finishService(Service service) {
        Employee employee = new Employee();

        if ()
    }
}
