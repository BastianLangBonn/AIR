package org.brsu.assignments.assignment7.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.brsu.assignments.assignment7.model.City;
import org.brsu.assignments.assignment7.model.SalesmanNode;

/**
 * Class implementing the solver for the travelling salesman problem.
 * 
 * @author bastian
 *
 */
public class TravellingSalesmanRunner {

  private CityFileReader cityFileReader;
  private Set<City> cities;

  public TravellingSalesmanRunner() throws IOException {
    cityFileReader = new CityFileReader();
    cities = cityFileReader.readCitiesFromFile("resources/assignment7/cities.txt");
  }

  public SalesmanNode solveProblem(int numberOfSeeds) {
    List<SalesmanNode> solutions = new ArrayList<SalesmanNode>();
    for (int i = 0; i < numberOfSeeds; i++) {
      SalesmanNode node = createRandomConfiguration();
      SalesmanNode solution = getSolution(node);
      solutions.add(solution);
    }
    Collections.sort(solutions);
    return solutions.get(0);
  }

  private SalesmanNode createRandomConfiguration() {
    List<City> path = new ArrayList<City>(cities);
    Collections.shuffle(path);
    return new SalesmanNode(path);
  }

  private SalesmanNode getSolution(SalesmanNode node) {
    List<City> path = node.getPath();
    SalesmanNode currentShortestNode = node;
    for (int i = 0; i < path.size(); i++) {
      // System.out.print(String.format("\nElement:%d, currentPathLength: %f ",
      // i, currentShortestNode.getLength()));
      for (int j = i; j < path.size(); j++) {
        // System.out.print(String.format(", %d;", j));
        ArrayList<City> possibleSuccessor = new ArrayList<City>(path);
        possibleSuccessor.set(i, path.get(j));
        possibleSuccessor.set(j, path.get(i));
        SalesmanNode successorNode = new SalesmanNode(possibleSuccessor);
        if (successorNode.getLength() < currentShortestNode.getLength()) {
          currentShortestNode = successorNode;
        }
      }
    }
    if (currentShortestNode.getLength() == node.getLength()) {
      return node;
    }
    System.out.println(String.format("Current path length: %f", currentShortestNode.getLength()));
    return getSolution(currentShortestNode);
  }

  public static void main(String[] args) throws IOException {
    TravellingSalesmanRunner runner = new TravellingSalesmanRunner();
    SalesmanNode solution = runner.solveProblem(1);
    System.out.println(String.format("Solution: %s", solution.toString()));
  }
}
