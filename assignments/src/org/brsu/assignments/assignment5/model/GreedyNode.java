package org.brsu.assignments.assignment5.model;

public class GreedyNode implements Comparable<GreedyNode> {

  private int estimate;
  private Game state;

  public GreedyNode(Game state, int estimate) {
    this.state = state;
    this.estimate = estimate;
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
    GreedyNode other = (GreedyNode) obj;
    if (state == null) {
      if (other.state != null)
        return false;
    } else if (!state.equals(other.state))
      return false;
    return true;
  }

  @Override
  public int compareTo(GreedyNode node) {
    return Integer.compare(estimate, node.estimate);
  }

  public int getEstimate() {
    return estimate;
  }

  public Game getState() {
    return state;
  }

}
