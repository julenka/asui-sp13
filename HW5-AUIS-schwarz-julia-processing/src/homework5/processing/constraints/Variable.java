package homework5.processing.constraints;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a generic variable type that constraints operate on.
 * Constraints operate on Variable objects.
 * @author julenka
 *
 * @param <E>
 */
public abstract class Variable<E> {
	List<Constraint> m_constraints = new ArrayList<Constraint>();
	
	/**
	 * @return the value of the variable.
	 */
	public abstract E getValue();
	
	/**
	 * Sets the value of the variable
	 * @param value
	 */
	public abstract void setValue(E value);
	
	/**
	 * Called when a variable value changes. re-evaluates all constraints that depend on this variable.
	 */
	public void invalidate()
	{
		for (Constraint c : m_constraints) {
			c.evaluate();
		}
	}
	
	public void addConstraint(Constraint c)
	{
		m_constraints.add(c);
	}
	
	public void removeConstraint(Constraint c)
	{
		m_constraints.remove(c);
	}
	
	
}
