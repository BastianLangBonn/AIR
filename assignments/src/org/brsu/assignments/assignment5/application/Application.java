package org.brsu.assignments.assignment5.application;

import org.brsu.assignments.assignment5.algorithms.AStar;
import org.brsu.assignments.assignment5.algorithms.Greedy;
import org.brsu.assignments.assignment5.model.Game;
import org.brsu.assignments.assignment5.model.heuristic.ManhattenDistance;
import org.brsu.assignments.assignment5.model.heuristic.MisplacedTiles;

public class Application {
  public static void main(String[] args) {
    Greedy manhattenGreedy = new Greedy(new ManhattenDistance());
    Greedy missplacedGreedy = new Greedy(new MisplacedTiles());
    AStar manhattenStar = new AStar(new ManhattenDistance());
    AStar missplacedAStar = new AStar(new MisplacedTiles());
    // Game simpleGame = new Game("1,2,3,4,5,0,6,7,8");
    Game simpleGame = new Game("8,4,0,7,3,2,6,1,5");
    boolean manhattenGreedySucceeded = manhattenGreedy.execute(simpleGame);
    boolean missplacedGreedySucceeded = missplacedGreedy.execute(simpleGame);
    boolean manhattenStarSucceeded = manhattenStar.execute(simpleGame);
    boolean missplacedStarSucceeded = missplacedAStar.execute(simpleGame);

    System.out.println(String.format("Greedy with manhatten distance took %d steps. Success: %s",
        manhattenGreedy.getNumberOfSteps(), manhattenGreedySucceeded));
    System.out.println(String.format("Greedy with misplaced distance took %d steps. Success: %s",
        missplacedGreedy.getNumberOfSteps(), missplacedGreedySucceeded));
    System.out.println(String.format("A* with manhatten distance took %d steps. Success: %s",
        manhattenStar.getNumberOfSteps(), manhattenStarSucceeded));
    System.out.println(String.format("A* with misplaced distance took %d steps. Success: %s",
        missplacedAStar.getNumberOfSteps(), missplacedStarSucceeded));

  }
}
