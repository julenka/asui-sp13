package homework4.android.constraints;

import java.util.List;

public class IntProductConstraint extends Constraint {

	Variable <Integer> m_product;
	Variable<Integer>[] m_factors;
	
	public IntProductConstraint(Variable< Integer> product, 
			Variable<Integer>... factors){
		m_product = product;
		m_factors = factors;
		activate();
		evaluate();
	}
	
	@Override
	public void evaluate() {
		Integer product = 1;
		for (Variable<Integer> v : m_factors) {
			product = product * v.getValue();
		}
		m_product.setValue(product);
	}

	@Override
	public void activate() {
		for (Variable<Integer> f : m_factors) {
			f.addConstraint(this);
		}
	}

	@Override
	public void deactivate() {
		for (Variable<Integer> f : m_factors) {
			f.removeConstraint(this);
		}
	}

}
