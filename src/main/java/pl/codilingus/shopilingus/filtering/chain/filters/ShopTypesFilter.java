package pl.codilingus.shopilingus.filtering.chain.filters;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import pl.codilingus.shopilingus.api.model.FilteringParams;
import pl.codilingus.shopilingus.filtering.chain.ShopFilter;
import pl.codilingus.shopilingus.model.Shop;

public class ShopTypesFilter implements ShopFilter {

  @Override
  public boolean isApplicable(FilteringParams params) {
    return params.getShopTypes() != null;
  }

  @Override
  public List<Shop> filter(List<Shop> shopsToFilter, FilteringParams params) {
    return shopsToFilter.stream()
        .filter(shop -> !Collections.disjoint(shop.getTypes(), params.getShopTypes()))
        .collect(Collectors.toList());
  }

}
