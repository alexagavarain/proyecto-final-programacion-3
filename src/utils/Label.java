package utils;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

public class Label extends JLabel {

	public Label(String text, int fontSize, boolean plain) {
		setToolTipText("");
		setText(text);
		setFont(new Font("Arial", (plain ? Font.PLAIN:Font.BOLD), fontSize));
		setAlignmentX(CENTER_ALIGNMENT);
	}
	
	public Label(String text, int fontSize, boolean plain, Color color) {
		setToolTipText("");
		setText(text);
		setFont(new Font("Arial", (plain ? Font.PLAIN:Font.BOLD), fontSize));
		setForeground(color);
		setAlignmentX(CENTER_ALIGNMENT);
	}
}
