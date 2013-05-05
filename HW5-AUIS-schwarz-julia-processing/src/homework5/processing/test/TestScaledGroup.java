package homework5.processing.test;

import homework5.processing.graphicalobject.FilledRect;
import homework5.processing.graphicalobject.GraphicalObject;
import homework5.processing.graphicalobject.OutlineRect;
import homework5.processing.graphicalobject.ScaledGroup;
import homework5.processing.graphicalobject.SimpleGroup;

import java.awt.Color;

import processing.core.PApplet;

public class TestScaledGroup extends BaseGraphicalTest{
	int m_step = 0;
	SimpleGroup m_root;
	Color[] colors = { Color.BLACK, Color.RED, Color.BLUE };
	final int N_OBJECTS = 50;
	
	ScaledGroup m_group1;
	
	protected void setupTest() {
		SimpleGroup root = new SimpleGroup(0,0, width, height);
		testFrame.addChild(root);
		
		int nObjects = 10;
		
		println("creating black frame");
		
		

		println("creating ScaledGroup inside black frame, scale 1,1");
		m_group1 = new ScaledGroup(0, 0, width, height,1,1);
		root.addChild(m_group1);
		
		FilledRect background = new FilledRect(0,0,m_group1.getWidth(), m_group1.getHeight(), Color.LIGHT_GRAY);
		m_group1.addChild(background);
		m_group1.addChild(new OutlineRect(0, 0, m_group1.getWidth(), m_group1.getHeight(), Color.BLACK, 1));
		println("creating Rects at random places");
		GraphicalObject[] objects = new GraphicalObject[nObjects];
		for (int i = 0; i < nObjects; ++i) {
			objects[i] = new OutlineRect(-20 + (int)random(200), -20 + (int)random(200),
					(int)random(100), (int)random(100), Color.BLACK, 1);
			try {
				m_group1.addChild(objects[i]);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		

	}
	
	@Override
	public void mouseClicked() {
		// TODO Auto-generated method stub
		super.mouseClicked();
		m_step++;
		
		if (m_step == 1)
		{
			println("setting scale to 2,2");
			m_group1.setScaleX(2);
			m_group1.setScaleY(2);
		} else if (m_step == 2)
		{
			println("setting scale to 3,1");
			m_group1.setScaleX(3);
			m_group1.setScaleY(1);
		} else if (m_step == 3)
		{
			println("setting width, height to 200, 200");
			m_group1.setWidth(200);
			m_group1.setHeight(200);
		} else if (m_step == 4)
		{
			println("Created nested rectangles, 20 deep");
			ScaledGroup nested = new ScaledGroup(0, 0, width, height,1,1);
			int levels = 20;
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
			println("Changing nested scale factor from 0.1 to 2 with a step of 0.1");
			for (double i = 0.1; i <= 2; i+=0.1) {
				nested.setScaleX(i);
				nested.setScaleY(i);
			}

		} else {
			exit();
		}
	}

	public static void main(String args[]) {
		PApplet.main(new String[] { "homework5.processing.test.TestScaledGroup" });
	}

}
