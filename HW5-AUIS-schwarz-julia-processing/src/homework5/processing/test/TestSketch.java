package homework5.processing.test;

import processing.core.PApplet;

public class TestSketch extends PApplet{

	public TestSketch() {
	}

	@Override
	public void setup() {
		size(200,200, P2D);
		frame.setTitle("TestSketch");
		background(0);
	}

	@Override
	public void draw() {
		stroke(255);
		if (mousePressed) {
			line(mouseX,mouseY,pmouseX,pmouseY);
		}
	}

	public static void main(String args[]) {
		PApplet.main(new String[] { "homework5.processing.test.TestSketch" });
	}

}
