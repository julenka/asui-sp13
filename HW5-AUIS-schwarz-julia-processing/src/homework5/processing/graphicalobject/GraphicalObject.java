package homework5.processing.graphicalobject;
import homework5.processing.core.BoundaryRectangle;

import java.awt.Shape;

import processing.core.PGraphics;
import processing.core.PMatrix;

public interface GraphicalObject {
	public void draw(PGraphics graphics, Shape clipShape);
	
	public BoundaryRectangle getBoundingBox();
	public void moveTo(int x, int y);
	public Group getGroup();
	public void setGroup(Group group);
	public boolean contains(int x, int y);
	
	public void setAffineTransform(PMatrix af);
	public PMatrix getAffineTransform();
	

}
