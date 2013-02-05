package homework2.android;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;

public class GraphicalObjectBase implements GraphicalObject {

	protected Group m_group;
	protected Matrix m_transform;
	protected BoundaryRectangle m_boundaryRect = new BoundaryRectangle();
	protected Paint m_paint = new Paint();
	
	public GraphicalObjectBase() {
		// TODO Auto-generated constructor stub
	}

	protected void doDamage()
	{
		if(m_group != null)
			m_group.damage(m_boundaryRect);		
	}
	
	@Override
	public void draw(Canvas graphics, Path clipShape) {
		// nop
	}

	@Override
	public BoundaryRectangle getBoundingBox() {
		return m_boundaryRect;
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

}
