package pl.codilingus.shopilingus.model.products;

import java.util.List;
import pl.codilingus.shopilingus.model.types.ProductType;

public class FoodProduct extends Product {

  private static final double FOOD_TAX = 0.07;

  private final List<String> ingredients;

  public FoodProduct(String name, double price, List<String> ingredients) {
    super(name, price, ProductType.FOOD, FOOD_TAX);
    this.ingredients = ingredients;
  }

  @Override
  public String getFullName() {
    return super.getName() + "(" + String.join(", ", this.ingredients) + ")";
  }

}
