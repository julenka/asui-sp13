package homework4.android.test;

import homework4.android.app.InteractiveWindowGroup;
import homework4.android.behavior.MoveBehavior;
import homework4.android.constraints.IPropertyChangedListener;
import homework4.android.graphicalobject.OutlineRect;
import homework4.android.graphicalobject.Text;
import android.graphics.Color;
import android.graphics.Typeface;

public class MyTestNotifyPropertyChanged extends InteractiveWindowGroup {

	@Override
	protected void setup() {
		final OutlineRect r = new OutlineRect(20, 20, 100, 100, Color.BLUE, 5);
		addChild(r);
		
		// add a rectangle
		addBehavior(new MoveBehavior(this));
		
		final Text t = new Text("box location goes here", 10, 20, Typeface.SANS_SERIF, 18, Color.BLACK);
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
