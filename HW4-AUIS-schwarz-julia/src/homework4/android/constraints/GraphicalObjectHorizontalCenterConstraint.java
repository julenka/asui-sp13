package homework4.android.constraints;

import homework4.android.graphicalobject.GraphicalObjectBase;

/**
 * Centers a graphical object horizontally with respect to another
 * @author Julia
 *
 */
public class GraphicalObjectHorizontalCenterConstraint extends
		GraphicalObjectCenterConstraint {
	
	public GraphicalObjectHorizontalCenterConstraint(GraphicalObjectBase dependent, 
			GraphicalObjectBase independent){
		super(dependent, independent, "X", "Width", "X", "Width");
	}

}
