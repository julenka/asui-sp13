package homework4.android.constraints;

import homework4.android.graphicalobject.GraphicalObjectBase;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.util.Log;

public class GraphicalObjectProperty<E> extends Variable<E>{
	GraphicalObjectBase m_graphicalObject;
	
	private Method m_setMethod;
	private Method m_getMethod;
	
	public GraphicalObjectProperty(GraphicalObjectBase gObj, String propertyName) {
		m_graphicalObject = gObj;
		
		updateGetMethod(propertyName);
		updateSetMethod(propertyName);
		
		m_graphicalObject.addPropertyChangedListener(propertyName, new IPropertyChangedListener<Integer>() {
			
			@Override
			public void onPropertyChanged(Integer oldValue, Integer newValue) {
				invalidate();
			}
		});
	}
	
	private void updateGetMethod(String propertyName){
		try {
			m_getMethod = m_graphicalObject.getClass().getMethod("get" + propertyName);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void updateSetMethod(String propertyName)
	{
		try {
			m_setMethod = m_graphicalObject.getClass().getMethod("set" + propertyName, int.class);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void setValue(E value) {	
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
	}

	@SuppressWarnings("unchecked")
	@Override
	public E getValue() {
		try {
			return (E) m_getMethod.invoke(m_graphicalObject);
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
		return null;
	}
	
}
