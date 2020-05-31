package whiteboard.graphics.dialogs;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

public class ConfirmDialog extends WhiteboardDialog
{
	
	/** */
	private static final long serialVersionUID = 4575863357831177914L;
	
	/** layout Objects */
	GridBagLayout layout;
	GridBagConstraints constraints;
	
	/**
	 * 
	 * @param promptText
	 * @param width
	 * @param height
	 */
	public ConfirmDialog(String promptText, int width, int height)
	{
		super();
		
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
		prompt.setText(promptText);
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.CENTER;
		this.add(prompt,constraints);

		/** Add confirm button */
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.anchor = GridBagConstraints.SOUTHWEST;
		constraints.insets = new Insets(2,2,2,2);
		this.add(ok,constraints);
		
		/** Add cancel button */
		constraints.gridx =2;
		constraints.gridy = 1;
		constraints.anchor = GridBagConstraints.SOUTHEAST;
		constraints.insets = new Insets(2,2,2,2);
		this.add(cancle,constraints);
		
		this.setSize(width,height);
		setVisible(true);
	}
	
	/**
	 * 
	 * @param e
	 */
	public void setokActionEvent(ActionListener e)
	{
		ok.addActionListener(e);

	}
}
