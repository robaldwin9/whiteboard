package whiteboard.graphics;


import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import whiteboard.config.Config;
import whiteboard.config.LabelConfig;
import whiteboard.graphics.buttons.PlusButton;
import whiteboard.graphics.labels.StaticLabel;
import whiteboard.graphics.labels.StatisticsLabel;
import whiteboard.graphics.labels.TimeSinceLabel;
import whiteboard.graphics.labels.TimeTilLabel;
import whiteboard.graphics.labels.TimerLabel;

public class View extends JFrame implements PropertyChangeListener, KeyListener
{
	/** Unique ID */
	private static final long serialVersionUID = 1L;
	
	/** Application configuration */
	Config config;
	
	/** layout Objects */
	GridBagLayout layout;
	GridBagConstraints constraints;
	
	/** Graphical Elements */
	Vector<StatisticsLabel> labels;
	JPanel container;
	JScrollPane scrollPane;
	PlusButton addItemButton;

	/**
	 * Constructor
	 */
	public View()
	{
		config = Config.getInstance();
		
		/** Label initialization*/
		Vector<LabelConfig> labelsConfig = config.getLabelsConfig();
		labels = new Vector<StatisticsLabel>(config.getLabelsConfig().size());
		
		for(int i = 0; i < labelsConfig.size(); i++)
		{
			LabelConfig config = labelsConfig.get(i);
			
			if(config.getType().equals(LabelConfig.TIME_TYPE))
			{
				labels.add(new TimeSinceLabel(config,this));
			}
			
			else if(config.getType().equals(LabelConfig.STATIC_TYPE))
			{
				labels.add(new StaticLabel(config,this));
			}
			
			else if(config.getType().equals(LabelConfig.TIMER_TYPE))
			{
				labels.add(new TimerLabel(config,this));
			}
			
//			else if(config.getType().equals(LabelConfig.TIME_TIL_TYPE))
//			{
//				labels.add(new TimeTilLabel(config,this));
//			}
		}

		/** layout Initialization */
		layout = new GridBagLayout();
		constraints = new GridBagConstraints();

		/** Window Dimensions*/
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(false);
		
		/** Common constraints */
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 0.5;
		constraints.weighty = 1.00;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		
		/** Initialize panel that contains everything */
		container = new JPanel();
		container.setBackground(config.getBgColor());
		container.setLayout(layout);
		scrollPane = new JScrollPane(container);
		
		/** Button to add labels to GUI */
		addItemButton = new PlusButton(this);
		
		/** Tray icon */
		Image icon = Toolkit.getDefaultToolkit().getImage(config.getIconPath());
		setIconImage(icon);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		addElementsToLayout();
		
		/*Set Full Screen */
		if(Config.getInstance().isFullScreen())
		{
			setUndecorated(true);
			GraphicsEnvironment graphics =
				      GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsDevice device = graphics.getDefaultScreenDevice();
	
			device.setFullScreenWindow(this);
		}
		
		else
		{
			pack();
			setSize(this.getWidth() + 100,this.getHeight());
		}

		setTitle("Whiteboard");
		setVisible(true);
	}
	
	/**
	 * Data changes in the model cause update here
	 */
	public void propertyChange(PropertyChangeEvent e) 
	{
		/** Updates GUI values */
	    if(Model.UPDATE_LABEL.equals(e.getPropertyName()))
		{
			LabelConfig newVal = (LabelConfig) e.getNewValue();
			labels.get(newVal.getId() - 1).setLabelData(newVal);
		}
	}
	
	/**
	 * 
	 */
	private void addElementsToLayout()
	{
		for(int i = 0; i < labels.size(); i++)
		{
			constraints.gridx = 0;
			constraints.gridy = i;
			container.add(labels.get(i).getDescriptorLabel(), constraints);
			
			constraints.gridx += 1;
			container.add(labels.get(i).getValueLabel(), constraints);
		}	
		
		container.add(addItemButton);
		this.add(scrollPane);
	}
	
	/**
	 * 
	 */
	@Override
	public void keyPressed(KeyEvent e) 
	{
		if(e.getKeyCode() == KeyEvent.VK_F12 )
		{
			if(isAlwaysOnTop())
			{
				setAlwaysOnTop(false);
			}
			
			else
			{
				setAlwaysOnTop(true);
			}
		}
		
		else if(e.getKeyCode() == KeyEvent.VK_F11)
		{
			if(getExtendedState() == JFrame.MAXIMIZED_BOTH)
			{
				setExtendedState(JFrame.NORMAL);
			}
			
			else
			{
				setExtendedState(JFrame.MAXIMIZED_BOTH);
			}
		}
	}
	
	/**
	 * 
	 */
	@Override
	public void keyTyped(KeyEvent e) 
	{
		
	}
	
	/**
	 * 
	 */
	@Override
	public void keyReleased(KeyEvent e) 
	{
		
	}
	
	public void removeLabel(StatisticsLabel item)
	{
		int index = item.getId() - 1;
		
		labels.remove(index);
		labels.trimToSize();
		container.remove(item.getDescriptorLabel());
		container.remove(item.getValueLabel());
		pack();
		setSize(this.getWidth() + 100,this.getHeight());
		revalidate();
		repaint();
	}
	
	public void addLabel(StatisticsLabel item)
	{
		labels.add(item);
		item.setId(labels.size());
		constraints.gridx = 0;
		constraints.gridy = labels.size();
		container.add(item.getDescriptorLabel(),constraints);
		constraints.gridx +=1;
		container.add(item.getValueLabel(),constraints);
		pack();
		setSize(this.getWidth() + 100,this.getHeight());
		revalidate();
		repaint();
	}
}
