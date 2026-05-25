package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import utils.CreateFont;

public class ClassesView extends JPanel{
	
	private int col = 0;
	private int row = 0;
	private final int MAX_COLS = 4;
	
	private JPanel container;
	
	public JPanel getContainer() {
		return container;
	}
	
	public ClassesView() { 
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(30, 20, 0, 20));
		createTitle("Clases", 23);
		createPanel();
	}
	
	public void createTitle(String name, float fontSize) {
		JLabel title = new JLabel(name);
	    title.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
		title.setFont(CreateFont.DEFAULT_BOLD.deriveFont(fontSize));		
		title.setAlignmentX(LEFT_ALIGNMENT);
		add(title, BorderLayout.NORTH);
	}
	
	public void createPanel() {
		container = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));		
	    add(container, BorderLayout.CENTER);
	}


}
