package homework2.android;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class DrawView extends View{	
	
	private GraphicalObject g;
	private Path clipRect;
	private boolean onDrawFirstCalled = false;
	
	public DrawView(Context context, AttributeSet aSet){
		super(context, aSet);		
	}
		
	
	public void setGraphicalObject(GraphicalObject g1){
		g = g1;
	}
	public GraphicalObject getGraphicalObject(){
		return g;
	}
	
	public void setClipRect(Path r){
		clipRect = r;
	}
	public Path getClipRect(){
		return clipRect;
	}
	
	
	public boolean getOnDrawFirstCalled(){
		return onDrawFirstCalled;
	}
	
	
	@Override
    public void onDraw(Canvas canvas) {		
		
		// make the canvas background white	
		canvas.drawColor(Color.WHITE);
		
		// if the GraphicalObject is not null, draw it
		if(g != null){			
			g.draw(canvas, clipRect);
		}
		
		// testing message - remove when delivered to students
		// Log.d("DrawView", "onDraw called");
		
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
