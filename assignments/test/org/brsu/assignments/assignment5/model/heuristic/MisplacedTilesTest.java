package org.brsu.assignments.assignment5.model.heuristic;

import static org.junit.Assert.assertEquals;

import org.brsu.assignments.assignment5.model.Game;
import org.junit.Test;

public class MisplacedTilesTest {

  @Test
  public void evaluate() {
    MisplacedTiles subject = new MisplacedTiles();
    Game game = new Game("1,3,2,4,5,6,7,8,0");
    int distance = subject.evaluate(game);
    int expectedDistance = 2;
    assertEquals("Distance is wrong.", expectedDistance, distance);
  }

}
