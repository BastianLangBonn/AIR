package org.brsu.assignments.assignment5.algorithms;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.brsu.assignments.assignment5.controller.Action;
import org.brsu.assignments.assignment5.controller.GameLogic;
import org.brsu.assignments.assignment5.model.AStarNode;
import org.brsu.assignments.assignment5.model.Game;
import org.brsu.assignments.assignment5.model.heuristic.Heuristic;
import org.brsu.assignments.assignment5.model.heuristic.ManhattenDistance;
import org.brsu.assignments.assignment5.model.heuristic.MisplacedTiles;

public class AStar {

  private Heuristic heuristic;
  private GameLogic gameLogic;
  private int numberOfSteps;
  private int pathLength;
  private int maxNodesStored;

  public AStar(Heuristic heuristic) {
    this.heuristic = heuristic;
    this.gameLogic = new GameLogic();
  }

  public boolean execute(Game game) {
    List<AStarNode> fringe = new LinkedList<AStarNode>();
    Set<AStarNode> visited = new HashSet<AStarNode>();
    AStarNode currentNode = new AStarNode(game, 0, heuristic.evaluate(game));
    numberOfSteps = 0;
    maxNodesStored = 0;
    while (true) {
      if (gameLogic.performGoalTest(currentNode.getState())) {
        pathLength = currentNode.getPathCost();
        return true;
      }
      visited.add(currentNode);
      List<Action> possibleActions = gameLogic.computePossibleActions(currentNode.getState());
      for (int i = 0; i < possibleActions.size(); i++) {
        Game possibleState = gameLogic.computeTransition(currentNode.getState(), possibleActions.get(i));
        int estimate = heuristic.evaluate(possibleState);
        AStarNode node = new AStarNode(possibleState, currentNode.getPathCost() + 1, estimate);
        if (!visited.contains(node) && !fringe.contains(node)) {
          fringe.add(node);
        }
      }
      Collections.sort(fringe);
      maxNodesStored = Math.max(maxNodesStored, fringe.size());
      if (fringe.isEmpty()) {
        return false;
      }
      currentNode = fringe.get(0);
      fringe.remove(0);
      numberOfSteps++;
    }
  }

  public int getNumberOfSteps() {
    return numberOfSteps;
  }

  public int getPathLength() {
    return pathLength;
  }

  public int getMaxNodesStored() {
    return maxNodesStored;
  }

  public static void main(String[] args) {
    AStar aStar = new AStar(new ManhattenDistance());
    boolean success = aStar.execute(new Game("1,2,3,4,5,6,7,0,8"));
    System.out.println(String.format("Execution of A* succeeded: %s. Execution took %d steps.", success,
        aStar.getNumberOfSteps()));
    aStar = new AStar(new MisplacedTiles());
    success = aStar.execute(new Game("1,2,3,0,5,6,7,8,4"));
    System.out.println(String.format("Execution of A* succeeded: %s. Execution took %d steps.", success,
        aStar.getNumberOfSteps()));
  }

}
