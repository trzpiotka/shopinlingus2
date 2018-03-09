package pl.codilingus.shopilingus.api;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.codilingus.shopilingus.api.model.FilteringOptions;
import pl.codilingus.shopilingus.api.model.FilteringParams;
import pl.codilingus.shopilingus.api.model.ProductDto;
import pl.codilingus.shopilingus.api.model.ShopDto;
import pl.codilingus.shopilingus.filtering.FilteringService;
import pl.codilingus.shopilingus.filtering.chain.ChainFilteringService;
import pl.codilingus.shopilingus.model.Shop;
import pl.codilingus.shopilingus.model.ShoppingCenter;
import pl.codilingus.shopilingus.repository.DataProvider;

@RestController
@CrossOrigin(origins = "*")
public class ShopilingusController {

  private FilteringService filteringService = new ChainFilteringService();
  private DataProvider dataProvider = new DataProvider();

  @RequestMapping(value = "/shops", method = RequestMethod.GET)
  public List<ShopDto> getShopList(FilteringParams filteringParams) {
    return filteringService.filterShops(dataProvider.getShoppingCenter(), filteringParams).stream()
        .map(shop -> new ShopDto(shop.getId(), shop.getName(), shop.getLocation()))
        .collect(Collectors.toList());
  }

  @RequestMapping(value = "/filters", method = RequestMethod.GET)
  public FilteringOptions getFilterValues() {
    ShoppingCenter shoppingCenter = dataProvider.getShoppingCenter();

    return new FilteringOptions(
        shoppingCenter.getAllShops(),
        shoppingCenter.getAllProducts(),
        shoppingCenter.getAllServices()
    );
  }

  @RequestMapping(value = "/shops/{shopId}/products", method = RequestMethod.GET)
  public ResponseEntity<List<ProductDto>> getListOfShopProducts(@PathVariable("shopId") Integer shopId) {
    Shop shop = dataProvider.getShoppingCenter().getShop(shopId);
    if (shop == null) {
      return ResponseEntity.notFound().build();
    } else {
      List<ProductDto> shopProductDtos = shop.getAllProductDetails().stream()
          .map(productDetails -> new ProductDto(productDetails.getProduct(), productDetails.getQuantity()))
          .collect(Collectors.toList());
      return ResponseEntity.ok(shopProductDtos);
    }
  }

}
