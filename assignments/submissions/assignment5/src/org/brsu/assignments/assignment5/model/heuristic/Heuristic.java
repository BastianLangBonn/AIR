package org.brsu.assignments.assignment5.model.heuristic;

import org.brsu.assignments.assignment5.model.Game;

public abstract class Heuristic {

  /**
   * Evaluates the given game depending on the particular {@link Heuristic}.
   * 
   * @param game
   * @return
   */
  public abstract int evaluate(Game game);
}
