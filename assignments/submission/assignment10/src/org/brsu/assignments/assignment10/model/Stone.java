package org.brsu.assignments.assignment10.model;

public enum Stone {
  X, EMPTY, O;

  public String toString() {
    if (this.equals(X)) {
      return "X";
    } else if (this.equals(O)) {
      return "O";
    } else {
      return " ";
    }
  }
}
