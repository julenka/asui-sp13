package homework4.android.constraints;

public class EqualConstraint<E> extends Constraint {

	Variable<E> m_dep;
	Variable<E> m_indep;
	public EqualConstraint(Variable<E> dependent, Variable<E> independent)
	{
		m_dep = dependent;
		
		m_indep = independent;
	}
	
	@Override
	public void evaluate() {
		m_dep.setValue(m_indep.getValue());
	}

	@Override
	public void activate() {
		m_indep.addConstraint(this);
		evaluate();
	}

	@Override
	public void deactivate() {
		m_indep.removeConstraint(this);
	}

}
