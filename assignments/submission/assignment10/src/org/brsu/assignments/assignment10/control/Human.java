package org.brsu.assignments.assignment10.control;

import java.util.Scanner;

import org.brsu.assignments.assignment10.model.Board;
import org.brsu.assignments.assignment10.model.Stone;

public class Human extends Player {

  private Scanner scanner;
  private String name;

  public Human(Scanner scanner, Stone stone, String name) {
    this.scanner = scanner;
    this.name = name;
    this.playersStone = stone;
  }

  @Override
  public int computeRowForNextMove(Board board) {
    int selectedRow = -1;
    while (selectedRow < 0 || selectedRow > 6) {
      System.out.println(String.format("Please insert row between 0 and 6 to place your next stone."));
      try {
        selectedRow = scanner.nextInt();
      } catch (Exception e) {
        System.out.println("Illegal Input");
        selectedRow = -1;
      } finally {
        scanner.nextLine();
      }
    }
    return selectedRow;
  }

  @Override
  public String toString() {
    return name;
  }

}
