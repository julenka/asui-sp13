package homework3.android;

import java.io.IOException;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;

public class TestWindowGroup extends WindowGroup {
	@Override
	protected void setup()
	{
		addChild(new OutlineRect(10, 10, 50, 50, Color.BLACK, 1));
		addChild(new OutlineRect(70, 10, 80, 50, Color.RED, 2));
		addChild(new FilledRect(10, 70, 50, 50, Color.BLACK));
		addChild(new FilledRect(70, 70, 80, 50, Color.RED));
		addChild(new Line(10, 130, 10, 180, Color.BLACK, 1));
		addChild(new Line(20, 130, 60, 130, Color.RED, 3));
		addChild(new Line(70, 130, 120, 180, Color.BLUE, 10));

		try {
			addChild(new Icon(loadImageFully("duke.gif"), 10, 200));
		} catch (IOException e) {
			println("duke.gif failed to load");
		}
		try {
			addChild(new Icon(loadImageFully("dog.gif"), 80, 200));
		} catch (IOException e) {
			println("dog.gif failed to load");
		}

		addChild(new Text("going", 10, 350, Typeface.create(Typeface.MONOSPACE, Typeface.NORMAL), 10, Color.BLACK));
		addChild(new Text("going", 70, 350, Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD), 14, Color.RED));
		addChild(new Text("gone", 140, 350, Typeface.create(Typeface.SERIF, Typeface.NORMAL), 24, Color.BLACK)); 				
		addChild(new Line(10, 350, 250, 350, Color.BLACK, 1));

		
		println("hit back key to exit");
	}

}
