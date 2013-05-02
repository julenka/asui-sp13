package homework5.processing.constraints;

/**
 * Interface that classes must implemente to listen to property changes of objects.
 * @author julenka
 *
 * @param <E>
 */
public interface IPropertyChangedListener<E> {
	void onPropertyChanged(E oldValue, E newValue);
}
