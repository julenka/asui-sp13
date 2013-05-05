package homework5.processing.test;

import homework5.processing.core.GraphicalObjectFrame;
import homework5.processing.core.InteractiveFrame;
import processing.core.PApplet;

public abstract class BaseInteractiveTest extends PApplet {
	protected InteractiveFrame testFrame;

	@Override
	public void setup() {
		size(720, 1080, JAVA2D);
		background(255);
		testFrame = new InteractiveFrame(this);
		setupTest();
	}
	
	int count = 0;
	
	protected abstract void setupTest();
	
	@Override
	public void draw() {
		testFrame.draw();
	}
}
