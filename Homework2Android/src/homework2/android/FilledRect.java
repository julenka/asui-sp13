package homework2.android;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Rect;

public class FilledRect implements GraphicalObject {

	protected Paint m_paint = new Paint();
	protected Rect m_rect = new Rect();
	protected BoundaryRectangle m_boundaryRect = new BoundaryRectangle();
	
	protected Group m_group;
	private Matrix m_transform;
	
	public FilledRect() 
	{
		m_paint.setStyle(Style.FILL);
		m_paint.setColor(Color.BLACK);
		setX(0);
		setY(0);
		setWidth(10);
		setHeight(10);
	}
	
	public FilledRect(int x, int y, int width, int height, int color)
	{
		m_paint.setStyle(Style.FILL);
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
		setColor(color);
	}
	
	// Getters and setters
	
	// TODO:public FilledRect (int x, int y, int width, int height,  Color color);
	// TODO:public Line (int x1, int y1, int x2, int y2, Color color, int lineThickness);
	// TODO: public Icon (Bitmap image, int x, int y);
	// TOOD: public Text (String text, int x, int y, Typeface font, int fontSize; Coor color);

	@Override
	public void draw(Canvas graphics, Path clipShape) {
		graphics.clipPath(clipShape);
		graphics.drawRect(m_rect, m_paint);
	}

	@Override
	public BoundaryRectangle getBoundingBox() {
		return m_boundaryRect;
	}

	@Override
	public void moveTo(int x, int y) {
		// todo: check this
		setX(x);
		setY(y);
	}

	@Override
	public Group getGroup() {
		return m_group;
	}

	@Override
	public void setGroup(Group group) {
		m_group = group;
	}

	@Override
	public boolean contains(int x, int y) {
		int relx = x - m_rect.left;
		int rely = y - m_rect.top;
		return relx >= 0 && rely >= 0 && relx < m_rect.width() && rely < m_rect.height();
	}

	@Override
	public void setAffineTransform(Matrix af) {
		m_transform = af;
	}

	@Override
	public Matrix getAffineTransform() {
		return m_transform;
	}

	protected void updateAndDamage()
	{
		m_boundaryRect.x =  m_rect.left - (int)(m_paint.getStrokeWidth() / 2);
		m_boundaryRect.y = m_rect.top - (int)(m_paint.getStrokeWidth() / 2);
		m_boundaryRect.width = m_rect.width() + (int)m_paint.getStrokeWidth();
		m_boundaryRect.height = m_rect.height() + (int)m_paint.getStrokeWidth();
		
		if(m_group != null)
			m_group.damage(m_boundaryRect);
	}
	
	// Getters and setters
	public int getX() {
		return m_rect.left;
	}

	public void setX(int x) {
		int dx = x - m_rect.left;
		m_rect.left = x;
		m_rect.right += dx;
		updateAndDamage();
	}

	public int getY() {
		return m_rect.top;
	}

	public void setY(int y) {
		int dy = y - m_rect.top;
		m_rect.top = y;
		m_rect.bottom += dy;
		updateAndDamage();
	}

	public int getWidth() {
		return m_rect.width();
	}

	public void setWidth(int width) {
		m_rect.right = m_rect.left + width;
		updateAndDamage();
	}

	public int getHeight() {
		return m_rect.height();
	}

	public void setHeight(int height) {
		m_rect.bottom = m_rect.top + height;
		updateAndDamage();
	}

	public int getColor() {
		return m_paint.getColor();
	}

	public void setColor(int color) {
		m_paint.setColor(color);
		updateAndDamage();
	}

}
