package utils;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

public class Label extends JLabel {

	public Label(String text, float fontSize, boolean plain) {
		setToolTipText("");
		setText(text);
		setFont(CreateFont.DEFAULT.deriveFont(fontSize));		
		setAlignmentX(CENTER_ALIGNMENT);
	}
	
	public Label(String text, float fontSize, boolean plain, Color color) {
		setToolTipText("");
		setText(text);
		setFont(CreateFont.DEFAULT.deriveFont(fontSize));		
		setForeground(color);
		setAlignmentX(CENTER_ALIGNMENT);
	}
}
