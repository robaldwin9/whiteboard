package whiteboard.config;

import whiteboard.time.TimeUtil;

public class TimerLabelConfig extends LabelConfig
{
	private int targetHours;
	private int targetMinutes;
	private int targetSeconds;
	long startTime = System.currentTimeMillis();
	
	public TimerLabelConfig(int id, String descriptor, String value, String type) 
	{
		super(id, descriptor, value, type);
		String[] timerTargets = value.split(":");
		targetHours = Integer.parseInt(timerTargets[0]);
		targetMinutes = Integer.parseInt(timerTargets[1]);
		targetSeconds = Integer.parseInt(timerTargets[2]);
		updateTime(startTime);
	}
	
	public TimerLabelConfig(LabelConfig labelConfig) 
	{
		super(labelConfig);
		TimerLabelConfig config = (TimerLabelConfig)labelConfig;
		targetHours = config.getTargetHours();
		targetMinutes = config.getTargetMinutes();
		targetSeconds = config.getTargetSeconds();
		startTime = config.getStartTime();
	}

	public void updateTime(long currentTime)
	{
		String value = "ERROR";
		long[] elapsedTimeValue = TimeUtil.getElapsedTime(startTime,currentTime);
		if(targetHours <= elapsedTimeValue[0] &&  targetMinutes <= elapsedTimeValue[1]
				&& targetSeconds <= elapsedTimeValue[2] )
		{
			startTime = System.currentTimeMillis();
			value = "0 Hours 0 Minutes 0 Seconds";
		}
		
		else
		{
		
		value = String.format("%2s",elapsedTimeValue[0]) + " Hours " +
				String.format("%2s",elapsedTimeValue[1]) + " Minutes "+ 
				String.format("%2s",elapsedTimeValue[2]) + " Seconds";
		
		}
		this.value = value;
	}

	@Override
	public String toConfigString() 
	{
		String configString = descriptor + "," + (targetHours +":"+ targetMinutes +":" + targetSeconds) + "," + TIMER_TYPE;
		return configString;
	}
	
	public int getTargetHours() 
	{
		return targetHours;
	}

	public void setTargetHours(int targetHours) 
	{
		this.targetHours = targetHours;
	}

	public int getTargetMinutes() 
	{
		return targetMinutes;
	}

	public void setTargetMinutes(int targetMinutes) 
	{
		this.targetMinutes = targetMinutes;
	}

	public int getTargetSeconds() {
		return targetSeconds;
	}

	public void setTargetSeconds(int targetSeconds) 
	{
		this.targetSeconds = targetSeconds;
	}
	
	public long getStartTime()
	{
		return startTime;
	}

}
