package org.brsu.assignments.assignment5.model.heuristic;

import java.util.List;

import org.brsu.assignments.assignment5.model.Game;
import org.brsu.assignments.assignment5.model.Tile;
import org.brsu.assignments.model.Position;

public class ManhattenDistance extends Heuristic {

  @Override
  public int evaluate(Game game) {
    List<List<Tile>> board = game.getBoard();
    int result = 0;
    for (List<Tile> row : board) {
      for (Tile tile : row) {
        result += computeDistanceToTargetPosition(game, tile);
      }
    }
    return result;
  }

  private int computeDistanceToTargetPosition(Game game, Tile tile) {
    Position positionOfTile = game.getPositionOfTile(tile);
    Position targetPosition = tile.getTargetPosition();
    return Math.abs(targetPosition.getRow() - positionOfTile.getRow())
        + Math.abs(targetPosition.getColumn() - positionOfTile.getColumn());
  }

}
