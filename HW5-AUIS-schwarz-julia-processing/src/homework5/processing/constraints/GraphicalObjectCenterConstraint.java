package homework5.processing.constraints;

import homework5.processing.graphicalobject.GraphicalObjectBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides generalized centering of any graphical object.
 * Graphical objects can specify which properties are use for the centering.
 * Centering works as follows:
 * dependent.x = (independent.x + independent.width - dependent.width)/2
 * the .x .width properties can be specified (nice and general) 
 * @author Julia
 *
 */
public class GraphicalObjectCenterConstraint extends Constraint {

	List<Constraint> m_constraints;
	
	public GraphicalObjectCenterConstraint(GraphicalObjectBase dependent, 
			GraphicalObjectBase independent,
			String depXPropertyName, String depWidthPropertyName,
			String indepXPropertyName, String indepWidthPropertyName)
	{
		// dependent x = indep x + indep.width / 2 - dep.width /2
		m_constraints = new ArrayList<Constraint>();
		// a = indep.width / 2
		Variable<Integer> indepW = new GraphicalObjectProperty<Integer>(independent, indepWidthPropertyName);
		Variable<Integer> indepX = new GraphicalObjectProperty<Integer>(independent, indepXPropertyName);
		Variable<Integer> depW = new GraphicalObjectProperty<Integer>(dependent, depWidthPropertyName);
		Variable<Integer> depX = new GraphicalObjectProperty(dependent, depXPropertyName);
		
		Variable a = new IntVariable();
		m_constraints.add(new IntDivisionConstraint(a, indepW, new IntVariable(2)));
		Variable b = new IntVariable();
		m_constraints.add(new IntDivisionConstraint(b, depW, new IntVariable(-2)));
		m_constraints.add(new IntSumConstraint(depX, indepX, a, b));
	}
	
	
	@Override
	public void evaluate() {
		// nop, constraints take care of themselves
	}

	@Override
	public void activate() {
		for (Constraint c : m_constraints) {
			c.activate();
		}

	}

	@Override
	public void deactivate() {
		for (Constraint c : m_constraints) {
			c.deactivate();
		}

	}

}
