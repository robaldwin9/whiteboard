package whiteboard.config;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;
import java.util.Vector;

import org.apache.logging.log4j.LogManager;
/**
 *  Reads, and stores configuration for application.
 *  
 * @author Ronald Baldwin
 *
 */
public class Config 
{
	/** file paths for Java properties files */
	final static String programAbsolutePath = "";
	final String labelsPropertiesPath = programAbsolutePath + "resources/labels.properties";
	String stylePropertiesPath = programAbsolutePath + "resources/style.properties";
	
	/**  Holds value and labels for displayed statistics */
	Vector<LabelConfig> labelsConfig;
	
	/** Window Configuration Data */
	Color bgColor;
	Color lblColor;
	Color buttonColor;
	Color dialogColor;
	
	int startWidth = 250;
	int startHeight = 250;
	int fontSize;
	int dialogFontSize;
	boolean fullScreen = false;
	
	String fontName = "Arial";
	
	boolean labelsUpdated = false;
	boolean staticLabelUpdated = false;
	boolean staticValuesUpdated = false;
	
	/** Path to GUI icon */
	private static String iconPath = "resources/time.png";

	/** Instance of Application configuration */
	private static volatile Config instance;
	
	/** Log4j configuration */
	static 
	{
	    File log4j2File = new File((programAbsolutePath + "resources/log4j.xml"));
	    System.setProperty("log4j2.configurationFile", log4j2File.toURI().toString());
	}
	
	/** Log4j logger */
	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(Config.class);
	
	/**
	 * No arguments constructor
	 */
	private Config()
	{
		readLabelsConfig(); 
		readStyle();
	}

