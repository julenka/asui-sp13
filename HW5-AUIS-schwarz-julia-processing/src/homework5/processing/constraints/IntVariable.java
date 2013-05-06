package homework5.processing.constraints;


/**
 * Represents an simple int value, isn't tied to a constant
 * @author julenka
 *
 */
public class IntVariable extends Variable<Integer> {

	int m_value = 0;

	public IntVariable(){}
	public IntVariable(int value)
	{
		m_value = value;
	}
	
	public Integer getValue(){
		return m_value;
	}
	
	public void setValue(Integer value)
	{
		m_value = value;
		invalidate();
	}
}
