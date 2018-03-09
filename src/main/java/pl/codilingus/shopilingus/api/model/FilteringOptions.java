package pl.codilingus.shopilingus.api.model;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import pl.codilingus.shopilingus.model.Service;
import pl.codilingus.shopilingus.model.Shop;
import pl.codilingus.shopilingus.model.products.ClothingProduct;
import pl.codilingus.shopilingus.model.products.Product;
import pl.codilingus.shopilingus.model.types.ProductType;
import pl.codilingus.shopilingus.model.types.ServiceType;
import pl.codilingus.shopilingus.model.types.ShopType;
import pl.codilingus.shopilingus.model.types.Size;

public class FilteringOptions {

  private List<NamedEntity> shops;
  private Set<ShopType> shopTypes;

  private List<NamedEntity> products;
  private Set<ProductType> productTypes;

  private List<NamedEntity> services;
  private Set<ServiceType> serviceTypes;

  private int minFloor;
  private int maxFloor;

  private Set<Size> sizes;

  public FilteringOptions(List<Shop> allShops, List<Product> allProducts, List<Service> allServices) {
    this.shops = allShops.stream()
        .map(shop -> new NamedEntity(shop.getId(), shop.getName()))
        .collect(Collectors.toList());
    this.shopTypes = allShops.stream()
        .flatMap(shop -> shop.getTypes().stream())
        .collect(Collectors.toSet());
    this.products = allProducts.stream()
        .map(product -> new NamedEntity(product.getId(), product.getName()))
        .collect(Collectors.toList());
    this.productTypes = allProducts.stream()
        .map(product -> product.getType())
        .collect(Collectors.toSet());
    this.services = allServices.stream()
        .map(service -> new NamedEntity(service.getId(), service.getName()))
        .collect(Collectors.toList());
    this.serviceTypes = allServices.stream()
        .map(service -> service.getType())
        .collect(Collectors.toSet());
    this.minFloor = allShops.stream()
        .mapToInt(shop -> shop.getLocation().getFloor())
        .min().orElse(0);
    this.maxFloor = allShops.stream()
        .mapToInt(shop -> shop.getLocation().getFloor())
        .max().orElse(0);
    this.sizes = allProducts.stream()
        .filter(product -> product instanceof ClothingProduct)
        .map(product -> (ClothingProduct) product)
        .map(clothingProduct -> clothingProduct.getSize())
        .collect(Collectors.toSet());
  }

  public List<NamedEntity> getShops() {
    return shops;
  }

  public Set<ShopType> getShopTypes() {
    return shopTypes;
  }

  public List<NamedEntity> getProducts() {
    return products;
  }

  public Set<ProductType> getProductTypes() {
    return productTypes;
  }

  public List<NamedEntity> getServices() {
    return services;
  }

  public Set<ServiceType> getServiceTypes() {
    return serviceTypes;
  }

  public int getMinFloor() {
    return minFloor;
  }

  public int getMaxFloor() {
    return maxFloor;
  }

  public Set<Size> getSizes() {
    return sizes;
  }
}
