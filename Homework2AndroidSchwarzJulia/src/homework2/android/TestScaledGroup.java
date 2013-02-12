package homework2.android;

import java.io.IOException;

import android.graphics.Color;

public class TestScaledGroup extends BetterTestFrame {

	public TestScaledGroup() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void test() {
		SimpleGroup root = new SimpleGroup(0,0, drawView.getWidth(), drawView.getHeight());
		addChild(root);
		
		int nObjects = 10;
		
		println("creating black frame");
		
		

		println("creating ScaledGroup inside black frame, scale 1,1");
		ScaledGroup group = new ScaledGroup(0, 0, drawView.getWidth(), drawView.getHeight(),1,1);
		root.addChild(group);
		
		FilledRect background = new FilledRect(0,0,group.getWidth(), group.getHeight(), Color.LTGRAY);
		group.addChild(background);
		group.addChild(new OutlineRect(0, 0, group.getWidth(), group.getHeight(), Color.BLACK, 1));
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
		println("setting scale to 2,2");
		group.setScaleX(2);
		group.setScaleY(2);
		redraw(root);
		
		println("setting scale to 1,2");
		group.setScaleX(1);
		group.setScaleY(2);
		redraw(root);

		println("setting scale to 3,1");
		group.setScaleX(3);
		group.setScaleY(1);
		redraw(root);
		
		println("setting width, height to 200, 200");
		group.setWidth(200);
		group.setHeight(200);
		redraw(root);
		
		println("Testing MoveTo on group");
		TestMoveTo(0, 0, drawView.getWidth() - group.getWidth(), drawView.getHeight() - group.getHeight(), 5, group, root);
		
		
		println("Created nested rectangles, 20 deep");
		ScaledGroup nested = new ScaledGroup(0, 0, drawView.getWidth(), drawView.getHeight(),1,1);
		int levels = 20;
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
		
		println("Changing nested scale factor from 0.1 to 2 with a step of 0.1");
		for (double i = 0.1; i <= 2; i+=0.1) {
			nested.setScaleX(i);
			nested.setScaleY(i);
			redraw(root);
			sleep(100);
		}

	}

}
