package homework5.processing.constraints;

/**
 * Interface any classes taht want to notify listeners of property changes must implement.
 * @author julenka
 *
 */
public interface IPropertyChangedNotifier {
	public <E> void addPropertyChangedListener(String propertyName, IPropertyChangedListener<E> listener);
	
	
}
