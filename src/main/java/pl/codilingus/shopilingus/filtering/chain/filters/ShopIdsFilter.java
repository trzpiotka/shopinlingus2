package pl.codilingus.shopilingus.filtering.chain.filters;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import pl.codilingus.shopilingus.api.model.FilteringParams;
import pl.codilingus.shopilingus.filtering.chain.ShopFilter;
import pl.codilingus.shopilingus.model.Shop;

public class ShopIdsFilter implements ShopFilter {

  @Override
  public boolean isApplicable(FilteringParams params) {
    return params.getShopIds() != null;
  }

  @Override
  public List<Shop> filter(List<Shop> shopsToFilter, FilteringParams params) {
    List<Shop> result = new LinkedList<>();
    Set<Integer> expectedShopIds = params.getShopIds();
    for (Shop shop : shopsToFilter) {
      if (expectedShopIds.contains(shop.getId())) {
        result.add(shop);
      }
    }
    return result;
  }

}
