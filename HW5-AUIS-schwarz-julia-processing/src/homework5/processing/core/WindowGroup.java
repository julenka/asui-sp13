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
import processing.core.PImage;
import processing.core.PMatrix2D;

public class WindowGroup extends PApplet implements Group  {
	PImage m_canvasImg;
	PGraphics m_canvas;
	boolean m_screenDirty ;
	Rectangle m_clipShape;
	LinkedList<GraphicalObject> children = new LinkedList<GraphicalObject> ();

	Rectangle m_damagedRegion = new Rectangle();
	@Override
	public void setup() {
		// TODO update back buffer when the size of the screen changes.
		size(720, 1080, JAVA2D);
		m_canvas = createGraphics(width, height, JAVA2D);
		m_canvas.background(255);
		updateCanvas();
		m_clipShape = new Rectangle(0, 0, width, height);
	}

	@Override
	public void draw() {
		// TODO (extra) If the screen is damaged, redraw all parts that intersect clipping rect
		if(m_screenDirty) {
			updateCanvas();
			m_screenDirty = false;
			m_damagedRegion = null;
		}

		// Blit the saved buffer to screen
		image(m_canvasImg, 0, 0);
	}

	void updateCanvas() {
		// draw the buffer
		m_canvas.beginDraw();
		m_canvas.pushStyle();
		// clear damaged region
		m_canvas.background(m_canvas.backgroundColor);
		m_canvas.noStroke();
		m_canvas.rect(m_damagedRegion.x, m_damagedRegion.y, m_damagedRegion.width, m_damagedRegion.height);
		
		// draw the region that has been damaged
		draw(m_canvas, m_clipShape);
		m_canvas.popStyle();
		m_canvas.endDraw();
		m_canvasImg = m_canvas.get(0, 0, m_canvas.width, m_canvas.height);
	}
	
	/// Group interface

	@Override
	public void draw(PGraphics graphics, Shape clipShape) {
		// TODO: close graphics object
		// TODO: support clipping properly
		for (GraphicalObject child : children) {
			child.draw(graphics, clipShape);
		}
	}

	@Override
	public BoundaryRectangle getBoundingBox() {
		return new BoundaryRectangle(0, 0, displayWidth, displayHeight);
	}

	@Override
	public void moveTo(int x, int y) {
		// nop
	}

	@Override
	public Group getGroup() {
		return null;
	}

	@Override
	public void setGroup(Group group) {
		// nop
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
		if(!m_screenDirty){
			m_screenDirty = true;
			m_damagedRegion = new Rectangle(rectangle);
		} else {
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


}
