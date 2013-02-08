package homework2.android;

import android.graphics.Color;
import android.os.Bundle;

public class BetterTestOutlineRect extends BetterTestFrame {

	public BetterTestOutlineRect() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void test(){
		println("creating OutlineRect");
		int lineThickness = 1;
		OutlineRect r = new OutlineRect(10, 10, 50, 50, Color.RED, lineThickness);
		addChild(r);
		pause();
		println("changing line thickness from 1 to 100");
		for (int i = 1; i <= 100; i++) {
			r.setLineThickness(i);
			redraw(r);
			sleep(50);
		}
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
		println("doubling line thickness to " + lineThickness * 2);
		r.setLineThickness(lineThickness * 2);
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
