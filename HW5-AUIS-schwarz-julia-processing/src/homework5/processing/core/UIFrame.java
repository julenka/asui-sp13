package homework5.processing.core;

import homework5.processing.graphicalobject.GraphicalObject;
import homework5.processing.graphicalobject.Group;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.LinkedList;
import java.util.List;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PMatrix2D;

public class UIFrame implements Group {
	PApplet parent;
	
	boolean m_screenDirty = false;
	Rectangle m_boundaryRectangle;
	protected LinkedList<GraphicalObject> children = new LinkedList<GraphicalObject> ();

	Rectangle m_damagedRegion = new Rectangle(0,0,-1,-1);
	
	Object m_dirtyLock = new Object();
	
	public UIFrame(PApplet parent) {
		this.parent = parent;
		m_boundaryRectangle = new Rectangle(0, 0, parent.width, parent.height);
	}
	
	/**
	 * Parent sketch should call draw when the want the UI to get drawn.
	 * Allows you to draw UI at any layer you want.
	 */
	public void draw() {
		PGraphics g = parent.g;
		// TODO verify that this lock is actually necessary
		synchronized (m_dirtyLock) {
			if(m_screenDirty) {
				g.fill(g.backgroundColor);
				g.noStroke();
				g.rect(m_damagedRegion.x, m_damagedRegion.y, m_damagedRegion.width, m_damagedRegion.height);
				g.pushStyle();
				draw(g, m_boundaryRectangle);
				g.popStyle();
				m_damagedRegion = new Rectangle(0,0,-1,-1);
				m_screenDirty = false;
			}
		}
	}

	/**
	 * For testing
	 */
	public void clearChildren(){
		children.clear();
		damage(getBoundingBox());
	}
	
	/// Group interface
	@Override
	public void draw(PGraphics graphics, Shape clipShape) {
		for (GraphicalObject child : children) {
			child.draw(graphics, clipShape);
		}
	}

	@Override
	public BoundaryRectangle getBoundingBox() {
		return new BoundaryRectangle(0, 0, parent.width, parent.height);
	}

	@Override
	public void moveTo(int x, int y) {
	}

	@Override
	public Group getGroup() {
		return null;
	}

	@Override
	public void setGroup(Group group) {
	}

	@Override
	public void setAffineTransform(PMatrix2D af) {

	}

	@Override
	public PMatrix2D getAffineTransform() {
		return null;
	}

	@Override
	public void addChild (GraphicalObject child) {
		child.setGroup (this);
		children.add (child);	
		damage (child.getBoundingBox());
	}
	@Override
	public void removeChild (GraphicalObject child) {
		child.setGroup(null);
		children.remove (child);	
		damage (child.getBoundingBox());
	}
	@Override
	public void bringChildToFront (GraphicalObject child) {
		children.remove (child);
		children.add (child);
		damage(getBoundingBox());
	}
	@Override
	public void resizeToChildren () {
		// not supported for now
	}

	@Override
	public void damage(BoundaryRectangle rectangle) {
		synchronized (m_dirtyLock) {
			m_screenDirty = true;			
			m_damagedRegion.add(rectangle);
		}
	}

	@Override
	public List<GraphicalObject> getChildren() {
		return children;
	}

	@Override
	public Point parentToChild(Point pt) {
		return pt;
	}

	@Override
	public Point childToParent(Point pt) {
		return pt;
	}

	@Override
	public void resizeChild(GraphicalObject child) {
		damage(child.getBoundingBox());
	}

	@Override
	public boolean contains(int x, int y) {
		int relx = x - m_boundaryRectangle.x;
		int rely = y - m_boundaryRectangle.y;
		
		return relx >= 0 && rely >= 0 && relx < m_boundaryRectangle.width && rely < m_boundaryRectangle.height;	}

}
