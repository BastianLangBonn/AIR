package org.brsu.assignments.assignment5.controller;

import java.util.LinkedList;
import java.util.List;

import org.brsu.assignments.assignment5.model.Board;
import org.brsu.assignments.assignment5.model.Tile;
import org.brsu.assignments.model.Position;

public class GameLogic {

  public List<Action> computePossibleActions(Board board) {
    LinkedList<Action> result = new LinkedList<Action>();
    Position emptyPosition = board.getPositionOfTile(Tile.TILE_EMPTY);
    if (emptyPosition.getColumn() > 0) {
      result.add(Action.LEFT);
    }
    if (emptyPosition.getColumn() < 2) {
      result.add(Action.RIGHT);
    }
    if (emptyPosition.getRow() > 0) {
      result.add(Action.UP);
    }
    if (emptyPosition.getRow() < 2) {
      result.add(Action.DOWN);
    }
    return result;
  }
}
