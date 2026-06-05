package views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import models.Subject;
import utils.AppColors;
import utils.IconLoader;
import utils.InvisibleScrollBar;
import utils.Label;
import utils.SubjectButton;
import utils.WrapLayout;

public class TasksView extends JPanel {
	
	private List<SubjectButton> subjectBtns;
	private JPanel subjectMenu;
	
	private JButton addBtn;
	private JPanel pendingTasksPnl;
	private JPanel completedTasksPnl;
	private JLabel tasksCount;
	
	private CardLayout cardLayout;
	private JPanel cardContainer;
	
	private static final String SHOW_TASKS = "SHOW_TASKS";
    private static final String EMPTY_TASKS = "EMPTY_TASKS";

	public TasksView() { 
		setLayout(new BorderLayout());	
		setBorder(BorderFactory.createEmptyBorder(30, 20, 0, 20));
		
		subjectBtns = new ArrayList<>();
		
		createHeader();
		createCardLayout();
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
	
	public List<SubjectButton> getSubjectBtns() {
		return subjectBtns;
	}

	public JButton createAddBtn() {
		addBtn = new JButton ("Nueva tarea");
	    addBtn.setIcon(IconLoader.getIcon("/assets/img/add.svg", 20, 20));  
		addBtn.setBackground(AppColors.primaryAccent);
		addBtn.setForeground(Color.WHITE);
		return addBtn;
	}
	
	public void createHeader() {
		JPanel header = new JPanel(new BorderLayout());
		header.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
		
		header.add(createTitlePanel(), BorderLayout.WEST);
		header.add(createAddBtn(), BorderLayout.EAST);
		header.add(createSubjectMenu(), BorderLayout.SOUTH);
		
		add(header, BorderLayout.NORTH);
	}
	
	private JPanel createTitlePanel() {
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));	
		
		Label title = new Label("Mis tareas", 23, false);
		tasksCount = new Label("", 14, true, AppColors.menuItem);
		
		title.setAlignmentX(LEFT_ALIGNMENT);
		tasksCount.setAlignmentX(LEFT_ALIGNMENT);
		
		titlePanel.add(title);
		titlePanel.add(Box.createVerticalStrut(5));
		titlePanel.add(tasksCount);
		
		return titlePanel;
	}
	
	private JPanel subtitlePnl(String text, String iconPath) {
		JPanel container = new JPanel(new FlowLayout(FlowLayout.LEFT));
		container.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		
		Label subtitle = new Label(text, 12, false, AppColors.menuItem);
		subtitle.setIcon(IconLoader.getIcon(iconPath, 8, 8));
		subtitle.setIconTextGap(10);
		
		container.add(subtitle);
		
		return container;
	}
	
	private JScrollPane createSubjectMenu() {
		subjectMenu = new JPanel();
		subjectMenu.setLayout(new BoxLayout(subjectMenu, BoxLayout.X_AXIS));
		subjectMenu.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
				
		JScrollPane scrollPane = new JScrollPane(subjectMenu);
		
		scrollPane.getHorizontalScrollBar().setUI(new InvisibleScrollBar());
		scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0));
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    scrollPane.getHorizontalScrollBar().setUnitIncrement(16);
	    
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		
		return scrollPane;
	}
	
	public void addAllTasksBtn() {
		SubjectButton allTasksBtn = new SubjectButton("Todas", 20);	
		allTasksBtn.setBackground(AppColors.primaryAccent);
		allTasksBtn.setForeground(Color.WHITE);
		allTasksBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		subjectBtns.add(allTasksBtn);
		subjectMenu.add(allTasksBtn);
		subjectMenu.add(Box.createHorizontalStrut(7));
	}
	
	public void addSubjectBtn(Subject subject) {
		SubjectButton subjectBtn = new SubjectButton(subject, 20);	
		subjectBtn.setBackground(subject.getSubColor());
		subjectBtn.setForeground(subject.getColor());
		subjectBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		subjectBtns.add(subjectBtn);
		subjectMenu.add(subjectBtn);
		subjectMenu.add(Box.createHorizontalStrut(7));
	}
	
	private JScrollPane createTaskContainer() {
		JPanel taskContainer = new JPanel();
		taskContainer.setLayout(new BoxLayout(taskContainer, BoxLayout.Y_AXIS));
		
		pendingTasksPnl = new JPanel(new WrapLayout(FlowLayout.LEFT, 15, 15));		
		completedTasksPnl = new JPanel(new WrapLayout(FlowLayout.LEFT, 15, 15));
				
		taskContainer.add(subtitlePnl("PENDIENTES", "/assets/img/pending.svg"));
		taskContainer.add(pendingTasksPnl);
		taskContainer.add(subtitlePnl("COMPLETADAS", "/assets/img/completed.svg"));
		taskContainer.add(completedTasksPnl);
		
		JScrollPane scroll = new JScrollPane(taskContainer);
		scroll.setBorder(null);
		
		scroll.getVerticalScrollBar().setUI(new InvisibleScrollBar());
		scroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
	    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    scroll.getVerticalScrollBar().setUnitIncrement(16);
	    
	    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

	    return scroll;
	}
	
	public JPanel createEmptyTaskPanel() {
		JPanel container = new JPanel();
		Label lbl = new Label("No hay tareas", 23, false, AppColors.menuItem);
		container.add(lbl);
		return container;
	}
	
	public void createCardLayout() {
		cardLayout = new CardLayout();
		cardContainer = new JPanel(cardLayout);
		
		JScrollPane showTasks = createTaskContainer();
		JPanel emptyTasks = createEmptyTaskPanel();
		
		cardContainer.add(showTasks, SHOW_TASKS);
		cardContainer.add(emptyTasks, EMPTY_TASKS);
		
		add(cardContainer, BorderLayout.CENTER);
		
	}
	
	

	
}
