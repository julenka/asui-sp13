package homework4.android.graphicalobject;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;

/**
 * Class for drawing filled rectangles.
 * @author julenka
 */
public class FilledRect extends GraphicalObjectBase {
	// stores information about our rectangle (x,y,width, height)
	protected Rect m_rect = new Rect();
	
	// stores the actual rectangle we will draw (which can have different dimensions).
	private RectF m_rectToDraw = new RectF();
	
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
	public void doDraw(Canvas graphics, Path clipShape) {
		graphics.save();
		graphics.clipPath(clipShape);
		
		// need to actually draw the rectangle inside the rect, accounting for stroke width
		float delta = m_paint.getStrokeWidth() / 2;
		m_rectToDraw.left = m_rect.left + delta;
		m_rectToDraw.right = m_rect.right - delta;
		m_rectToDraw.bottom = m_rect.bottom - delta;
		m_rectToDraw.top = m_rect.top + delta;
		
		if(m_rectToDraw.left >= m_rectToDraw.right || m_rectToDraw.top > m_rectToDraw.bottom)
		{
			// if the line thickness is so big that we would fill a rectangle, just fill the rectangle
			Paint p = new Paint(m_paint);
			p.setStyle(Style.FILL);
			graphics.drawRect(m_rect, p);
		} else
		{
			graphics.drawRect(m_rectToDraw, m_paint);	
		}

		graphics.restore();   
	}

	@Override
	public void moveTo(int x, int y) {
		int oldX = m_rect.left;
		int oldY = m_rect.top;
		
		int dx = x - m_rect.left;
		m_rect.left = x;
		m_rect.right += dx;
		int dy = y - m_rect.top;
		m_rect.top = y;
		m_rect.bottom += dy;
		boundsChanged();
		notifyPropertyChanged("X", oldX, x);
		notifyPropertyChanged("Y", oldY, y);
	}

	protected void updateBounds()
	{
		m_boundaryRect.x =  m_rect.left;
		m_boundaryRect.y = m_rect.top;
		m_boundaryRect.width = m_rect.width();
		m_boundaryRect.height = m_rect.height();
	}
	
	// Getters and setters
	public int getX() {
		return m_rect.left;
	}

	public void setX(int x) {
		int oldX = m_rect.left;
		int dx = x - m_rect.left;
		m_rect.left = x;
		m_rect.right += dx;
		
		boundsChanged();
		notifyPropertyChanged("X", oldX, x);
	}

	public int getY() {
		return m_rect.top;
	}

	public void setY(int y) {
		int oldY = m_rect.top;
		int dy = y - m_rect.top;
		m_rect.top = y;
		m_rect.bottom += dy;
		boundsChanged();
		notifyPropertyChanged("Y", oldY, y);
	}

	public int getWidth() {
		return m_rect.width();
	}

	public void setWidth(int width) {
		int oldWidth = m_rect.right - m_rect.left;
		m_rect.right = m_rect.left + width;
		boundsChanged();
		notifyPropertyChanged("Width", oldWidth, width);
	}

	public int getHeight() {
		return m_rect.height();
	}

	public void setHeight(int height) {
		int oldHeight = m_rect.bottom - m_rect.top;
		m_rect.bottom = m_rect.top + height;
		boundsChanged();
		notifyPropertyChanged("Height", oldHeight, height);
	}

	public int getColor() {
		return m_paint.getColor();
	}

	public void setColor(int color) {
		int oldColor = m_paint.getColor();
		m_paint.setColor(color);
		boundsChanged();
		notifyPropertyChanged("Color", oldColor, color);
	}

}
