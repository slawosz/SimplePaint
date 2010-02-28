import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import java.awt.Font;


class FontSelectorRenderer extends JLabel implements ListCellRenderer  {
	
	String font;
	
	public FontSelectorRenderer(String font) {
		this.font = font;
		setOpaque(true);
	    setHorizontalAlignment(CENTER);
	    setVerticalAlignment(CENTER);
	}

	@Override
	public Component getListCellRendererComponent(JList arg0, Object arg1,
			int arg2, boolean arg3, boolean arg4) {
		// TODO Auto-generated method stub
		String font = (String) arg1;
		setText(font);
		setFont(new Font( font, Font.PLAIN, 14));
		//System.err.print("render" + arg1);
		return this;
		
		
	}
	
	
	
}
