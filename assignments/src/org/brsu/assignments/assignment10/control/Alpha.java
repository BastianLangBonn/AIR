package org.brsu.assignments.assignment10.control;

import org.brsu.assignments.assignment10.model.Board;
import org.brsu.assignments.assignment10.model.State;
import org.brsu.assignments.assignment10.model.Stone;

public class Alpha extends Player {

  private static final int WINNING_VALUE = 10000;
  private static final int MAX_DEPTH_LEVEL = 4;
  private Stone playersStone;
  private String name;
  private Stone opponentStone;

  public Alpha(Stone playersStone, String name) {
    this.name = name;
    this.playersStone = playersStone;
    opponentStone = null;
    if (playersStone.equals(Stone.X)) {
      opponentStone = Stone.O;
    } else {
      opponentStone = Stone.X;
    }
  }

  @Override
  public int computeRowForNextMove(Board board) {
    System.out.print("Alpha is thinking...");
    int maxValue = -20000;
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
    System.out.println("playing row " + index);
    return index;
  }

  private int minMax(Board board, boolean isMax, int depth) {
    System.out.print(".");
    BoardValidator validator = new BoardValidator(board);
    State computeState = validator.computeState();
    // Check terminal states
    if (computeState.equals(State.X_WON)) {
      if (playersStone.equals(Stone.X)) {
        return WINNING_VALUE;
      } else {
        return -WINNING_VALUE;
      }
    } else if (computeState.equals(State.O_WON)) {
      if (playersStone.equals(State.O_WON)) {
        return WINNING_VALUE;
      } else {
        return -WINNING_VALUE;
      }
    } else if (computeState.equals(State.DRAW)) {
      return 0;
    }

    // Check depth limit
    BoardEvaluator evaluator = new BoardEvaluator(board);
    if (depth == MAX_DEPTH_LEVEL) {
      return evaluator.evaluate(playersStone, opponentStone);
    }
    // Compute next action according to turn
    int max = -20000;
    int min = 20000;
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
