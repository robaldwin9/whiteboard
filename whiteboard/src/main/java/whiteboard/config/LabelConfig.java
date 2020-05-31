package whiteboard.config;

public abstract class LabelConfig 
{
	/** */
	public static final String STATIC_TYPE = "s";
	
	/** */
	public static final String TIME_TYPE = "t";
	
	/** */
	public static final String TIMER_TYPE = "tt";
	
	/** */
	public static final String TIME_TIL_TYPE = "TimeTilType";
	
	/** */
	private String type = "";
	
	/** */
	protected String descriptor;
	
	/** */
	protected String value;
	
	/** properties file number - 1 */
	protected int id;

	/**
	 * 
	 * @param descriptor
	 * @param value
	 * @param type
	 */
	public LabelConfig(int id, String descriptor, String value, String type)
	{
		this.descriptor = descriptor;
		this.value = value;
		this.type = type;
		this.id = id;
	}
	
	public LabelConfig(LabelConfig config)
	{
		this.descriptor = config.descriptor;
		this.value = config.value;
		this.type = config.type;
		this.id = config.id;
	}
	

	/**
	 * 
	 * @return
	 */
	public String getType() 
	{
		return type;
	}
	
	/**
	 * 
	 * @param type
	 */
	public void setType(String type) 
	{
		this.type = type;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getDescriptor() 
	{
		return descriptor;
	}
	
	/**
	 * 
	 * @param descriptor
	 */
	public void setDescriptor(String descriptor)
	{
		this.descriptor = descriptor;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getValue() 
	{
		return value;
	}
	
	/**
	 * 
	 * @param value
	 */
	public void setValue(String value) 
	{
		this.value = value;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getId() 
	{
		return id;
	}
	
	public String toString()
	{
		
		return toConfigString();
	}
	
	public void setId(int id) 
	{
		this.id = id;
	}
	
	public abstract String toConfigString();
}
