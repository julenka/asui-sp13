package homework4.android.constraints;

public abstract class Constraint {

	
	public abstract void evaluate();
	
	public abstract void activate(); 
	
	// Call this when you want to remove this constraint
	// must be called on destruction
	public abstract void deactivate();
	
}
