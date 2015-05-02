package org.brsu.assignments.assignment5.model;

import java.util.HashMap;
import java.util.Map;

import org.brsu.assignments.model.Position;

public class Tile {

  public static Tile TILE_EMPTY = new Tile("", new Position(2, 2));
  public static Tile TILE_ONE = new Tile("I", new Position(0, 0));
  public static Tile TILE_TWO = new Tile("II", new Position(0, 1));
  public static Tile TILE_THREE = new Tile("III", new Position(0, 2));
  public static Tile TILE_FOUR = new Tile("IV", new Position(1, 0));
  public static Tile TILE_FIVE = new Tile("V", new Position(1, 1));
  public static Tile TILE_SIX = new Tile("VI", new Position(1, 2));
  public static Tile TILE_SEVEN = new Tile("VII", new Position(2, 0));
  public static Tile TILE_EIGHT = new Tile("VIII", new Position(2, 1));

  private static Map<String, Tile> stringMapping = new HashMap<String, Tile>();

  static {
    stringMapping.put("0", TILE_EMPTY);
    stringMapping.put("1", TILE_ONE);
    stringMapping.put("2", TILE_TWO);
    stringMapping.put("3", TILE_THREE);
    stringMapping.put("4", TILE_FOUR);
    stringMapping.put("5", TILE_FIVE);
    stringMapping.put("6", TILE_SIX);
    stringMapping.put("7", TILE_SEVEN);
    stringMapping.put("8", TILE_EIGHT);
  }

  private String label;
  private Position targetPosition;

  private Tile(String label, Position targetPosition) {
    this.label = label;
    this.targetPosition = targetPosition;
  }

  public static Tile getTile(String tileLabel) {
    return stringMapping.get(tileLabel);
  }

  public String getLabel() {
    return label;
  }

  public Position getTargetPosition() {
    return targetPosition;
  }

  @Override
  public String toString() {
    return String.format(label);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((label == null) ? 0 : label.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Tile other = (Tile) obj;
    if (label == null) {
      if (other.label != null)
        return false;
    } else if (!label.equals(other.label))
      return false;
    return true;
  }

}
