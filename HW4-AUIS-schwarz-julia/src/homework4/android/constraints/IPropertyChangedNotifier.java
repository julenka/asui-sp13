package homework4.android.constraints;

public interface IPropertyChangedNotifier {
	public <E> void addPropertyChangedListener(String propertyName, IPropertyChangedListener<E> listener);
	
	
}
