package homework2.android;

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
		root.addChild(new OutlineRect(9, 9, 182, 182, Color.BLACK, 1));

		println("creating ScaledGroup inside black frame, scale 1,1");
		ScaledGroup group = new ScaledGroup(10, 10, 180, 180,1,1);
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
		println("setting scale to 2,2");
		group.setScaleX(2);
		group.setScaleY(2);
		redraw(root);
		pause();
		
		println("setting scale to 1,2");
		group.setScaleX(1);
		group.setScaleY(2);
		redraw(root);
		pause();
		
		println("setting scale to 3,1");
		group.setScaleX(3);
		group.setScaleY(1);
		redraw(root);
		pause();
		
		
		println("Testing MoveTo on group");
		TestMoveTo(0, 0, drawView.getWidth() - group.getWidth(), drawView.getHeight() - group.getHeight(), 5, group, root);
		pause();
	}

}
