package org.brsu.assignments.assignment4;

import java.util.LinkedList;
import java.util.List;

import org.brsu.assignments.model.Map;
import org.brsu.assignments.model.Position;
import org.brsu.assignments.utils.ElementLocalizer;
import org.brsu.assignments.utils.MapReader;
import org.brsu.assignments.visualization.MainFrame;

public class Assignment4Runner {

  private MapReader mapReader;
  private static ElementLocalizer elementLocalizer;
  private static IterativeDeepeningAgent agent;

  public Assignment4Runner() {
    mapReader = new MapReader();
    elementLocalizer = new ElementLocalizer();
  }

  public static void main(String[] args) throws Exception {
    // agent = new IterativeDeepeningAgent();
    // MapReader mapReader = new MapReader();
    //
    // List<List<String>> map2 =
    // mapReader.readMapFromFile("resources/assignment4/maps/map2.txt");
    // List<List<String>> map3 =
    // mapReader.readMapFromFile("resources/assignment4/maps/map3.txt");
    //
    // Position startPosition2 = elementLocalizer.localizeElement("s", map2);
    // Position startPosition3 = elementLocalizer.localizeElement("s", map3);
    //
    // List<Position> path2 = agent.searchMapForTarget(new Map(map2),
    // startPosition2, "1");
    // List<Position> path3 = agent.searchMapForTarget(new Map(map3),
    // startPosition3, "1");
    //
    // // new MainFrame(new Map(map2), startPosition1, path2);
    // // new MainFrame(new Map(map3), startPosition1, path3);
    //
    // System.out.println(path2);

    Assignment4Runner runner = new Assignment4Runner();
    runner.runMap("resources/assignment4/maps/map1.txt");
    // runner.runMap("resources/assignment4/maps/map2.txt");
    // runner.runMap("resources/assignment4/maps/map3.txt");
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
    new MainFrame(new Map(map), paths);
  }
}
