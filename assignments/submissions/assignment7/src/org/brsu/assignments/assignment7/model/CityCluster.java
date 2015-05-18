package org.brsu.assignments.assignment7.model;

import java.util.Set;

/**
 * Class representing a cluster of {@link City}s.
 * 
 * @author bastian
 *
 */
public class CityCluster {
  private double longitude;
  private double latitude;
  private Set<City> cities;

  public CityCluster(Set<City> cities, double longitude, double latitude) {
    this.cities = cities;
    this.longitude = longitude;
    this.latitude = latitude;
  }

  public void updateMean() {
    double sumOfLongitudes = 0;
    double sumOfLatitudes = 0;
    for (City city : cities) {
      sumOfLatitudes += city.getLatitude();
      sumOfLongitudes += city.getLongitude();
    }
    longitude = sumOfLongitudes / cities.size();
    latitude = sumOfLatitudes / cities.size();
  }

  public double getLongitude() {
    return longitude;
  }

  public double getLatitude() {
    return latitude;
  }

  public Set<City> getCities() {
    return cities;
  }

  public void addCity(City city) {
    cities.add(city);
  }

  public void removeCity(City city) {
    cities.remove(city);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((cities == null) ? 0 : cities.hashCode());
    long temp;
    temp = Double.doubleToLongBits(latitude);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(longitude);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    CityCluster other = (CityCluster) obj;
    if (cities == null) {
      if (other.cities != null)
        return false;
    } else if (!cities.equals(other.cities))
      return false;
    if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
      return false;
    if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "CityCluster [longitude=" + longitude + ", latitude=" + latitude + ", cities=" + cities + "]";
  }

}
