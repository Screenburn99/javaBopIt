import javax.swing.*;
import java.awt.*;

public class main
{
  public static void main(String args[])
  {
    // Create the main frame
    JFrame frame = new JFrame("Shape Drawer");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 400);
    
    // Create a tabbed pane
    JTabbedPane tabbedPane = new JTabbedPane();
    
    // Add tabs with custom panels for drawing shapes
    tabbedPane.addTab("Circle", new CirclePanel());
    tabbedPane.addTab("Rectangle", new RectanglePanel());
    
    // Add the tabbed pane to the frame
    frame.add(tabbedPane);
    
    // Make the frame visible
    frame.setVisible(true);
  }

  static class CirclePanel extends JPanel
  {
    @Override
    protected void paintComponent(Graphics g)
    {
      super.paintComponent(g);
      g.setColor(Color.BLUE);
      g.fillOval(50, 50, 100, 100); // Draw a blue circle
    }
  }

  // Panel for drawing a rectangle
  static class RectanglePanel extends JPanel
  {
    @Override
    protected void paintComponent(Graphics g)
    {
      super.paintComponent(g);
      g.setColor(Color.RED);
      g.fillRect(50, 50, 150, 100); // Draw a red rectangle
    }
  }
}
