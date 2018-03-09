package pl.codilingus.shopilingus.api.model;

import pl.codilingus.shopilingus.model.products.Product;
import pl.codilingus.shopilingus.model.types.ProductType;

public class ProductDto {

  private int id;
  private String name;
  private ProductType type;
  private double quantity;
  private double priceForConsumer;
  private double priceForCompany;

  public ProductDto(Product product, double quantity) {
    this.id = product.getId();
    this.name = product.getName();
    this.type = product.getType();
    this.quantity = quantity;
    this.priceForConsumer = product.getPriceForConsumer();
    this.priceForCompany = product.getPriceForCompany();
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public ProductType getType() {
    return type;
  }

  public double getQuantity() {
    return quantity;
  }

  public double getPriceForConsumer() {
    return priceForConsumer;
  }

  public double getPriceForCompany() {
    return priceForCompany;
  }

}
