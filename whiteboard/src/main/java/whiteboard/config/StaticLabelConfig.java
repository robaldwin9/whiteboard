package whiteboard.config;

public class StaticLabelConfig extends LabelConfig
{

	public StaticLabelConfig(int id, String descriptor, String value, String type) 
	{
		super(id, descriptor, value, type);
	}

	@Override
	public String toConfigString() 
	{
		String configString = descriptor + "," + value + "," + STATIC_TYPE;
		return configString;
	}

}
