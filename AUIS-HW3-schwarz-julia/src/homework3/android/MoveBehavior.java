package homework3.android;

import android.graphics.Point;
import android.util.Log;
import android.view.KeyEvent;

public class MoveBehavior extends BehaviorBase{
	
	static final String LOG_TAG="Homework3.MoveBehavrior";
		
	private GraphicalObject m_childToMove;
	
	private Point m_childStartLocation = new Point();
	

	public MoveBehavior() {
		super(DEFAULT_START, DEFAULT_STOP, null);
	}
	
	public MoveBehavior(Group tiedTo)
	{
		super(DEFAULT_START, DEFAULT_STOP, tiedTo);
	}
	
	public MoveBehavior(BehaviorEvent start, BehaviorEvent stop, Group tiedTo)
	{
		super(start, stop, tiedTo);
	}

	protected boolean startConditionSatisfied(BehaviorEvent event)
	{
		for (GraphicalObject child : m_group.getChildren()) {
			if(child.contains(event.getX(), event.getY())) return true;
		}
		return false;
	}
	
	protected void behaviorStarted(BehaviorEvent event)
	{
		m_childToMove = null;
		// TODO: maybe hold a lock on the children to that if a child gets removed this doesn't crash
		for (GraphicalObject child : m_group.getChildren()) {
			if(m_childToMove != null) break;
			if(child.contains(event.getX(), event.getY()))
			{
				m_childToMove = child;
			}
		}
		
		m_childStartLocation.x = m_childToMove.getBoundingBox().x;
		m_childStartLocation.y = m_childToMove.getBoundingBox().y;
	}
	
	
	
	// for the base class
	protected void doRunningInside(BehaviorEvent event)
	{
		if(m_childToMove == null)
		{
			Log.w(LOG_TAG, "move behavior is running but child to move is null. stopping behavrio");
			m_state = IDLE;
			return;
		}
		int dx = event.getX() - m_touchStartPoint.x;
		int dy = event.getY() - m_touchStartPoint.y;
		
		m_childToMove.moveTo(m_childStartLocation.x + dx, m_childStartLocation.y + dy);
		
		Log.v(LOG_TAG, "running inside x: " + event.getX() + " y: " + event.getY());
	}
	
	protected void doRunningOutside(BehaviorEvent event)
	{
		Log.v(LOG_TAG, "running outside x: " + event.getX() + " y: " + event.getY());
	}
	
	@Override
	protected void onStopped(BehaviorEvent event) {
		Log.v(LOG_TAG, "stopped");
	}
	
	@Override
	protected void onCancelled(BehaviorEvent event) {
		Log.v(LOG_TAG, "cancelled, moving to " + m_childStartLocation.x + ", " + m_childStartLocation.y);
		m_childToMove.moveTo(m_childStartLocation.x, m_childStartLocation.y);
	}
}
