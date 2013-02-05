package homework2.android;

import android.graphics.Color;
import android.os.Bundle;


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
	
	private void test(){
		
		// After implementing OutlineRect, uncomment the testing code 
			 
		println("creating FilledRect");
		
		FilledRect r = new FilledRect(10, 10, 50, 50, Color.RED);
		addChild(r);
		pause();
		println("moving rectangle with setX(), setY()");
		for (int x = 10; x < 150; x += 30) {
			r.setX(x);
			for (int y = 10; y < 150; y += 30) {
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
		println("moving rectangle with moveTo ()");
		for (int x = 10; x < 150; x += 30) {
			for (int y = 10; y < 150; y += 30) {
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
		r.setWidth(100);
		redraw(r);
		pause();
		println("moving rectangle with moveTo ()");
		for (int x = 10; x < 150; x += 30) {
			for (int y = 10; y < 150; y += 30) {
				r.moveTo(x, y);
				redraw(r);
				sleep(100);
			}
		}
		
		println("changing height  to 200");
		r.setHeight(200);
		redraw(r);
		pause();
		println("moving rectangle with moveTo ()");
		for (int x = 10; x < 150; x += 30) {
			for (int y = 10; y < 150; y += 30) {
				r.moveTo(x, y);
				redraw(r);
				sleep(100);
			}
		}
		println("final bounding box is " + r.getBoundingBox());
		println("final x/y position is "
		+ r.getX() + "," + r.getY());
		println("hit back key to exit");
		
	}
	

}
