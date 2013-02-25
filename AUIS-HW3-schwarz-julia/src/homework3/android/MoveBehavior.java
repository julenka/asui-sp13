package homework3.android;

import android.graphics.Point;
import android.util.Log;
import android.view.KeyEvent;

public class MoveBehavior implements Behavior {
	static final BehaviorEvent DEFAULT_STOP = new BehaviorEvent(BehaviorEvent.MOUSE_UP_ID, 0, BehaviorEvent.LEFT_MOUSE_KEY);
	static final BehaviorEvent DEFAULT_START = new BehaviorEvent(BehaviorEvent.MOUSE_DOWN_ID, 0, BehaviorEvent.LEFT_MOUSE_KEY);
	static final BehaviorEvent DEFAULT_CANCEL = new BehaviorEvent(BehaviorEvent.KEY_DOWN_ID, 0, KeyEvent.KEYCODE_ESCAPE);
	
	static final String LOG_TAG="Homework3.MoveBehavrior";
	
	
	private Group m_group;
	private int m_state;
	
	private BehaviorEvent m_startEvent;
	private BehaviorEvent m_stopEvent;
	private BehaviorEvent m_cancelEvent;
	
	private GraphicalObject m_childToMove;
	
	private Point m_touchStartPoint = new Point();
	private Point m_childStartLocation = new Point();
	
	public MoveBehavior() {
		this(DEFAULT_START, DEFAULT_STOP, null);
	}
	
	public MoveBehavior(Group tiedTo)
	{
		this(DEFAULT_START, DEFAULT_STOP, tiedTo);
	}
	
	public MoveBehavior(BehaviorEvent start, BehaviorEvent stop, Group tiedTo)
	{
		m_startEvent = start;
		m_stopEvent = stop;
		m_group = tiedTo;
		m_state = IDLE;
		m_cancelEvent = DEFAULT_CANCEL;
	}

	@Override
	public Group getGroup() {
		return m_group;
	}

	@Override
	public void setGroup(Group group) {
		m_group = group;
	}

	@Override
	public int getState() {
		return m_state;
	}

	@Override
	public BehaviorEvent getStartEvent() {
		return m_startEvent;
	}

	@Override
	public void setStartEvent(BehaviorEvent mask) {
		m_startEvent = mask;

	}

	@Override
	public BehaviorEvent getStopEvent() {
		return m_stopEvent;
	}

	@Override
	public void setStopEvent(BehaviorEvent mask) {
		m_stopEvent = mask;
	}

	@Override
	// start() is called when the start event occurs. It should do everything needed to start the Behavior and 
	// put it in a running state. start() may perform additional tests to decide whether or not 
	// the Behavior should be started.
	
	// A move Behavior should start running only if the start event happens when the mouse is over a graphical object in its group.
	public void start(BehaviorEvent event) {
		// check if behavior should start
		if(!startConditionSatisfied(event)) return;
		
		m_childToMove = null;
		// TODO: maybe hold a lock on the children to that if a child gets removed this doesn't crash
		for (GraphicalObject child : m_group.getChildren()) {
			if(m_childToMove != null) break;
			if(child.contains(event.getX(), event.getY()))
			{
				m_childToMove = child;
			}
		}
		
		m_touchStartPoint.x = event.getX();
		m_touchStartPoint.y = event.getY();
		m_childStartLocation.x = m_childToMove.getBoundingBox().x;
		m_childStartLocation.y = m_childToMove.getBoundingBox().y;
		
		m_state = RUNNING_INSIDE;
		Log.v(LOG_TAG, "started ");
	}
	
	// check if the start condition is satisfied. If not, don't start event
	protected boolean startConditionSatisfied(BehaviorEvent event)
	{
		for (GraphicalObject child : m_group.getChildren()) {
			if(child.contains(event.getX(), event.getY())) return true;
		}
		return false;
	}

	private boolean isInside(BehaviorEvent event)
	{
		return event.getX() >= 0 && event.getY() >=0 && event.getX() < m_group.getBoundingBox().getWidth() 
				&& event.getY() < m_group.getBoundingBox().getHeight();
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
	//running() is called whenever the mouse moves while the Behavior is running. 
	// It should update the state of the Behavior and determine whether the mouse is inside or outside the area of interest.
	public void running(BehaviorEvent event) {

		if(isInside(event)) m_state = RUNNING_INSIDE;
		else m_state = RUNNING_OUTSIDE;
		
		// check if we are outside the bounds of the group
		if(m_state == RUNNING_INSIDE)
		{
			doRunningInside(event);
		} else 
		{
			doRunningOutside(event);
		}
	}

	@Override
	// stop() is called when the stop event occurs. It should stop the Behavior and return it to an idle state.
	public void stop(BehaviorEvent event) {
		m_childToMove = null;
		// maybe make some visual changes
		Log.v(LOG_TAG, "stopped");
		m_state = IDLE;
	}

	@Override
	// cancel() is called when the cancel event occurs (usually ESC, but you can make it settable). 
	// It should cancel the Behavior as if it had never started, and return the Behavior to an idle state.
	public void cancel(BehaviorEvent event) {
		// TODO: restore everything
		Log.v(LOG_TAG, "cancelled, moving to " + m_childStartLocation.x + ", " + m_childStartLocation.y);
		m_childToMove.moveTo(m_childStartLocation.x, m_childStartLocation.y);
		
		m_state = IDLE;

	}

	public BehaviorEvent getCancelEvent() {
		return m_cancelEvent;
	}

	public void setCancelEvent(BehaviorEvent cancelEvent) {
		m_cancelEvent = cancelEvent;
	}

}
