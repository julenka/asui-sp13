package homework3.android;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

// Base class for testing interactive windows
public class TestInteractiveWindowGroup extends InteractiveWindowGroup {
	
	private SelectionHandles makeSelectableObject(GraphicalObject child)
	{
		SelectionHandles result = new SelectionHandles(0, 0, 100, 100);
		result.addChild(child);
		return result;
	}
	
	@Override
	protected void setup()
	{
		addChild(new Text("This is just a test", drawView.getWidth() / 2, drawView.getHeight() / 2, Typeface.create("Helvetica", Typeface.NORMAL), 24, Color.GREEN));
		
//		m_behaviors.add(new NewLineBehavior(Color.MAGENTA, 5, this));
		
		LayoutGroup buttons = new LayoutGroup(10, 10, 700, 400 , HORIZONTAL, 10);
		
		for (int i = 0; i < 5; i++) {
//			buttons.addChild(makeSelectableObject(new Text("object" + i, 0, 0,Typeface.create(Typeface.MONOSPACE, Typeface.NORMAL) , 14, Color.GREEN)));
			buttons.addChild(makeSelectableObject(new FilledRect(0,0,50,50, Color.GREEN)));
		}
		
		addChild(buttons);
		m_behaviors.add(new ChoiceBehavior(buttons));
//		m_behaviors.add(new MoveBehavior(this));
	}

}
