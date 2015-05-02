package org.brsu.assignments.assignment5.model;

public abstract class Heuristic {

  /**
   * Evaluates the given game depending on the particular {@link Heuristic}.
   * 
   * @param game
   * @return
   */
  public abstract int evaluate(Game game);
}
