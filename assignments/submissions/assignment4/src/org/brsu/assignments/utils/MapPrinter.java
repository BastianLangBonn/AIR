package org.brsu.assignments.utils;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Class to print out maps.
 * 
 * @author bastian
 * 
 */
public class MapPrinter {

  /**
   * Prints out a given List<List<String>>.
   * 
   * @param map
   */
  public void printMap(List<List<String>> map) {
    for (List<String> row : map) {
      for (String element : row) {
        System.out.print(element);
      }
      System.out.print("\n");
    }
  }

  /**
   * Prints a given Map into a file.
   * 
   * @param map
   * @param filename
   */
  public void printMapToFile(List<List<String>> map, String filename) {
    PrintWriter writer;
    try {
      writer = new PrintWriter(new BufferedWriter(
          new FileWriter(filename, true)));
      for (List<String> row : map) {
        for (String element : row) {
          writer.print(element);
        }
        writer.print("\n");
      }
      writer.print("\n");
      writer.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void clearFile(String filename) {
    PrintWriter writer;
    try {
      writer = new PrintWriter(filename + "_result.txt");
      writer.close();
      writer = new PrintWriter(filename);
      writer.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public void printResult(List<List<String>> map, int maxNumberOfNodesToVisit,
      int numberOfStepsUntilSolved, int maxNumberOfNodesVisited,
      boolean solved, long timeTaken, String outputFile) {
    PrintWriter writer;
    try {
      writer = new PrintWriter(new BufferedWriter(new FileWriter(outputFile
          + "_result.txt", true)));
      writer
          .print(String
              .format(
                  "Steps needed: %d\nMaximum nodes to visit stored: %d\nMaximum nodes visited stored: %d\nAll fields clean: %s\nTime taken: %d\n",
                  numberOfStepsUntilSolved, maxNumberOfNodesToVisit,
                  maxNumberOfNodesVisited, solved, timeTaken));
      writer.close();
      printMapToFile(map, outputFile + "_result.txt");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
