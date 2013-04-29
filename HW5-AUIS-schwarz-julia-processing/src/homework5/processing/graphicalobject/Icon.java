package homework5.processing.graphicalobject;

import java.awt.Shape;

import processing.core.PGraphics;
import processing.core.PImage;

/**
 * Draws an icon
 * @author julenka
 *
 */
public class Icon extends GraphicalObjectBase {

	private PImage m_image;
	private int m_x;
	private int m_y;
	
	public Icon (PImage image, int x, int y)
	{
		m_image = image;
		m_x = x;
		m_y = y;
		boundsChanged();
	}
	
	public Icon() {
		this(null, 0, 0);
	}

	protected void updateBounds()
	{
		m_boundaryRect.x = m_x;
		m_boundaryRect.y = m_y;
		m_boundaryRect.width = m_image.width;
		m_boundaryRect.height = m_image.height;
	}

	@Override
	public void moveTo(int x, int y) {
		int oldX = m_x;
		int oldY = m_y;
		m_x = x;
		m_y = y;
		boundsChanged();
		notifyPropertyChanged("X", oldX, x);
		notifyPropertyChanged("Y", oldY, y);
	}

	public int getX() {
		return m_x;
	}

	public void setX(int x) {
		int oldX = m_x;
		m_x = x;
		boundsChanged();
		notifyPropertyChanged("X", oldX, x);
	}

	public int getY() {
		return m_y;
	}

	public void setY(int y) {
		int oldY = m_y;
		m_y = y;
		boundsChanged();
		notifyPropertyChanged("Y", oldY, y);
	}

	public PImage getImage() {
		return m_image;
	}

	public void setImage(PImage image) {
		m_image = image;
		boundsChanged();
	}

	@Override
	protected void doDraw(PGraphics canvas) {
		canvas.image(m_image, m_x, m_y);
	}

}

