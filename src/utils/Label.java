package utils;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

public class Label extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Label(String text, float fontSize, boolean plain) {
		setToolTipText("");
		setText(text);
		setFont(plain ? CreateFont.DEFAULT.deriveFont(fontSize) : CreateFont.DEFAULT_BOLD.deriveFont(fontSize));		
		setAlignmentX(CENTER_ALIGNMENT);
	}
	
	public Label(String text, float fontSize, boolean plain, Color color) {
		setToolTipText("");
		setText(text);
		setFont(plain ? CreateFont.DEFAULT.deriveFont(fontSize) : CreateFont.DEFAULT_BOLD.deriveFont(fontSize));		
		setForeground(color);
		setAlignmentX(CENTER_ALIGNMENT);
	}
	
	public void setFontSize(float size) {
	    Font currentFont = getFont();

	    setFont(currentFont.deriveFont(size));
	}
}
