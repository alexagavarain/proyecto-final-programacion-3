package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class TaskComponent extends JPanel{
	
	public TaskComponent() {
		setSize(new Dimension (200, 200));
		setPreferredSize(new Dimension (200, 200));
		setLayout(new BorderLayout());
		setBackground(Color.RED);
	}

}
