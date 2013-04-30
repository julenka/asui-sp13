package homework5.processing.graphicalobject;

import homework5.android.constraints.IPropertyChangedListener;
import homework5.android.constraints.IPropertyChangedNotifier;
import homework5.processing.core.BoundaryRectangle;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import processing.core.PGraphics;
import processing.core.PGraphicsJava2D;
import processing.core.PMatrix;
import processing.core.PMatrix2D;

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
	protected PMatrix2D m_transform = new PMatrix2D();
	
	// the transformation matrix to use 
	// protected PMatrix m_transform = new Matrix();
	
	// rectangle specifying the bounds of this object
	protected BoundaryRectangle m_boundaryRect = new BoundaryRectangle();
	
	private Shape m_savedClipShape;
	
	public GraphicalObjectBase() {
	}
	
	/// Clipping
	protected void saveClipShape(PGraphics pg)
	{
		m_savedClipShape = getGraphics2D(pg).getClip();
	}
	
	protected void restoreClipShape(PGraphics pg)
	{
		// TODO throw error if null?
		setClipShape(pg, m_savedClipShape);
	}
	
	protected void setClipShape(PGraphics pg, Shape s) {
		Graphics2D g = getGraphics2D(pg);
		g.setClip(s);
	}
	
	private Graphics2D getGraphics2D(PGraphics pg)
	{
		return ((PGraphicsJava2D)pg).g2;		
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
	protected abstract void doDraw(PGraphics canvas);

	@Override
	public void draw(PGraphics graphics, Shape clipShape) {
		// TODO do not draw if the boundaryrect is completely out of the clipShape
		saveClipShape(graphics);
		setClipShape(graphics, clipShape);
		
		graphics.pushStyle();
		graphics.pushMatrix();
		
		doDraw(graphics);
		
		graphics.popMatrix();
		graphics.popStyle();
		restoreClipShape(graphics);
	}
	
	@Override
	public BoundaryRectangle getBoundingBox() {
		return new BoundaryRectangle(m_boundaryRect);
	}

	@Override
	public void moveTo(int x, int y) {
		// TODO: error/notify here
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
		
		return relx >= 0 && rely >= 0 && relx < m_boundaryRect.width && rely < m_boundaryRect.height;
	}

	@Override
	public void setAffineTransform(PMatrix2D af) {
		m_transform = af;
	}

	@Override
	public PMatrix2D getAffineTransform() {
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
		// TODO: log
		// Log.v(LOG_TAG, "added listener for property" + propertyName );
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
			// TODO: error message!
			// Log.v(LOG_TAG, "notifyPropertyChanged: no listeners for property name " + propertyName + " registered!");
			return;
		}
		for (IPropertyChangedListener<Object> listener : lst) 
		{
			listener.onPropertyChanged(oldValue, newValue);
		}
	}
}
