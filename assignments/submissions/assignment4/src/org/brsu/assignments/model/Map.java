package org.brsu.assignments.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Map {

  private List<List<String>> map;

  public Map(List<List<String>> map) {
    this.map = new ArrayList<List<String>>(map.size());
    for (List<String> row : map) {
      ArrayList<String> element = new ArrayList<String>(row);
      Collections.copy(element, row);
      this.map.add(element);
    }
  }

  /**
   * Returns the String at the given {@link Position}.
   * 
   * @param position
   * @return
   */
  public String getElementAtPosition(Position position) {
    return map.get(position.getRow()).get(position.getColumn());
  }

  /**
   * Sets the element at given {@link Position} to the given value.
   * 
   * @param position
   * @param value
   */
  public void setPosition(Position position, String value) {
    map.get(position.getRow()).set(position.getColumn(), value);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (List<String> row : map) {
      for (String element : row) {
        sb.append(element);
        sb.append(",");
      }
      sb.append("\n");
    }
    return sb.toString();
  }

  public int getWidth() {
    return map.get(0).size();
  }

  public int getHeight() {
    return map.size();
  }

  public List<List<String>> getListRepresentation() {
    return map;
  }

  public String getElementAtPosition(int x, int y) {
    return map.get(y).get(x);
  }
}
