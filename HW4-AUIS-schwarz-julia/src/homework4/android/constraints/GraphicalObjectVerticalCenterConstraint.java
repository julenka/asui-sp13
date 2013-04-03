package homework4.android.constraints;

import homework4.android.graphicalobject.GraphicalObjectBase;

public class GraphicalObjectVerticalCenterConstraint extends
		GraphicalObjectCenterConstraint {
	public GraphicalObjectVerticalCenterConstraint(GraphicalObjectBase dependent, 
			GraphicalObjectBase independent){
		super(dependent, independent, "Y", "Height", "Y", "Height");
	}
}
