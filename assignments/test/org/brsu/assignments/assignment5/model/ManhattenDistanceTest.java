package org.brsu.assignments.assignment5.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ManhattenDistanceTest {

  @Test
  public void evaluate() {
    ManhattenDistance subject = new ManhattenDistance();
    Game game = new Game("0,1,2,3,4,5,6,7,8");
    int expectedDistance = 16;
    int result = subject.evaluate(game);
    assertEquals("Distance is wrong.", expectedDistance, result);
  }

}
