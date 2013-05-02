package homework5.processing.constraints;

import homework5.processing.graphicalobject.GraphicalObjectBase;

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
