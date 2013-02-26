package homework3.android;

import android.graphics.Color;

public class NewRectBehavior extends NewBehavior {

	private Color m_color;
	private int m_lineThickness;
	
	public NewRectBehavior(Color color, int lineThickness)
	{
		super(false);
		m_color = color;
		m_lineThickness = lineThickness;
	}

	@Override
	public GraphicalObject make(int x1, int y1, int x2, int y2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void resize(GraphicalObject gobj, int x1, int y1, int x2, int y2) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void behaviorStarted(BehaviorEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean startConditionSatisfied(BehaviorEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void doRunningInside(BehaviorEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void doRunningOutside(BehaviorEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onStopped(BehaviorEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onCancelled(BehaviorEvent event) {
		// TODO Auto-generated method stub

	}

	public Color getColor() {
		return m_color;
	}

	public void setColor(Color color) {
		m_color = color;
	}

	public int getLineThickness() {
		return m_lineThickness;
	}

	public void setLineThickness(int lineThickness) {
		m_lineThickness = lineThickness;
	}

}
