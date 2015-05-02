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
public class Board {

  List<List<Tile>> board;

  public Board(List<List<Tile>> board) {
    this.board = board;
  }

  public Board(String boardStringRepresentation) {
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
    Board board = new Board("3,1,2,5,4,0,8,6,7");
    System.out.println(board.toString());
  }

}
