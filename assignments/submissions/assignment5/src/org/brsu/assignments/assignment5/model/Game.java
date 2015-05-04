package org.brsu.assignments.assignment5.model;

import java.util.ArrayList;
import java.util.List;

import org.brsu.assignments.model.Position;

/**
 * Class representing the field for the game.
 * 
 * @author bastian
 * 
 */
public class Game {

  List<List<Tile>> board;

  public Game(List<List<Tile>> board) {
    this.board = board;
  }

  public Game(String boardStringRepresentation) {
    String[] tiles = boardStringRepresentation.split(",");
    if (tiles.length != 9) {
      throw new IllegalArgumentException("String has to contain exactly 9 values separated by comma.");
    }

    board = new ArrayList<List<Tile>>(3);
    board.add(new ArrayList<Tile>(3));
    board.add(new ArrayList<Tile>(3));
    board.add(new ArrayList<Tile>(3));

    int row = 0;
    int column = 0;
    for (String tile : tiles) {
      board.get(row).add(Tile.getTile(tile));
      if (column == 2) {
        column = 0;
        row++;
      } else {
        column++;
      }
    }
  }

  public Game(Game initialState) {
    board = new ArrayList<List<Tile>>(3);
    for (List<Tile> row : initialState.getBoard()) {
      board.add(new ArrayList<Tile>(row));
    }
  }

  public List<List<Tile>> getBoard() {
    return board;
  }

  public Position getPositionOfTile(Tile tile) {
    for (int i = 0; i < board.size(); i++) {
      if (board.get(i).contains(tile)) {
        return new Position(i, board.get(i).indexOf(tile));
      }
    }
    throw new IllegalArgumentException("Tile is not in map");
  }

  @Override
  public String toString() {
    String result = "";
    for (List<Tile> row : board) {
      for (Tile tile : row) {
        result += tile.toString() + ", ";
      }
      result += "\n";
    }
    return result;
  }

  public static void main(String[] args) {
    Game board = new Game("3,1,2,5,4,0,8,6,7");
    System.out.println(board.toString());
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((board == null) ? 0 : board.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Game other = (Game) obj;
    if (board == null) {
      if (other.board != null)
        return false;
    } else if (!board.equals(other.board))
      return false;
    return true;
  }

}
