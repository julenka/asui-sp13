package homework2.android;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Point;
import android.graphics.RectF;

public class SimpleGroup extends GraphicalObjectBase implements Group {

	private List<GraphicalObject> m_children = new ArrayList<GraphicalObject>();
	
	private int m_x;
	private int m_y;
	private int m_width;
	private int m_height;
	private Path m_clipPath = new Path();
	
	public SimpleGroup() {
		this(0,0,100,100);
	}
	public SimpleGroup (int x, int y, int width,int height)
	{
		m_x = x;
		m_y = y;
		m_width = width;
		m_height = height;
		updateBoundaryRect();
	}
	
	private void updateBoundaryRect()
	{
		// update the transform as well
		m_transform.setTranslate(m_x, m_y);
		
		m_boundaryRect.x = m_x;
		m_boundaryRect.y = m_y;
		m_boundaryRect.width = m_width;
		m_boundaryRect.height = m_height;
		
		// update clip path
		m_clipPath.reset();
		m_clipPath.addRect(new RectF(0,0, m_width, m_height), Direction.CCW);
		doDamage();
	}
	
//	public LayoutGroup (int x, int y, int width, int height,int layout, int offset);  
//	public ScaledGroup (int x,int y,int width,int height,double scaleX,double scaleY);
	@Override
	public void draw(Canvas graphics, Path clipShape) {
		// TODO Auto-generated method stub
		graphics.save();
		graphics.translate(m_x, m_y);
		graphics.clipPath(clipShape);
		for (GraphicalObject child : m_children) {
			// draw to the clipshape of the child
			child.draw(graphics, m_clipPath);
		}
		graphics.restore();

	}

	@Override
	public void moveTo(int x, int y) {
		m_x = x;
		m_y = y;
		updateBoundaryRect();
	}

	@Override
	public void addChild(GraphicalObject child)
			throws AlreadyHasGroupRunTimeException {
		if(child.getGroup() != null)
			throw new AlreadyHasGroupRunTimeException("SimpleGroup addChild: child already has group");
		child.setGroup(this);
		m_children.add(child);
	}

	@Override
	public void removeChild(GraphicalObject child) {
		child.setGroup(null);
		m_children.remove(child);

	}

	@Override
	public void resizeChild(GraphicalObject child) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bringChildToFront(GraphicalObject child) {
		// TODO: what if the child isn't in the list of children?
		m_children.remove(child);
		// add it to the back
		m_children.add(child);
	}

	@Override
	public void resizeToChildren() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void damage(BoundaryRectangle rectangle) {
		// TODO Do I need to do anything else here?
		if(m_group != null)
		{
			m_group.damage(rectangle);
		}
	}

	@Override
	public List<GraphicalObject> getChildren() {
		return m_children;
	}

	@Override
	public Point parentToChild(Point pt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point childToParent(Point pt) {
		// TODO Auto-generated method stub
		return null;
	}
	public int getX() {
		return m_x;
	}
	public void setX(int x) {
		m_x = x;
		updateBoundaryRect();
	}
	public int getY() {
		return m_y;
	}
	public void setY(int y) {
		m_y = y;
		updateBoundaryRect();
	}
	public int getWidth() {
		return m_width;
	}
	public void setWidth(int width) {
		m_width = width;
		updateBoundaryRect();
	}
	public int getHeight() {
		return m_height;
	}
	public void setHeight(int height) {
		m_height = height;
		updateBoundaryRect();
	}

}
