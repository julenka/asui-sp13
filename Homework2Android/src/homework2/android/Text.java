package homework2.android;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;

public class Text implements GraphicalObject {

	// TOOD: public Text (String text, int x, int y, Typeface font, int fontSize; Coor color);
	
	public Text() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Canvas graphics, Path clipShape) {
		// TODO Auto-generated method stub

	}

	@Override
	public BoundaryRectangle getBoundingBox() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void moveTo(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public Group getGroup() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setGroup(Group group) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean contains(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setAffineTransform(Matrix af) {
		// TODO Auto-generated method stub

	}

	@Override
	public Matrix getAffineTransform() {
		// TODO Auto-generated method stub
		return null;
	}

}
