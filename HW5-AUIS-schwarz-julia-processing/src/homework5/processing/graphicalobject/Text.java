package homework5.processing.graphicalobject;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Toolkit;

import processing.core.PFont;
import processing.core.PGraphics;

public class Text extends GraphicalObjectBase {

	private String m_text;
	private int m_x;
	private int m_y;
	private Color m_color;
	private PFont m_font;
	private int m_fontSize;
	
	public Text (String text, int x, int y, PFont font, int fontSize, Color color)
	{
		m_text = text;
		m_x = x;
		m_y = y;
		m_font = font;
		m_color = color;
		m_fontSize = fontSize;
		boundsChanged();
	}
	
	public Text() {
		this("", 0, 0, new PFont(new Font("Arial", Font.PLAIN, 12), true), 12, Color.BLACK);
	}


	@Override
	public void moveTo(int x, int y) {
		// move the upper left corner
		int oldX = m_x;
		int oldY = m_y;
		m_x = x;
		m_y = y;
		boundsChanged();
		notifyPropertyChanged("X", oldX, x);
		notifyPropertyChanged("Y", oldY, y);
	}	
	
	@SuppressWarnings("deprecation")
	protected void updateBounds()
	{
		Font f = (Font)(m_font.getNative());
		FontMetrics fm = Toolkit.getDefaultToolkit().getFontMetrics(f);
		int height = fm.getHeight();
		int width = fm.stringWidth(m_text);
		m_boundaryRect.x = m_x;
		m_boundaryRect.y = m_y - fm.getAscent(); 
		m_boundaryRect.width = width;
		m_boundaryRect.height = height;
	}
	
	public Color getColor()
	{
		return m_color;
	}
	
	public void setColor(Color color)
	{
		Color oldColor = m_color;
		m_color = color;
		doDamage();
		notifyPropertyChanged("Color", oldColor, color);
	}
	
	public int getFontSize()
	{
		return m_fontSize;
	}
	
	public void setFontSize(int size)
	{
		int oldSize = m_fontSize;
		m_fontSize = size;
		boundsChanged();
		notifyPropertyChanged("FontSize", oldSize, size);
	}

	public PFont getFont()
	{
		return m_font;
	}
	
	public void setFont(PFont f)
	{
		m_font = f;
		boundsChanged();
	}
	
	public int getY() {
		return m_y;
	}

	public void setY(int y) {
		int oldY = m_y;
		m_y = y;
		boundsChanged();
		notifyPropertyChanged("Y", oldY, y);
	}

	public int getX() {
		return m_x;
	}

	public void setX(int x) {
		int oldX = m_x;
		m_x = x;
		boundsChanged();
		notifyPropertyChanged("X", oldX, x);
	}

	public String getText() {
		return m_text;
	}

	public void setText(String text) {
		String oldText = m_text;
		m_text = text;
		boundsChanged();
		notifyPropertyChanged("Text", oldText, text);
	}

	@Override
	protected void doDraw(PGraphics canvas) {
		//canvas.noStroke();
		canvas.stroke(m_color.getRed(), m_color.getGreen(), m_color.getBlue());
		canvas.fill(m_color.getRed(), m_color.getGreen(), m_color.getBlue());
		canvas.textFont(m_font);
		canvas.textSize(m_fontSize);
		canvas.textAlign(PGraphics.BOTTOM);
		canvas.text(m_text, m_x, m_y );
	}

}
