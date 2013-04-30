package homework5.processing.test;

import homework5.processing.core.WindowGroup;
import homework5.processing.graphicalobject.FilledRect;
import homework5.processing.graphicalobject.Icon;
import homework5.processing.graphicalobject.Line;
import homework5.processing.graphicalobject.OutlineRect;
import homework5.processing.graphicalobject.Text;

import java.awt.Color;


public class TestAllObjects extends WindowGroup{
	
	@Override
	public void setup() {
		super.setup();
		// after implementing all objects, uncomment the testing code
		addChild(new OutlineRect(10, 10, 50, 50, Color.BLACK, 1));
		addChild(new OutlineRect(70, 10, 80, 50, Color.RED, 2));


		addChild(new FilledRect(10, 70, 50, 50, Color.BLACK));
		addChild(new FilledRect(70, 70, 80, 50, Color.RED));

		addChild(new Line(10, 130, 10, 180, Color.BLACK, 1));
		addChild(new Line(20, 130, 60, 130, Color.RED, 3));
		addChild(new Line(70, 130, 120, 180, Color.BLUE, 10));

		// TODO make sure that the data directory is in bin directoy of output sketch
		addChild(new Icon(loadImage("duke.gif"), 10, 200));
		addChild(new Icon(loadImage("dog.gif"), 80, 200));

		addChild(new Text("going", 10, 350, createFont("Helvetica", 24), 24, Color.BLACK));
		addChild(new Text("going", 70, 350, createFont("Helvetica", 24), 24, Color.RED));
		addChild(new Text("gone", 140, 350, createFont("Helvetica", 24), 24, Color.BLUE)); 				
		addChild(new Line(10, 350, 250, 350, Color.BLACK, 1));

	}
}
