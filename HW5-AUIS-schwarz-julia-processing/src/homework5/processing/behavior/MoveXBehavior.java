package homework5.processing.behavior;

import homework5.processing.graphicalobject.Group;

public class MoveXBehavior extends MoveBehavior {
	
	public MoveXBehavior() {
		super();
	}
	
	public MoveXBehavior(Group tiedTo)
	{
		super(tiedTo);
	}
	
	public MoveXBehavior(BehaviorEvent start, BehaviorEvent stop, Group tiedTo)
	{
		super(start, stop, tiedTo);
	}

	// for the base class
	protected void doRunningInside(BehaviorEvent event)
	{
		if(m_childToMove == null)
		{
			m_state = IDLE;
			return;
		}
		int dx = event.getX() - m_touchStartPoint.x;
		
		m_childToMove.moveTo(m_childStartLocation.x + dx, m_childStartLocation.y);
		
	}
	
	@Override
	protected void doRunningOutside(BehaviorEvent event) {
		doRunningInside(event);
	}
}
