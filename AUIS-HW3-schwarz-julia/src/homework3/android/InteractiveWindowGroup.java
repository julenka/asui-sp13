package homework3.android;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;

public class InteractiveWindowGroup extends WindowGroup {

	List<Behavior> m_behaviors = new ArrayList<Behavior>();
    
	public InteractiveWindowGroup() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		drawView.setOnTouchListener(new InteractiveWindowTouchListener(this));
		drawView.setOnKeyListener(new InteractiveWindowKeyListener(this));
	}
	
	public List<Behavior> getBehaviors()
	{
		return m_behaviors;
	}
	
	public void addBehavior (Behavior inter)
	{
		m_behaviors.add(inter);
	}
	
    public void removeBehavior (Behavior inter)
    {
    	m_behaviors.remove(inter);
    }

    class InteractiveWindowTouchListener implements OnTouchListener
    {
    	InteractiveWindowGroup m_parent;
    	public InteractiveWindowTouchListener(InteractiveWindowGroup p)
    	{
    		m_parent = p;
    	}
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			return false;
		}
    
    }
    
    class InteractiveWindowKeyListener implements OnKeyListener
    {
    	InteractiveWindowGroup m_parent;
    	public InteractiveWindowKeyListener(InteractiveWindowGroup p)
    	{
    		m_parent = p;
    	}
		@Override
		public boolean onKey(View v, int keyCode, KeyEvent event) {
			// TODO Auto-generated method stub
			return false;
		}
    	
    }
}


