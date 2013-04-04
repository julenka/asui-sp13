package homework4.android.constraints;

/**
 * Interface that classes must implemente to listen to property changes of objects.
 * @author julenka
 *
 * @param <E>
 */
public interface IPropertyChangedListener<E> {
	void onPropertyChanged(E oldValue, E newValue);
}
