package homework5.processing.test;

import homework5.processing.behavior.MoveBehavior;
import homework5.processing.constraints.IPropertyChangedListener;
import homework5.processing.core.InteractiveWindowGroup;
import homework5.processing.graphicalobject.OutlineRect;
import homework5.processing.graphicalobject.Text;

import java.awt.Color;

public class TestNotifyPropertyChanged extends InteractiveWindowGroup {

	@Override
	public void setup() {
		super.setup();
		final OutlineRect r = new OutlineRect(20, 20, 100, 100, Color.BLUE, 5);
		addChild(r);
		
		// add a rectangle
		addBehavior(new MoveBehavior(this));
		
		final Text t = new Text("box location goes here", 10, 20, createFont("Helvetica", 24), 18, Color.BLACK);
		addChild(t);
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
	
	

}
