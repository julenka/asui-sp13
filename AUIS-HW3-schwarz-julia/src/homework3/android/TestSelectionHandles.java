package homework3.android;

import android.graphics.Color;
import android.graphics.Typeface;

public class TestSelectionHandles extends InteractiveWindowGroup {

	private SelectionHandles makeSelectableObject(GraphicalObject child)
	{
		SelectionHandles result = new SelectionHandles(0, 0, 100, 100);
		result.addChild(child);
		return result;
	}
	
	@Override
	protected void setup() {
		LayoutGroup buttons = new LayoutGroup(10, 10, 700 - 10, 400, HORIZONTAL, 10);
		
		for (int i = 0; i < 5; i++) {
		}
		
	}

}
