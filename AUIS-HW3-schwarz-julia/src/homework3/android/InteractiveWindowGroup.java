package homework3.android;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public abstract class InteractiveWindowGroup extends WindowGroup {
	static final String LOG_TAG = "Homework3";

	float m_lastX;
	float m_lastY;
	List<Behavior> m_behaviors = new ArrayList<Behavior>();
	Behavior m_currentBehavior;
	int m_modifier = 0x0;
	
	public InteractiveWindowGroup() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		drawView.setOnTouchListener(new OnTouchListener()
		{
			public boolean onTouch(View v, MotionEvent event) 
			{
				return onDrawViewTouchEvent(event);
			}
		}
				);
	}

	private BehaviorEvent motionToBehaviorEvent(MotionEvent m)
	{
		// update the touchmap with valued and look at all of them...
		int eventId = 0;
		int key = BehaviorEvent.LEFT_MOUSE_KEY;
		m_lastX = m.getX();
		m_lastY = m.getY();
		switch(m.getAction())
		{
		case MotionEvent.ACTION_DOWN:
			eventId = BehaviorEvent.MOUSE_DOWN_ID;
			break;
		case MotionEvent.ACTION_MOVE:
			eventId = BehaviorEvent.MOUSE_MOVE_ID;
			break;
		case MotionEvent.ACTION_UP:
			eventId = BehaviorEvent.MOUSE_UP_ID;
			break;
		}
//		Log.v(LOG_TAG, " key modifiers are " + m_modifier);
		return new BehaviorEvent(eventId, m_modifier, key, (int)m_lastX, (int)m_lastY);
	}

	private BehaviorEvent behaviorEventToGroup(BehaviorEvent in, Group g)
	{
		Point parentSpace = new Point(in.getX(), in.getY());
		Point childSpace = g.parentToChild(parentSpace);
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
		if(!b.getGroup().contains(bEvent.getX(), bEvent.getY())) return false;	
		if(!bEvent.matches(b.getStartEvent())) return false;
		return true;
	}

	public boolean onDrawViewTouchEvent(MotionEvent event)
	{
		//TODO: support multitouch
		BehaviorEvent bEvent = motionToBehaviorEvent(event);
		dispatchBehaviorEvent(bEvent);
		onDispatchCompleted();
		return true;
	}

	private void dispatchBehaviorEvent(BehaviorEvent bEvent)
	{
		if(m_currentBehavior == null)
		{
//			Log.v(LOG_TAG, "finding new behavior...");
			// find new behavior
			for (Behavior behavior : m_behaviors) {
				// exit once we have found a behavior
				if(m_currentBehavior != null) break;

				if(behavior.getGroup() == null)
				{
					Log.e(LOG_TAG, "group for behavior is null!");
					continue;
				}
//				Log.v(LOG_TAG, "checking behavior " + behavior.getClass().getSimpleName());
				if(canStartBehavior(behavior, bEvent))
				{
					Log.v(LOG_TAG, "starting behavior " + behavior.getClass().getSimpleName());
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
//			Log.v(LOG_TAG, "current behavior is " + m_currentBehavior.getClass().getSimpleName());
			if(m_currentBehavior.getGroup() == null)
			{
				Log.w(LOG_TAG, "Current behavior's group is null! Setting current behavior to null");
				m_currentBehavior = null;
				return;
			}
			BehaviorEvent childSpace = behaviorEventToGroup(bEvent, m_currentBehavior.getGroup());
			if(bEvent.matches(m_currentBehavior.getCancelEvent()))
			{
				Log.v(LOG_TAG, "canceling current behavior");
				m_currentBehavior.cancel(childSpace);
			} else if
			((bEvent.getX() > drawView.getWidth()|| bEvent.getX() < 0 ||
					bEvent.getY() > drawView.getHeight() || bEvent.getY() < 0)
					&& bEvent.getID() == BehaviorEvent.MOUSE_UP_ID 
					)
			{
				Log.v(LOG_TAG, "canceling current behavior");
				m_currentBehavior.cancel(childSpace);
			} else if(bEvent.matches(m_currentBehavior.getStopEvent()))
			{
				Log.v(LOG_TAG, "stopping current behavior");
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
		if(savedClipRect != null && screenDirty)
		{
			redraw();
		}
	}

	void updateKeyModifier(KeyEvent event)
	{
		m_modifier = 0x0;
		if(event.isShiftPressed()) m_modifier |= BehaviorEvent.SHIFT_MODIFIER;
		
	}
	
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event)
	{
		super.onKeyUp(keyCode, event);
		updateKeyModifier(event);
		// update modifier keys
		BehaviorEvent toDispatche = new BehaviorEvent(BehaviorEvent.KEY_UP_ID, m_modifier, keyCode, (int)m_lastX, (int)m_lastY);
		dispatchBehaviorEvent(toDispatche);
//		Log.v(LOG_TAG, "KeyUp " + keyCode);
		onDispatchCompleted();
		return false;
	}


	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		super.onKeyDown(keyCode, event);
		updateKeyModifier(event);
		BehaviorEvent toDispatche = new BehaviorEvent(BehaviorEvent.KEY_DOWN_ID, m_modifier, keyCode, (int)m_lastX, (int)m_lastY);
		dispatchBehaviorEvent(toDispatche);
//		Log.v(LOG_TAG, "KeyDown " + keyCode + " x: " + m_lastX + " y: " + m_lastY);
		onDispatchCompleted();
		return true;
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
}


