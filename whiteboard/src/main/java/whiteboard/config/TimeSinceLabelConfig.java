package whiteboard.config;

public class TimeSinceLabelConfig extends LabelConfig
{
	/** */
	private String date;
	
	public TimeSinceLabelConfig(int id, String descriptor, String value, String type) 
	{
		super(id, descriptor, value, type);
		date = value;
	}

	public TimeSinceLabelConfig(LabelConfig labelConfig) 
	{
		super(labelConfig);
		date = ((TimeSinceLabelConfig)labelConfig).getDate();
	}

	@Override
	public String toConfigString() 
	{
		String configString = descriptor + "," + date + "," + TIME_TYPE;
		return configString;
	}
	
	public void setDate(String date)
	{
		this.date = date;
	}
	
	public String getDate()
	{
		return date;
	}

}
