package pl.codilingus.shopilingus.filtering;

import java.util.List;
import pl.codilingus.shopilingus.api.model.FilteringParams;
import pl.codilingus.shopilingus.model.Shop;
import pl.codilingus.shopilingus.model.ShoppingCenter;

public interface FilteringService {

  List<Shop> filterShops(ShoppingCenter shoppingCenter, FilteringParams params);

}
