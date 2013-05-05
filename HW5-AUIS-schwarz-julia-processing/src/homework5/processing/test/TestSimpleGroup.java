package homework5.processing.test;

import homework5.processing.core.WindowGroup;
import homework5.processing.graphicalobject.FilledRect;
import homework5.processing.graphicalobject.GraphicalObject;
import homework5.processing.graphicalobject.OutlineRect;
import homework5.processing.graphicalobject.SimpleGroup;

import java.awt.Color;

import processing.core.PApplet;



public class TestSimpleGroup extends TestBase {
	
	int m_step = 0;
	SimpleGroup m_root;
	Color[] colors = { Color.BLACK, Color.RED, Color.BLUE };
	final int N_OBJECTS = 50;
	
	int m_counter = Integer.MAX_VALUE;
	GraphicalObject[] m_toMove = new GraphicalObject[N_OBJECTS]; 
	
	public void setupTest() {
		
		println("creating SimpleGroup");
		m_root = new SimpleGroup(0,0, width, height);
		testFrame.addChild(m_root);
		println("creating black frame");
		m_root.addChild(new OutlineRect(9, 9, 182, 182, Color.BLACK, 1));
		println("creating SimpleGroup inside black frame");
		SimpleGroup group = new SimpleGroup(10, 10, 180, 180);
		m_root.addChild(group);
		
		FilledRect background = new FilledRect(0,0,180,180, Color.LIGHT_GRAY);
		group.addChild(background);
		println("creating Rects at random places");
		
		for (int i = 0; i < N_OBJECTS; ++i) {
			m_toMove[i] = new OutlineRect(-20 + (int)random(200), -20 + (int)random(200),
					(int)random(100), (int)random(100), colors[(int)(random(colors.length))], 1);
			try {
				group.addChild(m_toMove[i]);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		println("Click to move group with setX(), setY()");
	}

	@Override
	public void mouseClicked() {
		// TODO Auto-generated method stub
		super.mouseClicked();
		m_step++;
		
		if (m_step == 1)
		{
			m_root.setX(100);
			m_root.setY(300);	
			println("Click to move group with moveTo()");
		} else if (m_step == 2)
		{
			m_root.moveTo(200, 150);
			println("Click to add nested groups");
		} else if(m_step == 3)
		{
			SimpleGroup nested = new SimpleGroup(20, 20, 200, 200);
			int levels = 10;
			int step = 10;
			SimpleGroup cur = nested;
			testFrame.addChild(cur);
			for (int i = 0; i < levels; i++) {
				// Add and outlinerect in this group
				cur.addChild(new FilledRect(0,0, cur.getWidth(), cur.getHeight(), colors[i % colors.length]));
				SimpleGroup child = new SimpleGroup(step, step, cur.getWidth() - 2 * step, cur.getHeight() - 2 * step);
				cur.addChild(child);
				cur = child;
			}
			println("Click to move objects around a bunch");
		} else if (m_step == 4) {
			m_counter = 0;
			frameRate(30);
			println("Done! Click to exit");
		} else {
			exit();
		}
		
	}
	@Override
	public void draw() {
		super.draw();
		int timesToMove = 50;
		if(m_step == 4 && m_counter < timesToMove)
		{
			int i = m_counter % m_toMove.length;
			m_toMove[i].moveTo((int)random(200) - 50, (int)random(200) - 50);
			m_counter++;
		}
	}
	

	public static void main(String args[]) {
		PApplet.main(new String[] { "homework5.processing.test.TestSimpleGroup" });
	}

}
