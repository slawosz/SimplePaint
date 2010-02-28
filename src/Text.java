import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.JComboBox;
import javax.swing.event.MouseInputListener;


class Text extends MyShape implements MouseInputListener {
	String textToDraw;	
	
	FontSelectorRenderer renderer = new FontSelectorRenderer("Verdana");
		
	public Text(Graphics g, ToolsPane t) {
		super(g,t);
	}
	
	public void draw() {
		int x1 = clickX;
		int y1 = clickY;
		if (mouseClicked) {
			g.drawString(textToDraw ,actualX, actualY);
		}
		else {
			g.drawString(textToDraw ,releaseX, releaseY);
		}	
		System.err.println(textToDraw);
	}
	
	
//	public Shape update() {
//		return this;
//	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.err.println("Klik");
		System.err.println(g);
		clickX = e.getX();
		clickY = e.getY();
		mouseClicked = true;
		drawingAlreadyStarted = true;
		textToDraw = toolsPane.text.getText();
		g.setXORMode(Color.green);
	}

	

	@Override
	public void mouseDragged(MouseEvent e) {
		if(drawingAlreadyStarted) 
			drawingAlreadyStarted = false;
		else 
			draw();
		actualX = e.getX();
		actualY = e.getY();
		draw();
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		mouseClicked = false;
		g.setPaintMode();
		draw();
		System.out.println("hasz: "+ g.hashCode());
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public int height() {
		int height = actualY - clickY;
		return height;
	}
	
	public int width() {
		int width = actualX - clickX;
		return width;
	}
	
	public String toString() {
		return "Text";
	}
	
}
