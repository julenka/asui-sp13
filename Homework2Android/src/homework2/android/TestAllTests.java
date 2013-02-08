package homework2.android;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;

public class TestAllTests extends BetterTestFrame {

	private List<BetterTestFrame> m_allTests = new ArrayList<BetterTestFrame>();
	private BetterTestFrame m_curFrame;
	public TestAllTests() {
		m_allTests.add(new BetterTestOutlineRect());
//		m_allTests.add(new TestFilledRect());
//		m_allTests.add(new BetterTestSimpleGroup());
		m_allTests.add(new TestScaledGroup());

	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
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
	}
	
	@Override
	public void unpause() {
		// TODO Auto-generated method stub
		super.unpause();
		m_curFrame.unpause();
	}
}
