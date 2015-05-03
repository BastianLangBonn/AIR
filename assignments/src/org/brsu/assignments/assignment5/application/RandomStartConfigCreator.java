package org.brsu.assignments.assignment5.application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomStartConfigCreator {

  public String createRandomConfiguration() {
    List<Integer> list = new ArrayList<Integer>();
    for (int i = 0; i < 9; i++) {
      list.add(i);
    }
    Collections.shuffle(list);
    String result = "";
    for (int i = 0; i < list.size(); i++) {
      result += list.get(i);
      if (i < list.size() - 1) {
        result += ",";
      }
    }
    return result;
  }

  public static void main(String[] args) {
    RandomStartConfigCreator randomStartConfigCreator = new RandomStartConfigCreator();
    System.out.println(randomStartConfigCreator.createRandomConfiguration());
    System.out.println(randomStartConfigCreator.createRandomConfiguration());
    System.out.println(randomStartConfigCreator.createRandomConfiguration());

  }

}
