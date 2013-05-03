package homework5.processing.test;

import homework5.processing.behavior.NewRectBehavior;
import homework5.processing.core.InteractiveWindowGroup;

import java.awt.Color;

import processing.core.PApplet;

public class TestNewRectBehavior extends InteractiveWindowGroup {
	@Override
	public void setup() {
		// TODO make it so that you don't have to call super.setup()!
		super.setup();
		m_behaviors.add(new NewRectBehavior(Color.BLACK, 5, this));
	}
	

	public static void main(String args[]) {
		PApplet.main(new String[] { "homework5.processing.test.TestNewRectBehavior" });
	}

}
