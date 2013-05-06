package homework5.processing.test;

import homework5.processing.core.InteractiveFrame;
import processing.core.PApplet;

/**
 * Base class for testing features in interactive graphical object system
 * (i.e. InteractiveFrame)
 * @author julenka
 *
 */
public abstract class BaseInteractiveTest extends PApplet {
	protected InteractiveFrame testFrame;

	@Override
	public void setup() {
		size(720, 1080, JAVA2D);
		background(255);
		testFrame = new InteractiveFrame(this);
		setupTest();
	}
	
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
