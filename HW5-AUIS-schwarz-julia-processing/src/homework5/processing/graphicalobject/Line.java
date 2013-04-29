package homework5.processing.graphicalobject;

import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;

import processing.core.PGraphics;

public class Line extends GraphicalObjectBase {
	private int m_x1;
	private int m_y1;
	private int m_x2;
	private int m_y2;
	private Color m_color;
	private int m_lineThickness;
	
	public Line (int x1, int y1, int x2, int y2, Color color, int lineThickness)
	{
		m_x1 = x1;
		m_x2 = x2;
		m_y1 = y1;
		m_y2 = y2;
		m_color = color;
		m_lineThickness = lineThickness;
		boundsChanged();
	}
	
	public Line() {
		this(0,0, 10, 10, Color.BLACK, 2);
	}

	protected void updateBounds()
	{
		Point upperLeft = getUpperLeft();
		upperLeft.x -= Math.ceil(m_lineThickness / 2.0);
		upperLeft.y -= Math.ceil(m_lineThickness / 2.0);

		Point lowerRight = getLowerRight();
		lowerRight.x += Math.ceil(m_lineThickness / 2.0);
		lowerRight.y += Math.ceil(m_lineThickness / 2.0);
		
		m_boundaryRect.x = upperLeft.x;
		m_boundaryRect.y = upperLeft.y;
		m_boundaryRect.width = lowerRight.x - upperLeft.x;
		m_boundaryRect.height = lowerRight.y - upperLeft.y;
	}
	
	@Override
	public void doDraw(PGraphics graphics) {
		graphics.stroke(m_color.getRed(), m_color.getGreen(), m_color.getBlue());
		graphics.line(m_x1, m_y1, m_x2, m_y2);
	}

	private Point getUpperLeft()
	{
		return new Point(Math.min(m_x1, m_x2), Math.min(m_y1, m_y2));
	}
	
	private Point getLowerRight()
	{
		return new Point(Math.max(m_x1, m_x2), Math.max(m_y1, m_y2));
		
	}
	
	@Override
	public void moveTo(int x, int y) {
		Point upperLeft = getUpperLeft();
		int dy = y - upperLeft.y;
		int dx = x - upperLeft.x;
		m_x1 += dx;
		m_x2 += dx;
		m_y1 += dy;
		m_y2 += dy;
		boundsChanged();
	}

	public Color getColor() {
		return m_color;
	}

	public void setColor(Color color) {
		Color oldColor = m_color;
		doDamage();
		notifyPropertyChanged("Color", oldColor, color);
	}

	public int getLineThickness()
	{
		return m_lineThickness;
	}
	
	public void setLineThickness(int t)
	{
		int oldThickness = m_lineThickness;
		m_lineThickness = t;
		boundsChanged();
		notifyPropertyChanged("LineThickness", oldThickness, t);
	}
	
	public int getX1() {
		return m_x1;
	}

	public void setX1(int x1) {
		int oldX1 = m_x1;
		m_x1 = x1;
		boundsChanged();
		notifyPropertyChanged("X1", oldX1, x1);
	}

	public int getY1() {
		return m_y1;
	}

	public void setY1(int y1) {
		int oldY1 = m_y1;
		m_y1 = y1;
		boundsChanged();
		notifyPropertyChanged("Y1", oldY1, y1);
	}

	public int getX2() {
		return m_x2;
	}

	public void setX2(int x2) {
		int oldX2 = m_x2;
		m_x2 = x2;
		boundsChanged();
		notifyPropertyChanged("X2", oldX2, x2);
	}

	public int getY2() {
		return m_y2;
	}

	public void setY2(int y2) {
		int oldY2 = m_y2;
		m_y2 = y2;
		boundsChanged();
		notifyPropertyChanged("Y2", oldY2, y2);
	}

}
