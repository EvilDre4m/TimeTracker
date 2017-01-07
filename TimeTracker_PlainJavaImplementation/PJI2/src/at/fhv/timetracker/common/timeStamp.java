package at.fhv.timetracker.common;

public class timeStamp {
	/*
	 * Timestamp string format; adhere to it or app will explode. True story...
	 * YYYY-MM-DD--HH-MM
	 */
	
	private String storedTimestamp;
	
	public timeStamp(String input) throws IllegalArgumentException{
		String pattern = "^\\d{4}-\\d{2}-\\d{2}--\\d{2}-\\d{2}";
		if(input.matches(pattern)){
			storedTimestamp = input;
		} else {
			throw new IllegalArgumentException("Invalid pattern of timestamp!");
		}
	}
	
	public String getTimestamp(){
		return storedTimestamp;
	}
	
	public void setTimestamp(String input) throws IllegalArgumentException{
		String pattern = "^\\d{4}-\\d{2}-\\d{2}--\\d{2}-\\d{2}";
		if(input.matches(pattern)){
			storedTimestamp = input;
		} else {
			throw new IllegalArgumentException("Invalid pattern of timestamp!");
		}
	}
	
	public boolean equals(timeStamp otherTimestamp){
		if(this.storedTimestamp.equals(otherTimestamp.getTimestamp())){
			return true;
		} else {
			return false;
		}
	}
	
	public long getInSeconds(){
		//YYYY-MM-DD--HH-MM
		//0    5  8   12 15
		String stringRep = new String(storedTimestamp);
		
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
		
		//FIXME Complete this crap (start is time = 0)				
	}
}
