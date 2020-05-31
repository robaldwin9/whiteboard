package whiteboard.graphics.dialogs;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JTextField;

import whiteboard.config.Config;

public class AddItemDialog extends WhiteboardDialog
{
	/** */
	private static final long serialVersionUID = 6657913286498715792L;
	
	/** */
	JTextField timerInputField;
	
	
	/** layout Objects */
	GridBagLayout layout;
	GridBagConstraints constraints;
	
	/* Check box for selecting label type to add */
	JCheckBox checkBox1;
	JCheckBox checkBox2;
	JCheckBox checkBox3;
	
	public AddItemDialog(String promptText, int width, int height)
	{
		Config config = Config.getInstance();
		/** layout Initialization */
		layout = new GridBagLayout();
		constraints = new GridBagConstraints();
		
		/** Common constraints */
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 1;
		constraints.weighty = 0.5;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		setLayout(layout);
		
		/** Add Label */
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.CENTER;
		prompt.setText(promptText);
		this.add(prompt,constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		checkBox1 = new JCheckBox("Time Value");
		checkBox1.setOpaque(false);
		checkBox1.setFont(new Font(fontName, Font.PLAIN, fontSize));
		checkBox1.setBackground(buttonColor);
		checkBox1.setForeground(lblColor);
		this.add(checkBox1,constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		checkBox2 = new JCheckBox("Static Value");
		checkBox2.setOpaque(false);
		checkBox2.setFont(new Font(fontName, Font.PLAIN, fontSize));
		checkBox2.setBackground(buttonColor);
		checkBox2.setForeground(lblColor);
		this.add(checkBox2,constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 3;
		checkBox3 = new JCheckBox("Repeate Timer");
		checkBox3.setOpaque(false);
		checkBox3.setFont(new Font(fontName, Font.PLAIN, fontSize));
		checkBox3.setBackground(buttonColor);
		checkBox3.setForeground(lblColor);
		this.add(checkBox3,constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.anchor = GridBagConstraints.WEST;
		timerInputField = new JTextField("",500);
		timerInputField.setFont(new Font(config.getFontName(), Font.PLAIN, config.getDialogFontSize()));
		this.add(timerInputField,constraints);
		
		/** Add OK button */
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets = new Insets(2,2,2,2);
		this.add(ok,constraints);
		
		/** Add cancel button */
		constraints.gridx =1;
		constraints.gridy = 4;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets = new Insets(2,2,2,2);
		this.add(cancle,constraints);
		
		setUndecorated(true);
		this.setSize(width,height);
		setVisible(true);
	}
	
	public void setokActionEvent(ActionListener e)
	{
		ok.addActionListener(e);
	}
	
	public boolean box1Checked()
	{
		return checkBox1.isSelected();
	}
	
	public boolean box2Checked()
	{
		return checkBox2.isSelected();
	}

	public boolean box3Checked() 
	{
		// TODO Auto-generated method stub
		return checkBox3.isSelected();
	}
	
	public String getTimerValueText()
	{
		return timerInputField.getText();
	}
}
