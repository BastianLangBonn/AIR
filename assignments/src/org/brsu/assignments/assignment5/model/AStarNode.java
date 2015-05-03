package org.brsu.assignments.assignment5.model;

public class AStarNode implements Comparable<AStarNode> {

  private int pathCost;
  private Game state;
  private int estimate;

  public AStarNode(Game state, int pathCost, int estimate) {
    this.state = state;
    this.pathCost = pathCost;
    this.estimate = estimate;
  }

  public int getPathCost() {
    return pathCost;
  }

  public Game getState() {
    return state;
  }

  public int getEstimate() {
    return estimate;
  }

  @Override
  public int compareTo(AStarNode node) {
    return Integer.compare(pathCost + estimate, node.pathCost + node.estimate);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((state == null) ? 0 : state.hashCode());
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
    AStarNode other = (AStarNode) obj;
    if (state == null) {
      if (other.state != null)
        return false;
    } else if (!state.equals(other.state))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "AStarNode [pathCost=" + pathCost + ", state=" + state + ", estimate=" + estimate + "]";
  }

}
