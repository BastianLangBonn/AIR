package org.brsu.assignments.assignment5.model.heuristic;

import static org.junit.Assert.assertEquals;

import org.brsu.assignments.assignment5.model.Game;
import org.junit.Before;
import org.junit.Test;

public class LinearConflictTest {

  private LinearConflict subject;

  @Before
  public void setUp() {
    subject = new LinearConflict();
  }

  @Test
  public void evaluate_zeroDistanceState_zero() {
    Game game = new Game("1,2,3,4,5,6,7,8,0");
    int estimate = subject.evaluate(game);
    assertEquals("Wrong distance computed.", 0, estimate);
  }

  @Test
  public void evaluate_oneDistanceState_one() {
    Game game = new Game("2,1,3,4,5,6,7,8,0");
    int estimate = subject.evaluate(game);
    assertEquals("Wrong distance computed.", 4, estimate);
  }

  @Test
  public void evaluate_sixDistanceState_six() {
    Game game = new Game("3,1,2,4,5,6,7,8,0");
    int estimate = subject.evaluate(game);
    assertEquals("Wrong distance computed.", 6, estimate);
  }

  @Test
  public void computeValueOfRow_allGoalPositions_zero() {
    Game game = new Game("1,2,3,4,5,6,7,8,0");
    int estimate = subject.computeEstimateForRow(game.getBoard().get(0), 0);
    assertEquals("Estimate for row is wrong.", 0, estimate);
  }

  @Test
  public void computeValueOfRow_noGoalPositionInRow_zero() {
    Game game = new Game("4,5,6,1,2,3,7,8,0");
    int estimate = subject.computeEstimateForRow(game.getBoard().get(0), 0);
    assertEquals("Estimate for row is wrong.", 0, estimate);
  }

  @Test
  public void computeValueOfRow_132_2() {
    Game game = new Game("1,3,2,4,5,6,7,8,0");
    int estimate = subject.computeEstimateForRow(game.getBoard().get(0), 0);
    assertEquals("Estimate for row is wrong.", 2, estimate);
  }

  @Test
  public void computeValueOfRow_213_2() {
    Game game = new Game("2,1,3,4,5,6,7,8,0");
    int estimate = subject.computeEstimateForRow(game.getBoard().get(0), 0);
    assertEquals("Estimate for row is wrong.", 2, estimate);
  }

  @Test
  public void computeValueOfRow_231_2() {
    Game game = new Game("2,3,1,4,5,6,7,8,0");
    int estimate = subject.computeEstimateForRow(game.getBoard().get(0), 0);
    assertEquals("Estimate for row is wrong.", 2, estimate);
  }

  @Test
  public void computeValueOfRow_312_2() {
    Game game = new Game("3,1,2,4,5,6,7,8,0");
    int estimate = subject.computeEstimateForRow(game.getBoard().get(0), 0);
    assertEquals("Estimate for row is wrong.", 2, estimate);
  }

  @Test
  public void computeValueOfRow_321_2() {
    Game game = new Game("3,2,1,4,5,6,7,8,0");
    int estimate = subject.computeEstimateForRow(game.getBoard().get(0), 0);
    assertEquals("Estimate for row is wrong.", 4, estimate);
  }

}
