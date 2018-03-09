package pl.codilingus.shopilingus.filtering.chain.filters;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import pl.codilingus.shopilingus.api.model.FilteringParams;
import pl.codilingus.shopilingus.filtering.chain.ShopFilter;
import pl.codilingus.shopilingus.model.ProductDetails;
import pl.codilingus.shopilingus.model.Shop;
import pl.codilingus.shopilingus.model.products.ClothingProduct;

public class ProductsFilter implements ShopFilter {

  @Override
  public boolean isApplicable(FilteringParams params) {
    return params.getProductNamePhrase() != null
        || params.getProductIds() != null
        || params.getProductTypes() != null
        || params.getMinProductQuantity() != null
        || params.getMinProductPrice() != null
        || params.getMaxProductPrice() != null
        || params.getSizes() != null;
  }

  @Override
  public List<Shop> filter(List<Shop> shopsToFilter, FilteringParams params) {
    Set<ProductDetails> allProductDetails = getProductDetailsFromAllShops(shopsToFilter);

    List<ProductDetails> filteredProductDetails = allProductDetails.stream()
        .filter(productDetails -> productHasExpectedNamePhrase(productDetails, params))
        .filter(productDetails -> productHasExpectedId(productDetails, params))
        .filter(productDetails -> productHasExpectedType(productDetails, params))
        .filter(productDetails -> productHasExpectedMinQuantity(productDetails, params))
        .filter(productDetails -> productHasPriceInExpectedRange(productDetails, params))
        .filter(productDetails -> productHasExpectedSize(productDetails, params))
        .collect(Collectors.toList());

    return shopsToFilter.stream()
        .filter(shop -> hasAnyOfFilteredProducts(shop, filteredProductDetails))
        .collect(Collectors.toList());
  }

  private Set<ProductDetails> getProductDetailsFromAllShops(List<Shop> shops) {
    return shops.stream()
        .flatMap(shop -> shop.getAllProductDetails().stream())
        .collect(Collectors.toSet());
  }

  private boolean productHasExpectedNamePhrase(ProductDetails productDetails, FilteringParams params) {
    if (params.getProductNamePhrase() != null) {
      return productDetails.getProduct().getName().toLowerCase().contains(params.getProductNamePhrase().toLowerCase());
    }
    return true;
  }

  private boolean productHasExpectedId(ProductDetails productDetails, FilteringParams params) {
    if (params.getProductIds() != null) {
      return params.getProductIds().contains(productDetails.getProduct().getId());
    }
    return true;
  }

  private boolean productHasExpectedType(ProductDetails productDetails, FilteringParams params) {
    if (params.getProductTypes() != null) {
      return params.getProductTypes().contains(productDetails.getProduct().getType());
    }
    return true;
  }

  private boolean productHasExpectedMinQuantity(ProductDetails productDetails, FilteringParams params) {
    if (params.getMinProductQuantity() != null) {
      return productDetails.getQuantity() >= params.getMinProductQuantity();
    }
    return true;
  }

  private boolean productHasPriceInExpectedRange(ProductDetails productDetails, FilteringParams params) {
    double minPrice = params.getMinProductPrice() != null
        ? params.getMinProductPrice()
        : Double.MIN_VALUE;
    double maxPrice = params.getMaxProductPrice() != null
        ? params.getMaxProductPrice()
        : Double.MAX_VALUE;
    double price = productDetails.getProduct().getPriceForConsumer();

    return price >= minPrice && price <= maxPrice;
  }

  private boolean productHasExpectedSize(ProductDetails productDetails, FilteringParams params) {
    if (params.getSizes() != null && !params.getSizes().isEmpty()) {
      if (productDetails.getProduct() instanceof ClothingProduct) {
        ClothingProduct clothingProduct = (ClothingProduct) productDetails.getProduct();
        return params.getSizes().contains(clothingProduct.getSize());
      }
      return false;
    }
    return true;
  }

  private boolean hasAnyOfFilteredProducts(Shop shop, List<ProductDetails> productDetails) {
    return !Collections.disjoint(shop.getAllProductDetails(), productDetails);
  }

}
