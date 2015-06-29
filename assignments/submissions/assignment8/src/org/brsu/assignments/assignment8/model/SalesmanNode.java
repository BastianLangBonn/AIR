package org.brsu.assignments.assignment8.model;

import java.util.List;

/**
 * Class representing one configuration for the traveling salesman problem.
 * 
 * @author bastian
 * 
 */
public class SalesmanNode implements Comparable<SalesmanNode> {
  private List<City> path;
  private double length;

  public SalesmanNode(List<City> path) {
    this.path = path;
    this.length = getLengthOfPath();
  }

  public double getLength() {
    return length;
  }

  private double getLengthOfPath() {
    if (path.size() == 0 || path.size() == 1) {
      return 0;
    }
    double result = 0;
    int index = 1;
    while (index < path.size()) {
      City firstCity = path.get(index - 1);
      City secondCity = path.get(index);
      result += Math.sqrt(Math.pow(firstCity.getLatitude() - secondCity.getLatitude(), 2)
          + Math.pow(firstCity.getLongitude() - secondCity.getLongitude(), 2));
      index++;
    }
    return result;
  }

  public List<City> getPath() {
    return path;
  }

  @Override
  public String toString() {
    return "SalesmanNode [path=" + path + ", length=" + length + "]";
  }

  @Override
  public int compareTo(SalesmanNode o) {
    return Double.compare(length, o.length);
  }
}
