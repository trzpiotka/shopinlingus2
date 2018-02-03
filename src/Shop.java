import java.util.LinkedList;
import java.util.List;

public class Shop {
    private static int nextId = 0;

    public static int id;
    public String name;
    private Location location;

    private List<ProductDetails> products = new LinkedList<>();
    private List<String> types;
    private List<Service> services = new LinkedList<>();
    private List<Employee> employees = new LinkedList<>();

    /*
    method overloading
    method overriding
     */


    public Shop(String name, int floor, int box, List<String> types) {
        this.id = Shop.nextId++;
        this.name = name;
        this.location = new Location(floor, box);
        this.types = types;
        this.products = new LinkedList<ProductDetails>();
        this.services = new LinkedList<Service>();
        this.employees = new LinkedList<Employee>();
    }


    public Shop(String name, int floor, int box) {
        this(name, floor, box, new LinkedList<String>());
    }

    public boolean isProductAvailable(Product product, int quantity) {
        for (ProductDetails availableProduct : this.products) {
            if (availableProduct.product == product && availableProduct.quantity >= quantity) {
                return true;
            }
        }
        return false;
    }

    public void buyProduct(Product product, int quantity) {
        if (isProductAvailable(product, quantity) == true) {
            for (ProductDetails availableProduct : this.products) {
                availableProduct.quantity -= quantity;
            }
        } else {
            System.out.println("W sklepie nie ma towaru lub wystarczającej ilości produktu");
        }
    }

//    public buyService(Service service) {
//
//    }

    public void hire(List<Employee> newEmployeesToHire) {
        for (Employee employee : newEmployeesToHire) {
            this.hire(employee);
        }
    }

    public void hire(Employee employee) {
        this.employees.add(employee);
    }

    public void fire(Employee employee) {
        this.employees.remove(employee);
    }

    public boolean isEmployeeAvailable(Employee employee) {
        if (employee.isAvailable == true) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "ID: " + this.id + " , Name: " + this.name + " , Address: " + this.location + " , Type: " + this.types +
                " Products: " + this.products + " , Services: " + this.services + " , Employees: " + this.employees;
    }

    // CRUD Product
    // CRUD Employees
    // CRUD Services
}
