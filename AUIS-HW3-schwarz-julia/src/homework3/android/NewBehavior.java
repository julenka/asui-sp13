package homework3.android;

public abstract class NewBehavior extends BehaviorBase {
	/*
	 When a NewBehavior starts, it should call its own make() method to create 
	 a new instance of a graphical object. The NewBehavior should add the object 
	 returned by make() to the group, so it will appear on screen immediately. 
	 While the Behavior is running, it should resize the new object to follow the 
	 mouse (assuming onePoint is false). When it stops, it should leave the object 
	 where it is.
	 */
	
	// TODO: can we declare abstract methods in a non-abstract class?
	// TODO: figure out how to create an abstract constructor

	protected boolean m_onePoint;
	
	/**
	 * If the onePoint parameter to the constructor is true, then the new Behavior needs 
	 * only one point to create the object. It calls make(x, y, x, y) to create the object, 
	 * then stops immediately after starting, never calling resize(). This is useful for fixed size objects, like icons.
	 * @param onePoint
	 */
	public NewBehavior (boolean onePoint){
		m_onePoint = onePoint;
	}
	
	
    
	/**
     * make() creates a graphical object from point (x1, y1) to point (x2, y2). 
     * This method is declared abstract. It should be overridden in a subclass of NewBehavior, 
     * which decides which graphical object to create and how to interpret the (x1,y1) and (x2, y2) coordinates.
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    public abstract GraphicalObject make (int x1, int y1, int x2, int y2);
    
    /**
     * resize() adjusts a graphical object created by make() with new points. 
     * (x1,y1) is the anchor point, which should be the same as was passed to make(). 
     * The point (x2,y2) follows the mouse cursor, so it will be different.
     * @param gobj
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    public abstract void resize (GraphicalObject gobj, int x1, int y1, int x2, int y2);
    
}