package homework2.android;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;

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
		// TODO: account affine transformation
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
		m_x = x;
		m_y = y;
		boundsChanged();
	}

	public int getX() {
		return m_x;
	}

	public void setX(int x) {
		m_x = x;
		boundsChanged();
	}

	public int getY() {
		return m_y;
	}

	public void setY(int y) {
		m_y = y;
		boundsChanged();
	}

	public Bitmap getImage() {
		return m_image;
	}

	public void setImage(Bitmap image) {
		m_image = image;
		boundsChanged();
	}

}

