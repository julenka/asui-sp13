package homework4.android.constraints;

public interface IPropertyChangedNotifier {
	// Adding in requirement to support constraints for graphical objects
	// TODO: figure out how to support any property changed (not just ints and floats)
	public void addIntPropertyChangedListener(String propertyName, IPropertyChangedListener<Integer> listener);
	
	public void addDoublePropertyChangedListener(String propertyName, IPropertyChangedListener<Double> listener);
}
