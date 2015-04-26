package org.brsu.assignments.assignment4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.brsu.assignments.model.Map;
import org.brsu.assignments.model.Node;
import org.brsu.assignments.model.Position;
import org.brsu.assignments.utils.ElementLocalizer;
import org.brsu.assignments.utils.MapPrinter;
import org.brsu.assignments.utils.MapReader;
import org.brsu.assignments.visualization.MainFrame;

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

  // private MainFrame mainFrame;

  public IterativeDeepeningAgent() {
    elementLocalizer = new ElementLocalizer();
  }

  public List<Position> searchMapForTarget(Map map, Position startPosition, String target) throws InterruptedException {
    this.map = map;
    // mainFrame = new MainFrame(map, startPosition, new ArrayList<Position>());
    int depthLimit = 0;
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
  }

  public static void main(String[] args) throws Exception {
    IterativeDeepeningAgent agent = new IterativeDeepeningAgent();
    MapReader reader = new MapReader();
    List<List<String>> map = reader.readMapFromFile("resources/assignment4/maps/map2.txt");
    ElementLocalizer localizer = new ElementLocalizer();
    Position startPosition = localizer.localizeElement("s", map);
    List<Position> path = agent.searchMapForTarget(new Map(map), startPosition, "1");
    path.addAll(agent.searchMapForTarget(new Map(map), path.get(path.size() - 1), "2"));
    path.addAll(agent.searchMapForTarget(new Map(map), path.get(path.size() - 1), "3"));
    path.addAll(agent.searchMapForTarget(new Map(map), path.get(path.size() - 1), "4"));
    path.addAll(agent.searchMapForTarget(new Map(map), path.get(path.size() - 1), "5"));
    path.addAll(agent.searchMapForTarget(new Map(map), path.get(path.size() - 1), "6"));
    path.addAll(agent.searchMapForTarget(new Map(map), path.get(path.size() - 1), "7"));
    path.addAll(agent.searchMapForTarget(new Map(map), path.get(path.size() - 1), "8"));
    path.addAll(agent.searchMapForTarget(new Map(map), path.get(path.size() - 1), "9"));
    new MapPrinter().printMap(map);
    System.out.println(path);
    System.out.println(path.size());
    LinkedList<List<Position>> paths = new LinkedList<List<Position>>();
    paths.add(path);
    new MainFrame(new Map(map), paths);
  }
}
