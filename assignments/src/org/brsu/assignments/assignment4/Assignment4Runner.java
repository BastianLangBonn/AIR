package org.brsu.assignments.assignment4;

import java.io.IOException;
import java.util.List;

import org.brsu.assignments.utils.MapPrinter;
import org.brsu.assignments.utils.MapReader;

public class Assignment4Runner {

  public static void main(String[] args) throws IOException {
    MapReader mapReader = new MapReader();
    List<List<String>> map = mapReader
        .readMapFromFile("resources/assignment2/maps/map1.txt");
    MapPrinter printer = new MapPrinter();
    printer.printMap(map);

  }
}
