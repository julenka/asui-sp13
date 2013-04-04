package homework4.android.constraints;

import homework4.android.graphicalobject.GraphicalObjectBase;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.util.Log;

/**
 * Encapsulates any property of a graphical object as a Variable that can be constrained
 * Assumes that graphical object properties are accessed using getPropertyName 
 * and setPropertyName
 * propertyName can then be specified.
 * @author Julia
 *
 * @param <E>
 */
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
	
	final String LOG_TAG = "Homework4.GraphicalObjectProperty";
	private void updateGetMethod(String propertyName){
		try {
			m_getMethod = m_graphicalObject.getClass().getMethod("get" + propertyName);
		} catch (NoSuchMethodException e) {
			Log.e(LOG_TAG, e.getMessage());
		}
	}
	
	private void updateSetMethod(String propertyName)
	{
		try {
			m_setMethod = m_graphicalObject.getClass().getMethod("set" + propertyName, int.class);
		} catch (NoSuchMethodException e) {
			Log.e(LOG_TAG, e.getMessage());
		}
	}

	@Override
	public void setValue(E value) {	
		try {
			m_setMethod.invoke(m_graphicalObject, value);
		} catch (IllegalArgumentException e) {
			Log.e(LOG_TAG, e.getMessage());
		} catch (IllegalAccessException e) {
			Log.e(LOG_TAG, e.getMessage());
		} catch (InvocationTargetException e) {
			Log.e(LOG_TAG, e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public E getValue() {
		try {
			return (E) m_getMethod.invoke(m_graphicalObject);
		} catch (IllegalArgumentException e) {
			Log.e(LOG_TAG, e.getMessage());
		} catch (IllegalAccessException e) {
			Log.e(LOG_TAG, e.getMessage());
		} catch (InvocationTargetException e) {
			Log.e(LOG_TAG, e.getMessage());
		}
		return null;
	}
	
}
