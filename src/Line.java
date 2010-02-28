import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;


class Line extends MyShape implements MouseInputListener {
	 

//	public Line(int x1, int y1, int x2, int y2, int width, Drawer drawer) {
//		super(drawer);
//		this.g = drawer.g;
//		this.x1 = x1;
//		this.y1 = y1;
//		this.x2 = x2;
//		this.y2 = y2;
//		this.width = width;
//	}
	
	public Line(Graphics g, ToolsPane tools) {
		super(g, tools);
		
	}
	
	public void draw(int x1, int y1, int x2, int y2 ) {
		g.drawLine(x1, y1, x2, y2);
	}
	
	public void draw() {
		int x1 = clickX;
		int y1 = clickY;
		if (mouseClicked) {
			draw(x1, y1, actualX, actualY);
		}
		else {
			draw(x1, y1, releaseX, releaseY);
		}
	}
	
	public String toString() {
		return "Linia";
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		g.setXORMode(Color.green);
		draw(10,10,300,300);
		
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
	public void mousePressed(MouseEvent e) {
		System.err.println("Klik");
		System.err.println(g);
		clickX = e.getX();
		clickY = e.getY();
		mouseClicked = true;
		drawingAlreadyStarted = true;
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
		releaseX = actualX;
		releaseY = actualY;
		System.err.println("Pierwszy klik:" + clickX);
		System.err.println(this);
		g.setPaintMode();
		draw();
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
