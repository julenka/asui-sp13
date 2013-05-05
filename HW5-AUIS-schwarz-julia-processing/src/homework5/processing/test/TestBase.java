package homework5.processing.test;

import homework5.processing.core.UIFrame;
import processing.core.PApplet;

public  abstract class TestBase extends PApplet {
	protected UIFrame testFrame;

	@Override
	public void setup() {
		size(720, 1080, JAVA2D);
		background(255);
		testFrame = new UIFrame(this);
		setupTest();
	}
	
	int count = 0;
	
	protected abstract void setupTest();
	
	@Override
	public void draw() {
		testFrame.draw();
	}

	public static void main(String args[]) {
		PApplet.main(new String[] { "homework5.processing.test.TestBase" });
	}


}
