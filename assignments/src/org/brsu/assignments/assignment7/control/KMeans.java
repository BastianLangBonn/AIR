package org.brsu.assignments.assignment7.control;

import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.brsu.assignments.assignment7.model.City;
import org.brsu.assignments.assignment7.model.CityCluster;

/**
 * Class to cluster a given set of points into k cluster.
 * 
 * @author bastian
 *
 */
public class KMeans {

  private Random random;

  private Set<CityCluster> computeInitialKClusters(int k) {
    Set<CityCluster> clusters = new HashSet<CityCluster>();
    random = new Random();
    for (int i = 0; i < k; i++) {
      double longitude = random.nextDouble() * 400 - 200;
      double latitude = random.nextDouble() * 400 - 200;
      CityCluster cluster = new CityCluster(new HashSet<City>(), longitude, latitude);
      clusters.add(cluster);
    }
    return clusters;
  }

  private Set<CityCluster> computeResultingClusters(Set<City> cities, Set<CityCluster> clusters) {
    HashSet<CityCluster> result = new HashSet<CityCluster>(clusters);
    for (City city : cities) {
      double distance = 40000;
      CityCluster nearestCluster = null;
      for (CityCluster cluster : clusters) {
        double distanceToCluster = Math.sqrt(Math.pow(city.getLongitude() - cluster.getLongitude(), 2)
            + Math.pow(city.getLatitude() - cluster.getLatitude(), 2));
        if (distanceToCluster < distance) {
          distance = distanceToCluster;
          nearestCluster = cluster;
        }
      }
      nearestCluster.addCity(city);
    }
    Set<CityCluster> toDelete = new HashSet<CityCluster>();
    for (CityCluster cluster : clusters) {
      cluster.updateMean();
      if (cluster.getCities().size() < 10) {
        toDelete.add(cluster);
      }
    }
    result.removeAll(toDelete);
    if (result.equals(clusters)) {
      return clusters;
    }
    return computeResultingClusters(cities, result);
  }

  public Set<CityCluster> computeKMeans(Set<City> cities, int k) {
    Set<CityCluster> initialKClusters = computeInitialKClusters(k);
    return computeResultingClusters(cities, initialKClusters);
  }

  public static void main(String[] args) throws IOException {
    KMeans kMeans = new KMeans();
    Set<City> cities = new CityFileReader().readCitiesFromFile("resources/assignment7/cities.txt");
    Set<CityCluster> clusters = kMeans.computeKMeans(cities, 70);
    for (CityCluster cluster : clusters) {
      System.out.println(cluster.toString());
    }
  }
}
