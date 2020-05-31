package whiteboard.graphics.labels;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import whiteboard.config.Config;
import whiteboard.config.LabelConfig;
import whiteboard.graphics.View;
import whiteboard.graphics.dialogs.EnterTextDialog;
import whiteboard.graphics.dialogs.ConfirmDialog;

public abstract class StatisticsLabel 
{      

	protected LabelConfig labelData;
	protected JLabel descriptorLabel;
	protected JLabel valueLabel;
	protected View view;
	protected StatisticsLabel instance; 
	protected String fontName;
	
	public StatisticsLabel(LabelConfig labelData, View view)
	{
		Config config = Config.getInstance();
		instance = this;
		fontName = config.getFontName();
		this.view = view;
		this.labelData = labelData;
		descriptorLabel = new JLabel();
		descriptorLabel.setFont(new Font(config.getFontName(), Font.BOLD, config.getFontSize()));
		descriptorLabel.setForeground(config.getlblColor());
		initDescriptorLabel();
		
		valueLabel = new JLabel();
		valueLabel.setFont(new Font(config.getFontName(), Font.BOLD, config.getFontSize()));
		valueLabel.setForeground(config.getlblColor());
		initValueLabel();
	}

	/** Getters Setters */
	public String getDescriptor() 
	{
		return labelData.getDescriptor();
	}

	public void setDescriptor(String descriptor) 
	{
		labelData.setDescriptor(descriptor);
	}

	public String getValue() 
	{
		return labelData.getValue();
	}

	public void setValue(String value) 
	{
		labelData.setValue(value);
	}

	public String getType() 
	{
		return labelData.getType();
	}

	public void setType(String type) 
	{
		labelData.setType(type);
	}
	
	public int getId()
	{
		return labelData.getId();
	}
	
	public void setId(int id)
	{
		labelData.setId(id);
	}
	
	public JLabel getDescriptorLabel() 
	{
		return descriptorLabel;
	}

	public void setDescriptorLabel(JLabel descriptorLabel) 
	{
		this.descriptorLabel = descriptorLabel;
	}

	public JLabel getValueLabel() 
	{
		return valueLabel;
	}

	public void setValueLabel(JLabel valueLabel) 
	{
		this.valueLabel = valueLabel;
	}
	
	public void setLabelData(LabelConfig config)
	{
		labelData = config;
		descriptorLabel.setText(labelData.getDescriptor());
		valueLabel.setText(labelData.getValue());
	}
	
	/** Abstract functions */
	private  void initDescriptorLabel()
	{
		descriptorLabel.setText(labelData.getDescriptor());
		descriptorLabel.addMouseListener(new MouseAdapter() 
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
				
				else
				{
					  EnterTextDialog input = new EnterTextDialog("Enter New Text:",450,150);
					    input.setokActionEvent(new ActionListener() {
							public void actionPerformed(ActionEvent e)
							{
							
								labelData.setDescriptor(input.getUserInputText());
								Config.getInstance().updateLabel(labelData);
									
								SwingUtilities.invokeLater(new Runnable() 
								{
					                   @Override
					                   public void run() 
					                   {
					                	   
					                    	  descriptorLabel.setText(labelData.getDescriptor());
					                    	  input.dispose();
					                   }
					            });
								
							}
						});
					    
					   
				}
			  
			}
	});
}
	protected abstract void initValueLabel();
	
}
