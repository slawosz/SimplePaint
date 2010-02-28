import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;


public class MyShape {
	Drawer drawer;
	Graphics g;
	ToolsPane toolsPane;
	Color color;
	//modification color
	Color modColor;
	int previousX;
	int previousY;
	int actualX;
	int actualY;
	int clickX;
	int clickY;
	int releaseX;
	int releaseY;
	boolean mouseClicked = false;
	boolean drawingAlreadyStarted = true;
	boolean isSelectedForModification = false;
	Cursor drawCursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
	Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
	int x1,y1,x2,y2,borderWidth;

	public MyShape(Graphics g) {
		this.g = g;		
	}
	
	public MyShape(Drawer drawer) {
		this.drawer = drawer;
	}
	
	public MyShape(Drawer drawer, Graphics g) {
		this.drawer = drawer;		
	}
	
	public MyShape( Graphics g, ToolsPane tools) {
		this.toolsPane = tools;		
		this.g = g;
	}
	
	public MyShape() {}
	
	public void draw() {
		System.err.println("Rysownik");
	}
	
	public void draw(Color color) {
		System.err.println("Rysownik " + color);
	}
	
	public void draw(Graphics g) {
	}
	
	public void setColor(Color color) {
		g.setColor(color);
		System.err.println("setColor" + color);
	}
	
	protected Color color() {
		if(isSelectedForModification) 
			return modColor;
		else
			return color;
	}
	

}
