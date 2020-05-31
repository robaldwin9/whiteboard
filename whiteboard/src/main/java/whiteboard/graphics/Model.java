package whiteboard.graphics;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Vector;

import whiteboard.config.Config;
import whiteboard.config.LabelConfig;

public class Model
{
	/** Label Configuration */
	Vector<LabelConfig> labels;
	
	/** observer that fires events for GUI to respond to */
	private PropertyChangeSupport observer;
	
	/** Constants for property change*/
	public static final String UPDATE_LABEL = "UL";
	
	/**
	 * 
	 */
	public Model()
	{
		labels = Config.getInstance().getLabelsConfig();
		observer = new PropertyChangeSupport(this);
	}
	
	/**
	 * 
	 * @param i
	 * @param newConfig
	 */
	public void updateLable(int i, LabelConfig newConfig)
	{
		LabelConfig oldValue = labels.get(i);
		labels.set(i,newConfig);
		this.observer.firePropertyChange(UPDATE_LABEL,oldValue,newConfig);
	}
	
	/**
	 * 
	 * @param listener
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener)
	{
		observer.addPropertyChangeListener(listener);
	}
}
