import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class main
{
  public static final Point screen = new Point((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
  public static double GameTime = -1;
  public static int WindowCount;
  public static boolean ConditionMet;
  public static int TabOrder;

  public static void main(String args[])
  {
    int ThisWindowCount;
    ConditionMet = true;

    while (true)
    {
      // Game start
      if (ConditionMet)
        WaitForClose(MainWindow("Bop It"));
      else
        WaitForClose(DefeatWindow());
      WindowCount = (int)(Math.random() * 10 + 6);
      ThisWindowCount = WindowCount;
      GameTime = System.currentTimeMillis();
      while (ThisWindowCount > 0)
      {
        // Game loop
        ConditionMet = false;
        WaitForClose(ActionWindow(ThisWindowCount + " Remain"));
        ThisWindowCount--;
        if (!ConditionMet)
          break;
      }
      GameTime = (System.currentTimeMillis() - GameTime) / 1000;
    }
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

    if (GameTime < 0)
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
      JLabel L = new JLabel("<html>Total: " + (Math.floor(GameTime * 1000) / 1000) + " seconds<br>Average: " + (Math.floor(GameTime / WindowCount * 1000) / 1000) + " per window</html>");
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
    
    R.add(RandomElement(W));
    R.setVisible(true);
    return R;
  }

  private static JFrame DefeatWindow()
  {
    final Point W = new Point(600, 400);
    final Point P = new Point((main.screen.x - W.x) / 2, (main.screen.y - W.y) / 2);
    JFrame R = new JFrame("DEFEAT");
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

    JLabel L = new JLabel("FAILURE");
    L.setFont(L.getFont().deriveFont(60f));
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

  private static Component RandomElement(Point size)
  {
    int I = (int)Math.floor(Math.random() * 4);
    switch (I)
    {
      case 1:
        String F1 = new String[] {"You smell.", "Cheese!", "Hike near!", "True!", "False!"}[(int)(Math.random() * 5)];
        JButton F = new JButton(F1);
        F.setFont(F.getFont().deriveFont(30f));
        F.addActionListener(new ActionListener()
        {
          @Override public void actionPerformed(ActionEvent e)
          {
            ConditionMet = false;
          }
        });
        ConditionMet = true;
        return F;
      case 2:
        JButton B = new JButton("Click here!");
        B.setFont(B.getFont().deriveFont(30f));
        B.addActionListener(new ActionListener()
        {
          @Override public void actionPerformed(ActionEvent e)
          {
            ConditionMet = true;
          }
        });
        return B;
      case 3:
        ArrayList<Boolean> A = new ArrayList<Boolean>();
        JTabbedPane T = new JTabbedPane();
        TabOrder = 0;
        for (int J = 0; J < 3; J++)
          A.add(AddRandomTab(T, J, A));
        if (!A.contains(true))
          ConditionMet = true;
        else if (!A.getFirst())
          while (!A.get(TabOrder))
            TabOrder++;
        return T;
      default:
        String S1 = new String[] {":)", ":3", ":D", ">:(", ":(", ">:)", ">:3", "-_-", "._.", "'-'", "o7", "", "hax", "Faster!", "GG"}[(int)(Math.random() * 15)];
        JLabel S = new JLabel(S1);
        S.setFont(S.getFont().deriveFont(60f));
        S.setHorizontalAlignment(SwingConstants.CENTER);
        ConditionMet = true;
        return S;
    }
  }

  private static boolean AddRandomTab(JTabbedPane T, int I, ArrayList<Boolean> A)
  {
    JButton B;
    if ((int)(Math.random() * 2) == 0)
    {
      B = new JButton("Click here!");
      B.setFont(B.getFont().deriveFont((int)(Math.random() * 4 + 2) * 10f));
      B.addActionListener(new ActionListener()
      {
        @Override public void actionPerformed(ActionEvent e)
        {
          if (TabOrder > -1)
            if (TabOrder != I)
            {
              TabOrder = -1;
              ConditionMet = false;
            }
            else
            {
              TabOrder++;
              if (TabOrder >= A.size())
                ConditionMet = true;
              else
                while (!A.get(TabOrder))
                {
                  TabOrder++;
                  if (TabOrder >= A.size())
                  {
                    ConditionMet = true;
                    break;
                  }
                }
            }
        }
      });
      T.addTab("Tab " + (I + 1), B);
      return true;
    }
    else
    {
      B = new JButton("Lick ear!");
      B.setFont(B.getFont().deriveFont((int)(Math.random() * 4 + 2) * 10f));
      B.addActionListener(new ActionListener()
      {
        @Override public void actionPerformed(ActionEvent e)
        {
          TabOrder = -1;
          ConditionMet = false;
        }
      });
      T.addTab("Tab " + (I + 1), B);
      return false;
    }
  }
}
