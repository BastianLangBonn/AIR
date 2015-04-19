package org.brsu.assignments.assignment1;

import java.util.LinkedList;
import java.util.List;

/**
 * Class to localize certain elements in a given map.
 * 
 * @author bastian
 * 
 */
public class ElementLocalizer {

  private static final String DIRT = "*";

  /**
   * Finds and returns the position of the first appearance of the given element
   * inside of a map.
   * 
   * @param element
   * @param map
   * @return
   */
  public Position localizeElement(String element, List<List<String>> map) {
    for (int i = 0; i < map.size(); i++) {
      if (map.get(i).contains(element)) {
        return new Position(i, map.get(i).indexOf(element));
      }
    }
    throw new IllegalArgumentException(String.format(
        "Element %s not found in map.", element));
  }

  /**
   * Returns a list of positions representing the neighbours of the given
   * position.
   * 
   * @param elementPosition
   * @param map
   * @return
   */
  public List<Position> getNeighboursOfElement(Position elementPosition,
      List<List<String>> map) {
    List<Position> result = new LinkedList<Position>();
    checkTopPosition(elementPosition, map, result);
    checkRightPosition(elementPosition, map, result);
    checkBottomPosition(elementPosition, map, result);
    checkLeftPosition(elementPosition, map, result);
    return result;
  }

  private void checkRightPosition(Position elementPosition,
      List<List<String>> map, List<Position> result) {
    if (elementPosition.getColumn() < map.get(0).size() - 1) {
      result.add(new Position(elementPosition.getRow(), elementPosition
          .getColumn() + 1));
    }
  }

  private void checkLeftPosition(Position elementPosition,
      List<List<String>> map, List<Position> result) {
    if (elementPosition.getColumn() > 0) {
      result.add(new Position(elementPosition.getRow(), elementPosition
          .getColumn() - 1));
    }
  }

  private void checkBottomPosition(Position elementPosition,
      List<List<String>> map, List<Position> result) {
    if (elementPosition.getRow() < map.size() - 1) {
      result.add(new Position(elementPosition.getRow() + 1, elementPosition
          .getColumn()));
    }
  }

  private void checkTopPosition(Position elementPosition,
      List<List<String>> map, List<Position> result) {
    if (elementPosition.getRow() > 0) {
      result.add(new Position(elementPosition.getRow() - 1, elementPosition
          .getColumn()));
    }
  }

  public boolean isDirtLeft(List<List<String>> map) {
    for (List<String> row : map) {
      for (String element : row) {
        if (element.equals(DIRT)) {
          return true;
        }
      }
    }
    return false;
  }
}
