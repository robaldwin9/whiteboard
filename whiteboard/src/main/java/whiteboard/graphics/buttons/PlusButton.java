package whiteboard.graphics.buttons;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import whiteboard.config.Config;
import whiteboard.config.LabelConfig;
import whiteboard.config.StaticLabelConfig;
import whiteboard.config.TimeSinceLabelConfig;
import whiteboard.config.TimerLabelConfig;
import whiteboard.graphics.View;
import whiteboard.graphics.dialogs.AddItemDialog;
import whiteboard.graphics.dialogs.ErrorDialog;
import whiteboard.graphics.labels.StaticLabel;
import whiteboard.graphics.labels.TimeSinceLabel;
import whiteboard.graphics.labels.TimerLabel;


public class PlusButton extends JButton
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PlusButton(View view)
	{
		Config config = Config.getInstance();
		this.setText("+");
		this.setFont(new Font(config.getFontName(),Font.BOLD, config.getFontSize()));
		setBackground(config.getDialogColor());
		setForeground(config.getlblColor());
		setFocusPainted(false);
		setPreferredSize(new Dimension(60,60));
		
		addActionListener(new ActionListener() 
		{
	         @Override
	         public void actionPerformed(ActionEvent e) 
	         {
	        	 AddItemDialog addItem = new AddItemDialog("Add item:",450,150);
				    addItem.setokActionEvent(new ActionListener() 
				    {
						public void actionPerformed(ActionEvent e)
						{
							
								
							SwingUtilities.invokeLater(new Runnable() 
							{
				                   @Override
				                   public void run() 
				                   {
				                	   if(addItem.box2Checked())
				                	   {
				                		   LabelConfig newConfig = new StaticLabelConfig(0,"NEW","NEW",LabelConfig.STATIC_TYPE);
					                	   StaticLabel sLabel = new StaticLabel(newConfig,view);
					                	   Config.getInstance().addLabel(newConfig);
					                	   view.addLabel(sLabel);
				                	   }
				                	   
				                	   if(addItem.box1Checked())
				                	   {
				                			DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
				                			Date now = new Date();
				                		    String dateValue = dateFormat.format(now);
				                		    LabelConfig newConfig = new TimeSinceLabelConfig(0,"NEW",dateValue,LabelConfig.TIME_TYPE);
				                		    TimeSinceLabel tLabel = new TimeSinceLabel(newConfig,view);
				                		    Config.getInstance().addLabel(newConfig);
				                		    view.addLabel(tLabel);
				                	   }
			                	   
				                	   if(addItem.box3Checked())
				                	   {
				                			String value = addItem.getTimerValueText();
				                			
				                			if(!value.matches("^[0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}"))
				                			{
				                				addItem.dispose();
				                				ErrorDialog dialog = new ErrorDialog("ERROR: Value did not match HH:MM:SS format",300,300);
				                				return;
				                			}
				                			
				                			value = addItem.getTimerValueText();
				                		    LabelConfig newConfig = new TimerLabelConfig(0,"NEW",value,LabelConfig.TIMER_TYPE);
				                		    TimerLabel tLabel = new TimerLabel(newConfig,view);
				                		    Config.getInstance().addLabel(newConfig);
				                		    view.addLabel(tLabel);
				                	   }
				                	   	  addItem.dispose();   	  
				                   }   
				            });
						}
					});
	         }
	   });
	}
}
