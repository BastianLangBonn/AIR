package org.brsu.assignments.assignment10.control;

import org.brsu.assignments.assignment10.model.Board;
import org.brsu.assignments.assignment10.model.Stone;

/**
 * Class to control a game of connect4.
 * 
 * @author bastian
 * 
 */
public class Game {

  private Board board;

  public Game() {
    board = new Board();
  }

  public boolean addStoneToRow(Stone stone, int rowIndex) {

    return true;
  }

  /**
   * Prints the current board set up to the console
   */
  public void printGameState() {

    System.out.println(board.toString());
  }

  public static void main(String[] args) {
    new Game().printGameState();
  }
}
