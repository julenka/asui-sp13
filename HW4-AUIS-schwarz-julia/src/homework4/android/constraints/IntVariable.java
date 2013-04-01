package homework4.android.constraints;

import java.util.ArrayList;
import java.util.List;

public class IntVariable {

	int m_value = 0;
	// constraitns to update when we change
	List<Constraint> m_constraints = new ArrayList<Constraint>();
	public IntVariable(){}
	public IntVariable(int value)
	{
		m_value = value;
	}
	
	public int getValue(){
		return m_value;
	}
	
	public void setValue(int value)
	{
		m_value = value;
		invalidate();
	}
	
	public void invalidate()
	{
		for (Constraint c : m_constraints) {
			c.invalidate();
		}
	}
	
	public void addConstraint(Constraint c)
	{
		m_constraints.add(c);
	}
	
}
