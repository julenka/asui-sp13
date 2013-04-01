package homework4.android.test;

import homework4.android.graphicalobject.GraphicalObject;
import homework4.android.graphicalobject.Group;
import homework4.android.graphicalobject.LayoutGroup;
import homework4.android.graphicalobject.OutlineRect;
import android.graphics.Color;
import android.os.Bundle;

/**
 * More thorough TestLayoutGroup, also doesn't clip the outline rect as occurs in the TestLayoutGroup code.
 * @author julenka
 *
 */
public class BetterTestLayoutGropu extends BetterTestFrame {

	public BetterTestLayoutGropu() {
	}
	
	private void runLayoutTest(int nObjects, int layoutType)
	{
		println("creating black frame");
		addChild(new OutlineRect(9, 9, 481, 481, Color.BLACK, 1));

		println("creating LayoutGroup inside black frame");
		Group group = new LayoutGroup(10, 10, 479, 479, layoutType, 0);
		addChild(group);

		println("creating random Rects");
		GraphicalObject[] objects = new GraphicalObject[nObjects];
		int[] colors = { Color.BLACK, Color.RED, Color.BLUE };
		for (int i = 0; i < nObjects; ++i) {
			objects[i] = new OutlineRect(random(200), random(200), 30 + random(20),
					30 + random(20), random(colors), 1 + random(5));
			group.addChild(objects[i]);
		}

		redraw(group);

		println("shuffling rectangles 10 times");
		GraphicalObject front = objects[objects.length - 1];
		for (int i = 0; i < 10; ++i) {
			GraphicalObject gobj;
			while ((gobj = (GraphicalObject) random(objects)) == front)
				;
			group.bringChildToFront(gobj);
			front = gobj;
			redraw(group);
			sleep(100);
		}

		println("doubling rectangle widths");
		for (int i = 0; i < objects.length; ++i) {
			OutlineRect r = (OutlineRect) objects[i];
			if(layoutType == HORIZONTAL)
				r.setWidth(r.getWidth() * 2);
			else
				r.setHeight(r.getHeight() * 2);
			redraw(group);
			sleep(100);
		}
		println("close the window to exit");
	}
	
	protected void test(){
		println("Running on horizontal layout");
		runLayoutTest(5, Group.HORIZONTAL);
		pause();
		println("Running on vertical layout");
		runLayoutTest(5, Group.VERTICAL);
	}

}
