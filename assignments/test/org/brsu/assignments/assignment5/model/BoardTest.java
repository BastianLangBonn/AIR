package org.brsu.assignments.assignment5.model;

import static org.junit.Assert.assertEquals;

import org.brsu.assignments.model.Position;
import org.junit.Test;

public class BoardTest {

  @Test
  public void getPositionOfTile() {
    Game subject = new Game("1,2,3,4,5,0,6,7,8");
    Position position = subject.getPositionOfTile(Tile.TILE_EMPTY);
    assertEquals("Position of tile is wrong.", new Position(1, 2), position);
  }

}
