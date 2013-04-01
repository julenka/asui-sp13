package homework4.android.constraints;

public class EqualIntConstraint extends Constraint {

	IntVariable m_dep;
	IntVariable m_indep;
	public EqualIntConstraint(IntVariable dependent, IntVariable independent)
	{
		m_dep = dependent;
		
		m_indep = independent;
		m_indep.addConstraint(this);
		
		invalidate();
	}
	
	@Override
	public void invalidate() {
		m_dep.setValue(m_indep.getValue());
	}

}
