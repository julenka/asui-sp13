package homework5.processing.constraints;

import homework5.processing.graphicalobject.GraphicalObjectBase;

public class GraphicalObjectVerticalCenterConstraint extends
		GraphicalObjectCenterConstraint {
	public GraphicalObjectVerticalCenterConstraint(GraphicalObjectBase dependent, 
			GraphicalObjectBase independent){
		super(dependent, independent, "Y", "Height", "Y", "Height");
	}
}
