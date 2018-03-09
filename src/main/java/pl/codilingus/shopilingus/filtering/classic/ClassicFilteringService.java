package pl.codilingus.shopilingus.filtering.classic;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import pl.codilingus.shopilingus.api.model.FilteringParams;
import pl.codilingus.shopilingus.filtering.FilteringService;
import pl.codilingus.shopilingus.model.Shop;
import pl.codilingus.shopilingus.model.ShoppingCenter;

public class ClassicFilteringService implements FilteringService {

  @Override
  public List<Shop> filterShops(ShoppingCenter shoppingCenter, FilteringParams params) {
    List<Shop> shops = shoppingCenter.getAllShops();

    shops = filterByShopNamePhrase(shops, params);
    shops = filterByShopIds(shops, params);
    shops = filterByShopTypes(shops, params);
    shops = filterByFloor(shops, params);
    shops = filterByPriceRange(shops, params);
    // rest of filters

    return shops;
  }

  private List<Shop> filterByShopNamePhrase(List<Shop> shops, FilteringParams params) {
    if (params.getShopNamePhrase() != null) {
      return shops.stream()
          .filter(shop -> shop.getName().contains(params.getShopNamePhrase()))
          .collect(Collectors.toList());
    }
    return shops;
  }

  private List<Shop> filterByShopIds(List<Shop> shops, FilteringParams params) {
    if (params.getShopIds() != null) {
      List<Shop> result = new LinkedList<>();
      Set<Integer> expectedShopIds = params.getShopIds();
      for (Shop shop : shops) {
        if (expectedShopIds.contains(shop.getId())) {
          result.add(shop);
        }
      }
      return result;
    }
    return shops;
  }

  private List<Shop> filterByShopTypes(List<Shop> shops, FilteringParams params) {
    if (params.getShopTypes() != null) {
      return shops.stream()
          // Google: java collection containsAnys
          .filter(shop -> !Collections.disjoint(shop.getTypes(), params.getShopTypes()))
          .collect(Collectors.toList());
    }
    return shops;
  }

  private List<Shop> filterByFloor(List<Shop> shops, FilteringParams params) {
    if (params.getFloors() != null) {
      List<Shop> result = new LinkedList<>();
      Set<Integer> expectedFloors = params.getFloors();
      for (Shop shop : shops) {
        if (expectedFloors.contains(shop.getLocation().getFloor())) {
          result.add(shop);
        }
      }
      return result;
    }
    return shops;
  }

  private List<Shop> filterByPriceRange(List<Shop> shops, FilteringParams params) {
    double minPrice = params.getMinProductPrice() != null
        ? params.getMinProductPrice()
        : Double.MIN_VALUE;
    double maxPrice = params.getMaxProductPrice() != null
        ? params.getMaxProductPrice()
        : Double.MAX_VALUE;

    return shops.stream()
        .filter(shop -> hasProductInPriceRange(shop, minPrice, maxPrice))
        .collect(Collectors.toList());
  }

  private boolean hasProductInPriceRange(Shop shop, double minPrice, double maxPrice) {
    return shop.getAllProductDetails().stream()
        .map(productDetails -> productDetails.getProduct())
        .anyMatch(product -> product.getPriceForConsumer() >= minPrice && product.getPriceForConsumer() <= maxPrice);
  }

}
