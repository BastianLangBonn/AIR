package org.brsu.assignments.assignment10.control;

import static org.junit.Assert.assertEquals;

import org.brsu.assignments.assignment10.model.Board;
import org.brsu.assignments.assignment10.model.Stone;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for {@link BoardHelper}
 * 
 * @author bastian
 * 
 */
public class BoardHelperTest {

  private BoardHelper subject;
  private Board board;

  @Before
  public void setUp() {
    board = new Board();
    subject = new BoardHelper(board);
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
}
