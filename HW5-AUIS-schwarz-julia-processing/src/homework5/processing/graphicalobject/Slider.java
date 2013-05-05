package homework5.processing.graphicalobject;

import homework5.processing.constraints.EqualConstraint;
import homework5.processing.constraints.GraphicalObjectProperty;

import java.awt.Color;

/**
 * Slider widget implemented for ValueRectTest and MyConstraintsTest
 * Exposes a value property which can be used to modify properties of other objects
 * @author Julia
 *
 */
public class Slider extends SimpleGroup
{
	int m_value;
	SimpleGroup m_sliderGroup;
	public Slider(int x, int y, int width, int height, int sliderWidth, Color sliderColor, int value)
	{
		super(x,y,width,height);

		addChild(new OutlineRect(0,0,width,height,Color.BLACK, 3));
		m_sliderGroup = new SimpleGroup(0, 0, width, height);
		addChild(m_sliderGroup);
		
		FilledRect slider = new FilledRect(0, 0, 
				sliderWidth, height, sliderColor);
		m_sliderGroup.addChild(slider);
		
		// add a constraing: slider x position is value
		GraphicalObjectProperty<Integer> sliderPos = new GraphicalObjectProperty<Integer>(slider, "X");
		GraphicalObjectProperty<Integer> sliderValue = new GraphicalObjectProperty<Integer>(this, "Value");
		new EqualConstraint(sliderValue, sliderPos).activate();
		new EqualConstraint(sliderPos, sliderValue).activate();
		
		setValue(value);
	}
	
	public SimpleGroup getSlider()
	{
		return m_sliderGroup;
	}
	
	public int getValue()
	{
		return m_value;
	}
	
	public void setValue(int value)
	{
		int oldVal = m_value;
		m_value = value;
		notifyPropertyChanged("Value", oldVal, value);
		boundsChanged();
	}
	
}