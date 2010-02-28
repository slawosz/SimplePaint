import java.awt.Graphics;


class Shape2D extends MyShape {
	
	
	public Shape2D(Graphics g, ToolsPane t) {
		super(g, t);
	}
	
	public void drawModificators() {
		
//		return;
//		if(isSelectedForModification) {
//			drawResizer();
//			drawPositioner();
//		}
	}
	
	protected void drawResizer() {
		g.fillRect(x2-10, y2-10, 10, 10);
	}
	
	protected void drawPositioner() {
		g.drawRect(x1, y1, 10, 10);
	}
	
}
