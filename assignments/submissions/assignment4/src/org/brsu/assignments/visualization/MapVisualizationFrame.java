package org.brsu.assignments.visualization;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JFrame;

import org.brsu.assignments.model.Map;
import org.brsu.assignments.model.Position;

/**
 * Class to display a map and the path the algorithm found.
 * 
 * @author bastian
 * 
 */
public class MapVisualizationFrame extends JFrame {

  private static final int FIELD_WIDTH = 10;
  private static final int FIELD_HEIGHT = 15;
  private static final long serialVersionUID = 1L;
  private Map map;
  private List<List<Position>> paths;

  public MapVisualizationFrame(Map map, List<List<Position>> paths) {
    this.map = map;
    this.paths = paths;
    setVisible(true);
    setSize(map.getWidth() * FIELD_WIDTH + 20, map.getHeight() * FIELD_HEIGHT + 30);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  public void drawScene(Map map, Position agentPosition, List<List<Position>> paths) {
    this.map = map;
    this.paths = paths;
    repaint();
  }

  @Override
  public void paint(Graphics g) {
    g.clearRect(0, 0, getWidth(), getHeight());
    drawMap(g);
    // drawAgent(g);
    drawPaths(g);
  }

  private void drawPaths(Graphics g) {
    Color formerColor = g.getColor();
    for (int i = 0; i < paths.size(); i++) {
      Color pathColor = new Color(255 - i * 255 / paths.size(), 255 - i * 255 / paths.size(), 0 + i * 255
          / paths.size());
      g.setColor(pathColor);
      drawPath(g, paths.get(i));
    }
    g.setColor(formerColor);
  }

  private void drawPath(Graphics g, List<Position> path) {
    if (paths.size() <= 1) {
      return;
    }
    for (int i = 1; i < path.size(); i++) {
      int startX = 12 + path.get(i - 1).getColumn() * FIELD_WIDTH;
      int startY = 5 + path.get(i - 1).getRow() * FIELD_HEIGHT;
      int targetX = 12 + path.get(i).getColumn() * FIELD_WIDTH;
      int targetY = 5 + path.get(i).getRow() * FIELD_HEIGHT;
      g.drawLine(startX, startY, targetX, targetY);
      g.drawLine(startX + 1, startY + 1, targetX + 1, targetY + 1);
      g.drawLine(startX + 2, startY + 2, targetX + 2, targetY + 2);
    }
  }

  private void drawMap(Graphics g) {
    for (int x = 0; x < map.getWidth(); x++) {
      for (int y = 0; y < map.getHeight(); y++) {
        g.drawString(map.getElementAtPosition(x, y), 10 + x * FIELD_WIDTH, 10 + y * FIELD_HEIGHT);
      }
    }
  }
}
