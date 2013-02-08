package homework2.android;

public class LayoutGroup extends SimpleGroup {

	private int m_offset;
	private int m_layout;
	public LayoutGroup() {
		this(0,0,10,10, HORIZONTAL, 0);
	}

	public LayoutGroup(int x, int y, int width, int height, int layout, int offset) {
		super(x, y, width, height);
		m_offset = offset;
		m_layout = layout;
	}

	private void updateLayoutHorizontal()
	{
		int x = 0;
		int y = 0;
		for (GraphicalObject o : m_children) {
			o.moveTo(x, y);
			x += o.getBoundingBox().width + m_offset;
		}
	}

	private void updateLayoutVertical()
	{
		int x = 0;
		int y = 0;
		for (GraphicalObject o : m_children) {
			o.moveTo(x, y);
			y += o.getBoundingBox().height + m_offset;
		}
	}
	
	
	// update the layout of the children
	// call when a child has been resized
	// a child has been added
	// or a child has been removed
	private void updateLayout()
	{
		if(m_layout == HORIZONTAL) updateLayoutHorizontal();
		else updateLayoutVertical();
		doDamage();
	}
	
	@Override
	public void addChild(GraphicalObject child)
			throws AlreadyHasGroupRunTimeException {
		super.addChild(child);
		updateLayout();
	}
	
	@Override
	public void bringChildToFront(GraphicalObject child) {
		super.bringChildToFront(child);
		updateLayout();
	}
	
	@Override
	public void removeChild(GraphicalObject child) {
		super.removeChild(child);
		updateLayout();
	}
	
	@Override
	public void resizeChild(GraphicalObject child) {
		super.resizeChild(child);
		updateLayout();
	}
	
	public int getOffset() {
		return m_offset;
	}

	public void setOffset(int offset) {
		m_offset = offset;
		updateLayout();
	}

	public int getLayout() {
		return m_layout;
	}

	public void setLayout(int layout) {
		m_layout = layout;
		updateLayout();
	}

}
