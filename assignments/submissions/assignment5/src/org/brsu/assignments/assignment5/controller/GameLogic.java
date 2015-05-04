package org.brsu.assignments.assignment5.controller;

import java.util.LinkedList;
import java.util.List;

import org.brsu.assignments.assignment5.model.Game;
import org.brsu.assignments.assignment5.model.Tile;
import org.brsu.assignments.model.Position;

public class GameLogic {

  public List<Action> computePossibleActions(Game board) {
    LinkedList<Action> result = new LinkedList<Action>();
    Position emptyPosition = board.getPositionOfTile(Tile.TILE_EMPTY);
    if (emptyPosition.getColumn() > 0) {
      result.add(Action.LEFT);
    }
    if (emptyPosition.getColumn() < 2) {
      result.add(Action.RIGHT);
    }
    if (emptyPosition.getRow() > 0) {
      result.add(Action.UP);
    }
    if (emptyPosition.getRow() < 2) {
      result.add(Action.DOWN);
    }
    return result;
  }

  public Game computeTransition(Game initialState, Action action) {
    Game game = new Game(initialState);
    Position emptyPosition = game.getPositionOfTile(Tile.TILE_EMPTY);
    Position neighbourPosition;
    if (action == Action.LEFT) {
      neighbourPosition = new Position(emptyPosition.getRow(), emptyPosition.getColumn() - 1);
    } else if (action == Action.RIGHT) {
      neighbourPosition = new Position(emptyPosition.getRow(), emptyPosition.getColumn() + 1);
    } else if (action == Action.UP) {
      neighbourPosition = new Position(emptyPosition.getRow() - 1, emptyPosition.getColumn());
    } else {
      neighbourPosition = new Position(emptyPosition.getRow() + 1, emptyPosition.getColumn());
    }
    List<List<Tile>> board = game.getBoard();
    Tile neighbourTile = board.get(neighbourPosition.getRow()).get(neighbourPosition.getColumn());
    board.get(neighbourPosition.getRow()).set(neighbourPosition.getColumn(), Tile.TILE_EMPTY);
    board.get(emptyPosition.getRow()).set(emptyPosition.getColumn(), neighbourTile);
    return game;
  }

  public boolean performGoalTest(Game game) {
    List<List<Tile>> board = game.getBoard();
    for (List<Tile> row : board) {
      for (Tile tile : row) {
        Position tilePosition = game.getPositionOfTile(tile);
        if (!tilePosition.equals(tile.getTargetPosition())) {
          return false;
        }
      }
    }
    return true;
  }
}
