package pl.codilingus.shopilingus.filtering.chain.filters;

import java.util.List;
import java.util.stream.Collectors;
import pl.codilingus.shopilingus.api.model.FilteringParams;
import pl.codilingus.shopilingus.filtering.chain.ShopFilter;
import pl.codilingus.shopilingus.model.Shop;

public class PriceRangeFilter implements ShopFilter {

  @Override
  public boolean isApplicable(FilteringParams params) {
    return true;
  }

  @Override
  public List<Shop> filter(List<Shop> shopsToFilter, FilteringParams params) {
    double minPrice = params.getMinProductPrice() != null
        ? params.getMaxProductPrice()
        : Double.MAX_VALUE;
    double maxPrice = params.getMaxProductPrice() != null
        ? params.getMaxProductPrice()
        : Double.MAX_VALUE;

    return shopsToFilter.stream()
        .filter(shop -> hasProductInPriceRange(shop, minPrice, maxPrice))
        .collect(Collectors.toList());
  }

  private boolean hasProductInPriceRange(Shop shop, double minPrice, double maxPrice) {
    return shop.getAllProductDetails().stream()
        .map(productDetails -> productDetails.getProduct())
        .anyMatch(product -> product.getPriceForConsumer() >= minPrice && product.getPriceForConsumer() <= maxPrice);
  }

}
