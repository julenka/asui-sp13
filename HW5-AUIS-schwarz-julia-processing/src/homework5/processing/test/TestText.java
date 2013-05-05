package homework5.processing.test;

import homework5.processing.core.WindowGroup;
import homework5.processing.graphicalobject.Text;

import java.awt.Color;

import processing.core.PApplet;



public class TestText extends TestBase {
	
	int m_step = 0;
	Text m_text; 
	
	@Override
	public void setupTest() {
		println("creating Text");
		int x = 0; 
		int y = 0;
		int h = 0;
		while(y < height)
		{
			while(x < width)
			{
				
				Text toAdd = new Text("going", x, y, createFont("Helvetica", 24), 24, Color.BLACK);
				testFrame.addChild(toAdd);
				x+= toAdd.getBoundingBox().width;
				h = toAdd.getBoundingBox().height;
			}
			y+=h;
			x = 0;
		}
		m_text = new Text("going", 10, 350, createFont("Helvetica", 24), 24, Color.BLACK);
		println("Click to test changing propertiesmove text with setX(), setY()");
	}

	@Override
	public void mouseClicked() {
		// TODO Auto-generated method stub
		super.mouseClicked();
		m_step++;
		if (m_step == 1)
		{
			testFrame.clearChildren();
			testFrame.addChild(m_text);
			println("Click to move text with moveTo()");
		} else if (m_step == 2)
		{
			m_text.moveTo(200, 200);
			println("Click to change text to blue");
		} else if(m_step == 3)
		{
			m_text.setColor(Color.BLUE);
			println("Click to change text size");
			println("Done! Click to exit");
		} else if (m_step == 4) {
			m_text.setFontSize(50);
		} else {
			exit();
		}
		
	}

	public static void main(String args[]) {
		PApplet.main(new String[] { "homework5.processing.test.TestText" });
	}


}
