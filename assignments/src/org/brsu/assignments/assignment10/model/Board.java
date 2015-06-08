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

  public Board(Board board) {
    Stone[][] stones = board.getStones();
    this.board = new Stone[stones.length][stones[0].length];
    for (int i = 0; i < stones.length; i++) {
      for (int j = 0; j < stones[0].length; j++) {
        this.board[i][j] = stones[i][j];
      }
    }
  }

  public Stone[][] getStones() {
    return board;
  }

  public void setStone(int column, int row, Stone stone) {
    board[column][row] = stone;
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Board state:\n");
    for (int i = board[0].length - 1; i >= 0; i--) {
      stringBuilder.append(String.format("%d ", i));
      for (int j = 0; j < board.length; j++) {
        if (board[j][i].equals(Stone.EMPTY)) {
          stringBuilder.append(". ");
        } else {
          stringBuilder.append(board[j][i]);
          stringBuilder.append(" ");
        }
      }
      stringBuilder.append("\n");
    }
    stringBuilder.append("  0 1 2 3 4 5 6");
    return stringBuilder.toString();
  }
}
