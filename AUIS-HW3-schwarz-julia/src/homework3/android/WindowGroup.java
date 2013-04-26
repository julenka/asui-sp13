package homework3.android;


import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Point;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public abstract class WindowGroup extends Activity implements Group {
	public DrawView drawView;
	public TextView debugTextView;	
	private String debugString;
	private static final boolean useConsole = false; // false -> use a TextView at the bottom of the window
	private static final String LOG_TAG = "Homework3.WindowGroup";

	private Path clipPath = new Path();

	protected boolean screenDirty = false;

	BoundaryRectangle savedClipRect;
	LinkedList<GraphicalObject> children = new LinkedList<GraphicalObject> ();

	@Override
	/**
	 * Sets the content of the page to be the layout defined in layout/text.xml
	 * Gets the drawView and debugView
	 * Starts a method which waits until the 
	 */
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		setContentView(R.layout.test);

		// title is set in AndroidManifest.xml
		// canvas is setup in onDraw in DrawView

		debugTextView = (TextView) findViewById(R.id.debugText);	
		debugTextView.setTextColor(Color.WHITE);
		debugTextView.setTextSize(16);		

		// if useConsole is true, print of log message in LogCat 
		if(useConsole){
			debugTextView.setText("Messages printed in LogCat");					
		}
		// else, initialize debugString, which is the output string for the debug text area
		else{					
			debugString = "";
		}

		println("Starting WindowGroup");

		drawView = (DrawView) findViewById(R.id.drawView);		

		// draw the window once the drawView has been set up.
		final GraphicalObject me = this;
		Thread t = new Thread(new Runnable() {
			public void run() {
				while(!drawView.getOnDrawFirstCalled()){
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				setup();
				addClipRect(new BoundaryRectangle(0,0, drawView.getWidth(), drawView.getHeight()));
				redraw();
			}
		});
		t.start();
	}
	
	/*
	 * Initialize any graphical objects here.
	 */
	protected abstract void setup();


	// note that clipRect is a BoundaryRectangle, and is therefore the size of the area to be drawn
	// it is NOT 1 pixel bigger (unlike Android's rectangles)
	public void addClipRect(final BoundaryRectangle r) {
		if (savedClipRect != null)
			savedClipRect.add(r);
		else if (r==null)
			savedClipRect = null;
		else
			savedClipRect = new BoundaryRectangle(r);	
	}

	public void redraw() {	
		if(!screenDirty) 
		{
			Log.e(LOG_TAG, "redraw called when screen was not dirty!");
			return;
		}
		// if savedClipRect is not null, redraw the canvas with all my children
		// else, print a message
		if (savedClipRect != null) {
			drawView.setGraphicalObject(this, savedClipRect);
			drawView.redraw();
			savedClipRect = null;
		}
		else{
			Log.e(LOG_TAG, "no clip rectangle");
			println("no clip rectangle");
		}
	}

	//
	// Group interface
	//
	@Override
	public void addChild (GraphicalObject child) {
		child.setGroup (this);
		children.add (child);	
		damage (child.getBoundingBox());
	}
	@Override
	public void removeChild (GraphicalObject child) {
		BoundaryRectangle b = child.getBoundingBox();
		child.setGroup(null);
		children.remove (child);	
		damage (child.getBoundingBox());
	}
	@Override
	public void bringChildToFront (GraphicalObject child) {
		children.remove (child);
		children.add (child);
		damage(getBoundingBox());
	}
	@Override
	public void resizeToChildren () {
		// not supported for now
	}

	@Override
	public synchronized void damage (BoundaryRectangle rectangle) {
		addClipRect(rectangle);
		screenDirty = true;
		
	}

	@Override
	public void draw(final Canvas graphics, final Path clipRect) {
		graphics.save();
		graphics.clipPath(clipRect);

		// set clip path of this group, which is the same dimensions as the drawView
		clipPath.reset();
		clipPath.addRect(new RectF(0, 0, drawView.getWidth(), drawView.getHeight()), Direction.CCW);
		
		for (GraphicalObject child : children) {
			// draw to the clipshape of the child
			child.draw(graphics, clipPath);
		}	
		graphics.restore();
		screenDirty = false;
	}

	@Override
	public BoundaryRectangle getBoundingBox() {
		// return the bounding box of the whole canvas
		return new BoundaryRectangle(0, 0, drawView.getWidth(), drawView.getHeight());
	}

	@Override
	public void moveTo (int x, int y) {
	}

	@Override
	public Group getGroup () {
		return null;
	}

	@Override
	public void setGroup (Group group) {
	}

	@Override
	public List<GraphicalObject> getChildren () {
		return children;
	}

	@Override
	public Point parentToChild (Point pt) {
		return pt;
	}

	@Override
	public Point childToParent (Point pt) {
		return pt;
	}

	@Override
	public void resizeChild(GraphicalObject child) {
	}


	// 
	// Message output
	//
	@Override
	public boolean contains(final int x, final int y){
		// check if x, y is within the canvas
		if(x>=0 && x <= drawView.getWidth() && y >= 0 && y <= drawView.getHeight()){
			return true;
		}
		else{
			return false;
		}
	}

	public void print(final String s){
		if(useConsole){			
			Log.i(LOG_TAG, s);
		}
		else{
			debugString += s;			
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					debugTextView.setText(debugString);				
				}
			});								
		}

	}

	public void println(final String s){
		if(useConsole){			
			Log.i(LOG_TAG, s);
		}
		else{
			debugString += s;
			debugString += "\n";
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					debugTextView.setText(debugString);				
				}
			});		
		}

	}

	//
	// Load image
	// load an image file from the "assets" part of the project
	//
	public Bitmap loadImageFully(final String filename) throws IOException {
		Bitmap myBitmap = BitmapFactory.decodeStream(this.getAssets().open(filename));
		if (myBitmap != null) return myBitmap;
		throw new IOException("cannot load file: " + filename);
	}




	private Matrix ax;
	@Override
	public Matrix getAffineTransform() {
		return ax;
	}

	@Override
	public void setAffineTransform(final Matrix af) {
		ax = af;
	}

}
