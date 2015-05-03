package org.brsu.assignments.assignment5.model.heuristic;

import java.util.List;

import org.brsu.assignments.assignment5.model.Game;
import org.brsu.assignments.assignment5.model.Tile;
import org.brsu.assignments.model.Position;

public class MisplacedTiles extends Heuristic {

  @Override
  public int evaluate(Game game) {
    int distance = 0;
    List<List<Tile>> board = game.getBoard();
    for (List<Tile> row : board) {
      for (Tile tile : row) {
        if (tile.equals(Tile.TILE_EMPTY)) {
          // Do not count empty tile
          continue;
        }
        Position targetPosition = tile.getTargetPosition();
        Position positionOfTile = game.getPositionOfTile(tile);
        if (!targetPosition.equals(positionOfTile)) {
          distance++;
        }
      }
    }
    return distance;
  }

}
