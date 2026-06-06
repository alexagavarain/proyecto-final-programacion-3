package views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
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
import utils.CreateFont;
import utils.IconLoader;
import utils.InvisibleScrollBar;
import utils.Label;
import utils.RoundedButton;
import utils.SubjectButton;
import utils.WrapLayout;

public class TasksView extends JPanel {
	
	private List<SubjectButton> subjectBtns;
	private JPanel subjectMenu;
	
	private JButton addBtn;
	private JPanel pendingTasksPnl;
	private JPanel completedTasksPnl;
	private JLabel tasksCount;
	
	private Label pendingLbl;
	private Label completedLbl;
	
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

	public Label getPendingLbl() {
		return pendingLbl;
	}
	
	public Label getCompletedLbl() {
		return completedLbl;
	}

	public JButton createAddBtn() {
		addBtn = new RoundedButton("Nueva tarea", 14);
		addBtn.setFont(CreateFont.DEFAULT_BOLD);
	    addBtn.setIcon(IconLoader.getIcon("/assets/img/add.svg", 20, 20));  
	    addBtn.setPreferredSize(new Dimension(140, 40));
		addBtn.setBackground(AppColors.primaryAccent);
		addBtn.setForeground(Color.WHITE);
		addBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addBtn.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
		return addBtn;
	}
	
	public void createHeader() {
		JPanel header = new JPanel(new BorderLayout());
		header.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
		
		JPanel btnContainer = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
	    btnContainer.setOpaque(false);
	    btnContainer.add(createAddBtn());
		
		header.add(createTitlePanel(), BorderLayout.WEST);
		header.add(btnContainer, BorderLayout.EAST);
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
	
	public Label createSubtitleLbl(String text, String iconPath) {
		Label subtitle = new Label(text, 12, false, AppColors.menuItem);
		subtitle.setIcon(IconLoader.getIcon(iconPath, 8, 8));
		subtitle.setIconTextGap(10);
		return subtitle;
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
	    JPanel taskContainer = new JPanel(new GridBagLayout());
	    
	    java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
	    gbc.gridx = 0;
	    gbc.gridy = java.awt.GridBagConstraints.RELATIVE; 
	    gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
	    gbc.weightx = 1.0; 
	    gbc.weighty = 0.0;
	    
	    WrapLayout pendingLayout = new WrapLayout(FlowLayout.LEFT, 12, 12);
	    WrapLayout completedLayout = new WrapLayout(FlowLayout.LEFT, 12, 12);
	    pendingLayout.setAlignOnBaseline(false);
	    completedLayout.setAlignOnBaseline(false);
	    
		JPanel pLblContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    pendingLbl = createSubtitleLbl("", "/assets/img/pending.svg");
	    pLblContainer.add(pendingLbl);
	    
	    pendingTasksPnl = new JPanel(pendingLayout);    
	    pendingTasksPnl.setBorder(BorderFactory.createEmptyBorder(0, -12, 0, -12));
	    
		JPanel cLblContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    completedLbl = createSubtitleLbl("", "/assets/img/completed.svg");
	    cLblContainer.add(completedLbl);
	    
	    completedTasksPnl = new JPanel(completedLayout);
	    completedTasksPnl.setBorder(BorderFactory.createEmptyBorder(0, -12, 0, -12));
	            
	    taskContainer.add(pLblContainer, gbc);
	    taskContainer.add(pendingTasksPnl, gbc);
	    
	    taskContainer.add(Box.createVerticalStrut(15), gbc); 
	    
	    taskContainer.add(cLblContainer, gbc);
	    taskContainer.add(completedTasksPnl, gbc);
	    
	    gbc.weighty = 1.0;
	    taskContainer.add(new JPanel() {{ setOpaque(false); }}, gbc);
	    
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
		JPanel container = new JPanel(new GridBagLayout());
		Label lbl = new Label("No tienes tareas", 20, false, AppColors.taskInfo);
		lbl.setBorder(BorderFactory.createEmptyBorder(0, 0, 150, 0));
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
	
	public void showView(String view) {
		cardLayout.show(cardContainer, view);
	}
	
	public void activeSubjectBtn(SubjectButton activeBtn) {
	    for (SubjectButton btn : subjectBtns) {
	        if (btn == activeBtn) {
	        	if (btn.getSubject() == null) {
	        		btn.setBackground(AppColors.primaryAccent);
	        		btn.setForeground(Color.WHITE);
	        	} else {
		            btn.setBackground(activeBtn.getSubject().getColor());
		            btn.setForeground(activeBtn.getSubject().getSubColor());
	        	}
	        } else {
	        	if (btn.getSubject() == null) {
	        		btn.setBackground(AppColors.iceGrey);
	        		btn.setForeground(AppColors.primaryAccent);
	        	} else {
	        	 btn.setBackground(btn.getSubject().getSubColor());
		         btn.setForeground(btn.getSubject().getColor());
	        	}
	        }
	    }
	}
	
}
