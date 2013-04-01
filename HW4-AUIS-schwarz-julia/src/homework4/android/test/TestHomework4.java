package homework4.android.test; //or edit this to be whatever package you want


import homework4.android.constraints.Constraint;
import homework4.android.constraints.EqualIntConstraint;
import homework4.android.constraints.IIntGetter;
import homework4.android.constraints.IIntSetter;
import homework4.android.constraints.IntProperty;
import homework4.android.constraints.IntVariable;
import homework4.android.graphicalobject.GraphicalObject;
import homework4.android.graphicalobject.Group;
import homework4.android.graphicalobject.OutlineRect;
import homework4.android.graphicalobject.SimpleGroup;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

public class TestHomework4 extends TestFrame {
	
	List<Constraint> m_constraints = new ArrayList<Constraint>();
	
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
						// TODO Auto-generated catch block
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

		try {

			println ("1. creating blue and red rects");
			final OutlineRect blueRect = new OutlineRect (0, 0, 50, 80, Color.BLUE, 5);
			final OutlineRect redRect = new OutlineRect (100, 0, 50, 80, Color.RED, 1);
			Group windowgroup = new SimpleGroup (0, 0, 300, 400);
			Group group = new SimpleGroup (0, 0, 300, 400);
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
			
			IIntGetter redYGetter = new IIntGetter() {
				
				@Override
				public int getValue() {
					return redRect.getY();
				}
			}; 
			IIntSetter redYSetter = new IIntSetter() {
				
				@Override
				public void setValue(int value) {
					redRect.setY(value);
				}
			};
			
			IIntGetter blueYGetter = new IIntGetter() {
				
				@Override
				public int getValue() {
					return blueRect.getY();
				}
			}; 
			IIntSetter blueYSetter = new IIntSetter() {
				
				@Override
				public void setValue(int value) {
					blueRect.setY(value);
				}
			};
			
			IntVariable redY = new IntProperty((OutlineRect)redRect, "y", redYGetter, redYSetter);
			IntVariable blueY = new IntProperty((OutlineRect)blueRect, "y", blueYGetter, blueYSetter);
			
			m_constraints.add(new EqualIntConstraint(redY, blueY));
			
			/*
	  redRect.setX( **New constraint = blueRect's right side ** );
	  redRect.setY( **New constraint = blueRect's Y ** );
			 */
			
			redraw (windowgroup);

			println("4. Move Blue, red should move automatically");
			pause();
			blueRect.moveTo(0,0);
			redraw (windowgroup);

			// *** Add in some more constraints and tests here to show the range of 
			// *** what you can express and the appropriate syntax

			println ("DONE. close the window to stop");

		} catch(Exception e) { println ("got an exception " + e); }
	}
}
