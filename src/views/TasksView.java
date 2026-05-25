package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import utils.AppColors;
import utils.CreateFont;
import utils.WrapLayout;

public class TasksView extends JPanel {
	
	private JButton addBtn;
	private JPanel taskContainer;
	
	public JButton getAddBtn() {
		return addBtn;
	}

	public JPanel getTaskContainer() {
		return taskContainer;
	}

	public TasksView() { 
		setLayout(new BorderLayout());	
		setBorder(BorderFactory.createEmptyBorder(30, 20, 0, 20));
		createHeader();
		createTaskContainer();
	}
	
	public JLabel createTitle(String name, float fontSize) {
		JLabel title = new JLabel(name);
		title.setToolTipText("");
		title.setFont(CreateFont.DEFAULT_BOLD.deriveFont(fontSize));		
		return title;
	}
	
	public JButton createAddBtn() {
		addBtn = new JButton ("Agregar tarea");
		addBtn.setBackground(AppColors.primaryAccent);
		addBtn.setForeground(Color.WHITE);
		return addBtn;
	}
	
	public void createHeader() {
		JPanel header = new JPanel(new BorderLayout());
		header.add(createTitle("Tareas", 23), BorderLayout.WEST);
		header.add(createAddBtn(), BorderLayout.EAST);
		header.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 20));
		add(header, BorderLayout.NORTH);
	}
	
	public void createSpace(JPanel container, int pixels) {
		container.add(Box.createVerticalStrut(pixels));
	}
	
	public void createTaskContainer() {
		taskContainer = new JPanel(new WrapLayout(FlowLayout.LEFT, 20, 20));
		
		JScrollPane scroll = new JScrollPane(taskContainer);

	    scroll.setBorder(null);

	    scroll.setHorizontalScrollBarPolicy(
	        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
	    );

	    scroll.setVerticalScrollBarPolicy(
	        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
	    );

	    scroll.getVerticalScrollBar().setUnitIncrement(16);

	    add(scroll, BorderLayout.CENTER);
	}

	
}
