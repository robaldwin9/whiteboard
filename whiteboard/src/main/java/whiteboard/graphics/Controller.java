package whiteboard.graphics;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import whiteboard.config.Config;
import whiteboard.config.LabelConfig;
import whiteboard.config.TimeSinceLabelConfig;
import whiteboard.config.TimerLabelConfig;
import whiteboard.time.ElapsedTimeTask;
import whiteboard.time.TimeUtil;
import whiteboard.time.TimerTask;

public class Controller
{
	/** GUI user sees */
	View view;
	
	/** Data displayed by view */
	Model model;
	
	/** Application configuration from Java .properties files */
	Config config;
	
	/** timer task that updates the configuration */
	TimerTask timerTask;
	
	/** */
	Vector<LabelConfig> labelConfig;
	
	/**
	 * 
	 * @param view
	 * @param model
	 * @param config
	 */
	public  Controller(View view, Model model)
	{
		this.config = Config.getInstance();
		labelConfig =  config.getLabelsConfig();
		this.view = view;
		this.model = model;
		model.addPropertyChangeListener(view);
		
		/** Start tracking time */
		Thread timerTask = new Thread(new TimerTask(this,config));
		timerTask.setDaemon(true);
		timerTask.start();
		
		Thread timerTask1 = new Thread(new ElapsedTimeTask(this,config));
		timerTask1.setDaemon(true);
		timerTask1.start();
	}
	
	public void requestGUIUpdateTimers()
	{
		labelConfig = config.getLabelsConfig();
		for(int i = 0; i < labelConfig.size(); i++ )
		{
			if(labelConfig.get(i).getType().equals(LabelConfig.TIMER_TYPE))
			{
					LabelConfig newConfig = new TimerLabelConfig(labelConfig.get(i));
					((TimerLabelConfig)newConfig).updateTime(System.currentTimeMillis());
					model.updateLable(i,newConfig);
			}
		}
	}
	
	/**
	 * Have GUI update
	 */
	public void requestGUIDateUpdate(Date date)
	{
		updateDates(date);
	}
	
	/**
	 * Gets vector of time elapsed from Configuration date to passed date
	 */
	 void updateDates(Date date)
	{
		labelConfig = config.getLabelsConfig();
		for(int i = 0; i < labelConfig.size(); i++ )
		{
			if(labelConfig.get(i).getType().equals(LabelConfig.TIME_TYPE))
			{
				SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
				TimeSinceLabelConfig config = (TimeSinceLabelConfig)labelConfig.get(i);
				
				try 
				{
					Date labelDate = formatter.parse(config.getDate());
					LabelConfig newConfig = new TimeSinceLabelConfig(labelConfig.get(i));
					newConfig.setValue(TimeUtil.getDateDifference(labelDate ,date));
					model.updateLable(i,newConfig);
				} 
				
				catch (ParseException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}
