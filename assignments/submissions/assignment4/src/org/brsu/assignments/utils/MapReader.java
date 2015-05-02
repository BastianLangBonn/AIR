package org.brsu.assignments.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to read a map from a file.
 * 
 * @author bastian
 * 
 */
public class MapReader {

  /**
   * Reads a file and puts it into a two dimensional String list.
   * 
   * @param filename
   * @return
   * @throws IOException
   */
  public List<List<String>> readMapFromFile(String filename) throws IOException {
    List<List<String>> map = new ArrayList<List<String>>();
    List<String> lines = Files.readAllLines(Paths.get(filename),
        Charset.forName("UTF-8"));
    for (String line : lines) {
      ArrayList<String> mapRow = new ArrayList<String>();
      for (int i = 0; i < line.length(); i++) {
        char element = line.charAt(i);
        mapRow.add(String.valueOf(element));
      }
      map.add(mapRow);
    }
    return map;
  }

  public static void main(String[] args) throws Exception {
    MapReader mapReader = new MapReader();
    List<List<String>> map1 = mapReader
        .readMapFromFile("resources/maps/map1.txt");
    List<List<String>> map2 = mapReader
        .readMapFromFile("resources/maps/map2.txt");
    List<List<String>> map3 = mapReader
        .readMapFromFile("resources/maps/map3.txt");
    MapPrinter mapPrinter = new MapPrinter();
    mapPrinter.printMap(map1);
    mapPrinter.printMap(map2);
    mapPrinter.printMap(map3);
  }

}
