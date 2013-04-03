package homework4.android.graphicalobject;

import android.graphics.Color;
import android.graphics.Paint.Style;

public class OutlineRect extends FilledRect {
	public OutlineRect()
	{
		this(0,0,10,10,Color.BLACK,1);
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
		int oldThickness = (int)m_paint.getStrokeWidth();
		m_paint.setStrokeWidth(lineThickness);
		notifyIntPropertyChanged("LineThickness", oldThickness, lineThickness);
		boundsChanged();
	}
	
	
}