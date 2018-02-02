import java.util.LinkedList;
import java.util.List;

public class Shop {
    private String name;
    private String address;
    private List<ProductDetails> products = new LinkedList<>();
    private String[] type;
    private List<Service> services = new LinkedList<>();
    private List<Employee> employees = new LinkedList<>();

    public Shop(String name, String address, String[] type) {
        this.name = name;
        this.address = address;
        this.type = type;
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
}
