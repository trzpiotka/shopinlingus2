package pl.codilingus.shopilingus.api.model;

public class NamedEntity {

  private int id;
  private String name;

  public NamedEntity(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

}
