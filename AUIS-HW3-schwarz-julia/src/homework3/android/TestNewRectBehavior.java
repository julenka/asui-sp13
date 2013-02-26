package homework3.android;

import android.graphics.Color;

public class TestNewRectBehavior extends InteractiveWindowGroup {
	@Override
	protected void setup() {
		m_behaviors.add(new NewRectBehavior(Color.BLACK, 6, this));
	}

}
