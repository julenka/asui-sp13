package homework2.android;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

public class SimpleGroup extends GraphicalObjectBase implements Group {

	protected List<GraphicalObject> m_children = new ArrayList<GraphicalObject>();
	// TODO: use boundaryrect operations!
	// TODO: don't draw if out of width
	
	protected int m_x;
	protected int m_y;
	protected int m_width;
	protected int m_height;
	protected Path m_clipPath = new Path();
	
	public SimpleGroup() {
		this(0,0,100,100);
	}
	public SimpleGroup (int x, int y, int width,int height)
	{
		m_x = x;
		m_y = y;
		m_width = width;
		m_height = height;
		
		// special case, 
		
		boundsChanged();
	}
	
	protected void updateBounds()
	{
		// update the transform as well
		m_transform.setTranslate(m_x, m_y);
		
		m_boundaryRect.x = m_x;
		m_boundaryRect.y = m_y;
		m_boundaryRect.width = m_width;
		m_boundaryRect.height = m_height;
		
		m_clipPath.reset();
		m_clipPath.addRect(new RectF(0, 0, m_width, m_height), Direction.CCW);
		
	}
	

	@Override
	public void doDraw(Canvas graphics, Path clipShape) {
		Paint dbgPaint = new Paint();
		dbgPaint.setStyle(Style.STROKE);
		dbgPaint.setStrokeWidth(2);
		dbgPaint.setColor(Color.GREEN);
//		graphics.drawPath(clipShape, dbgPaint);
		// update clip path to draw to
		graphics.save();

		graphics.clipPath(clipShape);
		graphics.concat(m_transform);
		dbgPaint.setColor(Color.MAGENTA);
//		graphics.drawPath(m_clipPath, dbgPaint);	
		
		
		// draw the rectangle to redraw
		for (GraphicalObject child : m_children) {
			// draw to the clipshape of the child
			// don't redraw if nothing to redraw
			child.draw(graphics, m_clipPath);
		}
		graphics.restore();
	}

	@Override
	public void moveTo(int x, int y) {
		m_x = x;
		m_y = y;
		boundsChanged();
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
	}

	@Override
	public void resizeChild(GraphicalObject child) {
		// do I need to do anything else here?
		// resizeToChildren();
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
			RectF container = new RectF(0,0,m_width, m_height);
			RectF damagedArea = boundaryRectangleToRect(rectangle);
			
			if(damagedArea.intersect(container))
			{
				m_transform.mapRect(damagedArea);
				m_group.damage(new BoundaryRectangle((int)damagedArea.left, (int)damagedArea.top, (int)damagedArea.width(), (int)damagedArea.height()));
			}

		}
		
	}

	@Override
	public List<GraphicalObject> getChildren() {
		return m_children;
	}

	private Point applyTransform(Point pt, Matrix tfrm)
	{
        // Create new float[] to hold the rotated coordinates
        float[] pts = new float[2];

        // Initialize the array with our Coordinate
        pts[0] = pt.x;
        pts[1] = pt.y;

        // Use the Matrix to map the points
        tfrm.mapPoints(pts);
		return new Point((int)pts[0],(int)pts[1]);
	}

	
	@Override
	public Point parentToChild(Point pt) {
		// apply inverse transform
		Matrix inv = new Matrix(m_transform);
		if(m_transform.invert(inv))
		{
			return applyTransform(pt, inv);
		}
		Log.w("SimpleGroup", "Error: cannot invert matrix!");
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
		m_x = x;
		boundsChanged();
	}
	public int getY() {
		return m_y;
	}
	public void setY(int y) {
		m_y = y;
		boundsChanged();
	}
	public int getWidth() {
		return m_width;
	}
	public void setWidth(int width) {
		m_width = width;
		boundsChanged();
	}
	public int getHeight() {
		return m_height;
	}
	public void setHeight(int height) {
		m_height = height;
		boundsChanged();
	}

}

