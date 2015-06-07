package org.brsu.assignments.assignment10.control;

import static org.junit.Assert.assertEquals;

import org.brsu.assignments.assignment10.model.Board;
import org.brsu.assignments.assignment10.model.State;
import org.brsu.assignments.assignment10.model.Stone;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for {@link BoardValidator}
 * 
 * @author bastian
 * 
 */
public class BoardValidatorTest {

  private BoardValidator subject;
  private Board board;

  @Before
  public void setUp() {
    board = new Board();
    subject = new BoardValidator(board);
  }

  @Test
  public void getStonesInColumn_allEmpty_0() {
    // Act
    int numberOfStones = subject.getStonesInColumn(0);

    // Assert
    assertEquals("Wrong number of stones in row", 0, numberOfStones);
  }

  @Test
  public void getStonesInColumn_threeStonesInColumn_3() {
    // Arrange
    board.setStone(0, 0, Stone.O);
    board.setStone(0, 1, Stone.O);
    board.setStone(0, 2, Stone.O);
    // Act
    int numberOfStones = subject.getStonesInColumn(0);

    // Assert
    assertEquals("Wrong number of stones in row", 3, numberOfStones);
  }

  @Test
  public void computeState_fullBoard_draw() {
    // Arrange
    for (int i = 0; i < board.getStones().length; i++) {
      for (int j = 0; j < board.getStones()[0].length; j++) {
        board.setStone(i, j, Stone.X);
      }
    }

    // Act
    State state = subject.computeState();

    // Assert
    assertEquals("Board is full.", State.DRAW, state);
  }

  @Test
  public void computeState_noFour_false() {
    // Arrange
    board.setStone(0, 0, Stone.X);
    board.setStone(1, 1, Stone.O);
    board.setStone(2, 2, Stone.X);
    board.setStone(3, 3, Stone.O);

    // Act
    State state = subject.computeState();

    // Assert
    assertEquals("Wrong state.", State.RUNNING, state);
  }

  @Test
  public void computeState_fourInARightDiagonal_XWon() {
    // Arrange
    board.setStone(0, 0, Stone.X);
    board.setStone(1, 1, Stone.X);
    board.setStone(2, 2, Stone.X);
    board.setStone(3, 3, Stone.X);

    // Act
    State state = subject.computeState();

    // Assert
    assertEquals("Wrong state.", State.X_WON, state);
  }

  @Test
  public void computeState_fourInALeftDiagonal_OWon() {
    // Arrange
    board.setStone(0, 3, Stone.O);
    board.setStone(1, 2, Stone.O);
    board.setStone(2, 1, Stone.O);
    board.setStone(3, 0, Stone.O);

    // Act
    State state = subject.computeState();

    // Assert
    assertEquals("Wrong state.", State.O_WON, state);
  }

  @Test
  public void computeState_fourInARow_OWon() {
    // Arrange
    board.setStone(0, 0, Stone.O);
    board.setStone(1, 0, Stone.O);
    board.setStone(2, 0, Stone.O);
    board.setStone(3, 0, Stone.O);

    // Act
    State state = subject.computeState();

    // Assert
    assertEquals("Wrong state.", State.O_WON, state);
  }

  @Test
  public void computeState_fourInAColumn_XWon() {
    // Arrange
    board.setStone(0, 0, Stone.X);
    board.setStone(0, 1, Stone.X);
    board.setStone(0, 2, Stone.X);
    board.setStone(0, 3, Stone.X);

    // Act
    State state = subject.computeState();

    // Assert
    assertEquals("Wrong state.", State.X_WON, state);
  }

  @Test
  public void computeState_bug1_Running() {
    // Arrange
    board.setStone(0, 0, Stone.X);
    board.setStone(1, 0, Stone.X);
    board.setStone(2, 0, Stone.X);
    board.setStone(0, 1, Stone.O);
    board.setStone(1, 1, Stone.O);
    board.setStone(1, 2, Stone.O);
    System.out.println(board);

    // Act
    State state = subject.computeState();

    // Assert
    assertEquals("Wrong state.", State.RUNNING, state);
  }

  @Test
  public void computeState_emptyBoard_false() {
    // Act
    State state = subject.computeState();

    // Assert
    assertEquals("Wrong state.", State.RUNNING, state);
  }
}
