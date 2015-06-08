package org.brsu.assignments.assignment10.control;

import static org.junit.Assert.assertEquals;

import org.brsu.assignments.assignment10.model.Board;
import org.brsu.assignments.assignment10.model.Stone;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for BoardEstimator
 * 
 * @author bastian
 *
 */
public class boardEvaluatorTest {
  private Board board;
  private BoardEvaluator subject;

  @Before
  public void setUp() {
    board = new Board();
    subject = new BoardEvaluator(board);
  }

  // @Test
  // public void evaluate_openEnded3DiagonalRight_90() {
  // // Arrange
  // board.setStone(1, 0, Stone.O);
  // board.setStone(1, 1, Stone.X);
  // board.setStone(1, 2, Stone.O);
  // board.setStone(2, 0, Stone.X);
  // board.setStone(2, 1, Stone.O);
  // board.setStone(2, 2, Stone.X);
  // board.setStone(2, 3, Stone.O);
  // board.setStone(3, 0, Stone.X);
  // board.setStone(3, 1, Stone.O);
  // board.setStone(3, 2, Stone.X);
  // board.setStone(3, 3, Stone.X);
  // board.setStone(3, 4, Stone.X);
  // board.setStone(3, 5, Stone.O);
  // board.setStone(4, 0, Stone.O);
  // board.setStone(4, 1, Stone.X);
  // board.setStone(4, 2, Stone.O);
  // board.setStone(4, 3, Stone.X);
  // System.out.println(board.toString());
  //
  // // Act
  // int xValue = subject.evaluate(Stone.X);
  // int oValue = subject.evaluate(Stone.O);
  //
  // // Assert
  // assertEquals("Wrong evaluation result for X.", 90, xValue);
  // assertEquals("Wrong evaluation result for O.", -90, oValue);
  // }
  //
  // @Test
  // public void evaluate_openEnded3DiagonalLeft_90() {
  // // Arrange
  // board.setStone(4, 0, Stone.O);
  // board.setStone(4, 1, Stone.X);
  // board.setStone(4, 2, Stone.O);
  // board.setStone(3, 0, Stone.X);
  // board.setStone(3, 1, Stone.O);
  // board.setStone(3, 2, Stone.X);
  // board.setStone(3, 3, Stone.O);
  // board.setStone(2, 0, Stone.X);
  // board.setStone(2, 1, Stone.O);
  // board.setStone(2, 2, Stone.X);
  // board.setStone(2, 3, Stone.X);
  // board.setStone(2, 4, Stone.X);
  // board.setStone(2, 5, Stone.O);
  // board.setStone(1, 0, Stone.O);
  // board.setStone(1, 1, Stone.X);
  // board.setStone(1, 2, Stone.O);
  // board.setStone(1, 3, Stone.X);
  // System.out.println(board.toString());
  //
  // // Act
  // int xValue = subject.evaluate(Stone.X);
  // int oValue = subject.evaluate(Stone.O);
  //
  // // Assert
  // assertEquals("Wrong evaluation result for X.", 90, xValue);
  // assertEquals("Wrong evaluation result for O.", -90, oValue);
  // }
  //
  // @Test
  // public void evaluate_openEndedThreeRow_90() {
  // // Arrange
  // board.setStone(1, 0, Stone.X);
  // board.setStone(2, 0, Stone.O);
  // board.setStone(2, 1, Stone.X);
  // board.setStone(3, 0, Stone.X);
  // board.setStone(3, 1, Stone.X);
  // board.setStone(3, 2, Stone.O);
  // board.setStone(4, 0, Stone.O);
  // board.setStone(4, 1, Stone.X);
  // board.setStone(5, 0, Stone.X);
  // System.out.println(board.toString());
  //
  // // Act
  // int xValue = subject.evaluate(Stone.X);
  // int oValue = subject.evaluate(Stone.O);
  //
  // // Assert
  // assertEquals("Wrong evaluation result for X.", 90, xValue);
  // assertEquals("Wrong evaluation result for O.", -90, oValue);
  // }
  //
  // @Test
  // public void evaluate_openEndedToRow_60() {
  // // Arrange
  // board.setStone(0, 0, Stone.O);
  // board.setStone(3, 0, Stone.X);
  // board.setStone(4, 0, Stone.X);
  // board.setStone(6, 0, Stone.O);
  // System.out.println(board.toString());
  //
  // // Act
  // int xValue = subject.evaluate(Stone.X);
  // int oValue = subject.evaluate(Stone.O);
  //
  // // Assert
  // assertEquals("Wrong evaluation result for X.", 60, xValue);
  // assertEquals("Wrong evaluation result for Y.", -60, oValue);
  // }

  @Test
  public void evaluate_empty_0() {
    // Arrange
    System.out.println(board.toString());

    // Act
    int xValue = subject.evaluate(Stone.X, Stone.O);
    int oValue = subject.evaluate(Stone.O, Stone.X);

    // Assert
    assertEquals("Wrong evaluation result for X.", 0, xValue);
    assertEquals("Wrong evaluation result for Y.", 0, oValue);
  }

  @Test
  public void evaluate_oneVerticalX_10() {
    // Arrange
    board.setStone(0, 0, Stone.X);
    System.out.println(board.toString());

    // Act
    int xValue = subject.evaluate(Stone.X, Stone.O);
    int oValue = subject.evaluate(Stone.O, Stone.X);

    // Assert
    assertEquals("Wrong evaluation result for X.", 10, xValue);
    assertEquals("Wrong evaluation result for Y.", -10, oValue);
  }

  @Test
  public void evaluate_twoVerticalX_100() {
    // Arrange
    board.setStone(0, 0, Stone.X);
    board.setStone(0, 1, Stone.X);
    System.out.println(board.toString());

    // Act
    int xValue = subject.evaluate(Stone.X, Stone.O);
    int oValue = subject.evaluate(Stone.O, Stone.X);

    // Assert
    assertEquals("Wrong evaluation result for X.", 100, xValue);
    assertEquals("Wrong evaluation result for Y.", -100, oValue);
  }

  @Test
  public void evaluate_threeVerticalX_1000() {
    // Arrange
    board.setStone(0, 0, Stone.X);
    board.setStone(0, 1, Stone.X);
    board.setStone(0, 2, Stone.X);
    System.out.println(board.toString());

    // Act
    int xValue = subject.evaluate(Stone.X, Stone.O);
    int oValue = subject.evaluate(Stone.O, Stone.X);

    // Assert
    assertEquals("Wrong evaluation result for X.", 1000, xValue);
    assertEquals("Wrong evaluation result for Y.", -1000, oValue);
  }

  @Test
  public void evalueate_oneHorizontalXThreeOneSide_10() {
    // Arrange
    board.setStone(0, 0, Stone.X);
    System.out.println(board.toString());

    // Act
    int xValue = subject.evaluate(Stone.X, Stone.O);
    int oValue = subject.evaluate(Stone.O, Stone.X);

    // Assert
    assertEquals("Wrong evaluation result for X.", 20, xValue);
    assertEquals("Wrong evaluation result for Y.", -20, oValue);
  }

}
