import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainClass extends JPanel {

  public void paint(Graphics g) {
    int w = getSize().width;
    int midW = w / 2;

    g.drawString("XOR Mode", 0, 30);

    g.drawOval(7, 37, 50, 50);

    g.setXORMode(Color.white);

    for (int i = 0; i < 15; i += 3) {
      g.drawOval(10 + i, 40 + i, 50, 50);
    }

    g.setPaintMode();

    g.drawString("Paint Mode", midW, 30);

    g.drawOval(midW + 7, 37, 50, 50);

    for (int i = 0; i < 15; i += 3) {
      g.drawOval(midW + 10 + i, 40 + i, 50, 50);
    }
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.getContentPane().add(new MainClass());

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(200, 200);
    frame.setVisible(true);
  }
}
       