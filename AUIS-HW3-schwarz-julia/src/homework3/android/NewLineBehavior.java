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

	private Color m_color;
	private int m_lineThickness;

	public NewLineBehavior(Color c, int t) {
		super(false);
		m_color = c;
		m_lineThickness = t;
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
