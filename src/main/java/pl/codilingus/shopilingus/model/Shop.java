package pl.codilingus.shopilingus.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import pl.codilingus.shopilingus.model.products.Product;
import pl.codilingus.shopilingus.model.types.ShopType;

public class Shop {

  private static int nextId = 1;

  private int id;
  private String name;
  private Location location;

  private Set<ShopType> types;
  private List<ProductDetails> allProductDetails;
  private List<Service> services;
  private List<Employee> employees;

  public Shop(String name, int floor, int box, Set<ShopType> types) {
    this.id = Shop.nextId++;
    this.name = name;
    this.location = new Location(floor, box);
    this.types = types;
    this.allProductDetails = new LinkedList<>();
    this.services = new LinkedList<>();
    this.employees = new LinkedList<>();
  }

  public Shop(String name, int floor, int box, ShopType... shopTypes) {
    this(name, floor, box, new HashSet<ShopType>(Arrays.asList(shopTypes)));
  }

  public Shop(String name, int floor, int box) {
    this(name, floor, box, new HashSet<>());
  }

  public void hire(List<Employee> newEmployeesToHire) {
    this.employees.addAll(newEmployeesToHire);
  }

  public void hire(Employee employee) {
    this.employees.add(employee);
  }

  public void fire(int employeeId) {
    this.employees.removeIf(employee -> employee.getId() == employeeId);
  }

  public void addProduct(Product product, double quantity) {
    ProductDetails existingProduct = getProductDetails(product.getId());
    if (existingProduct != null) {
      existingProduct.addQuantity(quantity);
    } else {
      this.allProductDetails.add(new ProductDetails(product, quantity));
    }
  }

  private ProductDetails getProductDetails(int productId) {
    for (ProductDetails productDetails : allProductDetails) {
      if (productDetails.getProduct().getId() == productId) {
        return productDetails;
      }
    }
    return null;
  }

  public Product getProduct(int productId) {
    ProductDetails productDetails = getProductDetails(productId);
    if (productDetails != null) {
      return productDetails.getProduct();
    }
    return null;
  }

  public void updateProduct(int productId, Product modifiedProduct) {
    ProductDetails productDetails = getProductDetails(productId);
    if (productDetails != null) {
      deleteProduct(productId);
      modifiedProduct.setId(productId);
      addProduct(modifiedProduct, productDetails.getQuantity());
    }
  }

  public void deleteProduct(int productId) {
    this.allProductDetails.removeIf(productDetails -> productDetails.getProduct().getId() == productId);
  }

  public void addService(Service service) {
    this.services.add(service);
  }

  public Service getService(int serviceId) {
    return this.services.stream()
        .filter(service -> service.getId() == serviceId)
        .findFirst()
        .orElse(null);
  }

  public void updateService(int serviceId, Service modifiedService) {
    deleteService(serviceId);
    modifiedService.setId(serviceId);
    addService(modifiedService);
  }

  public void deleteService(int serviceId) {
    Service service = getService(serviceId);
    if (service != null) {
      this.services.remove(service);
    }
  }

  public List<Product> getAllProducts() {
    return this.allProductDetails.stream()
        .map(productDetails -> productDetails.getProduct())
        .collect(Collectors.toList());
  }

  public int getNumberOfEmployees() {
    return this.employees.size();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public Set<ShopType> getTypes() {
    return types;
  }

  public List<ProductDetails> getAllProductDetails() {
    return allProductDetails;
  }

  public List<Service> getServices() {
    return services;
  }

  public Location getLocation() {
    return location;
  }
}
