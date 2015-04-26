package org.brsu.assignments.assignment4;

import java.util.LinkedList;
import java.util.List;

import org.brsu.assignments.model.Map;
import org.brsu.assignments.model.Position;
import org.brsu.assignments.utils.ElementLocalizer;
import org.brsu.assignments.utils.MapReader;
import org.brsu.assignments.visualization.MapVisualizationFrame;

/**
 * Class initializing and running the {@link IterativeDeepeningAgent} on the
 * three given maps.
 * 
 * @author bastian
 * 
 */
public class Assignment4Runner {

  private MapReader mapReader;
  private static ElementLocalizer elementLocalizer;
  private static IterativeDeepeningAgent agent;

  public Assignment4Runner() {
    mapReader = new MapReader();
    elementLocalizer = new ElementLocalizer();
  }

  public static void main(String[] args) throws Exception {

    if (args.length != 1) {
      System.err.println("Please start program with parameter \"map1\", \"map2\" or \"map3\".");
      return;
    }

    Assignment4Runner runner = new Assignment4Runner();
    if (args[0].equals("map1")) {
      runner.runMap("resources/assignment4/maps/map1.txt");
    } else if (args[0].equals("map2")) {
      runner.runMap("resources/assignment4/maps/map2.txt");
    } else if (args[0].equals("map3")) {
      runner.runMap("resources/assignment4/maps/map3.txt");
    }
  }

  public void runMap(String filename) throws Exception {
    List<List<String>> map = mapReader.readMapFromFile(filename);
    Position startPosition = elementLocalizer.localizeElement("s", map);
    agent = new IterativeDeepeningAgent();
    List<List<Position>> paths = new LinkedList<List<Position>>();
    List<Integer> targets = elementLocalizer.findTargetsInMap(new Map(map));
    for (Integer target : targets) {
      if (!paths.isEmpty()) {
        List<Position> lastPath = paths.get(paths.size() - 1);
        if (!lastPath.isEmpty()) {
          startPosition = lastPath.get(lastPath.size() - 1);
        }
      }
      paths.add(agent.searchMapForTarget(new Map(map), startPosition, String.valueOf(target)));
    }
    new MapVisualizationFrame(new Map(map), paths);

    System.out.println(String.format("Numbers of Nodes to expand stored at once: %d.",
        agent.getMaxNumberOfNodesToExpand()));
    System.out.println(String.format("Number of nodes visited: %d", agent.getNodesVisited()));
  }
}
