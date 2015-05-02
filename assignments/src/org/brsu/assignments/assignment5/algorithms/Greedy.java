package org.brsu.assignments.assignment5.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.brsu.assignments.assignment5.controller.Action;
import org.brsu.assignments.assignment5.controller.GameLogic;
import org.brsu.assignments.assignment5.model.Game;
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

    Game currentState = game;
    Game oldState = null;
    numberOfSteps = 0;
    while (true) {
      numberOfSteps++;
      if (gameLogic.performGoalTest(currentState)) {
        return true;
      }
      List<Action> possibleActions = gameLogic.computePossibleActions(currentState);
      List<Integer> evaluations = new ArrayList<Integer>(possibleActions.size());
      for (int i = 0; i < possibleActions.size(); i++) {
        Game possibleState = gameLogic.computeTransition(currentState, possibleActions.get(i));
        evaluations.add(heuristic.evaluate(possibleState));
      }
      Integer indexOfMaxElement = evaluations.indexOf(Collections.max(evaluations));
      Game nextState = gameLogic.computeTransition(currentState, possibleActions.get(indexOfMaxElement));
      if (nextState.equals(oldState)) {
        return false;
      }
      oldState = currentState;
      currentState = nextState;
    }
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
