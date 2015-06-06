package org.brsu.assignments.assignment10.model;


/**
 * Class representing the board of the connect 4 game.
 * 
 * @author bastian
 * 
 */
public class Board {
  private Stone[][] board;

  public Board() {
    board = new Stone[7][6];
    for (int column = 0; column < 7; column++) {
      for (int row = 0; row < 6; row++) {
        board[column][row] = Stone.EMPTY;
      }
    }
  }

  public Stone[][] getBoard() {
    return board;
  }

  public void setStone(int column, int row, Stone stone) {
    board[column][row] = stone;
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Board state:\n");
    for (Stone[] column : board) {
      for (Stone stone : column) {
        stringBuilder.append(stone);
        stringBuilder.append(",");
      }
      stringBuilder.append("\n");
    }
    return stringBuilder.toString();
  }

}
