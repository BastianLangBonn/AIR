package org.brsu.assignments.assignment5.model.heuristic;

import java.util.List;

import org.brsu.assignments.assignment5.model.Game;
import org.brsu.assignments.assignment5.model.Tile;

public class LinearConflict extends Heuristic {

  private ManhattenDistance manhattenDistance;

  public LinearConflict() {
    manhattenDistance = new ManhattenDistance();
  }

  @Override
  public int evaluate(Game game) {
    int result = 0;
    for (int i = 0; i < 3; i++) {
      result += computeEstimateForColumn(game.getBoard(), i);
      result += computeEstimateForRow(game.getBoard().get(i), i);
    }
    result += manhattenDistance.evaluate(game);
    return result;
  }

  public int computeEstimateForColumn(List<List<Tile>> board, int column) {
    int[] conflicts = new int[3];
    for (int i = 0; i < board.size(); i++) {
      Tile selectedTile = board.get(i).get(column);
      if (selectedTile.getTargetPosition().getColumn() != column) {
        // Target is not in current column
        conflicts[i] = 0;
        continue;
      }
      if (selectedTile.getTargetPosition().getRow() == i) {
        // Tile is on target position
        conflicts[i] = 0;
        continue;
      }
      int numberOfConflicts = computeNumberOfConflictsForTileInColumn(board, column, i);
      conflicts[i] = numberOfConflicts;
    }
    int result = computeFinalConflictValue(conflicts);
    return result;
  }

  private int computeNumberOfConflictsForTileInColumn(List<List<Tile>> board, int column, int i) {
    int numberOfConflicts = 0;
    for (int j = 0; j < board.size(); j++) {
      if (i == j) {
        // No conflict with self possible
        continue;
      }
      Tile otherTile = board.get(j).get(column);
      if (otherTile.getTargetPosition().getColumn() != column) {
        // Target of this tile is not in this column
        continue;
      }
      if (i < j
          && board.get(i).get(column).getTargetPosition().getColumn() > board.get(j).get(column).getTargetPosition()
              .getColumn()) {
        // tiles have to pass -> conflict
        numberOfConflicts++;
      } else if (i > j
          && board.get(i).get(column).getTargetPosition().getColumn() < board.get(j).get(column).getTargetPosition()
              .getColumn()) {
        // tiles have to pass -> conflict
        numberOfConflicts++;
      }
    }
    return numberOfConflicts;
  }

  public int computeEstimateForRow(List<Tile> tiles, int row) {
    int[] conflicts = new int[3];
    for (int i = 0; i < tiles.size(); i++) {
      Tile selectedTile = tiles.get(i);
      if (selectedTile.getTargetPosition().getRow() != row) {
        // Target is not in current row
        conflicts[i] = 0;
        continue;
      }
      if (selectedTile.getTargetPosition().getColumn() == i) {
        // Tile is on target position
        conflicts[i] = 0;
        continue;
      }
      int numberOfConflicts = computeNumberOfConflictsForTileInRow(tiles, row, i);
      conflicts[i] = numberOfConflicts;
    }
    return computeFinalConflictValue(conflicts);
  }

  /**
   * This function takes the array of conflicts per tile and computes the
   * resulting estimate for the entire row/column. Checking all possibilities
   * for a single row/line there are only three possible results: 0,2 or 4. 0
   * results only if there is no conflict at all. 4 results only if 2 tiles have
   * 2 conflicts and one tile is at its target position and has no conflict.
   * 
   * @param conflicts
   * @return
   */
  private int computeFinalConflictValue(int[] conflicts) {
    int result;
    int sumOfConflicts = 0;
    boolean zeroConflictPresent = false;
    for (int i = 0; i < conflicts.length; i++) {
      sumOfConflicts += conflicts[i];
      if (conflicts[i] == 0) {
        zeroConflictPresent = true;
      }
    }
    if (sumOfConflicts == 0) {
      result = 0;
    } else if (sumOfConflicts == 4 && zeroConflictPresent) {
      result = 4;
    } else {
      result = 2;
    }
    return result;
  }

  private int computeNumberOfConflictsForTileInRow(List<Tile> tiles, int row, int i) {
    int numberOfConflicts = 0;
    for (int j = 0; j < tiles.size(); j++) {
      if (j == i) {
        // No conflict with self possible
        continue;
      }
      Tile otherTile = tiles.get(j);
      if (otherTile.getTargetPosition().getRow() != row) {
        // Target if this tile is not in this row
        continue;
      }
      if (i < j && tiles.get(i).getTargetPosition().getColumn() > tiles.get(j).getTargetPosition().getColumn()) {
        // tiles have to pass -> conflict
        numberOfConflicts++;
      } else if (i > j && tiles.get(i).getTargetPosition().getColumn() < tiles.get(j).getTargetPosition().getColumn()) {
        // tiles have to pass -> conflict
        numberOfConflicts++;
      }
    }
    return numberOfConflicts;
  }
}
