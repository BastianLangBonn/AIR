package org.brsu.assignments.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

  public static void main(String[] args) {
    ArrayList<Position> path = new ArrayList<Position>();
    path.add(new Position(1, 1));
    path.add(new Position(1, 2));
    path.add(new Position(1, 3));
    path.add(new Position(1, 4));
    Node a = new Node(new Position(1, 5), path);
    path.add(new Position(1, 3));
    Node b = new Node(new Position(1, 5), path);

    System.out.println(a.equals(b));
    System.out.println(a.getPath());
    System.out.println(b.getPath());

  }
}
