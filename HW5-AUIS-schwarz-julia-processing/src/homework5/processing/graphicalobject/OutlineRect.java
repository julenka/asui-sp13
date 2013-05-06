package homework5.processing.graphicalobject;


import java.awt.Color;

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
		if(3 * m_lineThickness / 2 >= m_width || 
				3 * m_lineThickness / 2 > m_height)
		{
			// fill in a smaller rect
			canvas.noStroke();
			canvas.fill(m_color.getRed(), m_color.getGreen(), m_color.getBlue());
			canvas.rect(m_x, m_y, 
					m_width, m_height);
		} else
		{
			// draw with an outline
			canvas.stroke(m_color.getRed(), m_color.getGreen(), m_color.getBlue());
			canvas.strokeWeight(m_lineThickness);
			canvas.noFill();
			canvas.rect(m_x + m_lineThickness / 2.0f, m_y + m_lineThickness / 2.0f, 
					m_width - m_lineThickness, m_height - m_lineThickness);
		}
		
	}
	
	
}