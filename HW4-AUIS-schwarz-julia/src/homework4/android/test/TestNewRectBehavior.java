package homework4.android.test;

import homework4.android.app.InteractiveWindowGroup;
import homework4.android.behavior.NewRectBehavior;
import android.graphics.Color;
import android.graphics.Typeface;

public class TestNewRectBehavior extends InteractiveWindowGroup {
	@Override
	protected void setup() {
		m_behaviors.add(new NewRectBehavior(Color.BLACK, 6, this));
	}

}
