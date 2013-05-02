package homework5.processing.app;

import homework5.processing.behavior.MoveBehavior;
import homework5.processing.constraints.Constraint;
import homework5.processing.constraints.GraphicalObjectCenterConstraint;
import homework5.processing.core.InteractiveWindowGroup;
import homework5.processing.graphicalobject.FilledRect;
import homework5.processing.graphicalobject.GraphicalObject;
import homework5.processing.graphicalobject.Line;
import homework5.processing.graphicalobject.SimpleGroup;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import processing.core.PApplet;

public class NodeEdgeEditor extends InteractiveWindowGroup {

	class NodePair 
	{
		public NodePair(FilledRect r1, FilledRect r2){
			node1 = r1;
			node2 = r2;
		}
		public FilledRect node1;
		public FilledRect node2;
		@Override
		public boolean equals(Object o) {
			if(!(o instanceof NodePair)) return false;
			NodePair p = (NodePair)o;
			if(node1 == p.node1 && node2 == p.node2) return true;
			if(node2 == p.node1 && node1 == p.node2) return true;
			return false;
		}
	}
	
	enum EditorMode {Node, Edge, Move};
	
	private EditorMode m_mode = EditorMode.Node;
	SimpleGroup m_nodeLayer;
	SimpleGroup m_edgeLayer;
	MoveBehavior m_moveNodes;
	
	Set<NodePair> m_nodePairs = new HashSet<NodePair>();
	
	FilledRect m_startRect;
	List<Constraint> m_constraints = new ArrayList<Constraint>();

	@Override
	public void setup() {
		super.setup();
		println("Press 'n' to create nodes");
		println("Press 'e' to create edges");
		println("Press 'm' to move nodes");
		println("Currently creating nodes");
		
		m_nodeLayer = new SimpleGroup(0,0,width, height);
		addChild(m_nodeLayer);
		
		m_edgeLayer = new SimpleGroup(0,0, width, height);
		addChild(m_edgeLayer);
		
		m_moveNodes = new MoveBehavior(m_nodeLayer);
		
	}
	
	private void activateConstraints()
	{
		for (Constraint c : m_constraints) {
			c.activate();
		}
	}
	
	private void clearConstraints()
	{
		for (Constraint c : m_constraints) {
			c.deactivate();
		}
		m_constraints.clear();
	}
	
	@Override
	public void keyPressed() {
		super.keyPressed();
		if(!( keyCode == KeyEvent.VK_E || keyCode == KeyEvent.VK_M || keyCode == KeyEvent.VK_N))
			return;
		m_behaviors.clear();
		m_startRect = null;
		if(keyCode == KeyEvent.VK_M)
		{
			println("currently moving nodes");
			m_mode = EditorMode.Move;
			m_behaviors.add(m_moveNodes);
		} else if (keyCode == KeyEvent.VK_E)
		{
			m_mode = EditorMode.Edge;
			println("currently making edges");
		}else if (keyCode == KeyEvent.VK_N)
		{
			m_mode = EditorMode.Node;
			println("currently making nodes");
		}
	
	}
	
	@Override
	public void mousePressed() {
		super.mousePressed();
		
		boolean handled = false;
				
		if(m_mode == EditorMode.Move) return;
		
		switch (m_mode) {
		case Node:
			FilledRect addMe = new FilledRect((int)mouseX, (int)mouseY, 30, 30, Color.BLACK);
			m_nodeLayer.addChild(addMe);
			handled = true;
			break;
		case Edge:
			// find a node that you hit
			FilledRect hit = null;
			for (GraphicalObject n : m_nodeLayer.getChildren()) {
				if(n.contains((int)mouseX, (int) mouseY))
				{
					hit = (FilledRect)n;
					break;
				}
			}
			if(hit == null) break;
			
			if(m_startRect == null){
				m_startRect = hit;
				m_startRect.setColor(Color.GREEN);
				return;
			} else if(m_startRect != hit)
			{
				// don't add an edge if it is already there
				NodePair test = new NodePair(m_startRect, hit);
				for (NodePair p : m_nodePairs) {
					if(p.equals(test)) return;
				}
				
				// add a line, constrain to each center
				Line toAdd = new Line(10,10,20,20, Color.BLACK, 4);
				// add a constraint to the line
				m_constraints.add( new GraphicalObjectCenterConstraint(toAdd, hit, "X1", "LineThickness", "X", "Width"));
				m_constraints.add(new GraphicalObjectCenterConstraint(toAdd, hit, "Y1", "LineThickness", "Y", "Height"));
				m_constraints.add( new GraphicalObjectCenterConstraint(toAdd, m_startRect, "X2", "LineThickness", "X", "Width"));
				m_constraints.add(new GraphicalObjectCenterConstraint(toAdd, m_startRect, "Y2", "LineThickness", "Y", "Height"));
				m_edgeLayer.addChild(toAdd);
				m_nodePairs.add(new NodePair(m_startRect, hit));
				m_startRect.setColor(Color.BLACK);
				m_startRect = null;
				
				activateConstraints();
			}
			
			break;
		}
	}
	

	public static void main(String args[]) {
		PApplet.main(new String[] { "homework5.processing.app.NodeEdgeEditor" });
	}

}
