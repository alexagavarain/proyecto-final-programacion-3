package views;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import utils.CreateFont;

public class ClassesView extends JPanel{
	
	public ClassesView() { 
		setLayout(new BorderLayout());
		createTitle("Clases", 25);
	}
	
	public void createTitle(String name, float fontSize) {
		JLabel appName = new JLabel(name);
		appName.setToolTipText("");
		appName.setFont(CreateFont.DEFAULT_BOLD.deriveFont(fontSize));		
		appName.setAlignmentX(CENTER_ALIGNMENT);
		add(appName, BorderLayout.NORTH);
	}

}
