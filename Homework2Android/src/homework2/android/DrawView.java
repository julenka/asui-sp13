package homework2.android;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class DrawView extends View{	

	private GraphicalObject g;
	private boolean onDrawFirstCalled = false;
	private Map<GraphicalObject, Path> children = new LinkedHashMap<GraphicalObject, Path>(); // use linked HashMap to guarantee children order

	public DrawView(final Context context, final AttributeSet aSet){
		super(context, aSet);
	}

    /*
      This is an incorrect implementation, in that there is no way to REMOVE
      objects once they have been added, and the objects are NOT necessarily
      drawn in the right order. 

      Therefore, setGraphicalObject should only be called multiple times by
      test code.

      A real application using these classes should install exactly one
      group at the top level, and never remove it. TestFrame will call
      setGraphicalObject on that object once.  Then, the application can
      add and remove objects from that group and handle changes correctly.
     */
	public void setGraphicalObject(final GraphicalObject g1, final Path r){
		g = g1;
		children.put(g1, r);
	}

	public GraphicalObject getGraphicalObject(){
		return g;
	}

	public boolean getOnDrawFirstCalled(){
		return onDrawFirstCalled;
	}


	@Override
	public void onDraw(final Canvas canvas) {		
		// make the canvas background white	
		canvas.drawColor(Color.WHITE);

		// draw children
		for (Entry<GraphicalObject, Path> entry : children.entrySet())
			entry.getKey().draw(canvas, entry.getValue());

		// used by testing activities. make sure the testing procedure starts after the first onDraw is called 
		if(!onDrawFirstCalled)
			onDrawFirstCalled = true;		
	}

	public void redraw(){
		// testing message - remove when delivered to students
		// Log.d("DrawView", "redraw called");				

		// force redraw
		invalidate();	
	}
}
