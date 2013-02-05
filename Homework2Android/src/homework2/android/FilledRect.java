package homework2.android;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Rect;

public class FilledRect extends GraphicalObjectBase {

	protected Rect m_rect = new Rect();
	
	public FilledRect() 
	{
		this(0,0,10,10, Color.BLACK);
	}
	
	public FilledRect(int x, int y, int width, int height, int color)
	{
		m_paint.setStyle(Style.FILL);
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
		setColor(color);
	}
	
	// Getters and setters
	
	@Override
	public void draw(Canvas graphics, Path clipShape) {
		graphics.clipPath(clipShape);
		graphics.drawRect(m_rect, m_paint);
	}

	@Override
	public void moveTo(int x, int y) {
		// todo: check this
		setX(x);
		setY(y);
	}

	protected void updateAndDamage()
	{
		m_boundaryRect.x =  m_rect.left - (int)(m_paint.getStrokeWidth() / 2);
		m_boundaryRect.y = m_rect.top - (int)(m_paint.getStrokeWidth() / 2);
		m_boundaryRect.width = m_rect.width() + (int)m_paint.getStrokeWidth();
		m_boundaryRect.height = m_rect.height() + (int)m_paint.getStrokeWidth();
		
		doDamage();
	}
	
	// Getters and setters
	public int getX() {
		return m_rect.left;
	}

	protected void setX(int x) {
		int dx = x - m_rect.left;
		m_rect.left = x;
		m_rect.right += dx;
		updateAndDamage();
	}

	public int getY() {
		return m_rect.top;
	}

	public void setY(int y) {
		int dy = y - m_rect.top;
		m_rect.top = y;
		m_rect.bottom += dy;
		updateAndDamage();
	}

	public int getWidth() {
		return m_rect.width();
	}

	public void setWidth(int width) {
		m_rect.right = m_rect.left + width;
		updateAndDamage();
	}

	public int getHeight() {
		return m_rect.height();
	}

	public void setHeight(int height) {
		m_rect.bottom = m_rect.top + height;
		updateAndDamage();
	}

	public int getColor() {
		return m_paint.getColor();
	}

	public void setColor(int color) {
		m_paint.setColor(color);
		updateAndDamage();
	}

}
