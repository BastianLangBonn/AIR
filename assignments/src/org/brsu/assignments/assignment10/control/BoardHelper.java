package org.brsu.assignments.assignment10.control;

import org.brsu.assignments.assignment10.model.Board;
import org.brsu.assignments.assignment10.model.Stone;

/**
 * Class containing helper methods for the {@link Board} class
 * 
 * @author bastian
 * 
 */
public class BoardHelper {

  private Board board;

  public BoardHelper(Board board) {
    this.board = board;
  }

  public int getStonesInColumn(int columnIndex) {
    Stone[][] stones = board.getBoard();
    int result = 0;
    for (Stone stone : stones[columnIndex]) {
      if (!stone.equals(Stone.EMPTY)) {
        result++;
      }
    }
    return result;
  }

}
