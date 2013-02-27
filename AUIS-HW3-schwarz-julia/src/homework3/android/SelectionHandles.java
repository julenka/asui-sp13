package homework3.android;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.graphics.Path.Direction;
import android.graphics.Path;

public class SelectionHandles extends SimpleGroup implements Selectable {
	static final int HANDLE_SIZE = 5;
	private boolean m_interimSelected = false;
	private boolean m_selected = false;

	private static Paint m_selectionPaint;

	static
	{
		m_selectionPaint = new Paint();
		m_selectionPaint.setStyle(Style.STROKE);
		m_selectionPaint.setColor(Color.GRAY);
		m_selectionPaint.setStrokeWidth(HANDLE_SIZE);
	}
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

	@Override
	public void doDraw(Canvas graphics, Path clipShape) {
		super.doDraw(graphics, clipShape);
		if(m_interimSelected)
		{
			m_selectionPaint.setColor(Color.GRAY);
			graphics.drawRect(boundaryRectangleToRect(m_boundaryRect), m_selectionPaint);	
		}
		else if(m_selected)
		{
			m_selectionPaint.setColor(Color.BLACK);
			graphics.drawRect(boundaryRectangleToRect(m_boundaryRect), m_selectionPaint);
		}

	}

	@Override
	public void addChild(GraphicalObject child)
			throws AlreadyHasGroupRunTimeException {
		super.addChild(child);
		resizeToChildren();
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
