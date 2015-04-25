package org.brsu.assignments.assignment4;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.brsu.assignments.model.Map;
import org.brsu.assignments.model.Position;
import org.brsu.assignments.utils.ElementLocalizer;

/**
 * Class that explores a map using the iterative deepening depth first approach.
 * 
 * @author bastian
 * 
 */
public class IterativeDeepeningAgent {

  private Map map;
  private ElementLocalizer elementLocalizer;
  private Position currentPosition;

  public IterativeDeepeningAgent() {
    elementLocalizer = new ElementLocalizer();
  }

  public void exploreMap(Map map) {
    this.map = map;
    currentPosition = elementLocalizer.localizeElement("s", map);
    List<Integer> targets = getTargetsFromMap(map);

  }

  /**
   * Check for integers in map and return them in sorted order.
   * 
   * @param map
   * @return
   */
  public List<Integer> getTargetsFromMap(Map map) {
    LinkedList<Integer> result = new LinkedList<Integer>();
    for (int x = 0; x < map.getWidth(); x++) {
      for (int y = 0; y < map.getHeight(); y++) {
        String element = map.getPosition(x, y);
        try {
          int parsedInteger = Integer.parseInt(element);
          result.add(parsedInteger);
        } catch (NumberFormatException e) {
          System.out.println(String.format("Element \"%s\" is no goal element",
              element));
        }
      }
    }
    Collections.sort(result);
    return result;
  }
}
