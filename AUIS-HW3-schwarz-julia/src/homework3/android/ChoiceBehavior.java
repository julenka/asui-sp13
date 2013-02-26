package homework3.android;

import java.util.ArrayList;
import java.util.List;

public class ChoiceBehavior extends BehaviorBase {
	private boolean m_firstOnly = false;
	private int m_type = SINGLE;

	private List<GraphicalObject> m_selectedObjects = new ArrayList<GraphicalObject>();
	
	public ChoiceBehavior (int type, boolean firstOnly){
		m_type = type;
		m_firstOnly = firstOnly;
	}

	public ChoiceBehavior (int type, boolean firstOnly, Group attachedTo){
		m_type = type;
		m_firstOnly = firstOnly;
	}

	
	public List<GraphicalObject> getSelection ()
	{
		return m_selectedObjects;
	}

	public static final int SINGLE = 0;
	public static final int TOGGLE = 1;
	public static final int MULTIPLE = 2;

	public ChoiceBehavior(Group tiedTo) {
		super(tiedTo);
		// TODO Auto-generated constructor stub
	}

	public ChoiceBehavior(BehaviorEvent start, BehaviorEvent stop, Group tiedTo) {
		super(start, stop, DEFAULT_RUNNING, tiedTo);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void behaviorStarted(BehaviorEvent event) {
		// TODO Auto-generated method stub

	}

	protected boolean startConditionSatisfied(BehaviorEvent event)
	{
		for (GraphicalObject child : m_group.getChildren()) {
			if(child.contains(event.getX(), event.getY())) return true;
		}
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
	public boolean isFirstOnly() {
		return m_firstOnly;
	}
	public void setFirstOnly(boolean firstOnly) {
		m_firstOnly = firstOnly;
	}
	public int getType() {
		return m_type;
	}
	public void setType(int type) {
		m_type = type;
	}

}
