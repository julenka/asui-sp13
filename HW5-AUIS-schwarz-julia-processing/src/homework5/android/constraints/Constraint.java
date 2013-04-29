package homework5.android.constraints;

/**
 * Represents an abstract constraint.
 * New constraints must extend this.
 * @author Julia
 *
 */
public abstract class Constraint {

	
	/**
	 * This method evaluates the value of the constraint and updates variables appropriately, arbitrary code can be placed here. 
	 */
	public abstract void evaluate();
	
	/**
	 * This method must be called in order to start the constraint. When constructed, constraints are not active.
	 */
	public abstract void activate(); 
	
	/**
	 * Call this when you want to remove this constraint
	 * must be called on destruction
	 */
	public abstract void deactivate();
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		deactivate();
	}
	
}
