package whiteboard.time;

import java.util.Date;

public class TimeUtil 
{
	/*
	 * 
	 * @param date1 - First Date object
	 * @param date2 - Second Date object
	 * @return String - Representing time elapsed between the two
	 */
	public static String getDateDifference(Date date1, Date date2)
	{
		String value = "ERROR";
		
		long diffInMilliseconds = date2.getTime() - date1.getTime();
		long diffInSeconds = diffInMilliseconds / 1000;
		long diffInMinutes = diffInSeconds / 60;
		long diffInHours = diffInMinutes / 60;
		long diffInDays = diffInHours / 24;
		
		value = String.format("%2s", diffInDays) + " Days " + 
				String.format("%2s",diffInHours % 24)   + " Hours " +  
				String.format("%2s",diffInMinutes % 60) + " Minutes";
		
		return value;
	}
	
	/**
	 * 
	 * @param startTime
	 * @param currentTime
	 * @return
	 */
	public static long[] getElapsedTime(long startTime, long currentTime)
	{
		long[] elapsedTimeBreakDown = new long[3];
		long elapsedTime = currentTime - startTime;
		long elapsedSeconds = elapsedTime / 1000;
		long elapsedMinutes = elapsedSeconds / 60;
		long elapsedHours = elapsedMinutes / 60;
		
		elapsedTimeBreakDown[0] = elapsedHours % 24;
		elapsedTimeBreakDown[1] = elapsedMinutes % 60;
		elapsedTimeBreakDown[2] = elapsedSeconds % 60;
		
		return elapsedTimeBreakDown;
	}
}
