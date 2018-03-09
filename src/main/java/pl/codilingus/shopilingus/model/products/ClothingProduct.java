package pl.codilingus.shopilingus.model.products;

import pl.codilingus.shopilingus.model.types.ProductType;
import pl.codilingus.shopilingus.model.types.Size;

public class ClothingProduct extends Product {

  private static final double CLOTHING_TAX = 0.23;

  private final Size size;

  public ClothingProduct(String name, double price, Size size) {
    super(name, price, ProductType.CLOTHES, CLOTHING_TAX);
    this.size = size;
  }

  @Override
  public String getFullName() {
    return super.getName() + "(" + this.size.name() + ")";
  }

  public Size getSize() {
    return size;
  }
}
