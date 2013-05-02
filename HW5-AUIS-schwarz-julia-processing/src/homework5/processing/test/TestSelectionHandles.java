package homework5.processing.test;

import homework5.processing.core.InteractiveWindowGroup;
import homework5.processing.graphicalobject.GraphicalObject;
import homework5.processing.graphicalobject.LayoutGroup;
import homework5.processing.graphicalobject.SelectionHandles;

public class TestSelectionHandles extends InteractiveWindowGroup {

	private SelectionHandles makeSelectableObject(GraphicalObject child)
	{
		SelectionHandles result = new SelectionHandles(0, 0, 100, 100);
		result.addChild(child);
		return result;
	}
	
	@Override
	public void setup() {
		super.setup();
		LayoutGroup buttons = new LayoutGroup(10, 10, 700 - 10, 400, HORIZONTAL, 10);
		
		for (int i = 0; i < 5; i++) {
//			buttons.add(makeSelectableObject(new Text("object" + i, 0, 0,Typeface.create(Typeface.MONOSPACE, Typeface.NORMAL) , 14, Color.GREEN)));	
		}
	}

}
