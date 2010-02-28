import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.colorchooser.DefaultColorSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

class ToolsPane extends JPanel implements ActionListener  {
	
	public JRadioButton jr1 = new JRadioButton("Koło");
	public JRadioButton jr2 = new JRadioButton("Prostokąt", true);
	public JRadioButton jr3 = new JRadioButton("Linia");
	public JRadioButton jr4 = new JRadioButton("Punkt");
	public JRadioButton jr5 = new JRadioButton("Text");
	public JRadioButton fake = new JRadioButton();
	public JButton colorButton = new JButton();
	public JButton delete = new JButton("Usuń");
	JTextArea text = new JTextArea("", 5, 20);
	public DefaultColorSelectionModel colorChooserModel = new DefaultColorSelectionModel(Color.black);
	public JList list;
	Graphics g;
	MyShape actualShape;// = new Oval(drawPane.getGraphics());
	MyShape selectedShape;
	Vector<MyShape> shapes = new Vector<MyShape>();
	DrawPane drawPane; 
	MyModelList m;
	//wiemy który jest zaznaczony
	AbstractButton buttonSelected =  jr2;
	ButtonGroup bg = new ButtonGroup(); 
	
	String[] avaibleFonts = {
			"Arial",
			"Times",
			"Serif",
			"SansSerif",
			"Verdana",
			"Courier"
	};

	public ToolsPane() {
		setSize(800,600);
		setRadioButtons();
		JComboBox fonts = new JComboBox(avaibleFonts);
		FontSelectorRenderer renderer = new FontSelectorRenderer("Verdana");
		fonts.setRenderer(renderer); 
		add(text);
		add(fonts);
		fonts.addActionListener(this);
		jr1.addChangeListener(changeListener);
		jr2.addChangeListener(changeListener);
		jr3.addChangeListener(changeListener);
		jr4.addChangeListener(changeListener);
		jr5.addChangeListener(changeListener);
		//setSize jest używane bez zarządcy rozkładu
		
		setBackground(Color.yellow);
		list = new JList(new MyModelList());
		JScrollPane jscroll = new JScrollPane(list);
		jscroll.setPreferredSize(new Dimension(200,200));
		add(jscroll);
		
		//zdarzenia z listy
		
		
		list.addListSelectionListener(listSelectionListener);
		
		colorButton.setPreferredSize(new Dimension(50,50));
		colorButton.setBackground(Color.black);
		colorButton.addActionListener(this);
		add(colorButton);
		m = (MyModelList)this.list.getModel();
	}

	private void setRadioButtons() {
		
		jr1.setPreferredSize(new Dimension(200,20));
		jr2.setPreferredSize(new Dimension(200,20));
		jr3.setPreferredSize(new Dimension(200,20));
		jr4.setPreferredSize(new Dimension(200,20));
		jr5.setPreferredSize(new Dimension(200,20));
		
		jr1.setActionCommand("Oval");
		jr3.setActionCommand("Line");
		jr2.setActionCommand("Rectangle");
		jr4.setActionCommand("Point");
		jr5.setActionCommand("Text");
		colorButton.setActionCommand("_ColorButton");
		delete.setActionCommand("delete");
		
		bg.add(jr1);
		bg.add(jr2);
		bg.add(jr3);
		bg.add(jr4);
		bg.add(jr5);
		bg.add(fake);
		add(jr1);
		add(jr2);
//		add(jr3);
//		add(jr4);
		add(jr5);
		add(fake);
		fake.setVisible(false);
		//add(delete);
		jr1.addActionListener(this);
		jr2.addActionListener(this);
		jr3.addActionListener(this);
		jr4.addActionListener(this);
		jr5.addActionListener(this);
		delete.addActionListener(this);
	}

	public void drawn(MyShape shape) {
		m.add(m.getSize(),shape);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if("_ColorButton".equals(e.getActionCommand())) {
			//System.err.println("przed wywołaniem" + g.hashCode());
			new MyColorChooser(colorChooserModel, actualShape, colorButton);
		}
		if("delete".equals(e.getActionCommand())) {
			
		}
		System.err.println("co tu się stało? " + e.getSource());
	}
	
	private void changeListener(MyShape newShape) {
		drawPane.removeMouseListener((MouseListener)actualShape);
		drawPane.removeMouseMotionListener((MouseMotionListener)actualShape);
		actualShape = newShape;
		drawPane.addMouseListener((MouseListener)actualShape);
		drawPane.addMouseMotionListener((MouseMotionListener)actualShape);
	}
	
	final ToolsPane _this = this;
	ChangeListener changeListener = new ChangeListener() {
		@Override
		public void stateChanged(ChangeEvent e) {
			AbstractButton button = (AbstractButton)e.getSource();
			//czy tu przydała by się tablica sterująca?
			buttonSelected = button;
			String t = button.getText();
			if (buttonSelected.isSelected()) {
				actualShape.isSelectedForModification = false;
				actualShape.draw();
				if(t == "Prostokąt") {
					changeListener(new Rectangle(drawPane.getGraphics(), _this));
				}
				if(t == "Koło") {
					changeListener(new Oval(drawPane.getGraphics(), _this));
				}
				if(t == "Punkt") {
					changeListener(new Point(drawPane.getGraphics(), _this));
				}
				if(t == "Linia") {
					changeListener(new Line(drawPane.getGraphics(), _this));
				}
				if(t == "Text") {
					changeListener(new Text(drawPane.getGraphics(), _this));
				}
			}
		}
	 };
	
	 ListSelectionListener listSelectionListener = new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				actualShape.isSelectedForModification = false;
				actualShape.draw();
//				try {
//					actualShape.draw();
//					actualShape.isSelectedForModification = false;
//					//drawPane.removeMouseListener((MouseListener)selectedShape);
//					//drawPane.removeMouseMotionListener((MouseMotionListener)selectedShape);
//				} catch (NullPointerException e1) {
//					//e1.printStackTrace();
//				}
				changeListener((MyShape) m.getElementAt(list.getSelectedIndex()));
				actualShape.isSelectedForModification = true;
				actualShape.draw(Color.red);
				fake.setSelected(true);
			}
			
		};
}