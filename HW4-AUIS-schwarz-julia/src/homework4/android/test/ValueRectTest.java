package homework4.android.test;

import homework4.android.app.InteractiveWindowGroup;
import homework4.android.behavior.MoveXBehavior;
import homework4.android.constraints.EqualConstraint;
import homework4.android.constraints.GraphicalObjectProperty;
import homework4.android.graphicalobject.Slider;
import homework4.android.graphicalobject.Text;
import homework4.android.graphicalobject.ValueRect;
import android.graphics.Color;
import android.graphics.Typeface;

public class ValueRectTest extends InteractiveWindowGroup {

	@Override
	protected void setup() {
		Text txt = new Text("move slider to change thickness & value", 10, 50,Typeface.DEFAULT, 20, Color.BLACK);
		addChild(txt);
		
		Slider s = new Slider(0, 100, drawView.getWidth(), 50, 30, Color.BLACK, 50);
		addChild(s);
		m_behaviors.add(new MoveXBehavior(s.getSlider()));
		
		ValueRect r = new ValueRect(2, 200, 300, 200, Color.BLACK, 3, 0);
		addChild(r);
		GraphicalObjectProperty<Integer> rValue = new GraphicalObjectProperty<Integer>(r, "Value");
		GraphicalObjectProperty<Integer> sliderValue = new GraphicalObjectProperty<Integer>(s, "Value");
		GraphicalObjectProperty<Integer> rThickness = new GraphicalObjectProperty<Integer>(r, "LineThickness");
		new EqualConstraint(rValue, sliderValue).activate();
		new EqualConstraint(rThickness, rValue).activate();
	}
	
}