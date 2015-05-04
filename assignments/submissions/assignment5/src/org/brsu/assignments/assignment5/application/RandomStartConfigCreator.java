package org.brsu.assignments.assignment5.application;

import java.util.List;
import java.util.Random;

import org.brsu.assignments.assignment5.controller.Action;
import org.brsu.assignments.assignment5.controller.GameLogic;
import org.brsu.assignments.assignment5.model.Game;

public class RandomStartConfigCreator {

  public Game createRandomConfiguration() {
    Game startConfig = new Game("1,2,3,4,5,6,7,8,0");
    GameLogic gameLogic = new GameLogic();
    Game currentState = startConfig;
    Random random = new Random();
    for (int i = 0; i < 1000; i++) {
      List<Action> possibleActions = gameLogic.computePossibleActions(currentState);
      currentState = gameLogic.computeTransition(currentState,
          possibleActions.get(random.nextInt(possibleActions.size())));
    }
    return currentState;
  }

  public static void main(String[] args) {
    RandomStartConfigCreator randomStartConfigCreator = new RandomStartConfigCreator();
    System.out.println(randomStartConfigCreator.createRandomConfiguration());
    System.out.println(randomStartConfigCreator.createRandomConfiguration());
    System.out.println(randomStartConfigCreator.createRandomConfiguration());

  }

}
