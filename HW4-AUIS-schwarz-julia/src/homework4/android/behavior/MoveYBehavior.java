package homework4.android.behavior;

import homework4.android.graphicalobject.Group;

public class MoveYBehavior extends MoveBehavior {
	public MoveYBehavior() {
		super();
	}
	
	public MoveYBehavior(Group tiedTo)
	{
		super(tiedTo);
	}
	
	public MoveYBehavior(BehaviorEvent start, BehaviorEvent stop, Group tiedTo)
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
		int dy = event.getY() - m_touchStartPoint.y;
		
		m_childToMove.moveTo(m_childStartLocation.x, m_childStartLocation.y + dy);
		
	}
	
	
	@Override
	protected void doRunningOutside(BehaviorEvent event) {
		doRunningInside(event);
	}
}
