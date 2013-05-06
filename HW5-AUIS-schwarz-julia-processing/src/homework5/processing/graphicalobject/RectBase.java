package homework5.processing.graphicalobject;

import java.awt.Color;

public abstract class RectBase extends GraphicalObjectBase {

	protected int m_x;
	protected int m_y;
	protected int m_width;
	protected int m_height;
	protected Color m_color;
	
	// Getters and setters

	public RectBase(int x, int y, int width, int height, Color color)
	{
		m_x = x;
		m_y = y;
		m_width = width;
		m_height = height;
		m_color = color;
		boundsChanged();
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

	protected void updateBounds()
	{
		m_boundaryRect.x =  m_x;
		m_boundaryRect.y = m_y;
		m_boundaryRect.width = m_width;
		m_boundaryRect.height = m_height;
	}
	
	// Getters and setters
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

	public int getWidth() {
		return m_width;
	}

	public void setWidth(int width) {
		int oldWidth = m_width;
		m_width = width;
		boundsChanged();
		notifyPropertyChanged("Width", oldWidth, width);
	}

	public int getHeight() {
		return m_height;
	}

	public void setHeight(int height) {
		int oldHeight = m_height;
		m_height = height;
		boundsChanged();
		notifyPropertyChanged("Height", oldHeight, height);
	}

	public Color getColor() {
		return m_color;
	}

	public void setColor(Color color) {
		Color oldColor = m_color;
		m_color = color;
		boundsChanged();
		notifyPropertyChanged("Color", oldColor, color);
	}

}
