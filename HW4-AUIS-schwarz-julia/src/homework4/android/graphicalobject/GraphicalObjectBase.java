package homework4.android.graphicalobject;

import homework4.android.constraints.IPropertyChangedListener;
import homework4.android.constraints.IPropertyChangedNotifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.Log;

/**
 * Base class for all graphical objects, including groups (which are also graphical objects). 
 * Almost all of the objects implemented in this assignment
 * have a lot of common code so I made this base class to factor out all of the common code)
 * @author julenka
 *
 */
public abstract class GraphicalObjectBase implements GraphicalObject, IPropertyChangedNotifier {
	static final String LOG_TAG = "GraphicalObjectBase";
	// the parent group
	protected Group m_group;
	
	// the transformation matrix to use 
	protected Matrix m_transform = new Matrix();
	
	// rectangle specifying the bounds of this object
	protected BoundaryRectangle m_boundaryRect = new BoundaryRectangle();
	
	protected Paint m_paint = new Paint();
	
	// rectangle specifying what to clip to
	private RectF m_clipBounds = new RectF();
	

	public GraphicalObjectBase() {
	}
	
	protected RectF boundaryRectangleToRect(BoundaryRectangle r)
	{
		return new RectF(r.x, r.y, r.x + r.width, r.y + r.height);
	}

	/**
	 * Call this method whenever the bounds of the graphical object changes.
	 * Does damange, makes call to update bounds, and calls doResized if appropriate
	 */
	protected void boundsChanged()
	{
		int oldWidth = m_boundaryRect.width;
		int oldHeight = m_boundaryRect.height;
		doDamage();
		updateBounds();
		doDamage();
		if(m_boundaryRect.width != oldWidth || m_boundaryRect.height != oldHeight)
			doResized();
	}
	
	/**
	 * Implement code here to update the bounds of you m_boundaryRect 
	 */
	protected abstract void updateBounds();
	
	protected void doResized()
	{
		if(m_group != null)
			m_group.resizeChild(this);
	}
	
	protected void doDamage()
	{
		if(m_group != null)
			m_group.damage(m_boundaryRect);		
	}
	
	/**
	 * Implement drawing code here, draw() calls this method after doing some basic computation
	 * @param graphics
	 * @param clipShape
	 */
	protected abstract void doDraw(Canvas graphics, Path clipShape);
	
	@Override
	/**
	 * Computes whether the object is within bounds of clipped region. If not, don't draw
	 * the object. Otherwise draw the object
	 */
	public void draw(Canvas graphics, Path clipShape) {

		
		// do not draw if the boundaryrect is completely out of the clipShape
		clipShape.computeBounds(m_clipBounds, true);
		RectF boundary = boundaryRectangleToRect(m_boundaryRect);
		
//		Paint dbgPaint = new Paint();
//		dbgPaint.setStyle(Style.STROKE);
//		dbgPaint.setColor(Color.MAGENTA);
//		graphics.drawRect(boundary, dbgPaint);
		
		if(!RectF.intersects(boundary, m_clipBounds)) return;
		
		doDraw(graphics, clipShape);
		
	}
	
	@Override
	public BoundaryRectangle getBoundingBox() {
		return new BoundaryRectangle(m_boundaryRect);
	}

	@Override
	public void moveTo(int x, int y) {
		Log.v("GraphicalObjectBase", "Oops, you forgot to implement moveTo() for class " + this.getClass());
	}

	@Override
	public Group getGroup() {
		return m_group;
	}

	@Override
	public void setGroup(Group group) {
		m_group = group;
	}

	@Override
	public boolean contains(int x, int y) {
		int relx = x - m_boundaryRect.x;
		int rely = y - m_boundaryRect.y;
//		Log.v("Homework3.GraphicalObjectBase", "x: " + x + "y: " + y + "relx: " + relx + " rely: " + rely + " width: " + m_boundaryRect.width + " hegiht: " + m_boundaryRect.height);
		
		return relx >= 0 && rely >= 0 && relx < m_boundaryRect.width && rely < m_boundaryRect.height;
	}

	@Override
	public void setAffineTransform(Matrix af) {
		m_transform = af;
	}

	@Override
	public Matrix getAffineTransform() {
		return m_transform;
	}
	
	/**
	 * Constraints
	 */
	// for now, only support constraints on int and float properties
	Map<String, List<IPropertyChangedListener<Object>>> m_propertyChangedListeners = new HashMap<String, List<IPropertyChangedListener<Object>>>();

	public  <E> void addPropertyChangedListener(String propertyName, 
			IPropertyChangedListener<E> listener)
	{
		if(!m_propertyChangedListeners.containsKey(propertyName)) m_propertyChangedListeners.put(propertyName, new ArrayList<IPropertyChangedListener<Object>>());
		List<IPropertyChangedListener<Object>> lst = m_propertyChangedListeners.get(propertyName);
		
		lst.add((IPropertyChangedListener<Object>)listener);
		
		Log.v(LOG_TAG, "added listener for property" + propertyName );
	}

	protected void notifyDoublePropertyChanged(String propertyName, double oldValue, double newValue)
	{
		if(Math.abs(oldValue - newValue) < 0.000000001) return;
		notifyPropertyChanged(propertyName, oldValue, newValue);
	}
	
	public <E> void notifyPropertyChanged(String propertyName, E oldValue, E newValue)
	{
		if(oldValue.equals(newValue)) return;
		List<IPropertyChangedListener<Object>> lst = m_propertyChangedListeners.get(propertyName);
		if(lst == null)
		{
			Log.v(LOG_TAG, "notifyPropertyChanged: no listeners for property name " + propertyName + " registered!");
			return;
		}
		for (IPropertyChangedListener<Object> listener : lst) 
		{
			listener.onPropertyChanged(oldValue, newValue);
		}
	}
}
