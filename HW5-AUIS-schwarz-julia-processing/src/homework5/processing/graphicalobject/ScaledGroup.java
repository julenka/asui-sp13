package homework5.processing.graphicalobject;

/**
 * Implements a group where all objects are scaled by an x and y factor
 * @author julenka
 *
 */
public class ScaledGroup extends SimpleGroup {

	private double m_scaleX;
	private double m_scaleY;
	
	public ScaledGroup() {
	}

	public ScaledGroup(int x, int y, int width, int height, double scaleX, double scaleY) {
		super(x, y, width, height);
		m_scaleX = scaleX;
		m_scaleY = scaleY;
		boundsChanged();
	}
	
	/**
	 * update the bounds of the rectangle and also of our affine transform
	 */
	protected void updateBounds()
	{
		// update the transform as well
		m_transform.reset();
		m_transform.translate(m_x, m_y);
		m_transform.scale((float)m_scaleX, (float)m_scaleY);
		
		m_boundaryRect.x = m_x;
		m_boundaryRect.y = m_y;
		m_boundaryRect.width = m_width;
		m_boundaryRect.height = m_height;
		if(m_scaleX * m_scaleY != 0){
			m_clipRegion.width = (int)(m_width / m_scaleX);
			m_clipRegion.height = (int)(m_height / m_scaleY);
		}
		
	}

	public double getScaleX() {
		return m_scaleX;
	}

	public void setScaleX(double scaleX) {
		double oldScale = m_scaleX;
		m_scaleX = scaleX;
		boundsChanged();
		notifyPropertyChanged("ScaleX", oldScale, scaleX);
	}

	public double getScaleY() {
		return m_scaleY;
	}

	public void setScaleY(double scaleY) {
		double oldScale = m_scaleY;
		m_scaleY = scaleY;
		boundsChanged();
		notifyDoublePropertyChanged("ScaleY", oldScale, scaleY);
	}
}
