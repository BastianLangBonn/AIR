package org.brsu.assignments.assignment1;

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
      writer = new PrintWriter(filename);
      // writer.print("");
      writer.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

  }
}
