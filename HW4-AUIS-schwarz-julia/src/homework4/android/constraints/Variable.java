package homework4.android.constraints;

import java.util.ArrayList;
import java.util.List;

public abstract class Variable<E> {
	List<Constraint> m_constraints = new ArrayList<Constraint>();
	
	public abstract E getValue();
	public abstract void setValue(E value);
	
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
