import java.awt.*;
import java.awt.event.*;
//import java.util.*;
import javax.swing.*;

public class main
{
  public static final Point screen = new Point((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
  public static double GameTime = -1;
  public static int WindowCount;

  public static void main(String args[])
  {
    int ThisWindowCount;

    while (true)
    {
      // Game start
      WaitForClose(MainWindow("Bop It"));
      WindowCount = (int)(Math.random() * 10 + 6);
      ThisWindowCount = WindowCount;
      GameTime = System.currentTimeMillis();
      while (ThisWindowCount > 0)
      {
        // Game loop
        WaitForClose(ActionWindow("/* " + ThisWindowCount + " */"));
        ThisWindowCount--;
      }
      GameTime = (System.currentTimeMillis() - GameTime) / 1000;
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
    final Point W = new Point(600, 400);
    final Point P = new Point((main.screen.x - W.x) / 2, (main.screen.y - W.y) / 2);
    JFrame R = new JFrame(name);
    R.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    R.setSize(W.x, W.y);
    R.setResizable(false);
    
    R.setLocation(P);
    R.addComponentListener(new ComponentAdapter()
    {
      @Override public void componentMoved(ComponentEvent e)
      {
        R.setLocation(P);
      }
    });

    if (main.GameTime < 0)
    {
      JLabel L = new JLabel("Ready to Bop It?");
      L.setFont(L.getFont().deriveFont(30f));
      L.setHorizontalAlignment(SwingConstants.CENTER);
      R.add(L, BorderLayout.CENTER);

      JButton B = new JButton("BEGIN");
      B.setPreferredSize(new Dimension(100, 50));
      B.setFont(B.getFont().deriveFont(20f));
      B.addActionListener(new ActionListener()
      {
        @Override public void actionPerformed(ActionEvent e)
        {
          R.setEnabled(false);
          R.dispose();
        }
      });
      R.add(B, BorderLayout.SOUTH);
    }
    else
    {
      JLabel L = new JLabel("<html>Total: " + (main.GameTime) + " seconds<br>Average: " + (main.GameTime / main.WindowCount) + " per window</html>");
      L.setFont(L.getFont().deriveFont(30f));
      L.setHorizontalAlignment(SwingConstants.CENTER);
      R.add(L, BorderLayout.CENTER);
      
      JButton B = new JButton("AGAIN");
      B.setPreferredSize(new Dimension(100, 50));
      B.setFont(B.getFont().deriveFont(20f));
      B.addActionListener(new ActionListener()
      {
        @Override public void actionPerformed(ActionEvent e)
        {
          R.setEnabled(false);
          R.dispose();
        }
      });
      R.add(B, BorderLayout.SOUTH);
      /*
      JButton C = new JButton("Exit");
      C.setPreferredSize(new Dimension(100, 50));
      C.setFont(C.getFont().deriveFont(20f));
      C.addActionListener(new ActionListener()
      {
        @Override public void actionPerformed(ActionEvent e)
        {
          System.exit(0);
        }
      });
      R.add(C, BorderLayout.SOUTH);
      */
    }

    R.setVisible(true);
    return R;
  }

  private static JFrame ActionWindow(String name)
  {
    final Point W = new Point((int)(Math.random() * 2 + 3) * 100, (int)(Math.random() * 2 + 3) * 100);
    final Point P = new Point((int)(Math.random() * (main.screen.x - W.x)), (int)(Math.random() * (main.screen.y - W.y)));
    JFrame R = new JFrame(name);
    R.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    R.setSize(W.x, W.y);
    R.setResizable(false);

    R.setLocation(P);
    R.addComponentListener(new ComponentAdapter()
    {
      @Override public void componentMoved(ComponentEvent e)
      {
        R.setLocation(P);
      }
    });

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
    });
    
    //R.add(TabbedButton("Bop It", "False Sun"));
    R.setVisible(true);
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

  private static Component TabbedButton(String name, String text)
  {
    JButton B = new JButton(text);
    B.setPreferredSize(new Dimension(100, 50));
    B.setFont(B.getFont().deriveFont(20f));
    B.addActionListener(new ActionListener()
    {
      @Override public void actionPerformed(ActionEvent e)
      {
        JFrame R = new JFrame(name);
        R.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        R.setSize(200, 200);
        R.setLocationRelativeTo(null);
        R.setVisible(true);
      }
    });
    return B;
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