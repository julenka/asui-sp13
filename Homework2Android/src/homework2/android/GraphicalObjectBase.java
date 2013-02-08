package homework2.android;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.Log;

public class GraphicalObjectBase implements GraphicalObject {

	protected Group m_group;
	protected Matrix m_transform = new Matrix();
	protected BoundaryRectangle m_boundaryRect = new BoundaryRectangle();
	protected Paint m_paint = new Paint();
	
	private RectF m_clipBounds = new RectF();
	public GraphicalObjectBase() {
		// TODO Auto-generated constructor stub
	}

	
	protected RectF boundaryRectangleToRect(BoundaryRectangle r)
	{
		return new RectF(r.x, r.y, r.x + r.width, r.y + r.height);
	}
	
	protected void boundsChanged()
	{
		int oldWidth = m_boundaryRect.width;
		int oldHeight = m_boundaryRect.height;
		doDamage();
		updateBounds();
		doDamage();
		if(m_boundaryRect.width != oldWidth || m_boundaryRect.height != oldHeight)
			doResized();
	}
	
	protected void updateBounds()
	{
		// implement updateBounds here
	}
	
	protected void doResized()
	{
		if(m_group != null)
			m_group.resizeChild(this);
	}
	
	protected void doDamage()
	{
		if(m_group != null)
			m_group.damage(m_boundaryRect);		
	}
	
	protected void doDraw(Canvas graphics, Path clipShape)
	{
		Log.v("GraphicalObject", "Oops, you forgot to implement doDraw for class " + this.getClass());
		// override this
		// nop
	}
	
	@Override
	public void draw(Canvas graphics, Path clipShape) {
		// do not draw if the boundaryrect is completely out of the clipShape
		clipShape.computeBounds(m_clipBounds, true);
		RectF boundary = boundaryRectangleToRect(m_boundaryRect);
		if(!RectF.intersects(boundary, m_clipBounds)) return;
		
		doDraw(graphics, clipShape);
		
	}

	@Override
	public BoundaryRectangle getBoundingBox() {
		return new BoundaryRectangle(m_boundaryRect);
	}

	@Override
	public void moveTo(int x, int y) {
		// nop
	}

	@Override
	public Group getGroup() {
		return m_group;
	}

	@Override
	public void setGroup(Group group) {
		m_group = group;
	}

	@Override
	public boolean contains(int x, int y) {
		int relx = x - m_boundaryRect.x;
		int rely = y - m_boundaryRect.y;
		return relx >= 0 && rely >= 0 && relx < m_boundaryRect.width && rely < m_boundaryRect.height;
	}

	@Override
	public void setAffineTransform(Matrix af) {
		m_transform = af;

	}

	@Override
	public Matrix getAffineTransform() {
		return m_transform;
	}

	// TODO: setX, setY
	
}
