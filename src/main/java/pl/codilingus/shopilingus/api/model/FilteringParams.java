package pl.codilingus.shopilingus.api.model;

import java.util.Set;
import pl.codilingus.shopilingus.model.types.ProductType;
import pl.codilingus.shopilingus.model.types.ServiceType;
import pl.codilingus.shopilingus.model.types.ShopType;
import pl.codilingus.shopilingus.model.types.Size;

public class FilteringParams {

  private Set<Integer> floors;

  private String shopNamePhrase;
  private Set<Integer> shopIds;
  private Set<ShopType> shopTypes;

  private String productNamePhrase;
  private Set<Integer> productIds;
  private Set<ProductType> productTypes;
  private Integer minProductQuantity;
  private Set<Size> sizes;
  private Double minProductPrice;
  private Double maxProductPrice;

  private String serviceNamePhrase;
  private Set<Integer> serviceIds;
  private Set<ServiceType> serviceTypes;
  private Integer minServiceDuration;
  private Integer maxServiceDuration;

  public Set<Integer> getFloors() {
    return floors;
  }

  public void setFloors(Set<Integer> floors) {
    this.floors = floors;
  }

  public String getShopNamePhrase() {
    return shopNamePhrase;
  }

  public void setShopNamePhrase(String shopNamePhrase) {
    this.shopNamePhrase = shopNamePhrase;
  }

  public Set<Integer> getShopIds() {
    return shopIds;
  }

  public void setShopIds(Set<Integer> shopIds) {
    this.shopIds = shopIds;
  }

  public Set<ShopType> getShopTypes() {
    return shopTypes;
  }

  public void setShopTypes(Set<ShopType> shopTypes) {
    this.shopTypes = shopTypes;
  }

  public String getProductNamePhrase() {
    return productNamePhrase;
  }

  public void setProductNamePhrase(String productNamePhrase) {
    this.productNamePhrase = productNamePhrase;
  }

  public Set<Integer> getProductIds() {
    return productIds;
  }

  public void setProductIds(Set<Integer> productIds) {
    this.productIds = productIds;
  }

  public Set<ProductType> getProductTypes() {
    return productTypes;
  }

  public void setProductTypes(Set<ProductType> productTypes) {
    this.productTypes = productTypes;
  }

  public Integer getMinProductQuantity() {
    return minProductQuantity;
  }

  public void setMinProductQuantity(Integer minProductQuantity) {
    this.minProductQuantity = minProductQuantity;
  }

  public Set<Size> getSizes() {
    return sizes;
  }

  public void setSizes(Set<Size> sizes) {
    this.sizes = sizes;
  }

  public String getServiceNamePhrase() {
    return serviceNamePhrase;
  }

  public void setServiceNamePhrase(String serviceNamePhrase) {
    this.serviceNamePhrase = serviceNamePhrase;
  }

  public Set<Integer> getServiceIds() {
    return serviceIds;
  }

  public void setServiceIds(Set<Integer> serviceIds) {
    this.serviceIds = serviceIds;
  }

  public Set<ServiceType> getServiceTypes() {
    return serviceTypes;
  }

  public void setServiceTypes(Set<ServiceType> serviceTypes) {
    this.serviceTypes = serviceTypes;
  }

  public Integer getMinServiceDuration() {
    return minServiceDuration;
  }

  public void setMinServiceDuration(Integer minServiceDuration) {
    this.minServiceDuration = minServiceDuration;
  }

  public Integer getMaxServiceDuration() {
    return maxServiceDuration;
  }

  public void setMaxServiceDuration(Integer maxServiceDuration) {
    this.maxServiceDuration = maxServiceDuration;
  }

  public Double getMinProductPrice() {
    return minProductPrice;
  }

  public void setMinProductPrice(Double minProductPrice) {
    this.minProductPrice = minProductPrice;
  }

  public Double getMaxProductPrice() {
    return maxProductPrice;
  }

  public void setMaxProductPrice(Double maxProductPrice) {
    this.maxProductPrice = maxProductPrice;
  }

}
