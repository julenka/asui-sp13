package homework3.android;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

// Base class for testing interactive windows
public class TestInteractiveWindowGroup extends InteractiveWindowGroup {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		Thread t = new Thread(new Runnable() {
			public void run() {
				while(!drawView.getOnDrawFirstCalled()){
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				test();
			}
		});
		t.start();
		
	}
	
	private void test()
	{
		addChild(new Text("This is just a test", drawView.getWidth() / 2, drawView.getHeight() / 2, Typeface.create("Helvetica", Typeface.NORMAL), 24, Color.GREEN));
		m_behaviors.add(new MoveBehavior(this));
	}

}
