import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Shop {
    private static int nextId = 0;

    public int id;
    public String name;
    private Location location;

    public List<ProductDetails> products = new LinkedList<>();
    public List<String> types = new ArrayList<>();
    public List<Service> services = new LinkedList<>();
    public List<Employee> employees = new LinkedList<>();


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


    // PRODUCT METHODS

    public void addProduct(Product addNewProduct, double quantity) {
        for (ProductDetails searchedProduct : products) {
            if (searchedProduct.product.id == addNewProduct.id) {
                searchedProduct.quantity += quantity;
            } else {
                ProductDetails newProduct = new ProductDetails(addNewProduct, quantity);
                System.out.println("Dodano produkt do sklepu");
                this.products.add(newProduct);
            }
        }
    }

    public boolean isProductAvailable(String productName) {
        for (ProductDetails availableProduct : this.products) {
            if (availableProduct.product.name.equals(productName)) {
                return true;
            }
        }
        return false;
    }

    public Product getProduct(int productId) {
        for (ProductDetails product : products) {
            if (product.product.id == productId) {
                return product.product;
            }
        }
        return null;
    }

    public void updateProduct(int productId, Product newProduct) {
        for (ProductDetails productToUpdate : products) {
            if (productToUpdate.product.id == productId) {
                ProductDetails updatedProduct = new ProductDetails(newProduct, productToUpdate.quantity);
                products.remove(productToUpdate);
                products.add(updatedProduct);
            }
        }

//        public void updateProduct(int productId, Product modifiedProduct) {
//              ProductDetails productDetails = findProductDetailsById(productId);
//              deleteProduct(productId);
//              modifiedProduct.id = productId;
//              addProduct(modifiedProduct, productDetails.quantity);
//            }
    }

    public void deleteProduct(int productId) {
        for (ProductDetails product : products) {
            if (product.product.id == productId) {
                System.out.println("Usunięto produkt ze sklepu");
                products.remove(product);
            } else {
                System.out.println("Nie ma produktu o takim id");
            }
        }
    }

//    public void buyProduct(Product product, int quantity) {
//        if (isProductAvailable(product, quantity) == true) {
//            for (ProductDetails availableProduct : this.products) {
//                availableProduct.quantity -= quantity;
//            }
//        } else {
//            System.out.println("W sklepie nie ma towaru lub wystarczającej ilości produktu");
//        }
//    }


    // SERVICE METHODS

    public void addService(Service service) {
        System.out.println("Dodano usługę do sklepu");
        this.services.add(service);
    }

    public boolean isServiceAvailable(String serviceName) {
        for (Service availableService : this.services) {
            if (availableService.name.equals(serviceName)) {
                return true;
            }
        }
        return false;
    }

    public Service getService(int serviceId) {
        for (Service searchedService : services) {
            if (searchedService.id == serviceId) {
                return searchedService;
            }
        }
        return null;
    }

    public void updateService(int serviceId, Service newService) {
        for (Service serviceToUpdate : services) {
            if (serviceToUpdate.id == serviceId) {
                services.remove(serviceToUpdate);
                services.add(newService);
            }
        }
    }

    public void deleteService(int serviceId) {
        for (Service serviceToDelete : services) {
            if (serviceToDelete.id == serviceId) {
                System.out.println("Usunięto usługę ze sklepu");
                services.remove(serviceToDelete);
            }
        }
    }

//    public void buyService(Service service) {
//        for (Employee employee : this.employees) {
//            if (employee.isAvailable) {
//                employee.isAvailable = false;
//            }
//        }
//        System.out.println("Nie ma wolnego stanowiska");
//    }

    public void finishService(Service service) {
        for (Employee employee : this.employees) {
            if (!employee.isAvailable) {
                employee.isAvailable = true;
            }
        }
    }

    // EMPLOYEE METHODS

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
        return employee.isAvailable;
    }

    public void updateEmployee(Employee employee, Employee promotedEmployee) {
        fire(employee);
        hire(promotedEmployee);
    }

    public int getNumberOfEmployees() {
        return this.employees.size();
    }

}
