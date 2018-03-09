package pl.codilingus.shopilingus.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import pl.codilingus.shopilingus.model.products.Product;
import pl.codilingus.shopilingus.model.types.ProductType;
import pl.codilingus.shopilingus.model.types.ShopType;

public class ShoppingCenter {

  private static int nextId = 1;

  private int id;
  private List<Shop> shops;
  private String name;

  public ShoppingCenter(String name) {
    this.id = ShoppingCenter.nextId++;
    this.name = name;
    this.shops = new LinkedList<>();
  }

  public void addShop(Shop shop) {
    this.shops.add(shop);
  }

  public Shop getShop(int shopId) {
    return shops.stream()
        .filter(shop -> shop.getId() == shopId)
        .findFirst()
        .orElse(null);
  }

  public void updateShop(int shopId, Shop modifiedShop) {
    if (getShop(shopId) != null) {
      deleteShop(shopId);
      modifiedShop.setId(shopId);
      addShop(modifiedShop);
    }
  }

  public void deleteShop(int shopId) {
    Shop shop = getShop(shopId);
    if (shop != null) {
      this.shops.remove(shop);
    }
  }

  public List<Shop> findShopsByName(String name) {
    return shops.stream()
        .filter(shop -> shop.getName().equals(name))
        .collect(Collectors.toList());
  }

  public List<Product> getAllProducts() {
    List<Product> result = new LinkedList<>();
    for (Shop shop : this.shops) {
      for (ProductDetails productDetails : shop.getAllProductDetails()) {
        result.add(productDetails.getProduct());
      }
    }
    return result;
  }

  public List<Service> getAllServices() {
    List<Service> result = new LinkedList<>();
    for (Shop shop : this.shops) {
      result.addAll(shop.getServices());
    }
    return result;
  }

  public Set<ShopType> getAllShopTypes() {
    return shops.stream()
        .flatMap(shop -> shop.getTypes().stream())
        .collect(Collectors.toSet());
  }

  public Set<ProductType> getAllProductTypes() {
    return this.getAllProducts().stream()
        .map(product -> product.getType())
        .collect(Collectors.toSet());
  }

  public List<Shop> getAllShops() {
    return shops;
  }

}
