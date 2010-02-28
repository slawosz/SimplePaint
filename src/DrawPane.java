import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JPanel;

class DrawPane extends JPanel implements MouseListener  {
	
	public ToolsPane toolsPane;
	static int i = 1;
	//public Vector<MyShape> shapes = new Vector<MyShape>();
	
	public DrawPane() {
		setSize(800,600);
		setBackground(Color.white);	
		this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
	}

	@Override	
	public void paint(Graphics g) {
		//odrysowywujemy na grafice, która jest parametrem
		//a na jakiej grofice rysujemy??
		//przemyśleć.....
		super.paintComponent(g);
		MyModelList model = (MyModelList)toolsPane.list.getModel();
		for(MyShape s : model.getAllElements()) {
			s.draw(g);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}