package pl.codilingus.shopilingus.model;

import pl.codilingus.shopilingus.model.types.ServiceType;

public class Service {

  private static int nextId = 1;

  private int id;
  private String name;
  private ServiceType type;
  private double price;
  private int durationInMinutes;

  public Service(String name, ServiceType type, double price, int durationInMinutes) {
    this.id = Service.nextId++;
    this.name = name;
    this.type = type;
    this.price = price;
    this.durationInMinutes = durationInMinutes;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public ServiceType getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  public int getDurationInMinutes() {
    return durationInMinutes;
  }
}
