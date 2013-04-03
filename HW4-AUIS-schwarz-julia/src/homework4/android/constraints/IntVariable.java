package homework4.android.constraints;

import java.util.ArrayList;
import java.util.List;

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
