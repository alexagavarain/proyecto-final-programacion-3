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
	private JPanel pendingTasksPnl;
	private JPanel completedTasksPnl;
	private JLabel tasksCount;

	public TasksView() { 
		setLayout(new BorderLayout());	
		setBorder(BorderFactory.createEmptyBorder(30, 20, 0, 20));
		createHeader();
		createTaskContainer();
	}
	
	public JButton getAddBtn() {
		return addBtn;
	}

	public JPanel getPendingTasksPnl() {
		return pendingTasksPnl;
	}
	
	public JPanel getCompletedTasksPnl() {
		return completedTasksPnl;
	}
	
	public JLabel getTasksCount() {
		return tasksCount;
	}
	
	public JLabel createTitle(String name, float fontSize) {
		JLabel title = new JLabel(name);
		title.setToolTipText("");
		title.setFont(CreateFont.DEFAULT_BOLD.deriveFont(fontSize));		
		return title;
	}
	
	public JButton createAddBtn() {
		addBtn = new JButton ("Nueva tarea");
		addBtn.setBackground(AppColors.primaryAccent);
		addBtn.setForeground(Color.WHITE);
		return addBtn;
	}
	
	public void createHeader() {
		JPanel header = new JPanel(new BorderLayout());
		header.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 20));
		
		addTitlePanel(header);
		header.add(createAddBtn(), BorderLayout.EAST);
		
		add(header, BorderLayout.NORTH);
	}
	
	private void addTitlePanel(JPanel container) {
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));	
		titlePanel.add(createTitle("Mis Tareas", 23));
		tasksCount = new JLabel("");
		titlePanel.add(tasksCount);
		
		container.add(titlePanel, BorderLayout.WEST);

	}
	
	public void createSpace(JPanel container, int pixels) {
		container.add(Box.createVerticalStrut(pixels));
	}
	
	private JPanel subtitlePnl(String text) {
		JPanel container = new JPanel(new FlowLayout(FlowLayout.LEFT));
		container.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		JLabel subtitle = new JLabel(text);
		container.add(subtitle);
		return container;
	}
	
	public void createTaskContainer() {
		JPanel taskContainer = new JPanel();
		taskContainer.setLayout(new BoxLayout(taskContainer, BoxLayout.Y_AXIS));
		
		pendingTasksPnl = new JPanel(new WrapLayout(FlowLayout.LEFT, 20, 20));
				
		completedTasksPnl = new JPanel(new WrapLayout(FlowLayout.LEFT, 20, 20));
				
		taskContainer.add(subtitlePnl("PENDIENTES"));
		taskContainer.add(pendingTasksPnl);
		
		taskContainer.add(subtitlePnl("COMPLETADAS"));
		taskContainer.add(completedTasksPnl);
		
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
