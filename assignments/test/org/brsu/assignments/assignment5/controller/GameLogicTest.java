package org.brsu.assignments.assignment5.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.brsu.assignments.assignment5.model.Game;
import org.junit.Before;
import org.junit.Test;

public class GameLogicTest {

  private GameLogic subject;

  @Before
  public void setUp() {
    subject = new GameLogic();
  }

  @Test
  public void computeTransition_moveRight() {
    Game initialState = new Game("1,2,3,0,4,5,6,7,8");
    Action action = Action.RIGHT;
    Game expectedState = new Game("1,2,3,4,0,5,6,7,8");
    Game resultingState = subject.computeTransition(initialState, action);

    assertEquals("Resulting state is wrong.", expectedState, resultingState);
  }

  @Test
  public void computePossibleActions_topLeftCorner_rightDown() {
    Game board = new Game("0,2,1,3,4,5,6,7,8");
    List<Action> possibleActions = subject.computePossibleActions(board);
    assertEquals("Wrong number of possible actions.", 2, possibleActions.size());
    assertTrue(String.format("State %s is missing.", Action.RIGHT), possibleActions.contains(Action.RIGHT));
    assertTrue(String.format("State %s is missing.", Action.DOWN), possibleActions.contains(Action.DOWN));
  }

  @Test
  public void computePossibleActions_center_rightDownLeftUp() {
    Game board = new Game("2,1,3,4,0,5,6,7,8");
    List<Action> possibleActions = subject.computePossibleActions(board);
    assertEquals("Wrong number of possible actions.", 4, possibleActions.size());
    assertTrue(String.format("State %s is missing.", Action.RIGHT), possibleActions.contains(Action.RIGHT));
    assertTrue(String.format("State %s is missing.", Action.DOWN), possibleActions.contains(Action.DOWN));
    assertTrue(String.format("State %s is missing.", Action.LEFT), possibleActions.contains(Action.LEFT));
    assertTrue(String.format("State %s is missing.", Action.UP), possibleActions.contains(Action.LEFT));
  }
}
