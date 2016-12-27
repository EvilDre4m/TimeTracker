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
}