package pl.codilingus.shopilingus.model;

public class Location {

  private int floor;
  private int box;

  public Location(int floor, int box) {
    this.floor = floor;
    this.box = box;
  }

  public int getFloor() {
    return floor;
  }

  public int getBox() {
    return box;
  }

}
