package homework3.android;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.util.Log;

public class SelectionHandles extends SimpleGroup implements Selectable {
	static final int HANDLE_SIZE = 10;
	private boolean m_interimSelected = false;
	private boolean m_selected = false;
	private RectF m_childrenBoundaryRect = new RectF();

	private Paint m_selectionPaint;
	{
		m_selectionPaint = new Paint();
		m_selectionPaint.setStyle(Style.FILL);
		m_selectionPaint.setColor(Color.GRAY);
	}
	
	RectF m_drawRect = new RectF();



	public SelectionHandles(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public void resizeChild(GraphicalObject child) {
		resizeToChildren();
	}

	@Override
	protected void updateBounds() {
		// update the transform as well
		m_transform.setTranslate(m_x, m_y);
		
		m_boundaryRect.x = m_x;
		m_boundaryRect.y = m_y;
		m_boundaryRect.width = m_width;
		m_boundaryRect.height = m_height;
		
		m_clipPath.reset();
		m_clipPath.addRect(new RectF(0, 0, m_width, m_height), Direction.CCW);
	}

	private void setCornerRect(boolean bottom, boolean right)
	{
		float left = m_childrenBoundaryRect.left;
		float top = m_childrenBoundaryRect.top;
		if(bottom)
		{
			top = m_childrenBoundaryRect.bottom - HANDLE_SIZE;
		} 
		
		if(right)
		{
			left = m_childrenBoundaryRect.right - HANDLE_SIZE;
		} 	
		
		m_drawRect.left = left;
		m_drawRect.top = top;
		m_drawRect.bottom = top + HANDLE_SIZE;
		m_drawRect.right = left + HANDLE_SIZE;
	}
	
	@Override
	public boolean contains(int x, int y) {
		int relx = (int) (x - m_boundaryRect.x - m_childrenBoundaryRect.left);
		int rely = (int) (y - m_boundaryRect.y - m_childrenBoundaryRect.top);
		
		return relx >= 0 && rely >= 0 && relx < m_childrenBoundaryRect.width() && rely < m_childrenBoundaryRect.height();
	}
	
	@Override
	public void doDraw(Canvas graphics, Path clipShape) {
		super.doDraw(graphics, clipShape);
		

		
		
		graphics.save();

		graphics.clipPath(clipShape);
		graphics.concat(m_transform);
		
//		Paint dbgPaint = new Paint();
//		dbgPaint.setStyle(Style.STROKE);
//		dbgPaint.setStrokeWidth(3);
//		dbgPaint.setColor(Color.MAGENTA);
//		graphics.drawRect(m_childrenBoundaryRect, dbgPaint);
		
		if(m_interimSelected)
		{
			// TODO: draw the entire rect inside the region, offsetting corectly
			m_selectionPaint.setColor(Color.GRAY);
			drawCorners(graphics);
		}
		else if(m_selected)
		{
			m_selectionPaint.setColor(Color.BLACK);
			drawCorners(graphics);
		}
		
		graphics.restore();
		
	}
	
	private void drawCorners(Canvas graphics)
	{
		for(int i = 0; i < 4; i++)
		{
				setCornerRect(i / 2 == 0, i % 2 == 0);
				graphics.drawRect(m_drawRect, m_selectionPaint);	
		}
	}

	@Override
	public void addChild(GraphicalObject child)
			throws AlreadyHasGroupRunTimeException {
		super.addChild(child);
		fitToChildren();
		resizeToChildren();
	}
	
	private void fitToChildren()
	{
		m_childrenBoundaryRect.left = Float.MAX_VALUE;
		m_childrenBoundaryRect.top = Float.MAX_VALUE;
		m_childrenBoundaryRect.right = 0;
		m_childrenBoundaryRect.bottom = 0;
		for (GraphicalObject child : getChildren()) {
			BoundaryRectangle br = child.getBoundingBox();
			if(br.x < m_childrenBoundaryRect.left) m_childrenBoundaryRect.left = br.x;
			if(br.x + br.width > m_childrenBoundaryRect.right) m_childrenBoundaryRect.right = br.x + br.width;
			if(br.y < m_childrenBoundaryRect.top) m_childrenBoundaryRect.top = br.y;
			if(br.y + br.height > m_childrenBoundaryRect.bottom) m_childrenBoundaryRect.bottom = br.y + br.height;
		}
	}
	
	@Override
	public void removeChild(GraphicalObject child) {
		super.removeChild(child);
		resizeToChildren();
	}
	
	@Override
	public void setInterimSelected(boolean interimSelected) 
	{
		m_interimSelected = interimSelected;
		doDamage();
	}

	@Override
	public boolean isInterimSelected() {
		return m_interimSelected;
	}

	@Override
	public void setSelected(boolean selected) {
		m_selected = selected;
		doDamage();
	}

	@Override
	public boolean isSelected() {
		return m_selected;
	}

}
