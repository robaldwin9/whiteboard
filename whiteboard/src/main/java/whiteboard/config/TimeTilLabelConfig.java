package whiteboard.config;

public class TimeTilLabelConfig extends LabelConfig
{
	/** */
	private String date;
	
	public TimeTilLabelConfig(int id, String descriptor, String value, String type) 
	{
		super(id, descriptor, value, type);
	}
	
	public TimeTilLabelConfig(LabelConfig labelConfig) 
	{
		super(labelConfig);
		date = ((TimeTilLabelConfig)labelConfig).getDate();
	}

	@Override
	public String toConfigString() 
	{
		return  descriptor + "," + date + "," + TIME_TIL_TYPE;
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