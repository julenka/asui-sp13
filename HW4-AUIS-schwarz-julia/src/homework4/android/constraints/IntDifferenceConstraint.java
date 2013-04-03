package homework4.android.constraints;

public class IntDifferenceConstraint extends Constraint {
	Variable <Integer> m_difference;
	Variable<Integer>[] m_factors;
	
	public IntDifferenceConstraint(Variable< Integer> product, 
			Variable<Integer>... factors){
		m_difference = product;
		m_factors = factors;
	}
	@Override
	public void evaluate() {
		Integer difference = m_factors[0].getValue();
		for (Variable<Integer> v : m_factors) {
			difference = difference + v.getValue();
		}
		m_difference.setValue(difference);

	}

	@Override
	public void activate() {
		for (Variable<Integer> f : m_factors) {
			f.addConstraint(this);
		}
		evaluate();
	}

	@Override
	public void deactivate() {
		for (Variable<Integer> f : m_factors) {
			f.removeConstraint(this);
		}
	}

}
