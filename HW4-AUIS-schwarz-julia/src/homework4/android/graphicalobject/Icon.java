package homework4.android.graphicalobject;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;

/**
 * Draws an icon
 * @author julenka
 *
 */
public class Icon extends GraphicalObjectBase {

	private Bitmap m_image;
	private int m_x;
	private int m_y;
	
	public Icon (Bitmap image, int x, int y)
	{
		m_image = image;
		m_x = x;
		m_y = y;
		boundsChanged();
	}
	
	public Icon() {
		this(Bitmap.createBitmap(0, 0, null), 0, 0);
	}

	protected void updateBounds()
	{
		m_boundaryRect.x = m_x;
		m_boundaryRect.y = m_y;
		m_boundaryRect.width = m_image.getWidth();
		m_boundaryRect.height = m_image.getHeight();
	}
	
	@Override
	public void doDraw(Canvas graphics, Path clipShape) {
		graphics.save();
		graphics.clipPath(clipShape);
		graphics.drawBitmap(m_image,m_x, m_y, m_paint);
		graphics.restore();
	}
	
	@Override
	public void moveTo(int x, int y) {
		int oldX = m_x;
		int oldY = m_y;
		m_x = x;
		m_y = y;
		boundsChanged();
		notifyIntPropertyChanged("x", oldX, x);
		notifyIntPropertyChanged("y", oldY, y);
	}

	public int getX() {
		return m_x;
	}

	public void setX(int x) {
		int oldX = m_x;
		m_x = x;
		boundsChanged();
		notifyIntPropertyChanged("x", oldX, x);
	}

	public int getY() {
		return m_y;
	}

	public void setY(int y) {
		int oldY = m_y;
		m_y = y;
		boundsChanged();
		notifyIntPropertyChanged("y", oldY, y);
	}

	public Bitmap getImage() {
		return m_image;
	}

	public void setImage(Bitmap image) {
		m_image = image;
		boundsChanged();
	}

}

