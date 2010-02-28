import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JWindow;


class StartWindow extends JWindow implements Runnable {

	ImageIcon icon1, icon2;
	JLabel icon, black_icon;
	JTextArea textArea = new JTextArea("\n Autor:\nSławosz Sławiński\nMagic\nAwsome");
	//najbezpieczniejszy
	JLabel status = new JLabel("");
	JProgressBar jpb = new JProgressBar();
	public Vector<String> v = new Vector<String>();

	
	public StartWindow() {
		//wiem, ze jest dziedziczenie
		super();
		v.add("Application starting...");
		v.add("Cos tam 123....");
		v.add("Cos  blabla bla....");
		v.add("FATAL ERROR....");
		v.add("SEGMENTATION FAULT....");
		v.add("STACK OVERFLOW....");
		v.add("KERNEL PANIC....");
		v.add("FORMATING C Drive....");
		v.add("NOT ENOUGHT FREE SPACE....");
		v.add("I/O ERROR....");
		setSize(500,290);
		Toolkit t = Toolkit.getDefaultToolkit();
		Dimension d = t.getScreenSize();
		setLocation(d.width/2-250, d.height/2-150);
		setLayout(null);
		icon1 = new ImageIcon("tlo.jpg");
		icon2 = new ImageIcon("tlo_black.jpg");
		
		//musimy miec pewnosc ze sa obrazki, ze sie zalagowaly
		//zaladowaly sie asynchronicznie
		MediaTracker mt = new MediaTracker(StartWindow.this);
		mt.addImage(icon1.getImage(), 0);
		mt.addImage(icon2.getImage(), 1);
		
		try {
			mt.waitForAll();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//jesli nie bedzie obrazu, obiekt będzie pusty (szary)
		icon = new JLabel(icon1);
		black_icon = new JLabel(icon2);
		
		jpb.setMinimum(0);
		jpb.setMaximum(500);
		jpb.setValue(0);
		
		//nie mamy layoutu, więc
		
		icon.setBounds(0,0,320,240);
		black_icon.setBounds(0,0,500,300);
		textArea.setBounds(330,20,140,200);
		jpb.setBounds(5,245,490,25);
		status.setBounds(5,270,490,20);
		//co to jest contentPane
		//this.getContentPane().setBackground(Color.yellow);
		
		textArea.setFont(new Font("Georgia",Font.BOLD, 20));
		textArea.setOpaque(false);
		textArea.setForeground(Color.yellow);
		jpb.setForeground(Color.yellow);
		status.setForeground(Color.yellow);
		add(icon);
		add(textArea);
		add(jpb);
		add(status);
		add(black_icon);
		
		setVisible(true);
		new Thread(this).start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		//while(true) {
		int i = 0;
		while(i<=jpb.getMaximum()) {
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jpb.setValue(i);
			status.setText(v.elementAt((i-1) / 50));
			i++;
		}
			
		//}
		new Grafika();
		this.dispose();
	}
	
	

}
