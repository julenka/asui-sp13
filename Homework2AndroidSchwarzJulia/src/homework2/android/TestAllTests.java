package homework2.android;

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
public class TestAllTests extends BetterTestFrame {

	private List<BetterTestFrame> m_allTests = new ArrayList<BetterTestFrame>();
	private BetterTestFrame m_curFrame;
	public TestAllTests() {
		m_allTests.add(new BetterTestOutlineRect());
		m_allTests.add(new TestFilledRect());
		m_allTests.add(new BetterTestSimpleGroup());
		m_allTests.add(new TestScaledGroup());
		m_allTests.add(new BetterTestLayoutGropu());

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
		for (BetterTestFrame test : m_allTests) {
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
