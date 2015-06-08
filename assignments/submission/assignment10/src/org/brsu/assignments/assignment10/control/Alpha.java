package org.brsu.assignments.assignment10.control;

import org.brsu.assignments.assignment10.model.Board;
import org.brsu.assignments.assignment10.model.Stone;

public class Alpha extends Player {

  private String name;

  public Alpha(Stone playersStone, String name) {
    this.name = name;
    this.playersStone = playersStone;
  }

  @Override
  public int computeRowForNextMove(Board board) {
    System.out.print("Alpha is thinking...");
    int index = 0;
    for (int i = 0; i < 7; i++) {
      BoardValidator validator = new BoardValidator(new Board(board));
      int stonesInColumn = validator.getStonesInColumn(i);
      if (stonesInColumn < 6) {
        index = i;
        break;
      }
    }
    System.out.print("done\n");
    System.out.println("playing row " + index);
    return index;
  }

  @Override
  public String toString() {
    return name;
  }

}
