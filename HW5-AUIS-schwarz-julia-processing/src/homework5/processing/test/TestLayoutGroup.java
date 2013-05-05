package homework5.processing.test;

import homework5.processing.graphicalobject.GraphicalObject;
import homework5.processing.graphicalobject.Group;
import homework5.processing.graphicalobject.LayoutGroup;
import homework5.processing.graphicalobject.OutlineRect;
import homework5.processing.graphicalobject.SimpleGroup;

import java.awt.Color;

import processing.core.PApplet;

public class TestLayoutGroup extends BaseGraphicalTest {
	int m_step = 0;
	SimpleGroup m_root;
	Color[] colors = { Color.BLACK, Color.RED, Color.BLUE };
	final int N_OBJECTS = 50;
	
	int m_counter = Integer.MAX_VALUE;
	GraphicalObject[] m_toMove = new GraphicalObject[N_OBJECTS]; 
	LayoutGroup m_layout;
	
	public void setupTest() {
		testFrame.addChild(new OutlineRect(9, 9, 481, 181, Color.BLACK, 1));
		m_layout = new LayoutGroup(10, 10, 480, 180, Group.HORIZONTAL, 0);
		testFrame.addChild(m_layout);

		m_toMove = new GraphicalObject[N_OBJECTS];
		for (int i = 0; i < N_OBJECTS; ++i) {
			m_toMove[i] = new OutlineRect((int)random(200), (int)random(200), 30 + (int)random(20),
					30 + (int)random(20), Color.BLACK, 1 + (int)random(5));
			m_layout.addChild(m_toMove[i]);
		}

		println("click to shuffle order");
	}
	
	@Override
	public void mouseClicked() {
		super.mouseClicked();
		m_step++;
		
		if (m_step == 1)
		{
			GraphicalObject front = m_toMove[m_toMove.length - 1];
			for (int i = 0; i < 10; ++i) {
				GraphicalObject gobj;
				while ((gobj = (GraphicalObject) m_toMove[(int)random(m_toMove.length)]) == front)
					;
				m_layout.bringChildToFront(gobj);
				front = gobj;
			}
			println("click to double rectangle widths");
		} else if (m_step == 2)
		{
			for (int i = 0; i < m_toMove.length; ++i) {
				OutlineRect r = (OutlineRect) m_toMove[i];
				r.setWidth(r.getWidth() * 2);
			}
			println("close the window to exit");
		}  else {
			exit();
		}
		
	}
	

	public static void main(String args[]) {
		PApplet.main(new String[] { "homework5.processing.test.TestLayoutGroup" });
	}
}
