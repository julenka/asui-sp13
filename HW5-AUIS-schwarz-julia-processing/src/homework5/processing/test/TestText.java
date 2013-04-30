package homework5.processing.test;

import homework5.processing.core.WindowGroup;
import homework5.processing.graphicalobject.Text;

import java.awt.Color;



public class TestText extends WindowGroup {
	
	int m_step = 0;
	Text m_text; 
	
	@Override
	public void setup() {
		super.setup();
		println("creating Text");
		m_text = new Text("going", 10, 350, createFont("Helvetica", 24), 24, Color.BLACK);
		addChild(m_text);
		println("Click to move text with setX(), setY()");
	}

	@Override
	public void mouseClicked() {
		// TODO Auto-generated method stub
		super.mouseClicked();
		m_step++;
		if (m_step == 1)
		{
			m_text.setX(100);
			m_text.setY(300);	
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
	

}
