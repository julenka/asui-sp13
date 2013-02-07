package homework2.android;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;


public class TestFilledRect extends TestFrame {
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		Thread t = new Thread(new Runnable() {
			public void run() {
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
	
	
	
	
	private void expectedState(int x, int y)
	{
		println(String.format("expected location: (%d, %d)", x,y));
		
	}
	
	private void printActual(GraphicalObject o)
	{
		println("final bounding box is " + o.getBoundingBox());
	}
	
	private void TestMoveTo(int startx, int starty, int endx, int endy, int step, GraphicalObject o)
	{
		for(int y = starty; y <= endy; y += step)
		{
			for(int x = startx; x <= endx; x += step)
			{
				o.moveTo(x, y);
				redraw(o);
				sleep(100);
			}
		}
		expectedState(endx, endy);
		printActual(o);
	}

	private void TestSet(int startx, int starty, int endx, int endy, int step, FilledRect r)
	{
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
	
	
	private void test(){
		
		// After implementing OutlineRect, uncomment the testing code 
			 
		println("creating FilledRect");
		println("starting at (0,0), width=50, height=50");
		int curWidth = 50;
		int curHeight = 50;
		FilledRect r = new FilledRect(0, 0, curWidth, curHeight, Color.RED);
		addChild(r);
		println("click to continue...");
		pause();
		println("moving rectangle with setX(), setY() across entire screen");
		TestMoveTo(0,0,drawView.getWidth() - curWidth, drawView.getHeight() - curHeight, 50, r);
		
		pause();
		println("changing to blue");
		r.setColor(Color.BLUE);
		redraw(r);
		pause();
		println("moving rectangle with moveTo ()");
		TestMoveTo(0, 0, drawView.getWidth() - curWidth, drawView.getHeight() - curHeight, 50, r);
		
		pause();
		println("doubling width  to 100");
		curWidth = 100;
		r.setWidth(curWidth);
		redraw(r);
		pause();
		println("moving rectangle with set ()");
		TestMoveTo(0,0,drawView.getWidth() - curWidth, drawView.getHeight() - curHeight, 50, r);
		
		println("changing height  to 200");
		curHeight = 200;
		r.setHeight(curHeight);
		redraw(r);
		pause();
		println("moving rectangle with moveTo ()");
		TestMoveTo(0, 0, drawView.getWidth() - curWidth, drawView.getHeight() - curHeight, 10, r);
		
		println("hit back key to exit");
		
	}
	

}
