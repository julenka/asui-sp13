package homework3.android;

import android.util.Log;
import android.view.KeyEvent;

public class BehaviorBase implements Behavior {

	static final BehaviorEvent DEFAULT_STOP = new BehaviorEvent(BehaviorEvent.MOUSE_UP_ID, 0, BehaviorEvent.LEFT_MOUSE_KEY);
	static final BehaviorEvent DEFAULT_START = new BehaviorEvent(BehaviorEvent.MOUSE_DOWN_ID, 0, BehaviorEvent.LEFT_MOUSE_KEY);
	static final BehaviorEvent DEFAULT_CANCEL = new BehaviorEvent(BehaviorEvent.KEY_DOWN_ID, 0, KeyEvent.KEYCODE_C);
	
	static final String LOG_TAG="Homework3.BehaviorBase";
	
	
	protected Group m_group;
	protected int m_state;
	
	protected BehaviorEvent m_startEvent;
	protected BehaviorEvent m_stopEvent;
	protected BehaviorEvent m_cancelEvent;
	
	public BehaviorBase() {
		this(DEFAULT_START, DEFAULT_STOP, null);
	}
	
	public BehaviorBase(Group tiedTo)
	{
		this(DEFAULT_START, DEFAULT_STOP, tiedTo);
	}
	
	public BehaviorBase(BehaviorEvent start, BehaviorEvent stop, Group tiedTo)
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
		
		m_state = RUNNING_INSIDE;
		
		behaviorStarted(event);
		Log.v(LOG_TAG, "started ");
	}
	
	protected void behaviorStarted(BehaviorEvent event)
	{
		Log.e(LOG_TAG, "Oops, you forgot to implement behaviorStarted!");
	}
	
	protected boolean startConditionSatisfied(BehaviorEvent event)
	{
		Log.e(LOG_TAG, "Oops, you forgot to implement startConditionSatisfied!");
		return false;
	}

	
	private boolean isInside(BehaviorEvent event)
	{
		return event.getX() >= 0 && event.getY() >=0 && event.getX() < m_group.getBoundingBox().getWidth() 
				&& event.getY() < m_group.getBoundingBox().getHeight();
	}
	
	// for the base class
	// override this
	protected void doRunningInside(BehaviorEvent event)
	{
		Log.e(LOG_TAG, "Oops, you forgot to implement doRunningInside");
	}
	
	protected void doRunningOutside(BehaviorEvent event)
	{
		Log.e(LOG_TAG, "Oops, you forgot to implement doRunningOutside");
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
		// maybe make some visual changes
		Log.v(LOG_TAG, "stopped");
		m_state = IDLE;
		onStopped(event);
	}
	
	protected void onStopped(BehaviorEvent event)
	{
		Log.e(LOG_TAG, "Oops, you forgot to implement onStopped");
	}
	

	@Override
	// cancel() is called when the cancel event occurs (usually ESC, but you can make it settable). 
	// It should cancel the Behavior as if it had never started, and return the Behavior to an idle state.
	public void cancel(BehaviorEvent event) {
		// TODO: restore everything
		
		m_state = IDLE;
		onCancelled(event);

	}

	protected void onCancelled(BehaviorEvent event)
	{
		Log.e(LOG_TAG, "Oops, you forgot to implement onCancelled");
	}
	
	public BehaviorEvent getCancelEvent() {
		return m_cancelEvent;
	}

	public void setCancelEvent(BehaviorEvent cancelEvent) {
		m_cancelEvent = cancelEvent;
	}


}
