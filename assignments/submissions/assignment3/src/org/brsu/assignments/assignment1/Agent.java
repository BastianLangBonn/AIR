package org.brsu.assignments.assignment1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Agent {
  private static final String ROBOT = "r";
  private static final String VISITED = "°";
  private static final String EMPTY = " ";
  private static final String DIRT = "*";
  private static final String START_POSITION = "s";
  private ElementLocalizer localizer;
  private List<Position> visitedNodes;
  private List<Position> nodesToExplore;
  private MapPrinter mapPrinter;
  private Strategy strategy;
  private Position currentPosition;
  private int maxNumberOfNodesToVisit;
  private int numberOfStepsUntilSolved;
  private boolean solved;

  public Agent() {
    localizer = new ElementLocalizer();
    visitedNodes = new LinkedList<Position>();
    nodesToExplore = new LinkedList<Position>();
    mapPrinter = new MapPrinter();
    solved = false;
  }

  public void exploreMap(List<List<String>> originMap, String outputFile,
      Strategy strategy) {
    mapPrinter.clearFile(outputFile);
    this.strategy = strategy;
    List<List<String>> map = createDeepCopyOfMap(originMap);
    currentPosition = localizer.localizeElement(START_POSITION, map);
    explore(outputFile, map);
  }

  private void explore(String outputFile, List<List<String>> map) {
    while (localizer.isDirtLeft(map)) {
      numberOfStepsUntilSolved++;
      if (nodesToExplore.size() > maxNumberOfNodesToVisit) {
        maxNumberOfNodesToVisit = nodesToExplore.size();
      }
      visitedNodes.add(currentPosition);
      map.get(currentPosition.getRow()).set(currentPosition.getColumn(), ROBOT);
      addNeighboursToExploreList(map, currentPosition);
      mapPrinter.printMapToFile(map, outputFile);
      map.get(currentPosition.getRow()).set(currentPosition.getColumn(),
          VISITED);
      if (nodesToExplore.isEmpty()) {
        break;
      }
      updateStateDependingOnStrategy();
    }
    if (!localizer.isDirtLeft(map)) {
      solved = true;
    }
    mapPrinter.printResult(map, maxNumberOfNodesToVisit,
        numberOfStepsUntilSolved, solved, outputFile);
  }

  private void updateStateDependingOnStrategy() {
    if (strategy == Strategy.DEPTH_FIRST) {
      currentPosition = nodesToExplore.get(nodesToExplore.size() - 1);
      nodesToExplore.remove(nodesToExplore.size() - 1);
    } else if (strategy == Strategy.BREADTH_FIRST) {
      currentPosition = nodesToExplore.get(0);
      nodesToExplore.remove(0);
    }
  }

  private List<List<String>> createDeepCopyOfMap(List<List<String>> originMap) {
    List<List<String>> map = new ArrayList<List<String>>(originMap.size());
    for (List<String> row : originMap) {
      map.add(new ArrayList<String>(row));
    }
    return map;
  }

  private void addNeighboursToExploreList(List<List<String>> map,
      Position currentPosition) {
    List<Position> neighboursOfElement = localizer.getNeighboursOfElement(
        currentPosition, map);
    for (Position neighbour : neighboursOfElement) {
      if (visitedNodes.contains(neighbour)
          || nodesToExplore.contains(neighbour)) {
        continue;
      }
      String element = map.get(neighbour.getRow()).get(neighbour.getColumn());
      if (element.equals(DIRT) || element.equals(EMPTY)) {
        nodesToExplore.add(neighbour);
      }

    }
  }
}
