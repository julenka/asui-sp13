package homework5.processing.graphicalobject;


import java.awt.Color;
import java.awt.Shape;

import processing.core.PGraphics;

public class OutlineRect extends RectBase{
	int m_lineThickness;
	public OutlineRect()
	{
		this(0,0,10,10,Color.BLACK,1);
	}
	
	public OutlineRect(int x, int y, int width, int height, Color color, int lineThickness)
	{
		super(x,y,width,height,color);
		setLineThickness(lineThickness);
	}

	public int getLineThickness() {
		return m_lineThickness;
	}
	
	public void setLineThickness(int lineThickness) {
		int oldThickness = m_lineThickness;
		m_lineThickness = lineThickness;
		notifyPropertyChanged("LineThickness", oldThickness, lineThickness);
		boundsChanged();
	}

	@Override
	protected void doDraw(PGraphics canvas) {
		canvas.stroke(m_color.getRed(), m_color.getGreen(), m_color.getBlue());
		canvas.strokeWeight(m_lineThickness);
		canvas.noFill();
		canvas.rect(m_x + m_lineThickness / 2, m_y + m_lineThickness / 2, 
				m_width - m_lineThickness, m_height - m_lineThickness);
	}
	
	
}