	/**
	 * 
	 * @return - singleton instance of class
	 */
	synchronized public static Config getInstance()
	{
		if(instance == null)
		{
			instance = new Config();
		}
		
		return instance;
	}
	
	
	/**
	 * Reads style attributes used by the GUI 
	 */
	private void readStyle()
	{
		try 
		{
			/** load style properties file */
			FileInputStream input =  new FileInputStream(new File(stylePropertiesPath));
			Properties style = new Properties();
			style.load(input);
			
			/** Read properties */
			iconPath = System.getProperty("user.dir") + style.getProperty("iconPath");
			fullScreen = Boolean.parseBoolean(style.getProperty("fullscreen"));
			fontName = style.getProperty("fontName");
			startHeight = Integer.parseInt(style.getProperty("startingHeight"));
			startWidth = Integer.parseInt(style.getProperty("startingWidth"));
			fontSize = Integer.parseInt(style.getProperty("fontSize"));
			dialogFontSize = Integer.parseInt(style.getProperty("daialogfontSize"));
			
			
			try 
			{
			    Field field = Class.forName("java.awt.Color").getField(style.getProperty("bgColor"));
			    bgColor = (Color)field.get(null);
			    
			    field = Class.forName("java.awt.Color").getField(style.getProperty("lblColor"));
			    lblColor = (Color)field.get(null);
			    
			    field = Class.forName("java.awt.Color").getField(style.getProperty("btnColor"));
			    buttonColor = (Color)field.get(null);
			    
			    field = Class.forName("java.awt.Color").getField(style.getProperty("dialogColor"));
			    dialogColor = (Color)field.get(null);
			    
			} 
			
			catch (Exception e) 
			{
			    bgColor = Color.BLACK; // Not defined
			    lblColor = Color.WHITE;
			    buttonColor = Color.DARK_GRAY;
			    dialogColor = Color.CYAN;
			}
			
			input.close();
		} 
		
		catch (FileNotFoundException e) 
		{
			System.out.println("FileNotFoundException when trying to read " + stylePropertiesPath);
			e.printStackTrace();
		} 
		
		catch (IOException e) 
		{
			System.out.println("IOException when trying to read " + stylePropertiesPath);
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 
	 * @return - background color of GUI
	 */
	public Color getBgColor() 
	{
		return bgColor;
	}
	
	public Color getButtonColor()
	{
		return buttonColor;
	}
	
	/**
	 * 
	 * @return
	 */
	public Color getlblColor()
	{
		return lblColor;
	}
	
	/**
	 * 
	 * @return - Starting width of the Frame
	 */
	public int getStartWidth() 
	{
		return startWidth;
	}
	
	/**
	 * 
	 * @return - Starting height of the frame
	 */
	public int getStartheight() 
	{
		return startHeight;
	}
	
	/**
	 * 
	 * @return - is the application in full screen mode at start
	 */
	public boolean isFullScreen()
	{
		return fullScreen;
	}
	
	public String getIconPath() 
	{
		return iconPath;
	}
	
	public boolean labelsUpdated()
	{
		return labelsUpdated;
	}
	
	public void setLabelsUpdated(boolean val)
	{
		labelsUpdated = val;
	}
	
	public boolean isStaticLabelUpdated() 
	{
		return staticLabelUpdated;
	}

	public void setStaticLabelUpdated(boolean staticLabelUpdated)
	{
		this.staticLabelUpdated = staticLabelUpdated;
	}

	public boolean isStaticValuesUpdated() 
	{
		return staticValuesUpdated;
	}

	public void setStaticValuesUpdated(boolean staticValuesUpdated) 
	{
		this.staticValuesUpdated = staticValuesUpdated;
	}
	
	public int getDialogFontSize()
	{
		return dialogFontSize;
	}

	/**
	 * Reads Statistic values from configuration file
	 */
	private void readLabelsConfig()
	{
		/** Load values properties file */

		Properties values = new Properties();
			
		/** Read values file */
		try 
		{
			FileInputStream input = new FileInputStream(new File(labelsPropertiesPath));
			values.load(input);
			int statCount = Integer.parseInt(values.getProperty("statCount"));
			labelsConfig = new Vector<LabelConfig>(statCount);
				
			for(int i = 1; i < statCount + 1; i ++)
			{	
					String[] labelData = values.getProperty("stat" + i).split(",");
					//System.out.println(labelData[0] + " " + labelData[1] + " " + labelData[2]);
					
					if(labelData[labelData.length -1].equals(LabelConfig.STATIC_TYPE))
					{
						labelsConfig.add(i-1,new StaticLabelConfig(i,labelData[0],labelData[1],labelData[2]));
					}
					
					else if(labelData[labelData.length -1].equals(LabelConfig.TIME_TYPE))
					{
						labelsConfig.add(i-1,new TimeSinceLabelConfig(i,labelData[0],labelData[1],labelData[2]));
					}
					
					else if(labelData[labelData.length -1].equals(LabelConfig.TIMER_TYPE))
					{
						labelsConfig.add(i-1,new TimerLabelConfig(i,labelData[0],labelData[1],labelData[2]));
					}
					
//				else if(labelData[labelData.length -1].equals(LabelConfig.TIME_TIL_TYPE));
//				{
//						System.out.println(labelData[0] + " " + labelData[1] + " " + labelData[2]);
//						labelsConfig.add(i-1,new TimeTilLabelConfig(i,labelData[0],labelData[1],labelData[2]));
//					System.out.println(labelData[0] + " " + labelData[1] + " " + labelData[2]);
//					}
			} 
			
				input.close();
		
		}
			catch (FileNotFoundException e) 
			{
				logger.error("FileNotFoundException when trying to read " + labelsPropertiesPath);
			} 
			
			catch (IOException e) 
			{
				logger.error("IOException when trying to read " + labelsPropertiesPath);
			}
	}
	
	public void updateLabel(LabelConfig labelConfig)
	{
		/** Load values properties file */
		try 
		{
			Properties labels = new Properties();
			labels.setProperty("statCount", Integer.toString(labelsConfig.size()));
			labelsConfig.set(labelConfig.getId() - 1,labelConfig);
			System.out.println(labelConfig);
			
			for(int i = 0; i < labelsConfig.size(); i++)
			{
				labels.setProperty("stat"+ labelsConfig.get(i).getId(), labelsConfig.get(i).toConfigString());
			}
			
			File file = new File(labelsPropertiesPath);
			FileOutputStream out = new FileOutputStream(file,false);
			labels.store(out,"labels.properties");
			out.close();
			labelsUpdated = true;
		}

		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void removeLabel(LabelConfig labelConfig)
	{
		/** Load values properties file */
		try 
		{
			Properties labels = new Properties();
			
			/** remove item */
			int removedItemIndex = labelConfig.getId() -1;
			labelsConfig.remove(removedItemIndex);
			labelsConfig.trimToSize();
			
			/** write new number of elements to config */
			labels.setProperty("statCount", Integer.toString(labelsConfig.size()));
			
			/** Fix indexing */
			for(int i = removedItemIndex; i < labelsConfig.size();i++)
			{
				LabelConfig item = labelsConfig.get(i);
				item.setId(item.getId() -1);
			}
			
			System.out.println(labelConfig);
			
			for(int i = 0; i < labelsConfig.size(); i++)
			{
				labels.setProperty("stat"+ labelsConfig.get(i).getId(), labelsConfig.get(i).toConfigString());
			}
			
			File file = new File(labelsPropertiesPath);
			FileOutputStream out = new FileOutputStream(file,false);
			labels.store(out,"labels.properties");
			out.close();
			labelsUpdated = true;
		}

		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void addLabel(LabelConfig labelConfig)
	{
		/** Load values properties file */
		try 
		{
			Properties labels = new Properties();
			
			/** Add item */
			labelsConfig.add(labelConfig);
			labelConfig.setId(labelsConfig.size());
			
			
			/** write new number of elements to configuration */
			labels.setProperty("statCount", Integer.toString(labelsConfig.size()));
			
			/** Write to file */
			for(int i = 0; i < labelsConfig.size(); i++)
			{
				labels.setProperty("stat"+ labelsConfig.get(i).getId(), labelsConfig.get(i).toConfigString());
			}
			
			File file = new File(labelsPropertiesPath);
			FileOutputStream out = new FileOutputStream(file,false);
			labels.store(out,"labels.properties");
			out.close();
			labelsUpdated = true;
		}

		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Vector<LabelConfig> getLabelsConfig()
	{
		return labelsConfig;
	}
	
	/**Font name of all text in GUI */
	public String getFontName()
	{
		return fontName;
	}
	
	public int getFontSize()
	{
		return fontSize;
	}
	
	public Color getDialogColor()
	{
		return dialogColor;
	}
}