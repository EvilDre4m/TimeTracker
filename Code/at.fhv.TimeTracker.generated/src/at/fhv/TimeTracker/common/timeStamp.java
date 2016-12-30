package at.fhv.TimeTracker.common;

public class timeStamp {
    /**
     * Description of the property timestamp.
     */
    private String timestamp = "";
    
    // Start of user code (user defined attributes)
	    
	    // End of user code
    

	public timeStamp(String timestamp) throws IllegalArgumentException
	{
		String pattern = "^\\d{4}-\\d{2}-\\d{2}--\\d{2}-\\d{2}";
		if(timestamp.matches(pattern)){
			this.timestamp = timestamp;
		} else {
			throw new IllegalArgumentException("Invalid pattern of timestamp!");
		}
	}

		public String getTimestamp(){
		   return this.timestamp;
		}
		
		public void setTimestamp(String timestamp) throws IllegalArgumentException{
			String pattern = "^\\d{4}-\\d{2}-\\d{2}--\\d{2}-\\d{2}";
			if(timestamp.matches(pattern)){
				this.timestamp = timestamp;
			} else {
				throw new IllegalArgumentException("Invalid pattern of timestamp!");
			}
		}
		
	
	public Boolean equals(timeStamp otherTimestamp) {
	// Start of user code equals
		if(this.timestamp.equals(otherTimestamp.getTimestamp())){
			return true;
		} else {
			return false;
		}
	// End of user code
	}
	
	// Start of user code (user defined operations)
		
		// End of user code
	
}

