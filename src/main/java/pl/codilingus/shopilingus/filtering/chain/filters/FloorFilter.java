package pl.codilingus.shopilingus.filtering.chain.filters;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import pl.codilingus.shopilingus.api.model.FilteringParams;
import pl.codilingus.shopilingus.filtering.chain.ShopFilter;
import pl.codilingus.shopilingus.model.Shop;

public class FloorFilter implements ShopFilter {

  @Override
  public boolean isApplicable(FilteringParams params) {
    return params.getFloors() != null;
  }

  @Override
  public List<Shop> filter(List<Shop> shopsToFilter, FilteringParams params) {
    List<Shop> result = new LinkedList<>();
    Set<Integer> expectedFloors = params.getFloors();
    for (Shop shop : shopsToFilter) {
      if (expectedFloors.contains(shop.getLocation().getFloor())) {
        result.add(shop);
      }
    }
    return result;
  }

}
