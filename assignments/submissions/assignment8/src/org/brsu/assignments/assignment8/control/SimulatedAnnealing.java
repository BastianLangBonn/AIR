package org.brsu.assignments.assignment8.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.brsu.assignments.assignment8.model.City;
import org.brsu.assignments.assignment8.model.SalesmanNode;
import org.brsu.assignments.assignment8.view.MainFrame;

public class SimulatedAnnealing {

  private long temperature;
  private long runtime;
  private int iterations;
  private int uphills;

  public SimulatedAnnealing(long milliseconds) {
    this.runtime = milliseconds;
  }

  public SalesmanNode perform() throws IOException {
    // Read cities from file
    CityFileReader cityFileReader = new CityFileReader();
    Set<City> cities = cityFileReader.readCitiesFromFile("resources/assignment8/cities.txt");

    // create initial state
    List<City> cityList = new ArrayList<City>(cities.size());
    for (City city : cities) {
      cityList.add(city);
    }
    Collections.shuffle(cityList);
    SalesmanNode current = new SalesmanNode(cityList);

    iterations = 0;
    uphills = 0;
    temperature = runtime;
    long startTimeMilli = System.currentTimeMillis();
    while (temperature > 0) {
      List<City> nextCities = new ArrayList<City>(current.getPath());
      // Exchange two nodes
      // generate indexes to switch
      Random random = new Random();
      int firstIndex = random.nextInt(cities.size());
      int secondIndex = firstIndex;
      while (firstIndex == secondIndex) {
        secondIndex = random.nextInt(cities.size());
      }
      // Swap cities
      Collections.swap(nextCities, firstIndex, secondIndex);

      // Compare path length
      SalesmanNode next = new SalesmanNode(nextCities);
      double difference = current.getLength() - next.getLength();

      // take next if next's node is better
      if (difference > 0) {
        current = next;
      } else {
        double diceThrow = random.nextDouble();
        double probability = Math.exp(difference / temperature * 1000000);
        if (diceThrow <= probability) {
          current = next;
          uphills++;
        }
      }
      long currentTimeMillis = System.currentTimeMillis();

      temperature = runtime - (currentTimeMillis - startTimeMilli);
      iterations++;
    }

    return current;
  }

  public static void main(String[] args) throws IOException {
    Long runtimeInMinutes = Long.valueOf(args[0]);
    SimulatedAnnealing simulatedAnnealing = new SimulatedAnnealing(runtimeInMinutes * 60000);
    SalesmanNode solution = simulatedAnnealing.perform();
    new MainFrame(solution);
    System.out.println(String.format("Path length: %f", solution.getLength()));
    System.out.println(String.format("Number of iterations: %d", simulatedAnnealing.getIterations()));
    System.out.println(String.format("%f percent of total iterations went upwards",
        (double) simulatedAnnealing.getUphills() / simulatedAnnealing.getIterations() * 100));
  }

  public int getIterations() {
    return iterations;
  }

  public int getUphills() {
    return uphills;
  }

}
