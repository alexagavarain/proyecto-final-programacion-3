package utils;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class InputField extends JTextField{
	
	public InputField() {
		setMaximumSize(new Dimension(300, 28));
		setAlignmentX(CENTER_ALIGNMENT);
		setFont(new Font ("Arial", Font.PLAIN, 15));

		setBorder(
			    BorderFactory.createCompoundBorder(
			        BorderFactory.createLineBorder(AppColors.subtleAccent, 1, true),
			        BorderFactory.createEmptyBorder(5, 5, 5, 5)
			    )
		);
	}

}
