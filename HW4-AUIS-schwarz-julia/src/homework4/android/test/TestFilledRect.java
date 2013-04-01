package homework4.android.test;

import homework4.android.graphicalobject.FilledRect;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;


public class TestFilledRect extends BetterTestFrame {
	
	private void TestSet(int startx, int starty, int endx, int endy, int numSteps, FilledRect r)
	{
		int step = (endx - startx) / numSteps;
		for(int y = starty; y <= endy; y += step)
		{
			r.setY(y);
			for(int x = startx; x <= endx; x += step)
			{
				r.setX(x);				
				redraw(r);
				sleep(100);
			}
		}
		expectedState(endx, endy);
		printActual(r);
	}
	
	
	protected void test(){
		
		// After implementing OutlineRect, uncomment the testing code 
		println("creating FilledRect");
		println("starting at (0,0), width=10, height=10");
		int curWidth = 10;
		int curHeight = 10;
		FilledRect r = new FilledRect(0, 0, curWidth, curHeight, Color.RED);
		addChild(r);
		println("moving rectangle with setX(), setY() across entire screen");
		TestMoveTo(0,0,drawView.getWidth() - curWidth, drawView.getHeight() - curHeight, 5, r,r);
		
		println("changing to blue");
		r.setColor(Color.BLUE);
		redraw(r);
		println("moving rectangle with moveTo ()");
		TestMoveTo(0, 0, drawView.getWidth() - curWidth, drawView.getHeight() - curHeight, 5, r,r);
		
		println("doubling width  to 100");
		curWidth = 100;
		r.setWidth(curWidth);
		redraw(r);
		println("moving rectangle with set ()");
		TestSet(0,0,drawView.getWidth() - curWidth, drawView.getHeight() - curHeight, 5, r);
		
		println("changing height  to 200");
		curHeight = 200;
		r.setHeight(curHeight);
		redraw(r);
		println("moving rectangle with moveTo ()");
		TestMoveTo(0, 0, drawView.getWidth() - curWidth, drawView.getHeight() - curHeight, 5, r,r);
		
		println("hit back key to exit");
		
	}
	

}
