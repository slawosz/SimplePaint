import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.colorchooser.DefaultColorSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MouseInputListener;

import org.omg.CORBA.Current;


class MyColorChooser extends JFrame implements ChangeListener {
	
	JColorChooser chooser;
	Graphics g;
	JButton colorButton;
	MyShape shape;
	
	public MyColorChooser(DefaultColorSelectionModel model, MyShape shape, JButton colorButton) {
		chooser = new JColorChooser(model);
		this.shape = shape;
		this.colorButton = colorButton;
		setResizable(false);
		setSize(500,500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		chooser.setSize(400,400);
		setLayout(new BorderLayout());
		add(chooser);
		model.addChangeListener(this);
		setVisible(true);
	}


	@Override
	public void stateChanged(ChangeEvent e) {
		shape.setColor(chooser.getColor());
		colorButton.setBackground(chooser.getColor());
		
	}


}
