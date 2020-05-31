package whiteboard.time;

import java.text.DateFormat;
import java.util.Date;

import whiteboard.config.Config;
import whiteboard.graphics.Controller;

public class ElapsedTimeTask implements Runnable 
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
	public ElapsedTimeTask(Controller guiController, Config config)
	{
		this.guiController = guiController;
	}
	
	/**
	 * Update bits of application when timer elapses
	 */
	public void run() 
	{
		while(true)
		{
			guiController.requestGUIUpdateTimers();

			try 
			{
				Thread.sleep(1000);
			} 
			
			catch (InterruptedException e) 
			{
				System.out.println("ERROR: Timer task inturupt in StatisticsTimerTask.java");
			}
		}
	}
}
