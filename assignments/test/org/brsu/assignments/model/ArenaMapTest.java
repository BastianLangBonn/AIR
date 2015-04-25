package org.brsu.assignments.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ArenaMapTest {

  @Test
  public void constructorTest() {
    ArrayList<List<String>> list = new ArrayList<List<String>>();
    for (int i = 0; i < 10; i++) {
      ArrayList<String> row = new ArrayList<String>();
      list.add(row);
      for (int j = 0; j < 10; j++) {
        row.add(String.format("%d%d", i, j));
      }
    }
    Map arenaMap = new Map(list);
    list.get(3).set(5, "foo");
    System.out.println(list);
    System.out.println(arenaMap.toString());

  }
}
