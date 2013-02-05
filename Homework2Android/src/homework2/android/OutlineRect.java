package homework2.android;

import android.graphics.Paint.Style;

public class OutlineRect extends FilledRect {
	public OutlineRect()
	{
		super();
		m_paint.setStyle(Style.STROKE);
		setLineThickness(1);
	}
	
	public OutlineRect(int x, int y, int width, int height, int color, int lineThickness)
	{
		super(x,y,width,height,color);
		m_paint.setStyle(Style.STROKE);
		setLineThickness(lineThickness);
	}

	public int getLineThickness() {
		return (int)m_paint.getStrokeWidth();
	}
	
	public void setLineThickness(int lineThickness) {
		m_paint.setStrokeWidth(lineThickness);
		updateAndDamage();
	}
	
	
}