package homework3.android;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;

public class Text extends GraphicalObjectBase {

	private String m_text;
	private int m_x;
	private int m_y;
	private Rect m_rect = new Rect();
	
	public Text (String text, int x, int y, Typeface font, int fontSize, int color)
	{
		m_paint.setTextAlign(Align.LEFT);
		m_text = text;
		m_x = x;
		m_y = y;
		m_paint.setTextSize(fontSize);
		m_paint.setTypeface(font);
		m_paint.setColor(color);
		boundsChanged();
	}
	
	public Text() {
		this("", 0, 0, Typeface.create("Helvetica", Typeface.NORMAL), 12, Color.BLACK);
	}

	
	@Override
	public void doDraw(Canvas graphics, Path clipShape) {
		// draw so that m_x is in upper left hand corner
		// todo: apply affine transformation
		graphics.save();
		graphics.clipPath(clipShape);
		graphics.drawText(m_text, m_x, m_y, m_paint );
		graphics.restore();
	}

	@Override
	public void moveTo(int x, int y) {
		// move the upper left corner
		Point upperLeft = getUpperLeft();
		int dx = x - upperLeft.x;
		int dy = y - upperLeft.y;
		m_x += dx;
		m_y += dy;
		boundsChanged();
	}	
	
	/**
	 * Returns upper left corder of the text.
	 * @return
	 */
	private Point getUpperLeft()
	{
		Point result = new Point(m_x, m_y);
		m_paint.getTextBounds(m_text, 0, m_text.length(), m_rect);
		result.y += m_rect.top;
		return result;
	}
	
	protected void updateBounds()
	{
		// modifies m_rect
		Point upperLeft = getUpperLeft();
		int width = (int)m_paint.measureText(m_text);
		m_boundaryRect.x = upperLeft.x;
		m_boundaryRect.y = upperLeft.y;
		m_boundaryRect.width = width;
		m_boundaryRect.height = m_rect.height();
	}
	
	public int getColor()
	{
		return m_paint.getColor();
	}
	
	public void setColor(int color)
	{
		m_paint.setColor(color);
		doDamage();
	}
	
	public int getFontSize()
	{
		return (int) m_paint.getTextSize();
	}
	
	public void setFontSize(int size)
	{
		m_paint.setTextSize(size);
		boundsChanged();
	}

	public Typeface getFont()
	{
		return m_paint.getTypeface();
	}
	
	public void setFont(Typeface f)
	{
		m_paint.setTypeface(f);
		boundsChanged();
	}
	
	public int getY() {
		return m_y;
	}

	public void setY(int y) {
		m_y = y;
		boundsChanged();
	}

	public int getX() {
		return m_x;
	}

	public void setX(int x) {
		m_x = x;
		boundsChanged();
	}

	public String getText() {
		return m_text;
	}

	public void setText(String text) {
		m_text = text;
		boundsChanged();
	}

}
