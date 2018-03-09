package pl.codilingus.shopilingus.model;

import pl.codilingus.shopilingus.model.types.ServiceType;

public class Employee {

  private static int nextId = 1;

  private int id;
  private String name;
  private ServiceType[] skills;
  private boolean isAvailable;

  public Employee(String name, boolean isAvailable, ServiceType... skills) {
    this.id = Employee.nextId++;
    this.name = name;
    this.skills = skills;
    this.isAvailable = isAvailable;
  }

  public Employee(String name) {
    this(name, true);
  }

  public int getId() {
    return id;
  }

}
