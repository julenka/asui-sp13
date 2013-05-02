package homework5.processing.graphicalobject;


import java.awt.Color;
import java.awt.Shape;

import processing.core.PGraphics;

/**
 * Class for drawing filled rectangles.
 * @author julenka
 */
public class FilledRect extends RectBase {
	
	public FilledRect(int x, int y, int width, int height, Color color)
	{
		super(x,y,width, height, color);
	}
	
	@Override
	public void doDraw(PGraphics graphics) {
		graphics.noStroke();
		graphics.fill(m_color.getRed(), m_color.getGreen(), m_color.getBlue());
		graphics.rect(m_x, m_y, m_width, m_height);
		 
	}

}
