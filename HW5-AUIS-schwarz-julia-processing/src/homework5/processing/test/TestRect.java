package homework5.processing.test;

import homework5.processing.graphicalobject.FilledRect;
import homework5.processing.graphicalobject.OutlineRect;

import java.awt.Color;

import processing.core.PApplet;



public class TestRect extends BaseGraphicalTest {
	
	int m_step = 0;
	FilledRect m_rect;
	OutlineRect m_outlineRect;
	
	@Override
	protected void setupTest() {
		println("creating FilledRect, OutlineRect (click to continue)");
		m_rect = new FilledRect(10, 10, 50, 50, Color.RED);
		m_outlineRect = new OutlineRect(10, 300, 40,40, Color.GREEN, 2);
		testFrame.addChild(m_rect);
		testFrame.addChild(m_outlineRect);
		println("Click to move rectangle with setX(), setY()");
	}

	@Override
	public void mouseClicked() {
		super.mouseClicked();
		m_step++;
		if (m_step == 1)
		{
			m_rect.setX(100);
			m_rect.setY(300);	
			m_outlineRect.setX(200);
			m_outlineRect.setY(100);
			println("Click to move rectangle with moveTo()");
		} else if (m_step == 2)
		{
			m_rect.moveTo(200, 200);
			m_outlineRect.moveTo(100, 100);
			println("Click to change rect to blue");
		} else if(m_step == 3)
		{
			m_rect.setColor(Color.BLUE);
			m_outlineRect.setColor(Color.MAGENTA);
			println("Click to change outlineRect width");
			println("Done! Click to exit");
		} else if (m_step == 4) {
			m_outlineRect.setLineThickness(10);
		} else {
			exit();
		}
		
	}
	
	public static void main(String args[]) {
		PApplet.main(new String[] { "homework5.processing.test.TestRect" });
	}
}
