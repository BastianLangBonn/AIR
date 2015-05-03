package org.brsu.assignments.assignment5.algorithms;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.brsu.assignments.assignment5.controller.Action;
import org.brsu.assignments.assignment5.controller.GameLogic;
import org.brsu.assignments.assignment5.model.Game;
import org.brsu.assignments.assignment5.model.GreedyNode;
import org.brsu.assignments.assignment5.model.heuristic.Heuristic;
import org.brsu.assignments.assignment5.model.heuristic.ManhattenDistance;
import org.brsu.assignments.assignment5.model.heuristic.MisplacedTiles;

public class Greedy {

  private Heuristic heuristic;
  private GameLogic gameLogic;
  private int numberOfSteps;
  private int pathLength;
  private int maxNodesStored;

  public Greedy(Heuristic heuristic) {
    this.heuristic = heuristic;
    this.gameLogic = new GameLogic();
  }

  public boolean execute(Game game) {

    List<GreedyNode> fringe = new LinkedList<GreedyNode>();
    List<GreedyNode> visited = new LinkedList<GreedyNode>();
    GreedyNode currentNode = createNewNode(game);
    numberOfSteps = 0;
    maxNodesStored = 0;
    while (true) {
      if (gameLogic.performGoalTest(currentNode.getState())) {
        pathLength = currentNode.getPathLength();
        return true;
      }
      visited.add(currentNode);
      List<Action> possibleActions = gameLogic.computePossibleActions(currentNode.getState());
      for (int i = 0; i < possibleActions.size(); i++) {
        Game possibleState = gameLogic.computeTransition(currentNode.getState(), possibleActions.get(i));
        int estimate = heuristic.evaluate(possibleState);
        GreedyNode node = new GreedyNode(possibleState, estimate, currentNode.getPathLength() + 1);
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

  private GreedyNode createNewNode(Game state) {
    int estimate = heuristic.evaluate(state);
    return new GreedyNode(state, estimate, 0);
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
    Greedy greedy = new Greedy(new ManhattenDistance());
    boolean success = greedy.execute(new Game("1,4,7,6,8,0,5,2,3"));
    System.out.println(String.format("Execution of Greedy succeeded: %s. Execution took %d steps.", success,
        greedy.getNumberOfSteps()));
    greedy = new Greedy(new MisplacedTiles());
    success = greedy.execute(new Game("1,4,7,6,8,0,5,2,3"));
    System.out.println(String.format("Execution of Greedy succeeded: %s. Execution took %d steps.", success,
        greedy.getNumberOfSteps()));
  }
}
