package homework3.android;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;


public class TestText extends TestFrame {
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
	
	private void test(){
		
		// After implementing OutlineRect, uncomment the testing code 
			 
		println("creating text");
		
		Text r = new Text("this is Helvetica", 0, 20, Typeface.create("Helvetics", Typeface.NORMAL), 10, Color.BLACK);
		addChild(r);
		pause();
		println("moving text with setX(), setY()");
		for (int x = 0; x < 150; x += 30) {
			r.setX(x);
			for (int y = 20; y < 150; y += 30) {
				r.setY(y);
				redraw(r);
				sleep(100);
			}
		}
		println("final bounding box is " + r.getBoundingBox());
		println("final x/y position is "
		+ r.getX() + "," + r.getY());
		pause();
		println("changing to blue");
		r.setColor(Color.BLUE);
		redraw(r);
		pause();
		println("moving text with moveTo ()");
		for (int x = 0; x < 150; x += 30) {
			for (int y = 20; y < 150; y += 30) {
				r.moveTo(x, y);
				redraw(r);
				sleep(100);
			}
		}
		println("final bounding box is " + r.getBoundingBox());
		println("final x/y position is "
		+ r.getX() + "," + r.getY());
		pause();
		println("doubling width  to 100");
		redraw(r);
		pause();
		println("moving text with moveTo ()");
		for (int x = 0; x < 150; x += 30) {
			for (int y = 20; y < 150; y += 30) {
				r.moveTo(x, y);
				redraw(r);
				sleep(100);
			}
		}
		
		println("hit back key to exit");
		
	}
	

}
