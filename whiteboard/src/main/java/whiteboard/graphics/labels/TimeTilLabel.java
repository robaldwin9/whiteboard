package whiteboard.graphics.labels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.SwingUtilities;

import whiteboard.config.Config;
import whiteboard.config.LabelConfig;
import whiteboard.config.TimeTilLabelConfig;
import whiteboard.graphics.View;
import whiteboard.graphics.dialogs.ConfirmDialog;
import whiteboard.time.TimeUtil;

public class TimeTilLabel extends StatisticsLabel 
{

	public TimeTilLabel(LabelConfig labelData, View view) 
	{
		super(labelData, view);
	}

	@Override
	protected void initValueLabel() 
	{
		getUpdatedTimeValue(false);
		valueLabel.setText(labelData.getValue());
		valueLabel.addMouseListener(new MouseAdapter() 
		{
				public void mouseClicked(MouseEvent e)
				{
					if(SwingUtilities.isRightMouseButton(e))
					{
						  ConfirmDialog confirm = new ConfirmDialog("Are you sure you want to remove item",450,150);
						    confirm.setokActionEvent(new ActionListener() {
								public void actionPerformed(ActionEvent e)
								{
									Config.getInstance().removeLabel(labelData);
										
									SwingUtilities.invokeLater(new Runnable() 
									{
						                   @Override
						                   public void run() 
						                   {
						                	   	  confirm.dispose();
						                    	  view.removeLabel(instance);  
						                   }
						            });
								}
							});
					}
					
					if(SwingUtilities.isLeftMouseButton(e))
					{
						ConfirmDialog confirm = new ConfirmDialog("Reset selected time?",450,150);
						confirm.setokActionEvent( new ActionListener() 
						{		
							public void actionPerformed(ActionEvent e)
							{
								getUpdatedTimeValue(true);
								SwingUtilities.invokeLater(new Runnable() 
								{
						           @Override
						           public void run() 
						           {  
						             valueLabel.setText(labelData.getValue());	  
						           }
						        });
								Config.getInstance().updateLabel(labelData);
								confirm.dispose();
							}
						});
					}
					}
					
					
		
		});
		
	}
	
	public void getUpdatedTimeValue(boolean setnewDate)
	{
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
		Date now = new Date();
		String newTime = ""
				+ "0 Days 0 Hours 0 Minutes";
		
		if(setnewDate)
		{
			((TimeTilLabelConfig)labelData).setDate(dateFormat.format(now));
		}
		
		try 
		{
			newTime = TimeUtil.getDateDifference(now,dateFormat.parse(((TimeTilLabelConfig)labelData).getDate()));
		} 
		
		catch (ParseException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		labelData.setValue(newTime);
	}
}
