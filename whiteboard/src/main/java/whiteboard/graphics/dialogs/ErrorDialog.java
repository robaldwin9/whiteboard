package whiteboard.graphics.dialogs;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SpringLayout.Constraints;
import javax.swing.SwingUtilities;

import whiteboard.config.Config;

public class ErrorDialog extends JDialog
{
	/* */
	private static final long serialVersionUID = 1L;
	/** */
	protected JLabel prompt;
	
	/** */
	protected JButton ok;
	protected JButton cancle;
	protected String fontName;
	protected Color buttonColor;
	protected Color lblColor;
	protected int fontSize;
	
	/** layout Objects */
	GridBagLayout layout;
	GridBagConstraints constraints;
	
	public ErrorDialog(String promptText, int width, int height) 
	{
		/* Set style from configuration */
		Config config = Config.getInstance();
		fontName = config.getFontName();
		fontSize = config.getDialogFontSize();
		buttonColor = config.getButtonColor();
		lblColor  = config.getlblColor();
		
		/* Set layout */
		layout = new GridBagLayout();
		constraints = new GridBagConstraints();
		setLayout(layout);
		
		/** Add Label */
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(5,20,5,5);
		constraints.anchor = GridBagConstraints.WEST;
		prompt = new JLabel();
		prompt.setFont(new Font(fontName, Font.PLAIN, fontSize));
		prompt.setForeground(config.getlblColor());
		prompt.setText(promptText);
		add(prompt,constraints);
		
		/** Add OK button */
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets = new Insets(5,5,5,5);
		ok = new JButton("ok");
		ok.setBackground(config.getButtonColor());
		ok.setForeground(config.getlblColor());
		ok.setFont( new Font(fontName, Font.PLAIN, fontSize));
		ok.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
		});
		
		/* Set frame stuff */
		add(ok,constraints);
		setLocationRelativeTo(SwingUtilities.getWindowAncestor(this));
		getContentPane().setBackground(config.getDialogColor());
		setAlwaysOnTop(true);
		setUndecorated(true);
		pack();
		setVisible(true);
	}
}
