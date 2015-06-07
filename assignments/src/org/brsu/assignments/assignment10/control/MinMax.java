package org.brsu.assignments.assignment10.control;

import org.brsu.assignments.assignment10.model.Board;
import org.brsu.assignments.assignment10.model.State;
import org.brsu.assignments.assignment10.model.Stone;

public class MinMax extends Player {

  private String name;

  public MinMax(Stone stone, String name) {
    this.playersStone = stone;
    this.name = name;
  }

  @Override
  public int computeRowForNextMove(Board board) {
    System.out.print("MinMax is thinking...");
    int maxValue = -2;
    int index = 0;
    for (int i = 0; i < 7; i++) {
      Board possibleBoard = new Board(board);
      BoardValidator validator = new BoardValidator(board);
      int stonesInColumn = validator.getStonesInColumn(i);
      if (stonesInColumn < 6) {
        possibleBoard.setStone(i, stonesInColumn, playersStone);
        int value = minMax(possibleBoard, false, 0);
        if (value > maxValue) {
          maxValue = value;
          index = i;
        }
      }
    }
    System.out.print("done\n");
    return index;
  }

  private int minMax(Board board, boolean isMax, int depth) {
    System.out.print(".");
    BoardValidator validator = new BoardValidator(board);
    State computeState = validator.computeState();
    // Check terminal states
    if (computeState.equals(State.X_WON)) {
      if (playersStone.equals(Stone.X)) {
        return 1;
      } else {
        return -1;
      }
    } else if (computeState.equals(State.O_WON)) {
      if (playersStone.equals(State.O_WON)) {
        return 1;
      } else {
        return -1;
      }
    } else if (computeState.equals(State.DRAW)) {
      return 0;
    }
    if (depth == 3) {
      return 0;
    }
    // Compute next action according to turn
    int max = -2;
    int min = 2;
    for (int i = 0; i < 7; i++) {
      Board possibleBoard = new Board(board);
      int stonesInColumn = validator.getStonesInColumn(i);
      if (stonesInColumn < 6) {
        possibleBoard.setStone(i, stonesInColumn, playersStone);
        int value = minMax(possibleBoard, !isMax, depth + 1);
        if (value > max) {
          max = value;
        }
        if (value < min) {
          min = value;
        }
      }
    }
    return isMax ? max : min;
  }

  @Override
  public String toString() {
    return name;
  }
}
