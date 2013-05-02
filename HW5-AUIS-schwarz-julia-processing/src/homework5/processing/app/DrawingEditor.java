package homework5.processing.app;

import homework5.processing.behavior.Behavior;
import homework5.processing.behavior.ChoiceBehavior;
import homework5.processing.behavior.MoveBehavior;
import homework5.processing.behavior.NewLineBehavior;
import homework5.processing.behavior.NewRectBehavior;
import homework5.processing.core.InteractiveWindowGroup;
import homework5.processing.graphicalobject.GraphicalObject;
import homework5.processing.graphicalobject.SelectionHandles;
import homework5.processing.graphicalobject.Text;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import sun.jkernel.Bundle;

public class DrawingEditor extends InteractiveWindowGroup {

	private Behavior newRect;
	private Behavior newLine;
	private Behavior moveBehavior;
	private Behavior choiceBehavior;
	private Text status;
	
	@Override
	public void setup() {
		super.setup();
		println("Press 'l' to draw lines");
		println("Press 'r' to draw rectangles");
		println("Press 's' to enter selection mode");
		println("Press 'm' to enter move mode");
		println("Currently drawing lines");
		
		newRect = new NewRectBehavior(Color.RED, 5, this);
		newLine = new NewLineBehavior(Color.BLUE, 7, this);
		moveBehavior = new MoveBehavior(this);
		choiceBehavior = new ChoiceBehavior(this);
		
		m_behaviors.add(newLine);
	}

	private SelectionHandles makeSelectableObject(GraphicalObject child)
	{
		SelectionHandles result = new SelectionHandles(0, 0, 100, 100);
		result.addChild(child);
		return result;
	}
	
	private void addSelectionHandles()
	{
		List<GraphicalObject> childrenCopy = new ArrayList<GraphicalObject>(getChildren());
		for (GraphicalObject child : childrenCopy) {
			removeChild(child);
			addChild(makeSelectableObject(child));
		}
	}
	
	private void removeSelectionHandles()
	{
		List<GraphicalObject> childrenCopy = new ArrayList<GraphicalObject>(getChildren());
		for (GraphicalObject child : childrenCopy) {
			if(child instanceof SelectionHandles)
			{
				SelectionHandles sh = (SelectionHandles)child;
				removeChild(child);
				for (GraphicalObject child2 : sh.getChildren()) {
					addChild(child2);
				}
			}
		}
	}
	
	
	@Override
	public void keyPressed() {
		super.keyPressed();
		if(!(keyCode == KeyEvent.VK_R || keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_L || keyCode == KeyEvent.VK_M))
			return;
		
		m_behaviors.clear();
		removeSelectionHandles();
		if(keyCode == KeyEvent.VK_R)
		{
			m_behaviors.add(newRect);
			println("currently drawing rectangles");
		} else if (keyCode == KeyEvent.VK_S)
		{
			m_behaviors.add(choiceBehavior);
			addSelectionHandles();
			println("currently selecting items");
		}else if (keyCode == KeyEvent.VK_L)
		{
			m_behaviors.add(newLine);
			println("currently drawing lines");
		}else if (keyCode == KeyEvent.VK_M)
		{
			m_behaviors.add(moveBehavior);
			println("currently moving items");
		}
	}

}
