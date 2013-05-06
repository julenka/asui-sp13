package homework5.processing.app;

import homework5.processing.behavior.MoveXBehavior;
import homework5.processing.constraints.IPropertyChangedListener;
import homework5.processing.core.BoundaryRectangle;
import homework5.processing.core.InteractiveFrame;
import homework5.processing.graphicalobject.LayoutGroup;
import homework5.processing.graphicalobject.Slider;
import homework5.processing.graphicalobject.Text;

import java.awt.Color;

import processing.core.PApplet;
import processing.core.PImage;

/*****************************************************************************
 * Cherry Blossom Generator - Create new cherry trees with a mouse click!      *
 * Copyright (C) 2009  Luca Ongaro                                             *
 * Author's website: http://www.lucaongaro.eu/                                 *
 *                                                                             *
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.txt (text of the licence)  *
 *                                                                             *
 * This program is free software; you can redistribute it and/or               *
 * modify it under the terms of the GNU General Public License                 *
 * as published by the Free Software Foundation; either version 2              *
 * of the License, or (at your option) any later version.                      *
 *                                                                             *
 * This program is distributed in the hope that it will be useful,             *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of              *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the               *
 * GNU General Public License for more details.                                *
 *                                                                             *
 * You should have received a copy of the GNU General Public License           *
 * along with this program; if not, write to the Free Software                 *
 * Foundation, Inc., 51 Franklin Street, Fifth Floor,                          *
 * Boston, MA  02110-1301, USA.                                                *
 *                                                                             *
 ******************************************************************************/

/*****************************************************************************
 * Snow courtesy of Adelaide Cole                                             *
 ******************************************************************************/

public class CherryBlossom extends PApplet {
	int generations = 11;
	int n = 1;
	int slots = floor(pow(2, generations+1))-1;
	int nextSlot = 1;
	Branch[] branches = new Branch[slots];
	int c1 = color(0xCC, 0xCC, 0xFF);
	int c2 = color(0xEE, 0xEE, 0xFF);
	private boolean refreshTree = true;
	
	InteractiveFrame frame;
	float treeAngleShift = 0.5f;
	float treeVariance = 0.5f;
	float treeHeight = 100.0f;
	float randomness = 0.0f;
	
	PImage treeImage;
	
	
	boolean isSnowing = true;
	boolean UIVisible = true;
	
	public void setup() {
		size(700, 500, JAVA2D);
		frame = new InteractiveFrame(this);
		smooth();
		noStroke();
		setupSnow();
		setupUI();
		println("press spacebar or U to toggle UI");
		println("press s to toggle snow");
	}
	
	void setupUI(){
		LayoutGroup root = new LayoutGroup(0, 0, width, height / 3, LayoutGroup.VERTICAL , 5);
		final Text t1 = new Text("angle:", 0, 0,createFont("Helvetica",14), 14, Color.BLACK);
		root.addChild(t1);
		
		Slider s1 = new Slider(0, 0, width, 30, 30, Color.BLACK, width / 2);
		s1.addPropertyChangedListener("Value", new IPropertyChangedListener<Integer>() {
			
			@Override
			public void onPropertyChanged(Integer oldValue, Integer newValue) {
				// change the angle of the tree and redraw
				treeAngleShift = newValue / (float)width;
				t1.setText(String.format("angle: %.2f", treeAngleShift));
				refreshTree = true;
			}
		});
		root.addChild(s1);
		frame.addBehavior(new MoveXBehavior(s1.getSlider()));
		
		final Text t2 = new Text("height:", 0, 0,createFont("Helvetica",14), 14, Color.BLACK);
		root.addChild(t2);
		Slider s2 = new Slider(0, 0, width, 30, 30, Color.BLACK, width / 2);
		s2.addPropertyChangedListener("Value", new IPropertyChangedListener<Integer>() {
			
			@Override
			public void onPropertyChanged(Integer oldValue, Integer newValue) {
				// change the angle of the tree and redraw
				treeHeight = 200.0f * newValue / (float)width;
				t2.setText(String.format("height: %.2f", treeHeight));
				refreshTree = true;
			}
		});
		root.addChild(s2);
		
		final Text t3 = new Text("randomness:", 0, 0,createFont("Helvetica",14), 14, Color.BLACK); 
		root.addChild(t3);
		Slider s3 = new Slider(0, 0, width, 30, 30, Color.BLACK, width / 2);
		s3.addPropertyChangedListener("Value", new IPropertyChangedListener<Integer>() {
			
			@Override
			public void onPropertyChanged(Integer oldValue, Integer newValue) {
				// change the angle of the tree and redraw
				randomness = (float) newValue / width;
				t3.setText(String.format("randomness: %.2f", randomness));
				refreshTree = true;
			}
		});
		root.addChild(s3);
		
		frame.addBehavior(new MoveXBehavior(s1.getSlider()));
		frame.addBehavior(new MoveXBehavior(s2.getSlider()));
		frame.addBehavior(new MoveXBehavior(s3.getSlider()));
		
		
		frame.addChild(root);
	}

