package homework3.android;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;

public abstract class InteractiveWindowGroup extends WindowGroup {
	static final String LOG_TAG = "Homework3";

	List<Behavior> m_behaviors = new ArrayList<Behavior>();
	Behavior m_currentBehavior;
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
		// TODO: support multitouch using touchmap (need to totally change, just need to
		// update the touchmap with valued and look at all of them...
		int eventId = 0;
		int modifiers = m_keyModifier;
		int key = BehaviorEvent.LEFT_MOUSE_KEY;
		float x = m.getX();
		float y = m.getY();
		switch(m.getAction())
		{
		case MotionEvent.ACTION_DOWN:
			eventId = BehaviorEvent.MOUSE_DOWN_ID;
			break;
		case MotionEvent.ACTION_MOVE:
			Log.v(LOG_TAG, "action_move");
			eventId = BehaviorEvent.MOUSE_MOVE_ID;
			break;
		case MotionEvent.ACTION_UP:
			eventId = BehaviorEvent.MOUSE_UP_ID;
			break;
		}
		return new BehaviorEvent(eventId, modifiers, key, (int)x, (int)y);
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
		// update modifier keys
		updateModifier(event.getMetaState());
		dispatchBehaviorEvent(bEvent);
		onDispatchCompleted();
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
					Log.e(LOG_TAG, "group for behavior is null!");
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
			if(m_currentBehavior.getGroup() == null)
			{
				Log.w(LOG_TAG, "Current behavior's group is null! Setting current behavior to null");
				m_currentBehavior = null;
				return;
			}
			BehaviorEvent childSpace = behaviorEventToGroup(bEvent, m_currentBehavior.getGroup());
			if(bEvent.matches(m_currentBehavior.getStopEvent()))
			{
				// TODO: check if null currentBehavior's group is null

				// check if should stop
				m_currentBehavior.stop(childSpace);
			} else if(bEvent.matches(m_currentBehavior.getCancelEvent()))
			{
				m_currentBehavior.cancel(childSpace);
			}
			else if(bEvent.matches(m_currentBehavior.getRunningEvent()))
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
		Log.v(LOG_TAG, "dispatch completed");
		// check if we have damaged the screen
		if(savedClipRect != null && screenDirty)
		{
			Log.v(LOG_TAG, "screen dirty, redrawing...");
			redraw();
		}
	}
	
	int m_keyModifier = 0x0;
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event)
	{
		super.onKeyUp(keyCode, event);
		// update modifier keys
		updateModifier(event.getMetaState());
		BehaviorEvent toDispatche = new BehaviorEvent(BehaviorEvent.KEY_UP_ID, m_keyModifier, keyCode);
		dispatchBehaviorEvent(toDispatche);
		Log.v(LOG_TAG, "KeyUp " + event.getKeyCode());
		onDispatchCompleted();
		return false;
	}
	

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		super.onKeyDown(keyCode, event);
		updateModifier(event.getMetaState());
		BehaviorEvent toDispatche = new BehaviorEvent(BehaviorEvent.KEY_DOWN_ID, m_keyModifier, keyCode);
		dispatchBehaviorEvent(toDispatche);
		Log.v(LOG_TAG, "KeyDown " + event.getKeyCode());
		onDispatchCompleted();
		return true;
	}
	
	// Updates modifier keys based on getMetaState() returned method
	private void updateModifier(int metaState)
	{
		// TODO: actuall get modifiers to work
		int toMask = 0;
		m_keyModifier = metaState;
//		if(keyCode == KeyEvent.KEYCODE_SHIFT_LEFT || keyCode == KeyEvent.KEYCODE_SHIFT_RIGHT){
//			toMask = BehaviorEvent.SHIFT_MODIFIER;
//		} else if(keyCode == KeyEvent.KEYCODE_CTRL_LEFT || keyCode == KeyEvent.KEYCODE_CTRL_RIGHT)
//		{
//			toMask = BehaviorEvent.CONTROL_MODIFIER;
//		} else if(keyCode == KeyEvent.KEYCODE_ALT_LEFT || keyCode == KeyEvent.KEYCODE_ALT_RIGHT)
//		{
//			toMask = BehaviorEvent.ALT_MODIFIER;
//		} else if(keyCode == KeyEvent.KEYCODE_WINDOW)
//		{
//			toMask = BehaviorEvent.WINDOWS_KEY_MODIFIER;
//		} else if(keyCode == KeyEvent.KEYCODE_FUNCTION)
//		{
//			toMask = BehaviorEvent.FUNCTION_KEY_MODIFIER;
//		} 
//		// TODO: no command modifier
//		if(!isDown)
//		{
//			toMask = toMask ^ 0xffffff;
//			m_keyModifier = m_keyModifier & toMask;
//		} else
//		{
//			m_keyModifier = m_keyModifier | toMask;	
//		}
//		Log.v(LOG_TAG, String.format("new key modifier: %#8x", m_keyModifier) );
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


