
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import sun.security.action.GetBooleanAction;


class Drawer implements MouseListener, MouseMotionListener {
	int x1,y1,x2,y2;
	int actualX = 0;
	int actualY = 0;
	int previousX = 0;
	int previousY = 0;
	DrawPane drawPane;
	ToolsPane toolsPane;
	Graphics g;
	boolean mouseClicked = false;
	//Shape shape = new Line();
	MyShape currentShape;
	boolean isShiftPressed = false;
	
	public Drawer(DrawPane drawPane, ToolsPane toolsPane) {
		this.toolsPane = toolsPane;
		this.drawPane = drawPane;
	}
	
	public Drawer(DrawPane drawPane) {
		this.drawPane = drawPane;
	}
	
	public void draw(Shape s) {
		if (s instanceof Line) {
			Line line = (Line)s;
			//line.draw(line.x1,line.y1, line.x2, line.y2);
			line.draw();
		}
		if (s instanceof Oval) {
			Oval oval = (Oval)s;
			//oval.draw(oval.x1,oval.y1, oval.x2, oval.y2,isShiftPressed);
			oval.draw();
		}
	}

	//e przechowuje info o evencie
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		x1 = e.getX();
		y1 = e.getY();
		if (g == null)
			g = drawPane.getGraphics();
		System.err.println("Pressed at " + x1 + ", " + y1);
		mouseClicked = true;
		System.err.println(mouseClicked);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mouseClicked = false;
		draw(shape, x1, y1, actualX, actualY);
		//g.setXORMode(drawPane.getBackground());
		Shape newShape = createShape(shape);
		drawPane.shapes.add(newShape);
//		czy bez cast będzie działało?NIE
//		dlaczego tu jest cast
		MyModelList m = (MyModelList)drawPane.toolsPane.list.getModel();
		m.add(m.getSize(),newShape);
		System.err.println("Released at " + x2 + ", " + y2);
		System.err.println(mouseClicked);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		actualX = e.getX();
		actualY = e.getY();
		if (mouseClicked) {
			g.setXORMode(drawPane.getBackground());
			draw(shape, x1, y1, previousX, previousY);
			g.setPaintMode();
			previousX = actualX;
			previousY = actualY;
			isShiftPressed = e.isShiftDown();
			draw(shape, x1, y1, actualX, actualY);
			//
			System.out.println("Aktualny kształt to " + shape);
		}
		
	}

	private void draw(Shape shape, int startX, int startY, int endX, int endY) {
		if (shape instanceof Line) {
			g.drawLine(startX, startY, endX, endY);
		}
		if (shape instanceof Oval) {
			g.drawOval(startX, startY, endX, endY);
		}
		if (shape instanceof Text) {
			g.drawRect(startX, startY, endX, endY);
		}
		if (shape instanceof Point) {
			g.fillOval(startX + 3, startY + 3, startX, startY);
		}
//		if (shape instanceof Line) {
//			
//		}
		
	}
	
	private Shape createShape(Shape shape) {
		if (shape instanceof Line) {
			return new Line(x1, y1, actualX, actualY,1,this);
		}
		if (shape instanceof Oval) {
			return new Oval(x1, y1, actualX, actualY,1,this);
		}
		if (shape instanceof Text) {
			return new Line(x1, y1, actualX, actualY,1,this);
		}
		if (shape instanceof Point) {
			return new Point(x1, y1, 6,this);
		}
		return null;
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
	}

	public String toString() {
		return "This is Drawer";
	}
}
