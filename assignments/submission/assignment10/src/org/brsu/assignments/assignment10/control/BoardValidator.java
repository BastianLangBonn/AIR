package org.brsu.assignments.assignment10.control;

import org.brsu.assignments.assignment10.model.Board;
import org.brsu.assignments.assignment10.model.State;
import org.brsu.assignments.assignment10.model.Stone;

/**
 * Class containing helper methods for the {@link Board} class
 * 
 * @author bastian
 * 
 */
public class BoardValidator {

  private Board board;

  public BoardValidator(Board board) {
    this.board = board;
  }

  public int getStonesInColumn(int columnIndex) {
    Stone[][] stones = board.getStones();
    int result = 0;
    for (Stone stone : stones[columnIndex]) {
      if (!stone.equals(Stone.EMPTY)) {
        result++;
      }
    }
    return result;
  }

  private boolean isFull() {
    for (int i = 0; i < board.getStones().length; i++) {
      if (getStonesInColumn(i) < 6) {
        return false;
      }
    }
    return true;
  }

  public State computeState() {
    if (isFull()) {
      return State.DRAW;
    }
    Stone[][] stones = board.getStones();
    int count = 0;
    Stone currentStone = Stone.EMPTY;
    for (Stone[] column : stones) {
      count = 0;
      for (Stone stone : column) {
        // Reset counter if empty field
        if (stone.equals(Stone.EMPTY)) {
          count = 0;
          break;
        }
        // Increase counter if same stone as before
        if (currentStone.equals(stone)) {
          count++;
          if (count == 4) {
            if (currentStone.equals(Stone.O)) {
              return State.O_WON;
            } else {
              return State.X_WON;
            }
          }
          // Set counter to one if first time appearance of stone
        } else {
          currentStone = stone;
          count = 1;
        }
      }
    }

    // check rows
    count = 0;
    currentStone = Stone.EMPTY;
    for (int i = 0; i < stones[0].length; i++) {
      count = 0;
      for (int j = 0; j < stones.length; j++) {
        Stone stone = stones[j][i];
        if (stone.equals(Stone.EMPTY)) {
          count = 0;
        }
        if (stone.equals(currentStone)) {
          count++;
          if (count == 4) {
            if (currentStone.equals(Stone.O)) {
              return State.O_WON;
            } else {
              return State.X_WON;
            }
          }
        } else {
          currentStone = stone;
          count = 1;
        }
      }
    }

    // check right diagonals
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 3; j++) {
        if (!stones[i][j].equals(Stone.EMPTY)) {
          boolean won = checkRightDiagonalStartingWith(i, j);
          if (won) {
            if (stones[i][j].equals(Stone.O)) {
              return State.O_WON;
            } else {
              return State.X_WON;
            }
          }
        }
      }
    }
    // check left diagonals
    for (int i = 3; i < stones.length; i++) {
      for (int j = 0; j < 3; j++) {
        if (!stones[i][j].equals(Stone.EMPTY)) {
          boolean won = checkLeftDiagonalStartingWith(i, j);
          if (won) {
            if (stones[i][j].equals(Stone.O)) {
              return State.O_WON;
            } else {
              return State.X_WON;
            }
          }
        }
      }
    }

    return State.RUNNING;
  }

  private boolean checkLeftDiagonalStartingWith(int column, int row) {
    Stone[][] stones = board.getStones();
    Stone previousStone = stones[column][row];
    for (int i = 1; i < 4; i++) {
      if (!previousStone.equals(stones[column - i][row + i])) {
        return false;
      }
    }
    return true;
  }

  private boolean checkRightDiagonalStartingWith(int column, int row) {
    Stone[][] stones = board.getStones();
    Stone previousStone = stones[column][row];
    for (int i = 1; i < 4; i++) {
      if (!previousStone.equals(stones[column + i][row + i])) {
        return false;
      }
    }
    return true;
  }
}
