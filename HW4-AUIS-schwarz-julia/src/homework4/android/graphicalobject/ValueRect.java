package homework4.android.graphicalobject;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * A rectangle that has a 'value' property, which it displays 
 * as text.
 * This class demonstrates how to create a new object which has properties that can use constraints.
 * To create a property that can be set via constraints, implement a setPropertyName, where PropertyName is the name of your property.
 * in this method, after setting your property, call notifyPropertyChanged("PropertyName", oldValue, newValue).
 * If the value doesn't change the constraints don't update.
 * See the setValue method in ValueRect for an explanation.
 * @author Julia
 *
 */
public class ValueRect extends OutlineRect
{
	int m_value;
	Paint m_textPaint = new Paint();
	public ValueRect(int x, int y, int width, int height, int color, int lineThickness, int value)
	{
		super(x,y,width,height,color, lineThickness);
		m_textPaint.setTextSize(18);
		m_textPaint.setColor(Color.LTGRAY);
		m_value = value;
	}
	@Override
	public void doDraw(Canvas graphics, Path clipShape) {
		// TODO Auto-generated method stub
		super.doDraw(graphics, clipShape);
		
		graphics.save();
		graphics.clipPath(clipShape);
		
		// draw some text in the left and center
		graphics.drawText("value: " + m_value, getX() + 20, getY() + 50, m_textPaint);
		
		graphics.restore();   
	}
	
	public int getValue()
	{
		return m_value;
	}
	
	public void setValue(int value)
	{
		int oldVal = m_value;
		m_value = value;
		notifyPropertyChanged("Value", oldVal, value);
		boundsChanged();
	}
}