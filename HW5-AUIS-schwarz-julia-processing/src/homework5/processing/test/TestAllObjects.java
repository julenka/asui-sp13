package homework5.processing.test;

import homework5.processing.graphicalobject.FilledRect;
import homework5.processing.graphicalobject.Icon;
import homework5.processing.graphicalobject.Line;
import homework5.processing.graphicalobject.OutlineRect;
import homework5.processing.graphicalobject.Text;

import java.awt.Color;

import processing.core.PApplet;


public class TestAllObjects extends BaseGraphicalTest {

	@Override
	protected void setupTest() {
		// after implementing all objects, uncomment the testing code
		testFrame.addChild(new OutlineRect(10, 10, 50, 50, Color.BLACK, 1));
		testFrame.addChild(new OutlineRect(70, 10, 80, 50, Color.RED, 2));


		testFrame.addChild(new FilledRect(10, 70, 50, 50, Color.BLACK));
		testFrame.addChild(new FilledRect(70, 70, 80, 50, Color.RED));

		testFrame.addChild(new Line(10, 130, 10, 180, Color.BLACK, 1));
		testFrame.addChild(new Line(20, 130, 60, 130, Color.RED, 3));
		testFrame.addChild(new Line(70, 130, 120, 180, Color.BLUE, 10));

		testFrame.addChild(new Icon(loadImage("duke.gif"), 10, 200));
		testFrame.addChild(new Icon(loadImage("dog.gif"), 80, 200));

		testFrame.addChild(new Text("going", 70, 350, createFont("Helvetica", 24), 24, Color.RED));
		testFrame.addChild(new Text("going", 70, 350, createFont("Helvetica", 24), 24, Color.RED));
		testFrame.addChild(new Text("gone", 140, 350, createFont("Helvetica", 24), 24, Color.BLUE)); 				
		testFrame.addChild(new Line(10, 350, 250, 350, Color.BLACK, 1));

	}

	public static void main(String args[]) {
		PApplet.main(new String[] { "homework5.processing.test.TestAllObjects" });
	}
}
