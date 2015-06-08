package org.brsu.assignments.assignment10.control;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.brsu.assignments.assignment10.model.Stone;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Unit test for {@link Game}
 * 
 * @author bastian
 * 
 */
public class GameTest {

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  private Game subject;

  @Before
  public void setUp() {
    subject = new Game();
  }

  @Test
  public void addStoneToRow_indexTooHigh_exception() {
    // Arrange
    expectedException.expect(IllegalArgumentException.class);
    expectedException.expectMessage("Index");

    // Act
    subject.addStoneToRow(Stone.X, 7);
  }

  @Test
  public void addStoneToRow_negativeIndex_exception() {
    // Arrange
    expectedException.expect(IllegalArgumentException.class);
    expectedException.expectMessage("Index");

    // Act
    subject.addStoneToRow(Stone.X, -1);
  }

  @Test
  public void addStoneToRow_emptyRow_success() {
    // Act
    boolean success = subject.addStoneToRow(Stone.X, 1);

    // Assert
    assertTrue("Adding a stone to an empty column should not give any problems.", success);
  }

  @Test
  public void addStoneToRow_fullRow_failure() {
    // Arrange
    for (int i = 0; i < 6; i++) {
      subject.addStoneToRow(Stone.O, 1);
    }

    // Act
    boolean success = subject.addStoneToRow(Stone.X, 1);

    // Assert
    assertFalse("Adding another stone should not be possible in this column.", success);
  }
}
