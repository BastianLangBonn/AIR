package org.brsu.assignments.assignment5.application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.brsu.assignments.assignment5.algorithms.AStar;
import org.brsu.assignments.assignment5.algorithms.Greedy;
import org.brsu.assignments.assignment5.model.Game;
import org.brsu.assignments.assignment5.model.heuristic.LinearConflict;
import org.brsu.assignments.assignment5.model.heuristic.ManhattenDistance;
import org.brsu.assignments.assignment5.model.heuristic.MisplacedTiles;

public class Application {

    private static final String LINE_TO_PRINT = "%s distance solution needs %d steps. Time complexity: %d. Space complexity: %d. Success: %s.\n";
    private Greedy manhattenGreedy;
    private Greedy missplacedGreedy;
    private Greedy conflictGreedy;
    private AStar manhattenStar;
    private AStar missplacedAStar;
    private AStar conflictAStar;
    private RandomStartConfigCreator randomStartConfigCreator;

    public Application() {
        randomStartConfigCreator = new RandomStartConfigCreator();
        resetAlgorithms();
    }

    private void resetAlgorithms() {
        manhattenGreedy = new Greedy(new ManhattenDistance());
        missplacedGreedy = new Greedy(new MisplacedTiles());
        conflictGreedy = new Greedy(new LinearConflict());
        manhattenStar = new AStar(new ManhattenDistance());
        missplacedAStar = new AStar(new MisplacedTiles());
        conflictAStar = new AStar(new LinearConflict());
    }

    public void run() throws IOException {
        PrintWriter writer;
        writer = new PrintWriter(new BufferedWriter(new FileWriter("resources/assignment5/result", true)));
        for (int i = 0; i < 10; i++) {
            Game game = randomStartConfigCreator.createRandomConfiguration();
            System.out.println(String.format("created startConfig: %s", game));
            System.out.println("Computing greedyManhatten");
            boolean greedyManhattenSuccess = manhattenGreedy.execute(game);
            System.out.println("computing misplacedGreedy");
            boolean misplacedGreedySuccess = missplacedGreedy.execute(game);
            System.out.println("computing linear conflict greedy");
            boolean conflictGreedySuccess = conflictGreedy.execute(game);
            System.out.println("computing manhatten A*");
            boolean manhattenStarSuccess = manhattenStar.execute(game);
            System.out.println("computing misplaced A*");
            boolean misplacedStarSuccess = missplacedAStar.execute(game);
            System.out.println("computing linear conflict A*");
            boolean conflictStarSuccess = conflictAStar.execute(game);

            writer.print(String.format("---------Problem %d-----------\n", i));
            writer.print(game.toString() + "\n");
            writer.print(String.format(LINE_TO_PRINT, "Greedy with manhatten", manhattenGreedy.getPathLength(),
                    manhattenGreedy.getNumberOfSteps(), manhattenGreedy.getMaxNodesStored(), greedyManhattenSuccess));
            writer.print(String.format(LINE_TO_PRINT, "Greedy with misplaced", missplacedGreedy.getPathLength(),
                    missplacedGreedy.getNumberOfSteps(), missplacedGreedy.getMaxNodesStored(), misplacedGreedySuccess));
            writer.print(String.format(LINE_TO_PRINT, "Greedy with linear conflict", conflictGreedy.getPathLength(),
                    conflictGreedy.getNumberOfSteps(), conflictGreedy.getMaxNodesStored(), conflictGreedySuccess));

            writer.print(String.format(LINE_TO_PRINT, "A* with manhatten", manhattenStar.getPathLength(),
                    manhattenStar.getNumberOfSteps(), manhattenStar.getMaxNodesStored(), manhattenStarSuccess));
            writer.print(String.format(LINE_TO_PRINT, "A* with misplaced", missplacedAStar.getPathLength(),
                    missplacedAStar.getNumberOfSteps(), missplacedAStar.getMaxNodesStored(), misplacedStarSuccess));
            writer.print(String.format(LINE_TO_PRINT, "A* with linear conflict", conflictAStar.getPathLength(),
                    conflictAStar.getNumberOfSteps(), conflictAStar.getMaxNodesStored(), conflictStarSuccess));

        }
        writer.close();
    }

    public static void main(String[] args) throws IOException {
        new Application().run();
    }
}
