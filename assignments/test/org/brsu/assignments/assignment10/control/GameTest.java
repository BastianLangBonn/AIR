package org.brsu.assignments.assignment10.control;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.brsu.assignments.assignment10.model.Stone;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for {@link Game}
 * 
 * @author bastian
 * 
 */
public class GameTest {

  private Game subject;

  @Before
  public void setUp() {
    subject = new Game();
  }

  @Test
  public void addStoneToRow_emptyRow_success() {
    // Act
    boolean success = subject.addStoneToRow(Stone.X, 1);

    // Assert
    assertTrue(success);
  }

  @Test
  public void addStoneToRow_fullRow_failure() {
    // Act
    boolean success = subject.addStoneToRow(Stone.X, 1);

    // Assert
    assertFalse(success);
  }
}
