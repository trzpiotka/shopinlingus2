package pl.codilingus.shopilingus.filtering.stream;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import pl.codilingus.shopilingus.api.model.FilteringParams;
import pl.codilingus.shopilingus.filtering.FilteringService;
import pl.codilingus.shopilingus.model.Shop;
import pl.codilingus.shopilingus.model.ShoppingCenter;

public class StreamFilteringService implements FilteringService {

  @Override
  public List<Shop> filterShops(ShoppingCenter shoppingCenter, FilteringParams params) {
    return shoppingCenter.getAllShops().stream()
        .filter(shop -> hasShopNamePhrase(shop, params))
        .filter(shop -> hasExpectedId(shop, params))
        .filter(shop -> hasAnyExpectedShopType(shop, params))
        .filter(shop -> isOnExpectedFloor(shop, params))
        .filter(shop -> hasAnyProductInPriceRange(shop, params))
        // rest of filters
        .collect(Collectors.toList());
  }

  private boolean isOnExpectedFloor(Shop shop, FilteringParams params) {
    if (params.getFloors() != null) {
      return params.getFloors().contains(shop.getLocation().getFloor());
    }
    return true;
  }

  private boolean hasAnyExpectedShopType(Shop shop, FilteringParams params) {
    if (params.getShopTypes() != null) {
      return !Collections.disjoint(shop.getTypes(), params.getShopTypes());
    }
    return true;
  }

  private boolean hasExpectedId(Shop shop, FilteringParams params) {
    if (params.getShopIds() != null) {
      return params.getShopIds().contains(shop.getId());
    }
    return true;
  }

  private boolean hasShopNamePhrase(Shop shop, FilteringParams params) {
    if (params.getShopNamePhrase() != null) {
      return shop.getName().contains(params.getShopNamePhrase());
    }
    return true;
  }

  private boolean hasAnyProductInPriceRange(Shop shop, FilteringParams params) {
    double minPrice = params.getMinProductPrice() != null
        ? params.getMinProductPrice()
        : Double.MIN_VALUE;
    double maxPrice = params.getMaxProductPrice() != null
        ? params.getMaxProductPrice()
        : Double.MAX_VALUE;

    return hasAnyProductInPriceRange(shop, minPrice, maxPrice);
  }

  private boolean hasAnyProductInPriceRange(Shop shop, double minPrice, double maxPrice) {
    return shop.getAllProductDetails().stream()
        .map(productDetails -> productDetails.getProduct())
        .anyMatch(product -> product.getPriceForConsumer() >= minPrice && product.getPriceForConsumer() <= maxPrice);
  }

}
