package homework5.android.constraints;
/**
 * Constraints that sets a quotient to be the division of a numerator and denominator.
 * @author julenka
 *
 */
public class IntDivisionConstraint extends Constraint {
	Variable <Integer> m_quotient;
	Variable<Integer> m_numerator;
	Variable<Integer> m_denominator;
	
	public IntDivisionConstraint(Variable< Integer> quotient, Variable<Integer> numerator, Variable<Integer> denominator){
		m_quotient = quotient;
		m_numerator = numerator;
		m_denominator = denominator;
	}
	@Override
	public void evaluate() {
		m_quotient.setValue(m_numerator.getValue() / m_denominator.getValue());
	}

	@Override
	public void activate() {
		m_numerator.addConstraint(this);
		m_denominator.addConstraint(this);
		evaluate();
	}

	@Override
	public void deactivate() {
		m_numerator.addConstraint(this);
		m_denominator.addConstraint(this);
	}

}
