package homework3.android;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.graphics.Path.Direction;
import android.graphics.Path;

public class SelectionHandles extends SimpleGroup implements Selectable {
	static final int HANDLE_SIZE = 10;
	private boolean m_interimSelected = false;
	private boolean m_selected = false;

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
		int left = m_x;
		int top = m_y;
		if(bottom)
		{
			top = m_y + m_boundaryRect.height - HANDLE_SIZE;
		} 
		
		if(right)
		{
			left = m_x + m_boundaryRect.width - HANDLE_SIZE;
		} 	
		
		m_drawRect.left = left;
		m_drawRect.top = top;
		m_drawRect.bottom = top + HANDLE_SIZE;
		m_drawRect.right = left + HANDLE_SIZE;
	}
	
	@Override
	public void doDraw(Canvas graphics, Path clipShape) {
		super.doDraw(graphics, clipShape);
		
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
