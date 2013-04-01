package homework4.android.test;

import homework4.android.app.InteractiveWindowGroup;
import homework4.android.graphicalobject.GraphicalObject;
import homework4.android.graphicalobject.LayoutGroup;
import homework4.android.graphicalobject.SelectionHandles;
import android.graphics.Color;
import android.graphics.Typeface;

public class MyTestSelectionHandles extends InteractiveWindowGroup {

	private SelectionHandles makeSelectableObject(GraphicalObject child)
	{
		SelectionHandles result = new SelectionHandles(0, 0, 100, 100);
		result.addChild(child);
		return result;
	}
	
	@Override
	protected void setup() {
		// TODO Auto-generated method stub
		
		LayoutGroup buttons = new LayoutGroup(10, 10, 700 - 10, 400, HORIZONTAL, 10);
		
		for (int i = 0; i < 5; i++) {
//			buttons.add(makeSelectableObject(new Text("object" + i, 0, 0,Typeface.create(Typeface.MONOSPACE, Typeface.NORMAL) , 14, Color.GREEN)));	
		}
		
		
		// add selection to the region

	}

}
