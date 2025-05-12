import javax.swing.*;
import java.awt.*;

public class main
{
  private static int WindowCount = 0;
  
  public static void main(String args[])
  {
    while (true)
    {
      // Game start
      WaitForClose(MainWindow("Bop It"));
      WindowCount = 10;
      while (WindowCount > 0)
      {
        // Game loop
        WaitForClose(ActionWindow("/* " + WindowCount + " */"));
        WindowCount--;
      }

      // Game end
      boolean playAgain = false;
      if (playAgain)
        break;
    }

    /*
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
    */
  }

  private static JFrame MainWindow(String name)
  {
    JFrame R = new JFrame(name);
    R.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    R.setSize(600, 400);
    R.setLocationRelativeTo(null);
    R.setResizable(false);
    R.setVisible(true);

    R.addWindowListener(new WindowListener()
    {
      @Override public void windowClosing(WindowEvent e)
      {
        e.getWindow().setEnabled(false);
      }
      @Override public void windowOpened(WindowEvent e) {}
      @Override public void windowClosed(WindowEvent e) {}
      @Override public void windowIconified(WindowEvent e) {}
      @Override public void windowDeiconified(WindowEvent e) {}
      @Override public void windowActivated(WindowEvent e) {}
      @Override public void windowDeactivated(WindowEvent e) {}
      @Override public void windowStateChanged(WindowEvent e) {}
    });

    return R;
  }

  private static JFrame ActionWindow(String name)
  {
    JFrame R = new JFrame(name);
    R.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    R.setSize(400, 400);
    R.setLocation((int)(Math.random() * 100), (int)(Math.random() * 100));
    R.setResizable(false);
    R.setVisible(true);

    R.addWindowListener(new WindowListener()
    {
      @Override public void windowClosing(WindowEvent e)
      {
        e.getWindow().setEnabled(false);
      }
      @Override public void windowOpened(WindowEvent e) {}
      @Override public void windowClosed(WindowEvent e) {}
      @Override public void windowIconified(WindowEvent e) {}
      @Override public void windowDeiconified(WindowEvent e) {}
      @Override public void windowActivated(WindowEvent e) {}
      @Override public void windowDeactivated(WindowEvent e) {}
      @Override public void windowStateChanged(WindowEvent e) {}
    });
    
    return R;
  }

  private static void Wait(double seconds)
  {
    try
    {
      Thread.sleep((long)seconds * 1000);
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
  }

  private static void WaitForClose(JFrame frame)
  {
    while (frame.isEnabled())
      Wait(0.1);
  }

  static class CirclePanel extends JPanel
  {
    @Override protected void paintComponent(Graphics g)
    {
      super.paintComponent(g);
      g.setColor(Color.BLUE);
      g.fillOval(50, 50, 100, 100); // Draw a blue circle
    }
  }

  // Panel for drawing a rectangle
  static class RectanglePanel extends JPanel
  {
    @Override protected void paintComponent(Graphics g)
    {
      super.paintComponent(g);
      g.setColor(Color.RED);
      g.fillRect(50, 50, 150, 100); // Draw a red rectangle
    }
  }
}
