import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.event.MouseInputListener;

//implements mouseListener
//klasa polygon

//AffineTransform
class Oval extends MyShape implements MouseInputListener {
	int x1,y1,x2,y2,borderWidth;
	
	
//	public Oval(int x1, int y1, int x2, int y2, int width, Draw drawer) {
//		super(drawer);
//		this.g = drawer.g;
//		this.x1 = x1;
//		this.y1 = y1;
//		this.x2 = x2;
//		this.y2 = y2;
//		this.borderWidth = width;
//	}
	public Oval(Oval r) {
		super(r.g, r.toolsPane);
		this.x1 = r.x1;
		this.x2 = r.x2;
		this.y1 = r.y1;
		this.y2 = r.y2;
		this.borderWidth = r.borderWidth;
	}
	
	public Oval(Graphics g) {
		this.g = g;
	}
	
	public Oval(Graphics g, ToolsPane tools) {
		super(g, tools);
	}
	
	public Oval(Rectangle r) {
		super(r.g, r.toolsPane);
		this.x1 = r.x1;
		this.x2 = r.x2;
		this.y1 = r.y1;
		this.y2 = r.y2;
		this.borderWidth = r.borderWidth;
	}
	
	public void draw() {
		if (mouseClicked) {
			draw(x1, y1, actualX, actualY);
		}
		else {
			if(!isSelectedForModification)
			{
				g.setPaintMode();
			}
			g.setColor(color());
			draw(x1, y1, x2, y2);
		}	
		
	}
	
	public void draw(Graphics g) {
		this.g = g;
		draw();
	}
	
	public void draw(int x1, int y1, int x2, int y2) {
		
		int width = Math.abs( x2 - x1);
		int height = Math.abs(y2 - y1);
		
//		if (isShiftPressed) {
//			if (width > height) {
//				height = width;
//			}
//			else {
//				width = height;
//			}
//		}
	
		if ((x2 - x1 < 0) && (y2 - y1 < 0)) {
			g.drawOval(x2, y2, width, height);
			return;
		}
		if (x2 - x1 < 0) {
			g.drawOval(x2, y1, width, height);
			return;
		}
		if (y2 - y1 < 0) {
			g.drawOval(x1, y2, width, height);
			return;
		}
		
		g.drawOval(x1, y1, width, height);
		
	}
	
	public void draw(Color color) {
		Color actualColor = g.getColor();
		g.setColor(color);
		draw();
		//g.setColor(actualColor);
	}

//	public String toString() {
//		return "Koło";
//	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (isSelectedForModification) {
			g.setXORMode(Color.green);
			draw();
			int x = e.getX();
			int y = e.getY();
			if(e.getButton() == MouseEvent.BUTTON1) {
				x2 = x;
				y2 = y;
			} else {
				x1 = x;
				y1 = y;
			}
			g.setPaintMode();
			g.setColor(color());
			toolsPane.drawPane.repaint();
		}
		else {
			x1 = e.getX();
			y1 = e.getY();
			actualX = e.getX();
			actualY = e.getY();
		}
		
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
		if (isSelectedForModification)
			return;
		x1 = e.getX();
		y1 = e.getY();
		mouseClicked = true;
		drawingAlreadyStarted = true;
		g.setXORMode(Color.white);
		toolsPane.drawPane.setCursor(drawCursor);
	}

	

	@Override
	public void mouseDragged(MouseEvent e) {
		if (isSelectedForModification)
			return;
		System.err.println("TEGO NIE POWINNO BYĆ");
		if(drawingAlreadyStarted) 
			drawingAlreadyStarted = false;
		else 
			draw();
		actualX = e.getX();
		actualY = e.getY();
		if (e.isShiftDown())
			computeCircle();
		draw();
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if (isSelectedForModification)
			return;
		System.err.println("TEGO NIE POWINNO BYĆ");
		mouseClicked = false;
		x2 = actualX;
		y2 = actualY;
		System.err.println("Pierwszy klik:" + clickX);
		System.err.println(this);
		//g.setPaintMode();
		//g.setColor(Color.red);
		
		draw();
		this.color = g.getColor();
		
		toolsPane.drawn(new Oval(this));
		toolsPane.drawPane.setCursor(cursor);
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
	
	private void computeCircle() {
		if (height() >= 0 && width() >= 0) {
			if(height() > width())
				actualY = clickY + width();
			else
				actualX = clickX + height();
		}
		if (height() < 0 && width() < 0) {
			if(Math.abs(height()) > Math.abs(width()))
				actualY = clickY + width();
			else
				actualX = clickX + height();	
		}
		if (height() >= 0 && width() < 0) {
			if(height() > Math.abs(width()))
				actualY = clickY + Math.abs(width());
			else
				actualX = clickX - height();	
		}
		if (height() < 0 && width() >= 0) {
			if(Math.abs(height()) > width())
				actualY = clickY - width();
			else
				actualX = clickX + Math.abs(height());	
		}
			
	}
	
	public String toString() {
		return "Koło";
	}
	
	public void setColor(Color color) {
		g.setColor(color);
		System.err.println("setColor" + color);
	}
//	public Shape update() {
//		return ()this;
//	}
}
