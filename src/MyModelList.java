import java.util.Vector;

import javax.swing.AbstractListModel;



class MyModelList extends AbstractListModel {

	//Vector<String> v = new Vector<String>();
	Vector<MyShape> v = new Vector<MyShape>();
	
	public MyModelList() {
		
	}
	
	public Vector<MyShape> getAllElements() {
		return v;
	}
	
	@Override
	public MyShape getElementAt(int index) {
		return v.elementAt(index);
		
	}
	
	public void remove(int index) {
		
	}

	@Override
	public int getSize() {
		
		return v.size();
	}
	
	public void add(int index, MyShape s) {
		v.insertElementAt(s, index);
		//a co robi to?
		fireIntervalAdded(this, index, index);
	}
	
}