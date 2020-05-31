package whiteboard.graphics.labels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import whiteboard.config.Config;
import whiteboard.config.LabelConfig;
import whiteboard.graphics.View;
import whiteboard.graphics.dialogs.ConfirmDialog;
import whiteboard.graphics.dialogs.EnterTextDialog;


public class TimerLabel extends StatisticsLabel
{
	long startTime;

	public TimerLabel(LabelConfig labelData, View view) 
	{
		super(labelData, view);
	}
	

	@Override
	protected void initValueLabel() 
	{
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
				    EnterTextDialog input = new EnterTextDialog("Enter New text:",450,150);
				    input.setokActionEvent(new ActionListener() 
				    {
						public void actionPerformed(ActionEvent e)
						{
			                labelData.setValue(input.getUserInputText());
							Config.getInstance().updateLabel(labelData);
							
							SwingUtilities.invokeLater(new Runnable() 
							{
			                      @Override
			                      public void run() 
			                      {
			                    	  
			                    	  valueLabel.setText(labelData.getValue());
			                    	  input.dispose();
			                      }
			                  });
						}
					});
					
				}
			}
	});
		
	}

}
