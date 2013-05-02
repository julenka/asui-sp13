package homework5.processing.test; //or edit this to be whatever package you want

import homework5.processing.constraints.Constraint;
import homework5.processing.constraints.EqualConstraint;
import homework5.processing.constraints.GraphicalObjectHorizontalCenterConstraint;
import homework5.processing.constraints.GraphicalObjectProperty;
import homework5.processing.constraints.GraphicalObjectVerticalCenterConstraint;
import homework5.processing.constraints.IntProductConstraint;
import homework5.processing.constraints.IntVariable;
import homework5.processing.constraints.Variable;
import homework5.processing.core.WindowGroup;
import homework5.processing.graphicalobject.Group;
import homework5.processing.graphicalobject.OutlineRect;
import homework5.processing.graphicalobject.SimpleGroup;

import java.awt.Color;

import processing.core.PApplet;

public class TestHomework4 extends WindowGroup {
	int m_clickCount = 0;
	OutlineRect blueRect;
	OutlineRect redRect;
	
	boolean animate = false;
	
	@Override
	public void mouseClicked() {
		super.mouseClicked();
		m_clickCount++;

		if(m_clickCount==1)
		{
			println ("1. adding constraint to red rect to be at same y as blue");
			println ("     red should move to be at 80,30");
			redYBlueY.activate();

		}else if (m_clickCount == 2)
		{
			println("2. Move Blue, red should move automatically");
			blueRect.moveTo(0,0);
		} else if (m_clickCount == 3)
		{
			// *** Add in some more constraints and tests here to show the range of 
			// *** what you can express and the appropriate syntax
			println("3. Make red width, height  = 2 * bluewidth ");
			red2xBlueWidth.activate();
			red2xBlueHeight.activate();
		} else if(m_clickCount == 4)
		{
			println("4. Make blue centered in red (x coordinate)");
			redYBlueY.deactivate();
			
			centerHorizBlueInRed.activate();
			centerVertBlueInRed.activate();
			animate = true;
		} else
		{
			exit();
		}
		
	}
	
	Constraint redYBlueY;
	Constraint red2xBlueWidth;
	Constraint red2xBlueHeight;
	Constraint centerHorizBlueInRed; 
	Constraint centerVertBlueInRed; 
	public void setup(){
		super.setup();
		println("Initial setup...");
		blueRect = new OutlineRect (40, 40, 50, 80, Color.BLUE, 5);
		redRect = new OutlineRect (100, 100, 50, 160, Color.RED, 1);
		addChild (blueRect);
		addChild (redRect);
		
		Variable<Integer> redY = new GraphicalObjectProperty<Integer>(redRect, "Y");
		Variable<Integer> blueY = new GraphicalObjectProperty<Integer>(blueRect, "Y");

		redYBlueY = new EqualConstraint<Integer>(redY, blueY);
		
		Variable<Integer> redWidth = new GraphicalObjectProperty<Integer>(redRect, "Width");
		Variable<Integer> blueWidth = new GraphicalObjectProperty<Integer>(blueRect, "Width");
		Variable<Integer> redHeight = new GraphicalObjectProperty<Integer>(redRect, "Height");

		red2xBlueWidth = new IntProductConstraint(redWidth, blueWidth, new IntVariable(2) );
		red2xBlueHeight = new IntProductConstraint(redHeight, blueWidth, new IntVariable(2) );
		
		centerHorizBlueInRed = new GraphicalObjectHorizontalCenterConstraint(blueRect, redRect);
		centerVertBlueInRed = new GraphicalObjectVerticalCenterConstraint(blueRect, redRect);
		
		println("Click to continue...");
	}
	int count = 10;
	@Override
	public void draw() {
		super.draw();
		if(animate)
		{
			redRect.setX(count % width);
			redRect.setY(count % height);
			count++;
		}
	}
	public static void main(String args[]) {
		PApplet.main(new String[] { "homework5.processing.test.TestHomework4" });
	}
}
