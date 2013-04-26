package homework4.android.test;

import homework4.android.graphicalobject.Text;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
/**
 * A class that runs a series of BetterTestFrame tests
 * @author julenka
 *
 */
public class MyTestAllTests extends MyTestFrame {

	private List<MyTestFrame> m_allTests = new ArrayList<MyTestFrame>();
	private MyTestFrame m_curFrame;
	public MyTestAllTests() {
		m_allTests.add(new MyTestOutlineRect());
		m_allTests.add(new MyTestFilledRect());
		m_allTests.add(new MyTestSimpleGroup());
		m_allTests.add(new MyTestScaledGroup());
		m_allTests.add(new MyTestLayoutGropu());

	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		for (TestFrame test : m_allTests) {
			test.drawView = drawView;
			test.debugTextView = debugTextView;
			
		}
		
	}
	
	
	
	@Override
	protected void test() {
		for (MyTestFrame test : m_allTests) {
			println("Testing " + test.getClass());
			m_curFrame = test;
			test.test();
			println("Click to move to next test");
			pause();
		}
		
		Text done = new Text("DONE!", 0,0, Typeface.create("Helvetica", Typeface.BOLD), 72, Color.GREEN);
		done.moveTo(drawView.getWidth() / 2 - done.getBoundingBox().width / 2, drawView.getHeight() / 2 - done.getBoundingBox().height / 2);
		addChild(done);
		redraw(done);
		
	}
	
	@Override
	public void unpause() {
		super.unpause();
		m_curFrame.unpause();
	}
}
