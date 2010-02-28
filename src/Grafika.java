import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.AbstractListModel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Grafika extends JFrame {

	DrawPane drawPane;
	ToolsPane toolsPane = new ToolsPane();
	
	public static void main(String[] args) {
		new StartWindow();
	}
	
	public Grafika() {
		super("Nowa Aplikacja Graficzna :: Untitled.jpg");
		setSize(800,600);
		
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		setLayout(new BorderLayout());
		
		setTopMenu();
		

		JSplitPane jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		drawPane = new DrawPane();
		drawPane.toolsPane = toolsPane; 
		jsp.setLeftComponent(drawPane);
		jsp.setRightComponent(toolsPane);
		jsp.setDividerLocation(550);
		jsp.setEnabled(false);
		jsp.setOneTouchExpandable(true);
		JSplitPane jsp2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		jsp2.setTopComponent(jsp);
		jsp2.setDividerLocation(400);
		add(jsp,BorderLayout.CENTER);
		
		
		//add(jtb1, BorderLayout.SOUTH);
		setVisible(true);
		//zmusił mnie do castowania
		//toolsPane.actualShape = new Text(drawPane.getGraphics());
		toolsPane.actualShape = new Rectangle(drawPane.getGraphics(), toolsPane);
		
		toolsPane.g = drawPane.getGraphics();
		toolsPane.drawPane = drawPane;
		System.err.println("tools " + drawPane.getGraphics());
		drawPane.addMouseListener((MouseListener) toolsPane.actualShape);
		drawPane.addMouseMotionListener((MouseMotionListener) toolsPane.actualShape);
		
		this.addWindowListener(new WindowAdapter()
		{
		      public void windowClosing(WindowEvent e)
		      {
		    	  JOptionPane window = new JOptionPane();
					int v = window.showConfirmDialog(Grafika.this, "Czy na pewno chcesz opuścić program?","Czy na pewno chcesz opuścić program?",0);
					if (v == window.YES_OPTION)
						System.exit(0);
					else
						return;
		      }
		});
	}
	
	public void setToolbars() {
		JToolBar jtb = new JToolBar("MÓJ TÓLBAR");
		jtb.add(new JButton("Start1"));
		jtb.add(new JButton("Start1"));
		jtb.add(new JButton("Start1"));
		//add(jtb, BorderLayout.NORTH);
		JToolBar jtb1 = new JToolBar("MÓJ TÓLBAR");
		jtb1.add(new JButton("Start1"));
		jtb1.add(new JButton("Start1"));
		jtb1.add(new JButton("Start1"));
	}
	
	public void setTopMenu() {
		JMenuBar mb = new JMenuBar();
		JMenu mfile = new JMenu("File");
		mfile.setMnemonic(KeyEvent.VK_F);
		
		JMenu medit = new JMenu("Edit");
		medit.setMnemonic(KeyEvent.VK_E);
		
		JMenu mhelp = new JMenu("Help");
		mhelp.setMnemonic(KeyEvent.VK_H);
		
		JMenuItem help = new JMenuItem("Help");
		help.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new HelpWindow();
			}
			
		});
		
		mhelp.add(help);
		
		JMenuItem about = new JMenuItem("About");
		about.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AboutWindow();
			}
			
		});
		
		mhelp.add(about);
		
		JMenuItem fileOpen = new JMenuItem("Open");
		fileOpen.setAccelerator(
			KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK)	
		);
		mfile.add(fileOpen);
		
		
		fileOpen.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				int v = jfc.showOpenDialog(Grafika.this);
				if (v==jfc.CANCEL_OPTION) return;
				System.out.println(jfc.getSelectedFile().getAbsolutePath());
			}
			
		});
		
		JMenuItem fileSave = new JMenuItem("Save");
		fileSave.setAccelerator(
			KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK)	
		);
		mfile.add(fileSave);
		
		JMenuItem exit = new JMenuItem("Exit");
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
		
		exit.addActionListener( new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) {
						JOptionPane window = new JOptionPane();
						int v = window.showConfirmDialog(Grafika.this, "Czy na pewno chcesz opuścić program?","Czy na pewno chcesz opuścić program?",0);
						if (v == window.YES_OPTION)
							System.exit(0);
						else
							return;
					}
					
				}
		);
		mfile.add(new JSeparator());
		mfile.add(exit);
		
		
		//mb.add(mfile);
		//mb.add(medit);
		mb.add(new JSeparator());
		mb.add(mhelp);
		
		setJMenuBar(mb);		
	}

}
