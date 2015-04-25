package org.brsu.assignments.model;

public class Position {

  private int row;
  private int column;

  public Position(int row, int column) {
    this.row = row;
    this.column = column;
  }

  public int getRow() {
    return row;
  }

  public int getColumn() {
    return column;
  }

  @Override
  public String toString() {
    return String.format("[%d,%d]", row, column);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Position other = (Position) obj;
    if (row != other.row)
      return false;
    if (column != other.column)
      return false;
    return true;
  }

}
