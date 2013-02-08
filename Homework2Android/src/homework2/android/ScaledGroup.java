package homework2.android;

import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.util.Log;

public class ScaledGroup extends SimpleGroup {

	private double m_scaleX;
	private double m_scaleY;
	
	public ScaledGroup() {
		// TODO Auto-generated constructor stub
	}

	public ScaledGroup(int x, int y, int width, int height, double scaleX, double scaleY) {
		super(x, y, width, height);
		m_scaleX = scaleX;
		m_scaleY = scaleY;
		boundsChanged();
	}
	
	protected void updateBounds()
	{
		// update the transform as well
		m_transform.reset();
		m_transform.setTranslate(m_x, m_y);
		m_transform.preScale((float)m_scaleX, (float)m_scaleY);
		
//		Log.v("ScaledGroup", "new width: " + m_width + " new height: " + m_height);
		
		m_boundaryRect.x = m_x;
		m_boundaryRect.y = m_y;
		m_boundaryRect.width = m_width;
		m_boundaryRect.height = m_height;
		
		m_clipPath.reset();
		if(m_scaleX != 0 && m_scaleY != 0)
			m_clipPath.addRect(new RectF(0, 0, (int)(m_width / m_scaleX), (int)(m_height  / m_scaleY)), Direction.CCW);
		
	}

	public double getScaleX() {
		return m_scaleX;
	}

	public void setScaleX(double scaleX) {
		m_scaleX = scaleX;
		boundsChanged();
	}

	public double getScaleY() {
		return m_scaleY;
	}

	public void setScaleY(double scaleY) {
		m_scaleY = scaleY;
		boundsChanged();
	}
}
