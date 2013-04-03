package homework4.android.constraints;

import homework4.android.graphicalobject.GraphicalObjectBase;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.util.Log;

public class IntProperty extends IntVariable {
	GraphicalObjectBase m_graphicalObject;
	private IIntGetter m_getter;
	private IIntSetter m_setter;
	
	private Method m_setMethod;
	private Method m_getMethod;
	
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
	
	public IntProperty(GraphicalObjectBase gObj, String propertyName, String getMethod, String setMethod) {
		m_graphicalObject = gObj;
		
		updateGetMethod(getMethod);
		updateSetMethod(setMethod);
		// m_value = new Integer(m_getter.getValue());
		try {
			m_value = Integer.parseInt(m_getMethod.invoke(m_graphicalObject).toString());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		m_graphicalObject.addIntPropertyChangedListener(propertyName, new IPropertyChangedListener<Integer>() {
			
			@Override
			public void onPropertyChanged(Integer oldValue, Integer newValue) {
				m_value = newValue;
				invalidate();
			}
		});
	}
	
	private void updateGetMethod(String methodName){
		try {
			m_getMethod = m_graphicalObject.getClass().getMethod(methodName);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void updateSetMethod(String methodName)
	{
		try {
			m_setMethod = m_graphicalObject.getClass().getMethod(methodName, int.class);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void setValue(int value) {	
		
		// TODO Auto-generated method stub
		m_value = value;
		try {
			m_setMethod.invoke(m_graphicalObject, value);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//m_setter.setValue(value);
	}

	
	
}
