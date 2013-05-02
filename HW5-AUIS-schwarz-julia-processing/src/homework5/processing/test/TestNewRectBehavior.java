package homework5.processing.test;

import homework5.processing.behavior.NewRectBehavior;
import homework5.processing.core.InteractiveWindowGroup;

import java.awt.Color;

public class TestNewRectBehavior extends InteractiveWindowGroup {
	@Override
	public void setup() {
		// TODO make it so that you don't have to call super.setup()!
		super.setup();
		m_behaviors.add(new NewRectBehavior(Color.BLACK, 5, this));
	}

}