	public void draw() {
		//	  stroke(0x332010);
		if(refreshTree)
		{
			drawTree();
			g.endDraw();
			treeImage = new PImage(g.image);
			refreshTree = false;
		} else {
			if(treeImage != null)
			{
				image(treeImage, 0, 0);
				frame.damage(new BoundaryRectangle(0,0, width, height));
			}
		}
		g.beginDraw();
		if(UIVisible) frame.draw();
		if(isSnowing) drawSnow();
		g.endDraw();
		
	}

	void drawTree()
	{
		for(int i=0; i<slots; i++) {
			branches[i]=null;
		}
		branches[0] = new Branch(0, floor(random(1, 1 + round(5 * randomness))), treeHeight, new Position((width+0.0f)/2, height+0.0f), PI/2, treeAngleShift, randomness);
		nextSlot = 1;
		background(0xEEEEFF);
		gradientBackground(c1, c2);
		stroke(51, 32, 16);
		fill(0);
		for(int i=0; i<slots; i++) {
			while(branches[i] != null && branches[i].steps > 0) {
				branches[i].drawStep();
			}
			if(nextSlot <= slots - 2) {
				branches[nextSlot] = branches[i].generateChild(0);
				nextSlot += 1;
				branches[nextSlot] = branches[i].generateChild(1);
				nextSlot += 1;
			}
			branches[i].active = false;
		}
		noStroke();
		for(int i=0; i<slots; i++) {
			if(branches[i].generation == generations) {
				ellipseMode(CENTER);
				fill(0xF2, 0xAF, 0xC1);
				ellipse(branches[i].position.x+1.5f, branches[i].position.y, random(2, 10), random(2, 10));
				fill(0xED, 0x7A, 0x9E);
				ellipse(branches[i].position.x, branches[i].position.y+1.5f, random(2, 10), random(2, 10));
				fill(0xE5, 0x4C, 0x7C);
				ellipse(branches[i].position.x-1.5f, branches[i].position.y, random(2, 10), random(2, 10));
			}
		}
	}
	
	void gradientBackground(int c1, int c2) {
		for (int i=0; i<=width; i++){
			for (int j = 0; j<=height; j++){
				int c = color(
						(red(c1)+(j)*((red(c2)-red(c1))/height)),
						(green(c1)+(j)*((green(c2)-green(c1))/height)),
						(blue(c1)+(j)*((blue(c2)-blue(c1))/height))
						);
				set(i, j, c);
			}
		}
	}

