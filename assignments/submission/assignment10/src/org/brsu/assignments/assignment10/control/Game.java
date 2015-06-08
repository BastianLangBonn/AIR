package org.brsu.assignments.assignment10.control;

import java.util.Scanner;

import org.brsu.assignments.assignment10.model.Board;
import org.brsu.assignments.assignment10.model.State;
import org.brsu.assignments.assignment10.model.Stone;

/**
 * Class to control a game of connect4.
 * 
 * @author bastian
 * 
 */
public class Game {

  private static final int HUMAN = 0;
  private static final int MIN_MAX = 1;
  private static final int MIN_MAX_ALPHA = 2;
  private static final int BOARD_HEIGHT = 6;
  private static final int BOARD_WIDTH = 7;
  private Board board;
  private BoardValidator boardValidator;
  private Player player1;
  private Player player2;
  private Scanner scanner;

  public Game() {
    board = new Board();
    boardValidator = new BoardValidator(board);
  }

  public boolean addStoneToRow(Stone stone, int rowIndex) {
    if (rowIndex < 0 || rowIndex >= BOARD_WIDTH) {
      throw new IllegalArgumentException("Index out of bounds.");
    }
    int stonesInColumn = boardValidator.getStonesInColumn(rowIndex);
    if (stonesInColumn < BOARD_HEIGHT) {
      board.setStone(rowIndex, stonesInColumn, stone);
      return true;
    }
    return false;
  }

  public void start() {
    int selection = getPlayerSelection("player 1");
    player1 = createPlayerForSelection(selection, Stone.X, "player 1");
    selection = getPlayerSelection("player 2");
    player2 = createPlayerForSelection(selection, Stone.O, "player 2");
    Player currentPlayer = player1;
    State gameState = boardValidator.computeState();
    while (gameState.equals(State.RUNNING)) {
      // Select stone
      int selectedRow = -1;
      while (!isLegalRow(selectedRow)) {
        printGameState();
        // copy board to prevent overwriting
        Board copy = new Board(board);
        selectedRow = currentPlayer.computeRowForNextMove(copy);
      }
      addStoneToRow(currentPlayer.getStone(), selectedRow);
      System.out.println(String.format("%s set its stone into column %d.", currentPlayer.toString(), selectedRow));
      gameState = boardValidator.computeState();
      if (currentPlayer.equals(player1)) {
        currentPlayer = player2;
      } else {
        currentPlayer = player1;
      }
    }
    printGameState();

  }

  private boolean isLegalRow(int selectedRow) {
    if (selectedRow < 0 || selectedRow > 6) {
      return false;
    }
    if (boardValidator.getStonesInColumn(selectedRow) == 6) {
      return false;
    }
    return true;
  }

  private Player createPlayerForSelection(int selection, Stone playersStone, String name) {
    Player player = null;
    if (selection == HUMAN) {
      player = new Human(scanner, playersStone, name);
    } else if (selection == MIN_MAX) {
      player = new MinMax(playersStone, name);
    } else if (selection == MIN_MAX_ALPHA) {
      player = new Alpha(playersStone, name);
    }
    return player;
  }

  private int getPlayerSelection(String playerLabel) {
    scanner = new Scanner(System.in);
    int selection = -1;
    while (selection < 0 || selection > 2) {
      System.out.println(String.format("Please choose type of %s.", playerLabel));
      System.out.println(String.format("%d: Human\n%d: MinMax\n%d: MinMax with a/b pruning", HUMAN, MIN_MAX,
          MIN_MAX_ALPHA));
      try {
        selection = scanner.nextInt();
      } catch (Exception e) {
        System.err.println(e.getMessage());
        System.out.println("Illegal input.");
        scanner.nextLine();
        selection = -1;
      } finally {
        scanner.nextLine();
      }
    }
    System.out.println(String.format("Selected: %d", selection));
    scanner.reset();
    return selection;
  }

  /**
   * Prints the current board set up to the console
   */
  public void printGameState() {
    System.out.println(board.toString());
    System.out.println(boardValidator.computeState());
  }

  public static void main(String[] args) {
    Game game = new Game();
    game.start();
  }
}
