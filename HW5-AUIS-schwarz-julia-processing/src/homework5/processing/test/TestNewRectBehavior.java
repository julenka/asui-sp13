package homework5.processing.test;

import homework5.processing.behavior.NewRectBehavior;

import java.awt.Color;

import processing.core.PApplet;

public class TestNewRectBehavior extends BaseInteractiveTest {
	@Override
	public void setupTest() {
		testFrame.addBehavior(new NewRectBehavior(Color.BLACK, 5, testFrame));
	}
	

	public static void main(String args[]) {
		PApplet.main(new String[] { "homework5.processing.test.TestNewRectBehavior" });
	}

}
