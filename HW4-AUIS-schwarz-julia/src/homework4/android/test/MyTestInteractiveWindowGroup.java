package homework4.android.test;

import homework4.android.app.InteractiveWindowGroup;
import homework4.android.behavior.ChoiceBehavior;
import homework4.android.behavior.MoveBehavior;
import homework4.android.behavior.NewLineBehavior;
import homework4.android.graphicalobject.FilledRect;
import homework4.android.graphicalobject.GraphicalObject;
import homework4.android.graphicalobject.Group;
import homework4.android.graphicalobject.LayoutGroup;
import homework4.android.graphicalobject.OutlineRect;
import homework4.android.graphicalobject.ScaledGroup;
import homework4.android.graphicalobject.SelectionHandles;
import homework4.android.graphicalobject.Text;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;

// Base class for testing interactive windows
public class MyTestInteractiveWindowGroup extends InteractiveWindowGroup {
	ChoiceBehavior m_choiceBehavior;
	
	private SelectionHandles makeSelectableObject(GraphicalObject child)
	{
		SelectionHandles result = new SelectionHandles(0, 0, 100, 100);
		result.addChild(child);
		return result;
	}

	private void addLabelToGroup(Group g, String label )
	{
		g.addChild(new Text(label, 0, 0, Typeface.DEFAULT, 18, Color.BLACK));
	}
	
	private void addBorderToGroup(Group g)
	{
		g.addChild(new OutlineRect(0,0, g.getBoundingBox().width, g.getBoundingBox().height, Color.BLACK, 2));
	}
	
	@Override
	protected void setup()
	{
		addLabelToGroup(this, "create lines in root");
		
		Group moveGroup = new ScaledGroup(0, 100, drawView.getWidth(), 400, 2,2);
		
		addLabelToGroup(moveGroup, "move objects");
		moveGroup.addChild(new Text("This is just a test", 20, 50, Typeface.create("Helvetica", Typeface.NORMAL), 22, Color.GREEN));
		addChild(moveGroup);
		
		LayoutGroup buttons = new LayoutGroup(10, 10, 700, 400 , HORIZONTAL, 10);
		for (int i = 0; i < 5; i++) {
			buttons.addChild(makeSelectableObject(new FilledRect(0,0,50,50, Color.GREEN)));
		}
		
		addChild(buttons);
		m_choiceBehavior = new ChoiceBehavior(buttons);
		m_behaviors.add(m_choiceBehavior);
		m_behaviors.add(new MoveBehavior(moveGroup));
		m_behaviors.add(new NewLineBehavior(Color.MAGENTA, 5, moveGroup));
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode == KeyEvent.KEYCODE_S)
		{
			m_choiceBehavior.setType((m_choiceBehavior.getType() + 1) % 3);
			println("choice behavior is " + m_choiceBehavior.getType());
		} else if (keyCode == KeyEvent.KEYCODE_F)
		{
			m_choiceBehavior.setFirstOnly(!m_choiceBehavior.isFirstOnly());
			println("first only is " + m_choiceBehavior.isFirstOnly());
		}
		return super.onKeyDown(keyCode, event);
	}

}
