package pl.codilingus.shopilingus.model;

import pl.codilingus.shopilingus.model.products.Product;

public class ProductDetails {

  private Product product;
  private double quantity;

  public ProductDetails(Product product, double quantity) {
    this.product = product;
    this.quantity = quantity;
  }

  public ProductDetails(Product product, int quantity) {
    this(product, (double) quantity);
  }

  public Product getProduct() {
    return product;
  }

  public double getQuantity() {
    return quantity;
  }

  public void addQuantity(double quantity) {
    this.quantity += quantity;
  }

}
