package homework5.processing.test;

import homework5.processing.behavior.MoveBehavior;
import homework5.processing.constraints.IPropertyChangedListener;
import homework5.processing.graphicalobject.OutlineRect;
import homework5.processing.graphicalobject.Text;

import java.awt.Color;

import processing.core.PApplet;

public class TestNotifyPropertyChanged extends BaseInteractiveTest {

	@Override
	public void setupTest() {
		final OutlineRect r = new OutlineRect(20, 20, 100, 100, Color.BLUE, 5);
		testFrame.addChild(r);
		
		// add a rectangle
		testFrame.addBehavior(new MoveBehavior(testFrame));
		
		final Text t = new Text("box location goes here", 10, 20, createFont("Helvetica", 24), 18, Color.BLACK);
		testFrame.addChild(t);
		r.addPropertyChangedListener("Y", new IPropertyChangedListener<Integer>() {
			
			@Override
			public void onPropertyChanged(Integer oldValue, Integer newValue) {
				t.setText(r.getX() + ", " + r.getY());
			}
		});
		
		
		r.addPropertyChangedListener("Y", new IPropertyChangedListener<Integer>() {
			
			@Override
			public void onPropertyChanged(Integer oldValue, Integer newValue) {
				t.setText(r.getX() + ", " + r.getY());
			}
		});
	}
	

	public static void main(String args[]) {
		PApplet.main(new String[] { "homework5.processing.test.TestNotifyPropertyChanged" });
	}


}
