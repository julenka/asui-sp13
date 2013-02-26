package homework3.android;

import android.util.Log;

public class NewRectBehavior extends NewBehavior {
	static final String LOG_TAG = "Homework3.NewRectBehavior";
	private int m_color;
	private int m_lineThickness;
	
	public NewRectBehavior(int color, int lineThickness, Group g)
	{
		super(false, g);
		m_color = color;
		m_lineThickness = lineThickness;
		m_group = g;
	}
	
	public NewRectBehavior(int color, int lineThickness)
	{
		this(color, lineThickness, null);
	}

	@Override
	public GraphicalObject make(int x1, int y1, int x2, int y2) {
		return new OutlineRect(x1, y1, (x2 - x1), (y2 - y1), m_color, m_lineThickness);
	}

	@Override
	public void resize(GraphicalObject gobj, int x1, int y1, int x2, int y2) {
		int width = Math.abs(x2 - x1);
		int height = Math.abs(y2 - y1);
		OutlineRect r = (OutlineRect) gobj;
		r.moveTo(Math.min(x1,  x2), Math.min(y1, y2));
		r.setWidth(width);
		r.setHeight(height);
		Log.v(LOG_TAG, String.format("resize (%d, %d)  (%d, %d)", x1, x2, y1, y2));
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
