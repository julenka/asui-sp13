package homework5.processing.graphicalobject;

import homework5.processing.core.BoundaryRectangle;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import processing.core.PGraphics;
import processing.core.PMatrix2D;
import processing.core.PVector;

/**
 * Implements a SimpleGroup according to specification. Also acts as a base class for all other specialized groups
 * @author julenka
 *
 */
public class SimpleGroup extends GraphicalObjectBase implements Group {

	// children in this group
	protected List<GraphicalObject> m_children = new ArrayList<GraphicalObject>();
	
	// coordinates and dimensions
	protected int m_x;
	protected int m_y;
	protected int m_width;
	protected int m_height;
	protected Rectangle m_clipRegion = new Rectangle();
	
	public SimpleGroup() {
		this(0,0,100,100);
	}
	public SimpleGroup (int x, int y, int width,int height)
	{
		m_x = x;
		m_y = y;
		m_width = width;
		m_height = height;
		
		boundsChanged();
	}
	
	protected void updateBounds()
	{
		m_boundaryRect.x = m_x;
		m_boundaryRect.y = m_y;
		m_boundaryRect.width = m_width;
		m_boundaryRect.height = m_height;
		m_transform.reset();
		m_transform.translate(m_x, m_y);
		m_clipRegion.width = m_width;
		m_clipRegion.height = m_height;
	}
	

	@Override
	public void doDraw(PGraphics graphics) {
		graphics.applyMatrix(m_transform);
		// draw the rectangle to redraw
		for (GraphicalObject child : m_children) {
			// draw to the clipshape of the child
			child.draw(graphics, m_clipRegion);
		}
	}

	@Override
	public void moveTo(int x, int y) {
		int oldX = m_x;
		int oldY = m_y;
		m_x = x;
		m_y = y;
		boundsChanged();
		notifyPropertyChanged("x", oldX, x);
		notifyPropertyChanged("y", oldY, y);
	}

	@Override
	public void addChild(GraphicalObject child)
			throws AlreadyHasGroupRunTimeException {
		if(child.getGroup() != null)
			throw new AlreadyHasGroupRunTimeException("SimpleGroup addChild: child already has group");
		child.setGroup(this);
		m_children.add(child);
		
		// damage the region defined by the child
		damage(child.getBoundingBox());
	}

	@Override
	public void removeChild(GraphicalObject child) {
		child.setGroup(null);
		m_children.remove(child);
		doDamage();
	}

	@Override
	public void resizeChild(GraphicalObject child) {
	}

	@Override
	public void bringChildToFront(GraphicalObject child) {
		m_children.remove(child);
		// add it to the back
		m_children.add(child);
		// damage here.
		doDamage();
	}

	@Override
	public void resizeToChildren() {
		int w = 0;
		int h = 0;
		for (GraphicalObject child : m_children) {
			BoundaryRectangle r = child.getBoundingBox();
			int right = r.x + r.width;
			int bottom = r.y + r.height;
			if(right > w) w = right;
			if(bottom > h) h = bottom;
		}
		m_width = w;
		m_height = h;
		boundsChanged();
	}

	
	public void damage(BoundaryRectangle rectangle) {
		if(m_group != null)
		{
			Rectangle container = new Rectangle(0,0,m_width, m_height);
			// only damage if the new boundary rectangle is within the bounds of our control
			if(container.intersects(rectangle))
			{
				// apply the group's current transform to the damaged area before passing damage up
				PVector[] in = new PVector[2];
				in[0] = new PVector(rectangle.x, rectangle.y);
				in[1] = new PVector(rectangle.x + rectangle.width, rectangle.y + rectangle.height);
				PVector[] out = new PVector[]{new PVector(), new PVector()};
				for (int i = 0; i < out.length; i++) {
					m_transform.mult(in[i], out[i]);
				}
				m_group.damage(new BoundaryRectangle((int)(out[0].x), (int)(out[0].y), 
						(int)(out[1].x - out[0].x), (int)(out[1].y - out[0].y)));
			}
		}
	}

	@Override
	public List<GraphicalObject> getChildren() {
		return m_children;
	}

	/**
	 * Applies a transform to a given point, returns transformed point
	 */
	private Point applyTransform(Point pt, PMatrix2D tfrm)
	{
		PVector in = new PVector(pt.x, pt.y);
		PVector out = new PVector();
		tfrm.mult(in, out);
		return new Point((int)(out.x), (int)(out.y));
	}

	
	@Override
	public Point parentToChild(Point pt) {
		PMatrix2D inv = new PMatrix2D(m_transform);
		if(inv.invert())
		{
			return applyTransform(pt, inv);
		}
		return null;
	}

	@Override
	public Point childToParent(Point pt) {
		return applyTransform(pt, m_transform);
	}
	public int getX() {
		return m_x;
	}
	public void setX(int x) {
		int oldX = m_x;
		m_x = x;
		boundsChanged();
		notifyPropertyChanged("X", oldX, x);
	}
	public int getY() {
		return m_y;
	}
	public void setY(int y) {
		int oldY = m_y;
		m_y = y;
		boundsChanged();
		notifyPropertyChanged("Y", oldY, y);
	}
	public int getWidth() {
		return m_width;
	}
	public void setWidth(int width) {
		int oldWidth = m_width;
		m_width = width;
		boundsChanged();
		notifyPropertyChanged("Width", oldWidth, width);
	}
	public int getHeight() {
		return m_height;
	}
	public void setHeight(int height) {
		int oldHeight = m_height;
		
		m_height = height;
		boundsChanged();
		
		notifyPropertyChanged("Height", oldHeight, height);
	}


}

