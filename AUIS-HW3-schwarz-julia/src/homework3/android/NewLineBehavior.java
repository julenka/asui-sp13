package homework3.android;

import android.graphics.Color;

public class NewLineBehavior extends NewBehavior {
	
	/*
	 When a NewBehavior starts, it should call its own make() method to create 
	 a new instance of a graphical object. The NewBehavior should add the object 
	 returned by make() to the group, so it will appear on screen immediately. 
	 While the Behavior is running, it should resize the new object to follow the 
	 mouse (assuming onePoint is false). When it stops, it should leave the object 
	 where it is.
	 */

	private int m_color;
	private int m_lineThickness;

	
	public NewLineBehavior(int color, int lineThickness, Group g)
	{
		super(false, g);
		m_color = color;
		m_lineThickness = lineThickness;
		m_group = g;
	}
	public NewLineBehavior(int c, int t) {
		this(c,t,null);
	}

	@Override
	public GraphicalObject make(int x1, int y1, int x2, int y2) {
		return new Line(x1, y1, x2, y2, m_color, m_lineThickness);
	}

	@Override
	public void resize(GraphicalObject gobj, int x1, int y1, int x2, int y2) {
		Line l = (Line) gobj;
		l.setX2(x2);
		l.setY2(y2);
	}

	
	
	public int getColor() {
		return m_color;
	}

	public void setColor(int color) {
		m_color = color;
	}

	public int getLineThickness() {
		return m_lineThickness;
	}

	public void setLineThickness(int lineThickness) {
		m_lineThickness = lineThickness;
	}

}
