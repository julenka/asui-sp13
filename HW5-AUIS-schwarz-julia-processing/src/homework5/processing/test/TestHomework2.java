package homework5.processing.test;

import homework5.processing.core.BoundaryRectangle;
import homework5.processing.graphicalobject.GraphicalObject;
import homework5.processing.graphicalobject.Group;
import homework5.processing.graphicalobject.OutlineRect;
import homework5.processing.graphicalobject.SimpleGroup;

import java.awt.Color;

import processing.core.PApplet;
import processing.core.PImage;


public class TestHomework2 extends BaseGraphicalTest {

	BoundaryRectangle bounds;
	PImage duke;
	GraphicalObject r;
	Group windowgroup;
	Group group;
	OutlineRect r2;
	
	public void setupTest(){
		duke = loadImage("duke.gif");
		
		windowgroup = new SimpleGroup (0, 0, 300, 400);
		group = new SimpleGroup (0, 0, 300, 400);

		testFrame.addChild (windowgroup);
		windowgroup.addChild (group);
		println("click to start test...");

	}
	
	int step = 0;
	
	@Override
	public void mousePressed() {
		super.mousePressed();
		step++;
		
		switch(step){
		case 1:
			println ("1. creating blue rect, thick = 4, x,y=0");
			r = new OutlineRect (0, 0, 50, 80, Color.BLUE, 4);
			group.addChild (r);
			break;
		case 2:
			println ("2. moving to 30,30");
			r.moveTo(30,30);
			break;
		case 3:
			println ("3. remove from group--see if goes away");
			group.removeChild (r);
			break;
		case 4:
			println("4. put back; overlap with a red rectangle");
			group.addChild (r);
			r2 = new OutlineRect (10, 20, 50, 80, Color.RED, 8);
			group.addChild (r2);
			break;
		case 5:
			println("5. bring blue to front");
			group.bringChildToFront(r);
			break;
		case 6:
			println("6. moving red rect while behind blue");
			r2.moveTo(20,30);
			break;
		case 7:
			println("7. change color of red rectangle to green");
			r2.setColor(Color.GREEN);
			break;
		default:
			exit();
			break;
		}
	}
	
// TODO Add all the tests below according to pattern above
	
//		println("8. Creating Filled Yellow Rect");
//		GraphicalObject r3 = new FilledRect (30, 40, 100, 20, Color.YELLOW);
//		group.addChild(r3);
//
//		println("9. Putting yellow next to blue");
//		bounds = r.getBoundingBox();
//		r3.moveTo(bounds.x+bounds.width, bounds.y+(bounds.height/2));
//
//		println("10. creating lines and icons");
//
//		println ("Line");
//		Line line1 = new Line (70, 130, 120, 180, Color.BLUE, 10);
//		group.addChild (line1);
//		group.addChild (new Line (10, 130, 10, 180, Color.BLACK, 1));
//		group.addChild (new Line (20, 130, 60, 130, Color.RED, 3));
//
//		println ("Icon");
//		group.addChild (new Icon (duke, 10, 200));
//		Icon icon1 = new Icon (loadImageFully ("dog.gif"), 80, 200);
//		group.addChild (icon1);
//
//		println("11. moving blue line behind red line using setX1");
//		line1.setX1(40);
//		line1.setY1(110);
//
//		println("12. moving blue line behind black line using moveTo");
//		line1.moveTo(1, 150);
//
//		println("13. moving big icon using setX1");
//		icon1.setX(30);
//
//		println("14. moving big icon in front of little icon");
//		icon1.moveTo(30, 220);
//
//		println("15. Test group clipping");
//		group.addChild (new Line (299, 0, 299, 400, Color.BLACK, 1));
//		println("15a. filledrect");
//		group.addChild (new FilledRect(250, 100, 100, 40, Color.YELLOW));
//
//		println("15b. line");
//		group.addChild (new Line (250, 110, 400, 200, Color.GREEN, 4));
//
//		println("15c. text");

//		group.addChild (new Text ("reallyLongStringShouldGetCutOff", 250, 200,
//				Typeface.create (Typeface.SERIF, Typeface.BOLD), 20,
//				Color.BLACK));

//
//		Group lg = new LayoutGroup (0, 0, 400, 400, Group.VERTICAL,2);
//		println("16. adding r to another group, should crash");

//		try {
//			lg.addChild(r); //might crash
//		} catch(Exception e) { println ("addChild raised exception:" + e); }
//		println("16z: about to remove all. Get ready for different groups");

//		windowgroup.removeChild(group);


//
//		GraphicalObject la1 = new OutlineRect (0, 0, 50, 80, Color.LTGRAY , 4);
//		GraphicalObject la2 = new FilledRect (60, 100, 10, 30, Color.YELLOW);
//		GraphicalObject la3 = new Line (10, 200, 70, 20, Color.RED, 8);
//		GraphicalObject la4 = new Icon (duke, 0, 10);
//		GraphicalObject lb1 = new OutlineRect (0, 0, 50, 80, Color.LTGRAY, 4);
//		GraphicalObject lb2 = new FilledRect (60, 100, 10, 30, Color.YELLOW);
//		GraphicalObject lb3 = new Line (10, 200, 70, 20, Color.RED, 8);
//		GraphicalObject lb4 = new Icon (duke, 0, 10);
//
//		println("17. adding new objects to simplegroup");

//
//		Group topgroup = new SimpleGroup (0, 0, 400, 400);
//
//		windowgroup.addChild (topgroup);
//		Group sgroup = new SimpleGroup (10, 10, 200, 400);
//		topgroup.addChild(sgroup);
//		sgroup.addChild(la1);
//		sgroup.addChild(la2);
//		sgroup.addChild(la3);
//		sgroup.addChild(la4);

//
//		println("18. adding new objects to layoutgroup");

//		LayoutGroup layoutgroup = new LayoutGroup (220, 0, 150, 400,
//				Group.VERTICAL, 2);
//		layoutgroup.addChild(lb1);
//		layoutgroup.addChild(lb2);
//		layoutgroup.addChild(lb3);
//		layoutgroup.addChild(lb4);
//		topgroup.addChild(layoutgroup);

//
//		println("19. removing long line from layout group");

//		layoutgroup.removeChild(lb3);

//
//		println("20. moving simple group to right and down");

//		sgroup.moveTo(30,30);

//
//		println("21. changing layout group's offset to 30");

//		layoutgroup.setOffset(30);

//
//		println("22. changing layout group to horizontal at x=10");

//		layoutgroup.setX(10);
//		layoutgroup.setY(200);
//		layoutgroup.setLayout(HORIZONTAL);

//
//		println("23. changing layout group to be wider");

//		layoutgroup.setWidth(400);

//
//		println("23a. moving filled rect in layout group to see what happens");

//		((FilledRect)lb2).setY(20);

//
//		println("23b. moving simplegroup to try to make sure visible");

//		sgroup.moveTo(35,0);

//
//		println("23c. resize simplegroup to children. Bounds should be (35,0,200,400). Then (35,0,~75,~205)");
//		bounds = sgroup.getBoundingBox();
//		println("   before bounds = "+bounds.x+", "+bounds.y+", "+bounds.width+", "+bounds.height);
//		sgroup.resizeToChildren();
//		bounds = sgroup.getBoundingBox();
//		println("    after bounds = "+bounds.x+", "+bounds.y+", "+bounds.width+", "+bounds.height);

//
//		println("24. New scale group with scale = 0.5, 2.0");

//		GraphicalObject lc1 = new OutlineRect (0, 0, 50, 80, Color.LTGRAY, 4);
//		GraphicalObject lc2 = new FilledRect (60, 100, 10, 30, Color.YELLOW);
//		GraphicalObject lc3 = new Line (10, 200, 70, 20, Color.RED, 8);
//		GraphicalObject lc4 = new Icon (duke, 0, 10);
//		ScaledGroup scalegroup = new ScaledGroup (220, 0, 150, 400, 0.5, 2.0);
//		scalegroup.addChild(lc1);
//		scalegroup.addChild(lc2);
//		scalegroup.addChild(lc3);
//		scalegroup.addChild(lc4);
//		topgroup.addChild(scalegroup);

//
//		println("25. Change the scale to be even smaller: 0.2, 0.4");

//		scalegroup.setScaleX(0.2);
//		scalegroup.setScaleY(0.4);


//
//		println("removing all. Getting ready for different groups");
//		windowgroup.removeChild(topgroup);


//
//		println("26. Test overlapping regions");

//		println("26a. first group");
//		Group topgroup2 = new SimpleGroup (0, 0, 400, 400);
//		windowgroup.addChild (topgroup2);
//		Group sgroup2 = new SimpleGroup (10, 10, 200, 400);
//		FilledRect ld1 = new FilledRect (30, 20, 70, 150, Color.YELLOW);
//		GraphicalObject ld2 = new FilledRect (10, 60, 110, 70, Color.GREEN);
//		sgroup2.addChild(ld1);
//		sgroup2.addChild(ld2);
//		topgroup2.addChild(sgroup2);

//
//		println("26b. adding second group to the right of the first");

//		Group sgroup3 = new SimpleGroup (175, 10, 200, 400);
//		FilledRect le1 = new FilledRect (30, 20, 70, 150, Color.RED);
//		GraphicalObject le2 = new FilledRect (10, 60, 110, 70, Color.BLUE);
//		sgroup3.addChild(le1);
//		sgroup3.addChild(le2);
//		topgroup2.addChild(sgroup3);

//
//		println("26c. move r-b second group to be on top of first");

//		sgroup3.moveTo(45, 35);

//
//		println("26d. change color on top: red -> gray");

//		le1.setColor(Color.GRAY);

//
//		println("26e. change color on bottom: yellow -> black");

//		ld1.setColor(Color.BLACK);

//
//		println("26f. move black on bottom");

//		ld1.moveTo(15, 10);
	
	public static void main(String args[]) {
		PApplet.main(new String[] { "homework5.processing.test.TestHomework2" });
	}
}
