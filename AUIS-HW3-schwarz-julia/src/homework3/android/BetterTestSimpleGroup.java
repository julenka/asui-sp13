package homework3.android;

import android.graphics.Color;
import android.os.Bundle;

public class BetterTestSimpleGroup extends BetterTestFrame {

	public BetterTestSimpleGroup() {


	}

	@Override
	protected void test() {
		// After implementing OutlineRect and SimpleGroup, uncomment the testing code

		SimpleGroup root = new SimpleGroup(0,0, drawView.getWidth(), drawView.getHeight());
		addChild(root);
		int nObjects = 10;
		try {
			Bundle extras = getIntent().getExtras();
			nObjects = Integer.parseInt(extras.getString("nObject"));
			println("nObjects = " + nObjects);
		} catch (Exception e) {
			println("usage:  TestSimpleGroup [# of graphical objects]\n"
					+ "using " + nObjects + " objects by default");
		}

		println("creating black frame");
		root.addChild(new OutlineRect(9, 9, 182, 182, Color.BLACK, 1));

		println("creating SimpleGroup inside black frame");
		SimpleGroup group = new SimpleGroup(10, 10, 180, 180);
		root.addChild(group);

		FilledRect background = new FilledRect(0,0,180,180, Color.LTGRAY);
		group.addChild(background);
		println("creating Rects at random places");
		GraphicalObject[] objects = new GraphicalObject[nObjects];
		int[] colors = { Color.BLACK, Color.RED, Color.BLUE };
		for (int i = 0; i < nObjects; ++i) {
			objects[i] = new OutlineRect(-20 + random(200), -20 + random(200),
					random(100), random(100), random(colors), 1);
			try {
				group.addChild(objects[i]);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		redraw(root);

		pause();
		println("click to resize to children");
		group.resizeToChildren();
		background.setWidth(group.getBoundingBox().width);
		background.setHeight(group.getBoundingBox().height);
		redraw(root);
		pause();
		
		println("Created nested rectangles, 10 deep");
		SimpleGroup nested = new SimpleGroup(200, 200, 200, 200);
		int levels = 10;
		int step = 10;
		SimpleGroup cur = nested;
		root.addChild(cur);
		for (int i = 0; i < levels; i++) {
			// Add and outlinerect in this group
			cur.addChild(new FilledRect(0,0, cur.getWidth(), cur.getHeight(), colors[i % colors.length]));
			SimpleGroup child = new SimpleGroup(step, step, cur.getWidth() - 2 * step, cur.getHeight() - 2 * step);
			cur.addChild(child);
			cur = child;
		}
		redraw(root);

		println("moving rectangles 50 times");
		println("hit back key to stop");
		for (int i = 0; i < 50; ++i) {
			GraphicalObject gobj = (GraphicalObject) random(objects);
			gobj.moveTo(-20 + random(200), -20 + random(200));
			group.bringChildToFront(gobj);
			redraw(root);
			sleep(50);
		}
		println("done moving");
		TestMoveTo(0, 0, drawView.getWidth() - group.getWidth(), drawView.getHeight() - group.getHeight(), 5, group, root);
	}

}

