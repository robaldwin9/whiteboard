package whiteboard.time;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import whiteboard.config.Config;
import whiteboard.graphics.Controller;

public class TimerTask implements Runnable
{
	/** Holds the days date */
	Date today;
	
	/** formats date object to specific format */
	DateFormat dateFormat;
	
	/** Updates configuration during execution */
	Config config = Config.getInstance();
	
	/** Used to issue update to controller */
	Controller guiController;
	
	/** Pause update */
	
	/**
	 * Initialize
	 */
	public TimerTask(Controller guiController, Config config)
	{
		this.guiController = guiController;
		dateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		today = new Date();
	}
	
	/**
	 * Update bits of application when timer elapses
	 */
	public void run() 
	{
		Date newDate;
		while(true)
		{
			newDate = new Date();
			dateFormat.format(newDate);
			
			if(!today.toString().equals(newDate.toString()))
			{
				guiController.requestGUIDateUpdate(newDate);
			}
			
			try 
			{
				Thread.sleep(60000);
			} 
			
			catch (InterruptedException e) 
			{
				System.out.println("ERROR: Timer task inturupt in StatisticsTimerTask.java");
			}
		}
	}
}
