package org.brsu.assignments.assignment7.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.brsu.assignments.assignment7.model.City;
import org.brsu.assignments.assignment7.model.CityCluster;
import org.brsu.assignments.assignment7.model.SalesmanNode;
import org.brsu.assignments.assignment7.view.MainFrame;

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

  public SalesmanNode solveProblem(int numberOfSeeds, List<City> cities) {
    List<SalesmanNode> solutions = new ArrayList<SalesmanNode>();
    for (int i = 0; i < numberOfSeeds; i++) {
      SalesmanNode node = createRandomConfiguration(cities);
      SalesmanNode solution = getSolution(node);
      solutions.add(solution);
    }
    Collections.sort(solutions);
    return solutions.get(0);
  }

  private SalesmanNode createRandomConfiguration(List<City> cities) {
    List<City> path = new ArrayList<City>(cities);
    Collections.shuffle(path);
    return new SalesmanNode(path);
  }

  public SalesmanNode solveProblemUsingKMeans() {
    KMeans kMeans = new KMeans();
    Set<CityCluster> clusters = kMeans.computeKMeans(cities, 50);
    System.out.println(String.format("Clusters: %s", clusters.toString()));
    // Compute path for clusters
    List<SalesmanNode> clusterSolutions = new LinkedList<SalesmanNode>();
    for (CityCluster cluster : clusters) {
      SalesmanNode clusterSolution = solveProblem(1, new ArrayList<City>(cluster.getCities()));
      clusterSolutions.add(clusterSolution);
      System.out.println(String.format("Solution for a cluster: %s", clusterSolution.toString()));
    }

    // Convert cluster means into cities
    LinkedList<CityCluster> clusterList = new LinkedList<CityCluster>(clusters);
    LinkedList<City> clusterCityList = new LinkedList<City>();
    for (int i = 0; i < clusterList.size(); i++) {
      clusterCityList.add(new City(String.valueOf(i), clusterList.get(i).getLongitude(), clusterList.get(i)
          .getLatitude()));
    }
    // Compute path for cluster means
    SalesmanNode clusterPath = solveProblem(1, clusterCityList);
    System.out.println(String.format("Order of clusters: %s", clusterPath.toString()));

    // Get ordering of clusters
    List<List<City>> orderOfClusters = new ArrayList<List<City>>(clusterPath.getPath().size());
    for (City city : clusterPath.getPath()) {
      int index = Integer.parseInt(city.getName());
      orderOfClusters.add(clusterSolutions.get(index).getPath());
    }

    List<City> resultingPath = new LinkedList<City>();
    for (List<City> path : orderOfClusters) {
      resultingPath.addAll(path);
    }

    return new SalesmanNode(resultingPath);

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
    // System.out.println(String.format("Current path length: %f",
    // currentShortestNode.getLength()));
    return getSolution(currentShortestNode);
  }

  public static void main(String[] args) throws IOException {
    TravellingSalesmanRunner runner = new TravellingSalesmanRunner();
    SalesmanNode solution = runner.solveProblemUsingKMeans();
    // Set<City> cities = new
    // CityFileReader().readCitiesFromFile("resources/assignment7/cities.txt");
    // SalesmanNode solution = runner.solveProblem(10, new
    // LinkedList<City>(cities));
    System.out.println(String.format("Solution: %s", solution.toString()));

    for (City city : solution.getPath()) {
      System.out.println(city.getName());
    }

    System.out.println(solution.getLength());

    new MainFrame(solution);
  }
}
