package org.brsu.assignments.assignment7.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

import org.brsu.assignments.assignment7.model.City;
import org.brsu.assignments.assignment7.model.SalesmanNode;

public class MainFrame extends JFrame {

  private static final int MAGNIFIER = 5;
  private SalesmanNode solution;

  public MainFrame(SalesmanNode solution) {
    this.solution = solution;
    setSize(300, 300);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
    repaint();
  }

  @Override
  public void paint(Graphics g) {
    if (solution.getPath().size() < 1) {
      return;
    }
    for (int i = 1; i < solution.getPath().size(); i++) {
      City firstCity = solution.getPath().get(i - 1);
      City secondCity = solution.getPath().get(i);
      int xOffset = 700;
      int yOffset = 250;
      g.setColor(Color.RED);
      g.fillArc(xOffset + (int) (firstCity.getLongitude() * MAGNIFIER), yOffset
          + (int) (firstCity.getLatitude() * MAGNIFIER), MAGNIFIER, MAGNIFIER, 0, 360);
      g.setColor(Color.BLUE);
      g.drawLine(xOffset + (int) (firstCity.getLongitude() * MAGNIFIER), yOffset
          + (int) (firstCity.getLatitude() * MAGNIFIER), xOffset + (int) (secondCity.getLongitude() * MAGNIFIER),
          yOffset + (int) (secondCity.getLatitude() * MAGNIFIER));
    }
  }
}
