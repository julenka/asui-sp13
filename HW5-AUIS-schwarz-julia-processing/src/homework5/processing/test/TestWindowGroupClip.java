package homework5.processing.test;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;

import processing.core.PGraphics;
import processing.core.PGraphicsJava2D;

import homework5.processing.core.WindowGroup;
import homework5.processing.graphicalobject.GraphicalObject;

/***
 * Tests clipping functionality in WindowGroup
 * @author julenka
 *
 */
public class TestWindowGroupClip extends WindowGroup {

	public TestWindowGroupClip() {
	}
	
	@Override
	public void setup() {
		super.setup();
		
	}
	
	@Override
	public void draw(PGraphics graphics, Shape clipShape) {
		
		
		graphics.fill(255, 0, 0);
		graphics.ellipse(10,10, 40, 40);
	}

}
