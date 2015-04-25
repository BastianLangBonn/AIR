package org.brsu.assignments.assignment4;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.brsu.assignments.model.Map;
import org.brsu.assignments.model.Position;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for {@link IterativeDeepeningAgent}.
 * 
 * @author bastian
 * 
 */
public class IterativeDeepeningAgentTest {

  private Map map;
  private IterativeDeepeningAgent subject;

  @Test
  public void getTargetsFromMap_onlyInts() {
    List<Integer> targets = subject.getTargetsFromMap(map);
    assertEquals(100, targets.size());
    System.out.println(targets);
  }

  @Test
  public void getTargetsFromMap_someOtherStrings() {
    map.setPosition(new Position(3, 4), "text");
    List<Integer> targets = subject.getTargetsFromMap(map);
    assertEquals(99, targets.size());
    System.out.println(targets);
  }

  @Before
  public void initializeTests() {
    subject = new IterativeDeepeningAgent();
    ArrayList<List<String>> list = new ArrayList<List<String>>();
    for (int i = 0; i < 10; i++) {
      ArrayList<String> row = new ArrayList<String>();
      list.add(row);
      for (int j = 0; j < 10; j++) {
        row.add(String.format("%d%d", i, j));
      }
    }
    map = new Map(list);
  }
}
