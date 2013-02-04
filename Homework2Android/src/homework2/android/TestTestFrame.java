package homework2.android;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.graphics.Matrix;

public class TestTestFrame extends TestFrame{
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
		println("First Pause");
		pause();
		println("20 x's with sleep(250) in between");
		for (int x = 0; x < 20; x ++) {
				print("x");
				sleep(250);
		}
		println("");
		println("drawing a rectangle");
		pause();
		drawsomething();
		println("done");
		pause();
		println("hit back key to exit");
	 }
	 
	 
	 private class pretendrect implements GraphicalObject {
		
		public void draw(Canvas graphics, Path clipShape) {
			Paint p = new Paint();
			p.setStyle(Paint.Style.STROKE);
			p.setColor(Color.RED);			
			p.setStrokeWidth(2);
			graphics.drawRect(4, 4, 40, 40, p);			
			p.setColor(Color.BLUE);	
			p.setStyle(Paint.Style.FILL);
			graphics.drawRect(0,0,4,4, p);

		};
		public BoundaryRectangle getBoundingBox() {return null;};
		public void moveTo(int x, int y) {};
		public Group getGroup() {return null;};
		public void setGroup(Group group) {};
		public boolean contains(int x, int y) {return false;};
		public void setAffineTransform(Matrix af) {};
		public Matrix getAffineTransform() {return null;};
		public pretendrect(){}
	}
	private void drawsomething() {
		addClipRect(new BoundaryRectangle(10,10,40,40));
		redraw(new pretendrect());
	}
	
}
