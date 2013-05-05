package homework5.processing.app;

import homework5.processing.behavior.Behavior;
import homework5.processing.behavior.ChoiceBehavior;
import homework5.processing.behavior.MoveBehavior;
import homework5.processing.behavior.NewLineBehavior;
import homework5.processing.behavior.NewRectBehavior;
import homework5.processing.core.InteractiveFrame;
import homework5.processing.graphicalobject.GraphicalObject;
import homework5.processing.graphicalobject.SelectionHandles;
import homework5.processing.graphicalobject.Text;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;

public class DrawingEditor extends PApplet {

	private Behavior newRect;
	private Behavior newLine;
	private Behavior moveBehavior;
	private Behavior choiceBehavior;
	private Text status;
	InteractiveFrame interactiveFrame;
	@Override
	public void setup() {
		size(720, 1080, JAVA2D);
		background(255);
		interactiveFrame = new InteractiveFrame(this);
		println("Press 'l' to draw lines");
		println("Press 'r' to draw rectangles");
		println("Press 's' to enter selection mode");
		println("Press 'm' to enter move mode");
		println("Currently drawing lines");
		
		newRect = new NewRectBehavior(Color.RED, 5, interactiveFrame);
		newLine = new NewLineBehavior(Color.BLUE, 7, interactiveFrame);
		moveBehavior = new MoveBehavior(interactiveFrame);
		choiceBehavior = new ChoiceBehavior(interactiveFrame);
		
		interactiveFrame.addBehavior(newLine);
	}
	
	@Override
	public void draw() {
		interactiveFrame.draw();
	}

	private SelectionHandles makeSelectableObject(GraphicalObject child)
	{
		SelectionHandles result = new SelectionHandles(0, 0, 100, 100);
		result.addChild(child);
		return result;
	}
	
	private void addSelectionHandles()
	{
		List<GraphicalObject> childrenCopy = new ArrayList<GraphicalObject>(interactiveFrame.getChildren());
		for (GraphicalObject child : childrenCopy) {
			interactiveFrame.removeChild(child);
			interactiveFrame.addChild(makeSelectableObject(child));
		}
	}
	
	private void removeSelectionHandles()
	{
		List<GraphicalObject> childrenCopy = new ArrayList<GraphicalObject>(interactiveFrame.getChildren());
		for (GraphicalObject child : childrenCopy) {
			if(child instanceof SelectionHandles)
			{
				SelectionHandles sh = (SelectionHandles)child;
				interactiveFrame.removeChild(child);
				for (GraphicalObject child2 : sh.getChildren()) {
					interactiveFrame.addChild(child2);
				}
			}
		}
	}
	
	
	@Override
	public void keyPressed() {
		super.keyPressed();
		if(!(keyCode == KeyEvent.VK_R || keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_L || keyCode == KeyEvent.VK_M))
			return;
		
		interactiveFrame.clearBehaviors();
		removeSelectionHandles();
		if(keyCode == KeyEvent.VK_R)
		{
			interactiveFrame.addBehavior(newRect);
			println("currently drawing rectangles");
		} else if (keyCode == KeyEvent.VK_S)
		{
			interactiveFrame.addBehavior(choiceBehavior);
			addSelectionHandles();
			println("currently selecting items");
		}else if (keyCode == KeyEvent.VK_L)
		{
			interactiveFrame.addBehavior(newLine);
			println("currently drawing lines");
		}else if (keyCode == KeyEvent.VK_M)
		{
			interactiveFrame.addBehavior(moveBehavior);
			println("currently moving items");
		}
	}

	public static void main(String args[]) {
		PApplet.main(new String[] { "homework5.processing.app.DrawingEditor" });
	}
	
	
}
