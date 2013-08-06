package homework5.processing.core;

import homework5.processing.behavior.Behavior;
import homework5.processing.behavior.BehaviorEvent;
import homework5.processing.graphicalobject.Group;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.event.Event;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

/**
 * Frame that contains an interactive graphical object system.
 * Equivalent to InteractiveWindowGroup in HW2-HW4
 * @author julenka
 *
 */
public class InteractiveFrame extends GraphicalObjectFrame implements PConstants {
	float m_lastX;
	float m_lastY;
	protected List<Behavior> m_behaviors = new ArrayList<Behavior>();
	Behavior m_currentBehavior;
	int m_modifier = 0x0;

	public InteractiveFrame(PApplet parent) {
		super(parent);
		// register for mouse and key events
		parent.registerMethod("mouseEvent", this);
		parent.registerMethod("keyEvent", this);
	}

	/**
	 * Gets called when a mouse event occurs in the parent PApplet
	 * @param e
	 */
	public void mouseEvent(MouseEvent e)
	{
		int bEventId = 0;
		switch(e.getAction())
		{
		case MouseEvent.PRESSED:
			bEventId = BehaviorEvent.MOUSE_DOWN_ID;
			break;
		case MouseEvent.RELEASED:
			bEventId = BehaviorEvent.MOUSE_UP_ID;
			break;
		case MouseEvent.DRAGGED:
		case MouseEvent.MOVED:
			bEventId = BehaviorEvent.MOUSE_MOVE_ID;
			break;
		default:
//			System.out.println("mouse event " + e.getAction() + " not supported!");
			return;
		}
		int mouseKey = BehaviorEvent.LEFT_MOUSE_KEY;
		if(e.getButton() == RIGHT) mouseKey = BehaviorEvent.RIGHT_MOUSE_KEY;

		m_lastX = e.getX();
		m_lastY = e.getY();
		updateKeyModifier(e);
		dispatchBehaviorEvent(new BehaviorEvent(bEventId, m_modifier, BehaviorEvent.LEFT_MOUSE_KEY, 
				(int)m_lastX, (int)m_lastY));
		onDispatchCompleted();
	}

	/**
	 * Gets called when a keyboard event occurs in the parent PApplet
	 * @param e
	 */
	public void keyEvent(KeyEvent e)
	{
		int bEventId = 0;
		switch(e.getAction())
		{
		case KeyEvent.PRESSED:
			bEventId = BehaviorEvent.KEY_DOWN_ID;
			break;
		case KeyEvent.RELEASED:
			bEventId = BehaviorEvent.KEY_UP_ID;
			break;
		default:
//			System.out.println("key event id " + e.getAction() + " not supported");
			return;
		}
		updateKeyModifier(e);
		BehaviorEvent toDispatch = new BehaviorEvent(BehaviorEvent.KEY_DOWN_ID, m_modifier, e.getKeyCode(), (int)m_lastX, (int)m_lastY);
		dispatchBehaviorEvent(toDispatch);
		onDispatchCompleted();
	}


	/**
	 * Converts a mouse event to the coordinate space of a particular group
	 * @param in
	 * @param g
	 * @return
	 */
	private BehaviorEvent behaviorEventToGroup(BehaviorEvent in, Group g)
	{
		Point parentSpace = new Point(in.getX(), in.getY());
		List<Group> parents = new ArrayList<Group>();
		Group curParent = g.getGroup();

		while(curParent != this && curParent != null)
		{
			parents.add(curParent);
			curParent = curParent.getGroup();
		}
		Point childSpace = parentSpace; // g.parentToChild(parentSpace);

		for (int i = parents.size() - 1; i >=0; i--) {
			childSpace = parents.get(i).parentToChild(childSpace);
		}

		childSpace = g.parentToChild(childSpace);

		return new BehaviorEvent(in.getID(), in.getModifiers(), in.getKey(), childSpace.x, childSpace.y);
	}

	private boolean isRunning(Behavior b)
	{
		return b.getState() == Behavior.RUNNING_INSIDE || b.getState() == Behavior.RUNNING_OUTSIDE;
	}

	// To start behavior should be in the group and also 
	// the start behavior
	// b shoul not be null
	private boolean canStartBehavior(Behavior b, BehaviorEvent bEvent)
	{
		BehaviorEvent childSpace = behaviorEventToGroup(bEvent, b.getGroup());
		if(!bEvent.matches(b.getStartEvent())) return false;
		return true;
	}


	private void dispatchBehaviorEvent(BehaviorEvent bEvent)
	{
		if(m_currentBehavior == null)
		{
			// find new behavior
			for (Behavior behavior : m_behaviors) {
				// exit once we have found a behavior
				if(m_currentBehavior != null) break;

				if(behavior.getGroup() == null)
				{
					continue;
				}
				if(canStartBehavior(behavior, bEvent))
				{
					// try to start the event
					behavior.start(behaviorEventToGroup(bEvent, behavior.getGroup()));
					if(isRunning(behavior))
					{
						m_currentBehavior = behavior;
					}
				}
			}
		} else
		{
			// we are currently in a behavior
			if(m_currentBehavior.getGroup() == null)
			{
				m_currentBehavior = null;
				return;
			}
			BehaviorEvent childSpace = behaviorEventToGroup(bEvent, m_currentBehavior.getGroup());
			if(bEvent.matches(m_currentBehavior.getCancelEvent()))
			{
				m_currentBehavior.cancel(childSpace);
			} else if
			((bEvent.getX() > parent.width || bEvent.getX() < 0 ||
					bEvent.getY() > parent.height || bEvent.getY() < 0)
					&& bEvent.getID() == BehaviorEvent.MOUSE_UP_ID 
					)
			{
				m_currentBehavior.cancel(childSpace);
			} else if(bEvent.matches(m_currentBehavior.getStopEvent()))
			{
				// check if should stop
				m_currentBehavior.stop(childSpace);
			}  else if(bEvent.matches(m_currentBehavior.getRunningEvent()))
			{
				// give event to current active behavior
				m_currentBehavior.running(childSpace);
			}
			// check if behavior is now idle, if so, set to null
			if(m_currentBehavior.getState() == Behavior.IDLE)
			{
				m_currentBehavior = null;
			}

		}
	}

	private void onDispatchCompleted()
	{
		// check if we have damaged the screen
		if(m_screenDirty)
		{
			parent.redraw();
		}
	}

	
	void updateKeyModifier(Event event)
	{
		m_modifier = 0x0;
		if(event.isShiftDown()) m_modifier |= BehaviorEvent.SHIFT_MODIFIER;
		if(event.isControlDown()) m_modifier |= BehaviorEvent.CONTROL_MODIFIER;
		if(event.isMetaDown()) m_modifier |= BehaviorEvent.COMMAND_KEY_MODIFIER;
		if(event.isAltDown()) m_modifier |= BehaviorEvent.ALT_MODIFIER;
	}
	public void addBehavior (Behavior inter)
	{
		m_behaviors.add(inter);
	}

	public void removeBehavior (Behavior inter)
	{
		m_behaviors.remove(inter);
		if(m_currentBehavior == inter)
		{
			m_currentBehavior = null;
		}
	}
	
	public void clearBehaviors()
	{
		m_currentBehavior = null;
		m_behaviors.clear();
	}

}