	public class Branch {
		public int generation;
		public int steps;
		private float stepLength;
		public Position position;
		public float angle;
		public float maxAngleVar = 0.0f;
		public boolean active = true;
		public float angleShift = 0.5f;
		public float stepMagnitude;
		float randomness;
		Branch(int gen, int steps, float stepMagnitude, Position p, float ang, float angleShift, float randomness) {
			this.generation = gen;
			this.steps = steps;
			this.stepMagnitude = stepMagnitude;
			this.stepLength = stepMagnitude/(this.generation+1);
			this.position = p;
			this.angle = ang;
			this.angleShift = angleShift;
			this.randomness = randomness;
		}
		public void drawStep() {
			float r = random(-1, 1);
			this.angle = this.angle + this.maxAngleVar*r;
			this.stepLength = this.stepLength - this.stepLength*0.2f;
			Position oldPosition = new Position(0.0f, 0.0f);
			oldPosition.x = this.position.x;
			oldPosition.y = this.position.y;
			this.position.x += this.stepLength*cos(this.angle);
			this.position.y -= this.stepLength*sin(this.angle);
			strokeWeight(floor(20/(this.generation+1)));
			line(oldPosition.x, oldPosition.y, this.position.x, this.position.y);
			this.steps = this.steps - 1;
		}
		public Branch generateChild(int cn) {
			int newGen = this.generation + 1;
			float angleShiftLocal = angleShift;
			if (cn == 1) {
				angleShiftLocal = angleShiftLocal*(-1);
			}
			float childAngle = this.angle+angleShiftLocal;
			float px = this.position.x;
			float py = this.position.y;
			Position parentPos = new Position(px, py);
			Branch child = new Branch(newGen, floor(random(1, 1 + round(5 * randomness))), stepMagnitude, parentPos, childAngle, angleShift, randomness);
			return child;
		}
	}

	public class Position {
		public float x;
		public float y;
		Position(float ax, float ay) {
			this.x = ax;
			this.y = ay;
		}
	}

	int numberBalls = 400;
	int numberSnow = 800;
	 
	//this creates an empty array called myBalls that can contain numberBalls objects of the class Ball
	Ball[] myBalls = new Ball[numberBalls];
	Ball[] backsnow = new Ball[numberSnow];
	 
	void setupSnow(){	   
		for(int i = 0; i<numberSnow; i++)
		{
			backsnow[i] = new Ball();
			backsnow[i].myDiameter = 2;
			backsnow[i].posX = random(0, width);
			backsnow[i].posY = random(0, height);
			backsnow[i].speedX = random(0, .5f);
			backsnow[i].speedY = random(1, 2);
			backsnow[i].r = random(60, 140);
		}

		for(int i =0; i<numberBalls; i++)
		{
			myBalls[i] = new Ball();
			myBalls[i].myDiameter = 3;
			myBalls[i].posX = random(0, width);
			myBalls[i].posY = random(0, height);
			myBalls[i].speedX = random(0, 1);
			myBalls[i].speedY = random(1, 3);
			myBalls[i].r = random(140, 255);
			myBalls[i].a = 200;
		}

	}
	 
	void drawSnow()
	{
		for(int i =0; i<numberBalls; i++)
		{
			myBalls[i].update();
			backsnow[i].update();
		}
	}
	
	public void keyPressed() {
		switch(key) {
		case 's' : isSnowing = !isSnowing; break;
		case 'u' : case ' ' : UIVisible = !UIVisible; break;
		default: return;
		}
	}
	 
	class Ball //this does not exist until you call it
	{
	  //these are properties of the class
	  int myDiameter = 10;
	  float r = 255;
	  float g = 255;
	  float b = 255;
	  float a = 110;
	  float posX = 250; //these are the default properties of the class
	  float posY = 250;
	  float speedX = 3; //three pixels at a time
	  float speedY = 2;
	  //
	   
	  //this is the method
	  //and it is a function inside the class, it can be whatever name you decide
	  void update()
	  {
	   fill(r, a);
	   ellipse(posX, posY, myDiameter, myDiameter);
	  posX+= speedX;
	  posY+= speedY;
	   
	  if(posX > width) //when you have only one instruction in your block of instruction, you don't have to use the curly brackets
	  posX = 0;
	   
	  if(posX < 0)
	  posX= width;
	   
	  if(posY > height)
	  posY = 0;
	   
	  if(posY < 0)
	  posY = height;
	   
	  if(speedY < 2)
	  {
	  myDiameter = 3;
	  r = 130;
	  }
	   
	  if(speedY < 1.6)
	  {
	  myDiameter = 2;
	  r = 100;
	  }
	   
	  if(speedY < 1.2)
	  {
	  myDiameter = 1;
	  r = 70;
	  }
	   
	  }
	}


}
