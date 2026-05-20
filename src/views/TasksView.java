package views;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import utils.CreateFont;

public class TasksView extends JPanel {
	
	public TasksView() { 
		setLayout(new BorderLayout());
		createTitle("Tareas", 25);
	}
	
	public void createTitle(String name, float fontSize) {
		JLabel appName = new JLabel(name);
		appName.setToolTipText("");
		appName.setFont(CreateFont.DEFAULT_BOLD.deriveFont(fontSize));		
		appName.setAlignmentX(CENTER_ALIGNMENT);
		add(appName, BorderLayout.NORTH);
	}

}
