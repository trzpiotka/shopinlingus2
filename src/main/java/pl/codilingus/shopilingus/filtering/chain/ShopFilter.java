package pl.codilingus.shopilingus.filtering.chain;

import java.util.List;
import pl.codilingus.shopilingus.api.model.FilteringParams;
import pl.codilingus.shopilingus.model.Shop;

public interface ShopFilter {

  boolean isApplicable(FilteringParams params);

  List<Shop> filter(List<Shop> shopsToFilter, FilteringParams params);

}
