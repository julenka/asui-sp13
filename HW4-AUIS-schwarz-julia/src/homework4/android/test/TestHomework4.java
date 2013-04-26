package homework4.android.test; //or edit this to be whatever package you want


import homework4.android.constraints.Constraint;
import homework4.android.constraints.EqualConstraint;
import homework4.android.constraints.GraphicalObjectHorizontalCenterConstraint;
import homework4.android.constraints.GraphicalObjectProperty;
import homework4.android.constraints.GraphicalObjectVerticalCenterConstraint;
import homework4.android.constraints.IntProductConstraint;
import homework4.android.constraints.IntVariable;
import homework4.android.constraints.Variable;
import homework4.android.graphicalobject.Group;
import homework4.android.graphicalobject.OutlineRect;
import homework4.android.graphicalobject.SimpleGroup;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

public class TestHomework4 extends TestFrame {
	
	public void onCreate(Bundle savedInstanceState) {
		Log.d("DV", "onCreate  == "+this);	
		super.onCreate(savedInstanceState);	
		Thread t = new Thread(new Runnable() {
			public void run() {
				Log.d("DV", "run()  == "+this);	
				while(!drawView.getOnDrawFirstCalled()){
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				test();
			}
		});
		t.start();
	}

	private void test(){
		Log.d("DV", "test()  == "+this);	

		println ("1. creating blue and red rects");
		final OutlineRect blueRect = new OutlineRect (0, 0, 50, 80, Color.BLUE, 5);
		final OutlineRect redRect = new OutlineRect (100, 0, 50, 80, Color.RED, 1);
		Group windowgroup = new SimpleGroup (0, 0, drawView.getWidth(), drawView.getHeight());
		Group group = new SimpleGroup (0, 0, drawView.getWidth(), drawView.getHeight());
		addChild (windowgroup);

		windowgroup.addChild (group);
		group.addChild (blueRect);
		group.addChild (redRect);
		redraw (windowgroup);

		println ("2. moving blue to 30,30, red shouldn't move");
		pause();
		blueRect.moveTo(30,30);
		redraw (windowgroup);

		println ("3. adding constraint to red rect to be at right of blue");
		println ("     red should move to be at 80,30");
		pause();


		Variable<Integer> redY = new GraphicalObjectProperty<Integer>(redRect, "Y");
		Variable<Integer> blueY = new GraphicalObjectProperty<Integer>(blueRect, "Y");

		Constraint redYBlueY = new EqualConstraint<Integer>(redY, blueY);
		redYBlueY.activate();

		redraw (windowgroup);

		println("4. Move Blue, red should move automatically");
		pause();
		blueRect.moveTo(0,0);
		redraw (windowgroup);

		// *** Add in some more constraints and tests here to show the range of 
		// *** what you can express and the appropriate syntax

		println("4. Make red width, height  = 2 * bluewidth ");
		pause();
		Variable<Integer> redWidth = new GraphicalObjectProperty<Integer>(redRect, "Width");
		Variable<Integer> blueWidth = new GraphicalObjectProperty<Integer>(blueRect, "Width");
		Variable<Integer> redHeight = new GraphicalObjectProperty<Integer>(redRect, "Height");
		
		// make width of red 2 * width of blue
		Constraint red2xBlueWidth = new IntProductConstraint(redWidth, blueWidth, new IntVariable(2) );
		Constraint red2xBlueHeight = new IntProductConstraint(redHeight, blueWidth, new IntVariable(2) );
		
		red2xBlueWidth.activate();
		red2xBlueHeight.activate();
		
		redraw(windowgroup);
		
		println("5. Make blue centered in red (x coordinate)");
		redYBlueY.deactivate();
		Constraint centerHorizBlueInRed = new GraphicalObjectHorizontalCenterConstraint(blueRect, redRect);
		Constraint centerVertBlueInRed = new GraphicalObjectVerticalCenterConstraint(blueRect, redRect);
		centerHorizBlueInRed.activate();
		centerVertBlueInRed.activate();
		redraw(windowgroup);
		
		// make red centered in blue
		
		println("6. Changing blue width from 5 to 100");
		for(int i = 5; i < 100; i+=10)
		{
			blueRect.setWidth(i);
			redraw(windowgroup);
			sleep(20);
		}
		// add a slider that changes the width of the blue rectangle, for more testing
		
//		println("5. Adding ad-hoc slider. X = blue rectangle width");
//		SimpleGroup sliderGroup = new SimpleGroup(0, drawView.getHeight() - 100, drawView.getWidth(), 100);
//		addChild(sliderGroup);
		
		// make x coord of blue centered with red
		
		// make string = x,y coordinate of red
		// move red
	
		
		
		println ("DONE. close the window to stop");

	}
}
