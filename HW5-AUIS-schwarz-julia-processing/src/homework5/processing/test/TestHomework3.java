package homework5.processing.test;

public class TestHomework3  {

//	class mynewlinebehavior extends NewLineBehavior
//	{
//		class myline extends Line implements Selectable
//		{
//			int realColor;
//			boolean isinterimselected, isselected;
//
//			public myline(int x1, int y1, int x2, int y2, int color, int lineThickness)
//			{
//				super(x1,y1,x2,y2,color,lineThickness);
//				realColor = color;
//				isinterimselected = false;
//				isselected = false;
//			}
//			void myfixdraw() {
//				if (isinterimselected) {
//					if (isselected) this.setColor(Color.BLACK); //both
//					else this.setColor(Color.RED); // just interim
//				}
//				else if (isselected) this.setColor(Color.GREEN) ; //just regular selected
//				else this.setColor(realColor); // neither
//			}
//			public void setInterimSelected (boolean interimSelected) {
//				isinterimselected = interimSelected;
//				myfixdraw();
//			}
//			public boolean isInterimSelected () {return isinterimselected; }
//			public void setSelected (boolean selected) {
//				isselected = selected;
//				myfixdraw();
//			}
//			public boolean isSelected () {return isselected;}
//		} // end of myline
//
//
//		int myColor;
//		int thickness;
//
//		public mynewlinebehavior (int color, int lineThickness)
//		{
//			super( color, lineThickness);
//			myColor = color;
//			thickness = lineThickness;
//		}
//
//		public GraphicalObject make(int x1, int y1, int x2, int y2)
//		{
//			myline ml = new myline(x1,y1,x2,y2,myColor,thickness);
//			return ml;
//		}
//
//		public void resize(GraphicalObject gobj, int x1, int y1, int x2, int y2)
//		{
//			myline r = (myline)gobj;
//			r.setX1(x1);
//			r.setY1(y1);
//			r.setX2(x2);
//			r.setY2(y2);
//		}
//
//	}
//
//	final String LOG_TAG = "Homework3.TestHomework3";
//	@Override
//	protected void setup(){
//		Log.d("DV", "setup()  == "+this);	
//
//		try {
//			// I made the group's widht and height equal to the draw view size so that it was easier to debug
//			SimpleGroup sgroup = new SimpleGroup(0,0,drawView.getWidth(),drawView.getHeight());
//			this.addChild(sgroup);
//
//			NewLineBehavior nlint = new NewLineBehavior( Color.RED,7);
//			this.addBehavior(nlint);
//			nlint.setGroup(sgroup);
//			
//			// In android character codes are not equal to key codes. I have to modify my code to use key codes instead of character codes
//			Log.v(LOG_TAG, "'L' keycode is " + (int)'L');
//			Log.v(LOG_TAG, "'l' keycode is " + (int)'l');
//			Log.v(LOG_TAG, "'R' keycode is " + (int)'r');
//			Log.v(LOG_TAG, "'M' keycode is " + (int)'m');
//			
//			
//			nlint.setStartEvent(new BehaviorEvent(BehaviorEvent.KEY_DOWN_ID,BehaviorEvent.SHIFT_MODIFIER, KeyEvent.KEYCODE_L,0,0));
//			nlint.setStopEvent(new BehaviorEvent(BehaviorEvent.KEY_DOWN_ID,0, KeyEvent.KEYCODE_L,0,0));
//
//			NewRectBehavior nrint = new NewRectBehavior( Color.GREEN,2);
//			this.addBehavior(nrint);
//			nrint.setGroup(sgroup);
//			nrint.setStartEvent(new BehaviorEvent(BehaviorEvent.KEY_DOWN_ID,BehaviorEvent.SHIFT_MODIFIER, KeyEvent.KEYCODE_R,0,0));
//			nrint.setStopEvent(new BehaviorEvent(BehaviorEvent.KEY_DOWN_ID,0,KeyEvent.KEYCODE_R,0,0));
//
//			mynewlinebehavior mlint = new mynewlinebehavior(Color.BLUE,2);
//			this.addBehavior(mlint);
//			mlint.setGroup(sgroup);
//			mlint.setStartEvent(new BehaviorEvent(BehaviorEvent.KEY_DOWN_ID,BehaviorEvent.SHIFT_MODIFIER,KeyEvent.KEYCODE_M,0,0));
//			mlint.setStopEvent(new BehaviorEvent(BehaviorEvent.KEY_DOWN_ID,0,KeyEvent.KEYCODE_M,0,0));
//
//			MoveBehavior mint = new MoveBehavior();
//			this.addBehavior(mint);
//			mint.setGroup(sgroup);
//			// I am assuming a choiceb3ehavior starts on a left mouse down. Updated key code to match this.
//			mint.setStartEvent(new BehaviorEvent(BehaviorEvent.MOUSE_DOWN_ID,BehaviorEvent.SHIFT_MODIFIER,BehaviorEvent.LEFT_MOUSE_KEY ));
//			// I had to add this line to set the running event to match the start event.
//			mint.setRunningEvent(new BehaviorEvent(BehaviorEvent.MOUSE_MOVE_ID,BehaviorEvent.SHIFT_MODIFIER,BehaviorEvent.LEFT_MOUSE_KEY ));
//			mint.setStopEvent(new BehaviorEvent(BehaviorEvent.MOUSE_UP_ID,BehaviorEvent.SHIFT_MODIFIER,BehaviorEvent.LEFT_MOUSE_KEY ));
//
//			ChoiceBehavior cint = new ChoiceBehavior(ChoiceBehavior.MULTIPLE,false);
//			this.addBehavior(cint);
//			cint.setGroup(sgroup);
//			// I am assuming a choiceb3ehavior starts on a left mouse down. Updated key code to match this.
//			cint.setStartEvent(new BehaviorEvent(BehaviorEvent.MOUSE_DOWN_ID,0, BehaviorEvent.LEFT_MOUSE_KEY ));
//			cint.setStopEvent(new BehaviorEvent(BehaviorEvent.MOUSE_UP_ID,0,BehaviorEvent.LEFT_MOUSE_KEY));
//
//			print("Ready to interact!\n");
//			print("Press capital 'L' to start drawing a line, press lower 'l' to stop!\n");
//			print("Press capital 'R' to start drawing a rect, press lower 'r' to stop!\n");
//			print("Press capital 'M' to start drawing a myline, black=both, green=sel, blue=not, red=interim\n");
//
//			print("Shift-Left click to move, release to stop!\n");
//			println ("DONE. close the window to stop");
//
//		} catch(Exception e) { println ("got an exception: " + e); }
//	}

}
