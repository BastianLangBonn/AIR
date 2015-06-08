package org.brsu.assignments.assignment10.control;

import org.brsu.assignments.assignment10.model.Board;
import org.brsu.assignments.assignment10.model.Stone;

/**
 * Class for evaluating a certain board.
 * 
 * @author bastian
 *
 */
public class BoardEvaluator {

  private Board board;

  public BoardEvaluator(Board board) {
    this.board = board;
  }

  public int evaluate(Stone ownStone, Stone opponentStone) {

    int value = computeVerticalValue(ownStone);
    value -= computeVerticalValue(opponentStone);

    value += computeHorizontalValue(ownStone);
    value -= computeHorizontalValue(opponentStone);

    value += computeDiagonalValue(ownStone);
    value -= computeDiagonalValue(opponentStone);
    return value;
  }

  private int computeDiagonalValue(Stone evaluatedStone) {
    int overallValue = 0;
    // 0,2 -> 1;0,1 -> 2;0,0 -> 3;1,0 -> 3;2,0 ->2;3,0 -> 1;
    overallValue += computeRightDiagonal(0, 2, 1, evaluatedStone);
    overallValue += computeRightDiagonal(0, 1, 2, evaluatedStone);
    overallValue += computeRightDiagonal(0, 0, 3, evaluatedStone);
    overallValue += computeRightDiagonal(1, 0, 3, evaluatedStone);
    overallValue += computeRightDiagonal(2, 0, 2, evaluatedStone);
    overallValue += computeRightDiagonal(3, 0, 1, evaluatedStone);

    // 3,0 -> 1; 4,0 -> 2; 5,0 -> 3; 6,0 -> 3; 6,1 -> 2; 6,2 -> 1
    overallValue += computeLeftDiagonal(3, 0, 1, evaluatedStone);
    overallValue += computeLeftDiagonal(4, 0, 2, evaluatedStone);
    overallValue += computeLeftDiagonal(5, 0, 3, evaluatedStone);
    overallValue += computeLeftDiagonal(6, 0, 3, evaluatedStone);
    overallValue += computeLeftDiagonal(6, 1, 2, evaluatedStone);
    overallValue += computeLeftDiagonal(6, 2, 1, evaluatedStone);

    return overallValue;
  }

  private int computeLeftDiagonal(int column, int row, int numberOfChecks, Stone evaluatedStone) {
    Stone[][] stones = board.getStones();
    int overallValue = 0;
    for (int i = 0; i < numberOfChecks; i++) {
      int ownCount = 0;
      int emptyCount = 0;
      for (int j = 0; j < 4; j++) {
        Stone currentStone = stones[column - i - j][row + i + j];
        if (currentStone.equals(evaluatedStone)) {
          ownCount++;
        } else if (currentStone.equals(Stone.EMPTY)) {
          emptyCount++;
        }
      }
      if (ownCount + emptyCount < 4) {
        // opponent stones in selection
        overallValue += 0;
      } else {
        // compute value depending on own stones in selection
        overallValue += (ownCount + 1) * 10;
      }
    }
    return overallValue;
  }

  private int computeRightDiagonal(int column, int row, int numberOfChecks, Stone evaluatedStone) {
    Stone[][] stones = board.getStones();
    int overallValue = 0;
    for (int i = 0; i < numberOfChecks; i++) {
      int ownCount = 0;
      int emptyCount = 0;
      for (int j = 0; j < 4; j++) {
        Stone currentStone = stones[column + i + j][row + i + j];
        if (currentStone.equals(evaluatedStone)) {
          ownCount++;
        } else if (currentStone.equals(Stone.EMPTY)) {
          emptyCount++;
        }
      }
      if (ownCount + emptyCount < 4) {
        // opponent stones in selection
        overallValue += 0;
      } else {
        // compute value depending on own stones in selection
        overallValue += (ownCount + 1) * 10;
      }
    }
    return overallValue;
  }

  private int computeHorizontalValue(Stone evaluatedStone) {
    Stone[][] stones = board.getStones();
    int overallValue = 0;
    for (int row = 0; row < stones[0].length; row++) {
      int ownCount = 0;
      int emptyCount = 0;
      for (int column = 0; column < 4; column++) {
        Stone currentStone = stones[column][row];
        if (currentStone.equals(evaluatedStone)) {
          ownCount++;
        } else if (currentStone.equals(Stone.EMPTY)) {
          emptyCount++;
        }
      }
      if (ownCount + emptyCount < 4) {
        // opponent stones in selection
        overallValue += 0;
      } else {
        // compute value depending on own stones in selection
        overallValue += (ownCount + 1) * 10;
      }
    }
    return overallValue;
  }

  private int computeVerticalValue(Stone evaluatedStone) {
    Stone[][] stones = board.getStones();
    int overallValue = 0;
    for (int column = 0; column < stones.length; column++) {
      int ownCount = 0;
      int emptyCount = 0;
      for (int row = 0; row < 3; row++) {
        Stone currentStone = stones[column][row];
        if (currentStone.equals(evaluatedStone)) {
          ownCount++;
        } else if (currentStone.equals(Stone.EMPTY)) {
          emptyCount++;
        }
      }
      if (ownCount + emptyCount < 4) {
        // opponent stones in selection
        overallValue += 0;
      } else {
        // compute value depending on own stones in selection
        overallValue += (ownCount + 1) * 10;
      }
    }
    return overallValue;
  }

}
