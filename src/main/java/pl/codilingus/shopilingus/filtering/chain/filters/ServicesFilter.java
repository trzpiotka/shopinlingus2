package pl.codilingus.shopilingus.filtering.chain.filters;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import pl.codilingus.shopilingus.api.model.FilteringParams;
import pl.codilingus.shopilingus.filtering.chain.ShopFilter;
import pl.codilingus.shopilingus.model.Service;
import pl.codilingus.shopilingus.model.Shop;

public class ServicesFilter implements ShopFilter {

  @Override
  public boolean isApplicable(FilteringParams params) {
    return params.getServiceIds() != null
        || params.getServiceNamePhrase() != null
        || params.getServiceTypes() != null
        || params.getMinServiceDuration() != null
        || params.getMaxServiceDuration() != null;
  }

  @Override
  public List<Shop> filter(List<Shop> shopsToFilter, FilteringParams params) {
    Set<Service> allServices = getServicesFromAllShops(shopsToFilter);

    List<Service> filteredServices = allServices.stream()
        .filter(service -> serviceHasExpectedId(service, params))
        .filter(service -> serviceHasExpectedNamePhrase(service, params))
        .filter(service -> serviceHasExpectedType(service, params))
        .filter(service -> serviceHasDurationInExpectedRange(service, params))
        .collect(Collectors.toList());

    return shopsToFilter.stream()
        .filter(shop -> hasAnyOfFilteredServices(shop, filteredServices))
        .collect(Collectors.toList());
  }

  private Set<Service> getServicesFromAllShops(List<Shop> shops) {
    return shops.stream()
        .flatMap(shop -> shop.getServices().stream())
        .collect(Collectors.toSet());
  }

  private boolean serviceHasExpectedNamePhrase(Service service, FilteringParams params) {
    if (params.getServiceNamePhrase() != null) {
      return service.getName().toLowerCase().contains(params.getServiceNamePhrase().toLowerCase());
    }
    return true;
  }

  private boolean serviceHasExpectedId(Service service, FilteringParams params) {
    if (params.getServiceIds() != null) {
      return params.getServiceIds().contains(service.getId());
    }
    return true;
  }

  private boolean serviceHasExpectedType(Service service, FilteringParams params) {
    if (params.getServiceTypes() != null) {
      return params.getServiceTypes().contains(service.getType());
    }
    return true;
  }

  private boolean serviceHasDurationInExpectedRange(Service service, FilteringParams params) {
    int minDuration = params.getMinServiceDuration() != null
        ? params.getMinServiceDuration()
        : Integer.MIN_VALUE;
    int maxDuration = params.getMaxServiceDuration() != null
        ? params.getMaxServiceDuration()
        : Integer.MAX_VALUE;
    int duration = service.getDurationInMinutes();

    return duration >= minDuration && duration <= maxDuration;
  }

  private boolean hasAnyOfFilteredServices(Shop shop, List<Service> services) {
    return !Collections.disjoint(shop.getServices(), services);
  }

}
