package homework4.android.constraints;

import homework4.android.graphicalobject.GraphicalObjectBase;

import java.lang.reflect.Method;

import android.util.Log;

public class IntProperty extends IntVariable {
	GraphicalObjectBase m_graphicalObject;
	private IIntGetter m_getter;
	private IIntSetter m_setter;
	
	public IntProperty(GraphicalObjectBase gObj, String propertyName, IIntGetter getter, IIntSetter setter) {
		m_getter = getter;
		m_setter = setter;
		m_value = m_getter.getValue();
		m_graphicalObject = gObj;
		m_graphicalObject.addIntPropertyChangedListener(propertyName, new IPropertyChangedListener<Integer>() {
			
			@Override
			public void onPropertyChanged(Integer oldValue, Integer newValue) {
				m_value = newValue;
				invalidate();
			}
		});
	}

	@Override
	public void setValue(int value) {	
		
		// TODO Auto-generated method stub
		m_value = value;
		m_setter.setValue(value);
	}

}
