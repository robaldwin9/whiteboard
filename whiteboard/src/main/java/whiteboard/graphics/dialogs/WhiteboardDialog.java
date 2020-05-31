package whiteboard.graphics.dialogs;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import whiteboard.config.Config;

public abstract class WhiteboardDialog extends JDialog
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
	
	public WhiteboardDialog()
	{
		Config config = Config.getInstance();
		
		fontName = config.getFontName();
		fontSize = config.getDialogFontSize();
		buttonColor = config.getButtonColor();
		lblColor  = config.getlblColor();
		
		prompt = new JLabel();
		prompt.setFont(new Font(fontName, Font.PLAIN, fontSize));
		prompt.setForeground(config.getlblColor());
		
		ok = new JButton("ok");
		ok.setBackground(config.getButtonColor());
		ok.setForeground(config.getlblColor());
		ok.setFont( new Font(fontName, Font.PLAIN, fontSize));
		
		cancle = new JButton("Cancle");
		cancle.setBackground(config.getButtonColor());
		cancle.setForeground(config.getlblColor());
		cancle .setFont( new Font(fontName, Font.PLAIN, fontSize));
		cancle.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
		});
		
		setLocationRelativeTo(SwingUtilities.getWindowAncestor(this));
		setAlwaysOnTop(true);
		getContentPane().setBackground(config.getDialogColor());
		setUndecorated(true);
	}
}
