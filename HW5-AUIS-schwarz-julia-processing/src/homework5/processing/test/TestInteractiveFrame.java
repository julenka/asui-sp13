package homework5.processing.test;

import homework5.processing.behavior.ChoiceBehavior;
import homework5.processing.behavior.MoveBehavior;
import homework5.processing.behavior.NewLineBehavior;
import homework5.processing.graphicalobject.FilledRect;
import homework5.processing.graphicalobject.GraphicalObject;
import homework5.processing.graphicalobject.Group;
import homework5.processing.graphicalobject.LayoutGroup;
import homework5.processing.graphicalobject.OutlineRect;
import homework5.processing.graphicalobject.SelectionHandles;
import homework5.processing.graphicalobject.SimpleGroup;
import homework5.processing.graphicalobject.Text;

import java.awt.Color;
import java.awt.event.KeyEvent;

import processing.core.PApplet;

// Base class for testing interactive windows
public class TestInteractiveFrame extends BaseInteractiveTest {
	ChoiceBehavior m_choiceBehavior;
	
	private SelectionHandles makeSelectableObject(GraphicalObject child)
	{
		SelectionHandles result = new SelectionHandles(0, 0, 100, 100);
		result.addChild(child);
		return result;
	}

	private void addLabelToGroup(Group g, String label )
	{
		g.addChild(new Text(label, 0, 40, createFont("Helvetica", 24), 18, Color.BLACK));
	}
	
	private void addBorderToGroup(Group g)
	{
		g.addChild(new OutlineRect(0,0, g.getBoundingBox().width, g.getBoundingBox().height, Color.BLACK, 2));
	}
	
	@Override
	public void setupTest()
	{
		Group layoutRoot = new LayoutGroup(0, 0, width, height, LayoutGroup.VERTICAL, 10);
		testFrame.addChild(layoutRoot);
		
		Group lineGroup = new SimpleGroup(0,0,width, 300);
		addLabelToGroup(lineGroup, "create lines in root");
		testFrame.addBehavior(new NewLineBehavior(Color.MAGENTA, 5, lineGroup));
		layoutRoot.addChild(lineGroup);
		
		Group moveGroup = new SimpleGroup(0, 100, width, 300);
		addLabelToGroup(moveGroup, "move objects");
		moveGroup.addChild(new Text("This is just a test", 20, 50, createFont("Helvetica", 24), 22, Color.GREEN));
		layoutRoot.addChild(moveGroup);
		testFrame.addBehavior(new MoveBehavior(moveGroup));
		
		LayoutGroup buttons = new LayoutGroup(10, 10, 700, 400 , LayoutGroup.HORIZONTAL, 10);
		for (int i = 0; i < 5; i++) {
			buttons.addChild(makeSelectableObject(new FilledRect(0,0,50,50, Color.RED)));
		}
		
		layoutRoot.addChild(buttons);
		testFrame.addBehavior(new ChoiceBehavior(buttons));
		
	}
	
	
	@Override
	public void keyPressed() {
		super.keyPressed();
		if(keyCode == KeyEvent.VK_S)
		{
			m_choiceBehavior.setType((m_choiceBehavior.getType() + 1) % 3);
			println("choice behavior is " + m_choiceBehavior.getType());
		} else if (keyCode == KeyEvent.VK_F)
		{
			m_choiceBehavior.setFirstOnly(!m_choiceBehavior.isFirstOnly());
			println("first only is " + m_choiceBehavior.isFirstOnly());
		}
	}
	

	public static void main(String args[]) {
		PApplet.main(new String[] { "homework5.processing.test.TestInteractiveFrame" });
	}

}
