package pl.codilingus.shopilingus.model.products;

import pl.codilingus.shopilingus.model.types.ProductType;

public class Product {

  private static int nextId = 1;

  private int id;
  private String name;
  private double price;
  private ProductType type;
  private double tax;

  public Product(String name, double price, ProductType type, double tax) {
    this.id = Product.nextId++;
    this.name = name;
    this.price = price;
    this.type = type;
    this.tax = tax;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public ProductType getType() {
    return this.type;
  }

  public String getName() {
    return this.name;
  }

  public String getFullName() {
    return this.name;
  }

  public void print() {
    System.out.println(this.getFullName());
  }

  public double getPriceForCompany() {
    return this.price;
  }

  public double getPriceForConsumer() {
    return (1.0 + this.tax) * this.price;
  }

}
