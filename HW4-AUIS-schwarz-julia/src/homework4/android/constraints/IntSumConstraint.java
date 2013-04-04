package homework4.android.constraints;

/**
 * Constraint that sets a sum variable to be te sum of an arbitrary number of addends
 * @author julenka
 *
 */
public class IntSumConstraint extends Constraint {
	Variable <Integer> m_sum;
	Variable<Integer>[] m_addends;
	
	public IntSumConstraint(Variable< Integer> product, 
			Variable<Integer>... factors){
		m_sum = product;
		m_addends = factors;
		evaluate();
	}
	@Override
	public void evaluate() {
		Integer sum = 0;
		for (Variable<Integer> v : m_addends) {
			sum = sum + v.getValue();
		}
		m_sum.setValue(sum);

	}

	@Override
	public void activate() {
		for (Variable<Integer> f : m_addends) {
			f.addConstraint(this);
		}
		// TODO: factor out evalute()
		evaluate();
	}

	@Override
	public void deactivate() {
		for (Variable<Integer> f : m_addends) {
			f.removeConstraint(this);
		}
	}

}
