package pl.codilingus.shopilingus.repository;

import java.util.Arrays;
import pl.codilingus.shopilingus.model.Service;
import pl.codilingus.shopilingus.model.Shop;
import pl.codilingus.shopilingus.model.ShoppingCenter;
import pl.codilingus.shopilingus.model.products.ClothingProduct;
import pl.codilingus.shopilingus.model.products.FoodProduct;
import pl.codilingus.shopilingus.model.products.PharmacyProduct;
import pl.codilingus.shopilingus.model.products.Product;
import pl.codilingus.shopilingus.model.types.ProductType;
import pl.codilingus.shopilingus.model.types.ServiceType;
import pl.codilingus.shopilingus.model.types.ShopType;
import pl.codilingus.shopilingus.model.types.Size;

public class DataProvider {

  private ShoppingCenter myShoppingCenter;

  public DataProvider() {
    this.myShoppingCenter = createNewShoppingCenter();
  }

  private static ShoppingCenter createNewShoppingCenter() {
    ShoppingCenter rzeszowSquare = new ShoppingCenter("Rzeszow Round Square Center");
    rzeszowSquare.addShop(createAuchan());
    rzeszowSquare.addShop(createCropp());
    rzeszowSquare.addShop(createWhyWhyClothes());
    rzeszowSquare.addShop(createRossmann());
    rzeszowSquare.addShop(createHairdresser());
    return rzeszowSquare;
  }

  private static Shop createRossmann() {
    Shop superPharm = new Shop("SuperPharm", 1, 11, ShopType.PHARMACY, ShopType.COSMETICS);

    PharmacyProduct apap = new PharmacyProduct("Apap", "Paracetamolum", 13.75);
    PharmacyProduct acard = new PharmacyProduct("Acard", "Acidum acetylsalicylicum", 7.13);
    PharmacyProduct pavulon = new PharmacyProduct("Pavulon", "Pankuronium", 500, 0.95);

    superPharm.addProduct(apap, 17);
    superPharm.addProduct(acard, 52);
    superPharm.addProduct(pavulon, 13);
    return superPharm;
  }

  private static Shop createCropp() {
    Shop cropp = new Shop("Cropp", 2, 13, ShopType.CLOTHING);
    cropp.addProduct(new ClothingProduct("T-shirt", 39.9, Size.M), 7);
    cropp.addProduct(new ClothingProduct("Cardigan", 99.9, Size.XL), 2);
    cropp.addProduct(new ClothingProduct("Red shirt", 109.9, Size.M), 1);
    return cropp;
  }

  private static Shop createWhyWhyClothes() {
    Shop whyWhy = new Shop("Why why clothes", 0, 92, ShopType.CLOTHING);
    whyWhy.addProduct(new ClothingProduct("Red Mustang", 314.159265, Size.M), 1);
    whyWhy.addProduct(new ClothingProduct("Gray long sleeve", 49.9, Size.L), 2);
    whyWhy.addProduct(new ClothingProduct("Elegant cloak", 132, Size.L), 1);
    whyWhy.addProduct(new ClothingProduct("Stylish jeans", 85.51, Size.M), 3);
    return whyWhy;
  }

  private static Shop createAuchan() {
    Shop auchan = new Shop("Auchan", -1, 66, ShopType.BAKERY, ShopType.GROCERY, ShopType.FOOD, ShopType.CLOTHING);

    auchan.addService(new Service("Key cutting", ServiceType.KEY_CUTTING, 20, 10));
    auchan.addService(new Service("Housebreaking", ServiceType.KEY_CUTTING, 1000.50, 45));

    auchan.addProduct(new FoodProduct("Bread", 2.70, Arrays.asList("flour", "leaven", "water")), 27);
    auchan.addProduct(new FoodProduct("Roll", 0.60, Arrays.asList("flour", "leaven", "water")), 172);

    auchan.addProduct(new FoodProduct("Orange", 3.87, Arrays.asList("orange", "emulsifier")), 77.5);
    auchan.addProduct(new FoodProduct("Apple", 3.87, Arrays.asList("apple", "oxidizer")), 31.2);

    auchan.addProduct(new FoodProduct("Milka Chocolate", 4.75, Arrays.asList("cocoa", "emulsifier", "oxidizer")), 13);

    auchan.addProduct(new ClothingProduct("Fancy shirt", 9.99, Size.S), 3);
    auchan.addProduct(new ClothingProduct("Fancy shirt", 9.99, Size.M), 2);
    auchan.addProduct(new ClothingProduct("Fancy shirt", 9.99, Size.L), 9);

    return auchan;
  }

  private static Shop createHairdresser() {
    Shop trendy = new Shop("Trendy", 3, 159, ShopType.HAIRDRESSER);

    trendy.addProduct(new Product("Shampoo", 63.89, ProductType.COSMETICS, 0.23), 6);
    trendy.addProduct(new Product("Super Shampoo", 188.99, ProductType.COSMETICS, 0.23), 2);

    trendy.addService(new Service("Men's haircut", ServiceType.HAIRCUT, 35, 20));
    trendy.addService(new Service("Woman's haircut", ServiceType.HAIRCUT, 35, 50));
    trendy.addService(new Service("Balding hairstyle", ServiceType.HAIRCUT, 150, 3));

    return trendy;
  }

  public ShoppingCenter getShoppingCenter() {
    return this.myShoppingCenter;
  }

}
