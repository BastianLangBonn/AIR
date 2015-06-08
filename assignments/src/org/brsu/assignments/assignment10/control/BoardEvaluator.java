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
  private BoardValidator validator;

  public BoardEvaluator(Board board) {
    this.board = board;
    validator = new BoardValidator(board);
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
    // TODO Auto-generated method stub
    return 0;
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
