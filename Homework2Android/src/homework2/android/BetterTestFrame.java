package homework2.android;

import java.io.IOException;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

public class BetterTestFrame extends TestFrame {
	
	// todo: figure out how to load images
	public BetterTestFrame() {
		// TODO Auto-generated constructor stub
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
						// TODO Auto-generated catch block
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
