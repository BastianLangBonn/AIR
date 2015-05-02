package org.brsu.assignments.assignment4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.brsu.assignments.assignment4.model.Node;
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

  private static final String VERTICAL_WALL = "|";
  private static final String HORIZONTAL_WALL = "=";
  private Map map;
  private ElementLocalizer elementLocalizer;
  private LinkedList<Node> nodesToExpand;
  private LinkedList<Position> visitedPositions;
  private Node currentNode;
  private int nodesVisited;
  private int maxNumberOfNodesToExpand;
  private int depthLimit;

  // private MainFrame mainFrame;

  public IterativeDeepeningAgent() {
    elementLocalizer = new ElementLocalizer();
    nodesVisited = 0;
    maxNumberOfNodesToExpand = 0;
  }

  public List<Position> searchMapForTarget(Map map, Position startPosition, String target) throws InterruptedException {
    this.map = map;
    depthLimit = 0;
    boolean depthLimitReached;
    do {
      depthLimitReached = false;
      nodesToExpand = new LinkedList<Node>();
      nodesToExpand.add(new Node(startPosition, new ArrayList<Position>()));
      visitedPositions = new LinkedList<Position>();
      while (!nodesToExpand.isEmpty()) {
        // update current node
        currentNode = nodesToExpand.get(nodesToExpand.size() - 1);
        nodesToExpand.remove(nodesToExpand.size() - 1);
        // increase node counter
        nodesVisited++;
        // goal test
        String elementAtCurrentPosition = map.getElementAtPosition(currentNode.getPosition());
        if (elementAtCurrentPosition.equals(target)) {
          List<Position> path = currentNode.getPath();
          path.add(currentNode.getPosition());
          return path;
        }
        visitedPositions.add(currentNode.getPosition());
        if (currentNode.getLevel() < depthLimit) {
          // get neighbours
          List<Position> neighboursOfCurrentElement = elementLocalizer.getNeighboursOfElement(
              currentNode.getPosition(), map.getListRepresentation());
          addFittingNeighbours(neighboursOfCurrentElement);
        } else {
          depthLimitReached = true;
        }
      }
      depthLimit++;
    } while (depthLimitReached);
    return new ArrayList<Position>();
  }

  private void addFittingNeighbours(List<Position> neighboursOfCurrentElement) {
    // Check neighbours
    for (Position neighbour : neighboursOfCurrentElement) {
      String elementAtNeighbourPosition = map.getElementAtPosition(neighbour);
      // Check for obstacle or if node is already in path
      if (elementAtNeighbourPosition.equals(HORIZONTAL_WALL) || elementAtNeighbourPosition.equals(VERTICAL_WALL)
          || visitedPositions.contains(neighbour)) {
        continue;
      }
      // initialise path for neighbour node
      ArrayList<Position> neighboursPath = new ArrayList<Position>(currentNode.getPath().size() + 1);
      // copy current nodes path
      for (Position position : currentNode.getPath()) {
        neighboursPath.add(position);
      }
      neighboursPath.add(currentNode.getPosition());
      Node neighbourNode = new Node(neighbour, neighboursPath);
      if (!nodesToExpand.contains(neighbourNode)) {
        nodesToExpand.add(neighbourNode);
      }
    }
    if (nodesToExpand.size() > maxNumberOfNodesToExpand) {
      maxNumberOfNodesToExpand = nodesToExpand.size();
    }
  }

  public int getNodesVisited() {
    return nodesVisited;
  }

  public int getMaxNumberOfNodesToExpand() {
    return maxNumberOfNodesToExpand;
  }

}
