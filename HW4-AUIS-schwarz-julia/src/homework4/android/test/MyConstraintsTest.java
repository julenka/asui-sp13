package homework4.android.test;

import android.graphics.Color;
import android.graphics.Typeface;
import homework4.android.app.InteractiveWindowGroup;
import homework4.android.behavior.MoveXBehavior;
import homework4.android.constraints.Constraint;
import homework4.android.constraints.EqualConstraint;
import homework4.android.constraints.GraphicalObjectHorizontalCenterConstraint;
import homework4.android.constraints.GraphicalObjectProperty;
import homework4.android.constraints.GraphicalObjectVerticalCenterConstraint;
import homework4.android.constraints.IntProductConstraint;
import homework4.android.constraints.IntVariable;
import homework4.android.constraints.Variable;
import homework4.android.graphicalobject.LayoutGroup;
import homework4.android.graphicalobject.OutlineRect;
import homework4.android.graphicalobject.SimpleGroup;
import homework4.android.graphicalobject.Slider;
import homework4.android.graphicalobject.Text;

public class MyConstraintsTest extends InteractiveWindowGroup {

	@Override
	protected void setup() {
		// make the UI
		LayoutGroup root = new LayoutGroup(0, 0, drawView.getWidth(), drawView.getHeight(), LayoutGroup.VERTICAL , 5);
		root.addChild(new Text("width:", 0, 0,Typeface.DEFAULT, 18, Color.BLACK));
		
		Slider s1 = new Slider(0, 0, drawView.getWidth(), 30, 30, Color.BLACK, 20);
		root.addChild(s1);
		m_behaviors.add(new MoveXBehavior(s1.getSlider()));
		root.addChild(new Text("height:", 0, 0,Typeface.DEFAULT, 18, Color.BLACK));
		Slider s2 = new Slider(0, 0, drawView.getWidth(), 30, 30, Color.BLACK, 20);
		root.addChild(s2);
		m_behaviors.add(new MoveXBehavior(s2.getSlider()));
		root.addChild(new Text("x:", 0, 0,Typeface.DEFAULT, 18, Color.BLACK));
		Slider s3 = new Slider(0, 0, drawView.getWidth(), 30, 30, Color.BLACK, 50);
		root.addChild(s3);
		m_behaviors.add(new MoveXBehavior(s3.getSlider()));
		root.addChild(new Text("y:", 0, 0,Typeface.DEFAULT, 18, Color.BLACK));
		Slider s4 = new Slider(0, 0, drawView.getWidth(), 30, 30, Color.BLACK, 20);
		root.addChild(s4);
		m_behaviors.add(new MoveXBehavior(s4.getSlider()));
		
		SimpleGroup sg = new SimpleGroup(0,0,drawView.getWidth(), drawView.getHeight());
		root.addChild(sg);
		
		OutlineRect blueRect = new OutlineRect (0, 0, 50, 80, Color.BLUE, 5);
		OutlineRect redRect = new OutlineRect (100, 0, 50, 80, Color.RED, 1);
		sg.addChild(redRect);
		sg.addChild(blueRect);
		
		// Build the constraints
		
		// make red width, height = 2 * blue width
		Variable<Integer> redWidth = new GraphicalObjectProperty<Integer>(redRect, "Width");
		Variable<Integer> redHeight = new GraphicalObjectProperty<Integer>(redRect, "Height");
		Variable<Integer> redY = new GraphicalObjectProperty<Integer>(redRect, "Y");
		Variable<Integer> redX = new GraphicalObjectProperty<Integer>(redRect, "X");
		Variable<Integer> blueWidth = new GraphicalObjectProperty<Integer>(blueRect, "Width");
		Variable<Integer> blueHeight = new GraphicalObjectProperty<Integer>(blueRect, "Height");
		
		// make width of red 2 * width of blue
		new IntProductConstraint(redWidth, blueWidth, new IntVariable(2) ).activate();
		new IntProductConstraint(redHeight, blueHeight, new IntVariable(2) ).activate();
		// center blue in red
		new GraphicalObjectHorizontalCenterConstraint(blueRect, redRect).activate();
		new GraphicalObjectVerticalCenterConstraint(blueRect, redRect).activate();
		// tie sliders to variable values 
		new EqualConstraint(redX, new GraphicalObjectProperty<Integer>(s3, "Value")).activate();
		new EqualConstraint(redY, new GraphicalObjectProperty<Integer>(s4, "Value")).activate();
		new EqualConstraint(blueWidth, new GraphicalObjectProperty<Integer>(s1, "Value")).activate();
		new EqualConstraint(blueHeight, new GraphicalObjectProperty<Integer>(s2, "Value")).activate();
		addChild(root);
		
		println("adjust the sliders to change properties of the rectangles");
		println("blue rectangle will always stay in the center of red rectangle");
	}

}
