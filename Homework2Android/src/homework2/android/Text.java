package homework2.android;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.Log;

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
		updateBoundaryRect();
	}
	
	public Text() {
		this("", 0, 0, Typeface.create("Helvetica", Typeface.NORMAL), 12, Color.BLACK);
	}

	
	@Override
	public void draw(Canvas graphics, Path clipShape) {
		// draw so that m_x is in upper left hand corner
		// todo: apply affine transformation
		graphics.clipPath(clipShape);
		graphics.drawText(m_text, m_x, m_y, m_paint );
	}

	@Override
	public void moveTo(int x, int y) {
		// todo: this actually damages a little bit more than necessary, consider setting m_x, m_y directly 
		m_x = x;
		m_y = y;
		updateBoundaryRect();
	}

	private void updateBoundaryRect()
	{
		// broken
		m_paint.getTextBounds(m_text, 0, m_text.length(), m_rect);
		int width = (int)m_paint.measureText(m_text);
		m_boundaryRect.x = m_x;
		m_boundaryRect.y = m_y + m_rect.top;
		m_boundaryRect.width = width;
		m_boundaryRect.height = m_rect.height();
		
//		Log.v("Text",  String.format("(%d,%d,%d,%d)", m_rect.left, m_rect.top, m_rect.width(), m_rect.height()));
		// update bounding box
		doDamage();
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
		updateBoundaryRect();
	}

	public Typeface getFont()
	{
		return m_paint.getTypeface();
	}
	
	public void setFont(Typeface f)
	{
		m_paint.setTypeface(f);
		updateBoundaryRect();
	}
	
	public int getY() {
		return m_y;
	}

	public void setY(int y) {
		m_y = y;
		updateBoundaryRect();
	}

	public int getX() {
		return m_x;
	}

	public void setX(int x) {
		m_x = x;
		updateBoundaryRect();
	}

	public String getText() {
		return m_text;
	}

	public void setText(String text) {
		m_text = text;
		updateBoundaryRect();
	}

}
