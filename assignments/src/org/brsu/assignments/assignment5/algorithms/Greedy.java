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

  public Greedy(Heuristic heuristic) {
    this.heuristic = heuristic;
    this.gameLogic = new GameLogic();
  }

  public boolean execute(Game game) {

    List<GreedyNode> fringe = new LinkedList<GreedyNode>();
    List<GreedyNode> visited = new LinkedList<GreedyNode>();
    GreedyNode currentNode = createNode(game);
    numberOfSteps = 0;
    while (true) {
      numberOfSteps++;
      if (gameLogic.performGoalTest(currentNode.getState())) {
        return true;
      }
      visited.add(currentNode);
      List<Action> possibleActions = gameLogic.computePossibleActions(currentNode.getState());
      for (int i = 0; i < possibleActions.size(); i++) {
        Game possibleState = gameLogic.computeTransition(currentNode.getState(), possibleActions.get(i));
        int estimate = heuristic.evaluate(possibleState);
        GreedyNode node = new GreedyNode(possibleState, estimate);
        if (!visited.contains(node) && !fringe.contains(node)) {
          fringe.add(node);
        }
      }
      Collections.sort(fringe);
      // System.out.print("fringe estimates: ");
      // for (GreedyNode node : fringe) {
      // System.out.print(String.format("%d, ", node.getEstimate()));
      // }
      // System.out.print("\n");
      if (fringe.isEmpty()) {
        return false;
      }
      currentNode = fringe.get(0);
      fringe.remove(0);
    }
  }

  private GreedyNode createNode(Game state) {
    int estimate = heuristic.evaluate(state);
    return new GreedyNode(state, estimate);
  }

  public int getNumberOfSteps() {
    return numberOfSteps;
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
