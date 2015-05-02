package org.brsu.assignments.assignment4.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.brsu.assignments.model.Position;

/**
 * Class representing a node of the search problem
 * 
 * @author bastian
 * 
 */
public class Node {

  private Position position;
  private List<Position> path;

  public Node(Position position, List<Position> path) {
    this.position = position;
    this.path = new ArrayList<Position>(path);
  }

  public int getLevel() {
    return path.size();
  }

  public Position getPosition() {
    return position;
  }

  public List<Position> getPath() {
    ArrayList<Position> result = new ArrayList<Position>(path);
    Collections.copy(result, path);
    return result;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((position == null) ? 0 : position.hashCode());
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
    Node other = (Node) obj;
    if (position == null) {
      if (other.position != null)
        return false;
    } else if (!position.equals(other.position))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return String.format("Position: %s, Path: %s", position.toString(), path.toString());
  }
}
