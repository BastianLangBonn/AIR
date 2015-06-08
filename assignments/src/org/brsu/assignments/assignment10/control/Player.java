package org.brsu.assignments.assignment10.control;

import org.brsu.assignments.assignment10.model.Board;
import org.brsu.assignments.assignment10.model.Stone;

public abstract class Player {

  Stone playersStone;

  public abstract int computeRowForNextMove(Board board);

  public Stone getStone() {
    return playersStone;
  }
}
