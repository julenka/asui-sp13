package homework2.android;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.Log;

public class FilledRect extends GraphicalObjectBase {

	protected Rect m_rect = new Rect();
	
	public FilledRect() 
	{
		this(0,0,10,10, Color.BLACK);
	}
	
	public FilledRect(int x, int y, int width, int height, int color)
	{
		m_paint.setStyle(Style.FILL);
		m_rect.left = x;
		m_rect.top = y;
		m_rect.right = x + width;
		m_rect.bottom = y + height;
		m_paint.setColor(color);
		boundsChanged();
	}
	
	// Getters and setters

	@Override
	public void draw(Canvas graphics, Path clipShape) {
		graphics.save();
		graphics.clipPath(clipShape);
		// TODO: fix to match specification (outline should be entirely in the box)
		graphics.drawRect(m_rect, m_paint);
		graphics.restore();   
	}

	@Override
	public void moveTo(int x, int y) {
		// todo: check this
		int dx = x - m_rect.left;
		m_rect.left = x;
		m_rect.right += dx;
		int dy = y - m_rect.top;
		m_rect.top = y;
		m_rect.bottom += dy;
		boundsChanged();
		
	}

	// todo: pull up to graphicalobjectbase
	protected void updateBounds()
	{
		// TODO: fix to match specification (outline should be entirely in the box)
		m_boundaryRect.x =  m_rect.left - (int)(m_paint.getStrokeWidth() / 2);
		m_boundaryRect.y = m_rect.top - (int)(m_paint.getStrokeWidth() / 2);
		m_boundaryRect.width = m_rect.width() + (int)m_paint.getStrokeWidth();
		m_boundaryRect.height = m_rect.height() + (int)m_paint.getStrokeWidth();
	}
	
	// Getters and setters
	public int getX() {
		return m_rect.left;
	}

	protected void setX(int x) {
		int dx = x - m_rect.left;
		m_rect.left = x;
		m_rect.right += dx;
		
		boundsChanged();
	}

	public int getY() {
		return m_rect.top;
	}

	public void setY(int y) {
		int dy = y - m_rect.top;
		m_rect.top = y;
		m_rect.bottom += dy;
		boundsChanged();
	}

	public int getWidth() {
		return m_rect.width();
	}

	public void setWidth(int width) {
		m_rect.right = m_rect.left + width;
		boundsChanged();
	}

	public int getHeight() {
		return m_rect.height();
	}

	public void setHeight(int height) {
		m_rect.bottom = m_rect.top + height;
		boundsChanged();
	}

	public int getColor() {
		return m_paint.getColor();
	}

	public void setColor(int color) {
		m_paint.setColor(color);
		boundsChanged();
	}

}
