package homework3.android;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

// Base class for testing interactive windows
public class TestInteractiveWindowGroup extends InteractiveWindowGroup {
	@Override
	protected void setup()
	{
		addChild(new Text("This is just a test", m_drawView.getWidth() / 2, m_drawView.getHeight() / 2, Typeface.create("Helvetica", Typeface.NORMAL), 24, Color.GREEN));
		m_behaviors.add(new MoveBehavior(this));
		m_behaviors.add(new NewLineBehavior(Color.MAGENTA, 5, this));
	}

}
