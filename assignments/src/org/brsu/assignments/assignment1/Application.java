package org.brsu.assignments.assignment1;

import java.io.IOException;
import java.util.List;

public class Application {

  public static void main(String[] args) throws IOException {
    MapReader mapReader = new MapReader();
    List<List<String>> map1 = mapReader
        .readMapFromFile("src/resources/maps/map1.txt");
    List<List<String>> map2 = mapReader
        .readMapFromFile("src/resources/maps/map2.txt");
    List<List<String>> map3 = mapReader
        .readMapFromFile("src/resources/maps/map3.txt");

    Agent agent = new Agent();
    agent.exploreMap(map1, "src/resources/results/map1_bfs",
        Strategy.BREADTH_FIRST);
    agent = new Agent();
    agent.exploreMap(map2, "src/resources/results/map2_bfs",
        Strategy.BREADTH_FIRST);
    agent = new Agent();
    agent.exploreMap(map3, "src/resources/results/map3_bfs",
        Strategy.BREADTH_FIRST);

    agent = new Agent();
    agent.exploreMap(map1, "src/resources/results/map1_dfs",
        Strategy.DEPTH_FIRST);
    agent = new Agent();
    agent.exploreMap(map2, "src/resources/results/map2_dfs",
        Strategy.DEPTH_FIRST);
    agent = new Agent();
    agent.exploreMap(map3, "src/resources/results/map3_dfs",
        Strategy.DEPTH_FIRST);

  }
}
