import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;




public class HelpWindow extends JFrame implements ActionListener{
	
	final String HELP = setHelp();
	JLabel help = new JLabel(HELP);
	JButton exit = new JButton("Zamknij");
	
	public HelpWindow() {
		super();
		setResizable(false);
		setSize(300, 400);
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		help.setSize(new Dimension(200,200));
		exit.setSize(new Dimension(100,50));
		exit.setActionCommand("Zamknij");
		add(help);
		add(exit);
		//pack();
		setVisible(true);
		exit.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if("Zamknij".equals(e.getActionCommand())) {
			this.dispose();
		}
		
	}

	private String setHelp() {
		return "<html><h2>Program do rysowania<h2><h3>Funkcjonalność:</h3>" +
			"<li>Rysowanie kół, prostokątów, linii</li></html>";
	}
}
