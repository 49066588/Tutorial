package main_.reader_;

public abstract class AReader implements IReader {

	////////////
	// FIELDS //
	////////////
	
	private long long_limit = 0;
	
	/////////////
	// METHODS //
	/////////////
	
	@Override 
	public void setLimit(long long_limit) {
		
		// Check input parameter.
		if (long_limit < 0) {
			
			String string_message = "Cannot set limit. Input parameter \"long_limit\" is \""+long_limit+"\" but must not be smaller than 0.";
			throw new IllegalStateException(string_message);
			
		}
		
		// Assign input parameter.
		this.long_limit = long_limit;
		
	}
	
	@Override
	public long getLimit() {
		
		// Return limit.
		return this.long_limit;
		
	}

	@Override
	public long getColumnCount() {
		
		// Get column count.
		int integer_columnCount = this.getColumnNames().size();
		
		// Return column count.
		return integer_columnCount;
		
	}

}