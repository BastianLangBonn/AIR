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

  public Agent() {
    localizer = new ElementLocalizer();
    visitedNodes = new LinkedList<Position>();
    nodesToExplore = new LinkedList<Position>();
    mapPrinter = new MapPrinter();
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
      visitedNodes.add(currentPosition);
      map.get(currentPosition.getRow()).set(currentPosition.getColumn(), ROBOT);
      addNeighboursToExploreList(map, currentPosition);
      mapPrinter.printMapToFile(map, outputFile);
      map.get(currentPosition.getRow()).set(currentPosition.getColumn(),
          VISITED);
      if (nodesToExplore.isEmpty()) {
        return;
      }
      updateState();
    }
  }

  private void updateState() {
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
    for (Position position : localizer.getNeighboursOfElement(currentPosition,
        map)) {
      if (!visitedNodes.contains(position)
          && !nodesToExplore.contains(position)) {
        String element = map.get(position.getRow()).get(position.getColumn());
        if (element.equals(DIRT) || element.equals(EMPTY)) {
          nodesToExplore.add(position);
        }
      }
    }
  }
}
