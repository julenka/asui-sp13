package homework5.processing.test;

import homework5.processing.core.GraphicalObjectFrame;
import processing.core.PApplet;

public  abstract class BaseGraphicalTest extends PApplet {
	protected GraphicalObjectFrame testFrame;

	@Override
	public void setup() {
		size(720, 1080, JAVA2D);
		background(255);
		testFrame = new GraphicalObjectFrame(this);
		setupTest();
	}
	
	int count = 0;
	
	/**
	 * Add UI elements to testFrame here, e.g:
	 * testFrame.addChild(new Text...);
	 */
	protected abstract void setupTest();
	
	@Override
	public void draw() {
		testFrame.draw();
	}


}
