package pl.codilingus.shopilingus.api.model;

import pl.codilingus.shopilingus.model.Location;

public class ShopDto {

  private int id;
  private String name;
  private Location location;

  public ShopDto(int id, String name, Location location) {
    this.id = id;
    this.name = name;
    this.location = location;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Location getLocation() {
    return location;
  }

}
