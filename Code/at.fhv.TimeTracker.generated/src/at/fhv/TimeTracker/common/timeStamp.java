package at.fhv.TimeTracker.common;

public class timeStamp {
    /**
     * Description of the property timestamp.
     */
    private String timestamp = "01.01.00 00:00";
    
    // Start of user code (user defined attributes)
    
    // End of user code
    

	public timeStamp(String timestamp) throws IllegalArgumentException
	{
		String pattern = "^\\d{2}\\.\\d{2}\\.\\d{2} \\d{2}:\\d{2}";
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
			String pattern = "^\\d{2}\\.\\d{2}\\.\\d{2} \\d{2}:\\d{2}";
			if(timestamp.matches(pattern)){
				this.timestamp = timestamp;
			} else {
				throw new IllegalArgumentException("Invalid pattern of timestamp!");
			}
		}
		
	
	public long getInSeconds() {
	// Start of user code getInSeconds
		//YYYY-MM-DD--HH-MM
		//0    5  8   12 15  <-- indices
		String stringRep = new String(timestamp);
		
		String year = stringRep.substring(0, 4);
		String month = stringRep.substring(5, 7);
		String day = stringRep.substring(8, 10);
		String hour = stringRep.substring(12, 14);
		String minute = stringRep.substring(15, 17);
		
		long lYear = Long.valueOf(year);
		long lMonth = Long.valueOf(month);
		long lDay = Long.valueOf(day);
		long lHour = Long.valueOf(hour);
		long lMinute = Long.valueOf(minute);
		
		long lYearSinceZero = lYear - 1970; 

		//NOTE: Doesn't work, I know; can't fix now
		long totTime = lMinute 			* 60 +	
						lHour			* 60 * 60 +
						lDay			* 60 * 60 * 24 +
						lMonth			* 60 * 60 * 24 * 30 +
						lYearSinceZero	* 60 * 60 * 24 * 30 * 365;
		
		return totTime;
	// End of user code
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

