package pl.codilingus.shopilingus.filtering.chain;

import java.util.Arrays;
import java.util.List;
import pl.codilingus.shopilingus.api.model.FilteringParams;
import pl.codilingus.shopilingus.filtering.FilteringService;
import pl.codilingus.shopilingus.filtering.chain.filters.FloorFilter;
import pl.codilingus.shopilingus.filtering.chain.filters.ProductsFilter;
import pl.codilingus.shopilingus.filtering.chain.filters.ServicesFilter;
import pl.codilingus.shopilingus.filtering.chain.filters.ShopIdsFilter;
import pl.codilingus.shopilingus.filtering.chain.filters.ShopNamePhraseFilter;
import pl.codilingus.shopilingus.filtering.chain.filters.ShopTypesFilter;
import pl.codilingus.shopilingus.model.Shop;
import pl.codilingus.shopilingus.model.ShoppingCenter;

public class ChainFilteringService implements FilteringService {

  private static List<ShopFilter> getChainOfFilters() {
    return Arrays.asList(
        new FloorFilter(),

        new ShopNamePhraseFilter(),
        new ShopIdsFilter(),
        new ShopTypesFilter(),

        new ProductsFilter(),
        new ServicesFilter()
    );
  }

  @Override
  public List<Shop> filterShops(ShoppingCenter shoppingCenter, FilteringParams params) {
    List<Shop> shops = shoppingCenter.getAllShops();
    for (ShopFilter filter : getChainOfFilters()) {
      if (filter.isApplicable(params)) {
        shops = filter.filter(shops, params);
      }
    }
    return shops;
  }

}
