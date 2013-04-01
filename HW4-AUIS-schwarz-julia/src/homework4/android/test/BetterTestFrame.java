package homework4.android.test;

import homework4.android.graphicalobject.GraphicalObject;

import java.io.IOException;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

/**
 * A test frame that my custom tests extend. Contains a few utility method. Also allows 
 * any test that extends this class to be included in my TestAllTests class which runs through
 * all of my custom tests. Each custom test can also be displayed individually as regular TestFrames.
 * @author julenka
 */
public class BetterTestFrame extends TestFrame {
	
	public BetterTestFrame() {
	}
	
	protected void expectedState(int x, int y)
	{
		println(String.format("expected location: (%d, %d)", x,y));
		
	}
	
	protected void printActual(GraphicalObject o)
	{
		println("final bounding box is " + o.getBoundingBox());
	}
	

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		Thread t = new Thread(new Runnable() {
			public void run() {
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

	protected void TestMoveTo(int startx, int starty, int endx, int endy, int numsteps, GraphicalObject o, GraphicalObject toRedraw)
	{
		int step = (endx - startx) / numsteps;
		for(int y = starty; y <= endy; y += step)
		{
			for(int x = startx; x <= endx; x += step)
			{
				o.moveTo(x, y);
				redraw(toRedraw);
				sleep(100);
			}
		}
		expectedState(endx, endy);
		printActual(o);
	}

	protected void test()
	{
		println("Empty test method, implement your test method!");
	}
}
