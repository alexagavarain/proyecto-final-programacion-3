package views;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.time.format.DateTimeFormatter;
import java.awt.Shape;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import models.Subject;
import models.Task;
import utils.AppColors;
import utils.CreateFont;
import utils.IconLoader;
import utils.Label;
import utils.SubjectButton;

public class TaskCard extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int cornerRadius = 20;
	
	private Task task;
	private Subject subject;
	private String date;
	private Label title;
	
	private Label taskTitle;
	private Label completedTitle;
	
	private JButton edit;
	private JButton check;
	
	public TaskCard(Task task) {
		this.task = task; 
		this.subject = task.getGroupSubject().getSubjectProfessor().getSubject();
		
		setLayout(new BorderLayout());
		
		Dimension size = new Dimension (320, 120);
		setPreferredSize(size);
		setMaximumSize(size);
        setMinimumSize(size);
		setVisible(true);
		setBorder(BorderFactory.createEmptyBorder(1, 0, 1, 5));
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		date = task.getDeadline().format(formatter);
		
		westElements();
		taskDescription();
	}
	
	public JButton getEditBtn() {
		return edit;
	}

	public JButton getCheckBtn() {
		return check;
	}

	public void westElements() {
		JPanel container = new JPanel(new BorderLayout());
		
		JPanel colorLine = createColorLine();
		
		check = new JButton(IconLoader.getIcon("/assets/img/checkbox.svg", 22, 22));
	    check.setBorder(BorderFactory.createEmptyBorder(5, 10, 60, 0));	    
	    check.setContentAreaFilled(false);
	    check.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    
	    container.add(colorLine, BorderLayout.WEST);
	    container.add(check, BorderLayout.CENTER);
	    
		add(container, BorderLayout.WEST);
	}
	
	public JPanel createColorLine() {
		JPanel colorLine = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());

                Shape roundLeftOnly = new RoundRectangle2D.Double(
                    0, 0, getWidth() + cornerRadius/2, getHeight(), cornerRadius/2, cornerRadius/2
                );
                g2.setClip(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 0, 0));
                g2.fill(roundLeftOnly);

                g2.dispose();
            }
		};
            
		colorLine.setOpaque(false);
        colorLine.setPreferredSize(new Dimension(6, 0));
    	colorLine.setBackground(subject.getColor());  
		return colorLine;
	}
	
	public void taskDescription() {
		JPanel container = new JPanel(new BorderLayout());
		container.setBorder(BorderFactory.createEmptyBorder(18, 10, 5, 10));
		
		JPanel northElements = new JPanel(new BorderLayout());
		
		createTaskTitle();
		createCompletedTitle();
		
		title = task.getStatus().equals("Pendiente") ? taskTitle : completedTitle;
		
		edit = new JButton(IconLoader.getIcon("/assets/img/edit.svg", 13, 13));
	    edit.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));	    
	    edit.setContentAreaFilled(false);
	    edit.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		northElements.add(title, BorderLayout.WEST);
		northElements.add(edit, BorderLayout.EAST);
		
		container.add(northElements, BorderLayout.NORTH);
		
		JTextArea description = createTaskDescription();
		container.add(description, BorderLayout.CENTER);
		
		JPanel southElements = new JPanel(new BorderLayout());
		southElements.setBorder(BorderFactory.createEmptyBorder(5, 0, 8, 0));
		
		SubjectButton subjectLbl = createTaskSubject();
		Label deadline = createTaskDeadline();

		JPanel leftWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		leftWrapper.add(subjectLbl);

		southElements.add(leftWrapper, BorderLayout.WEST);
		southElements.add(deadline, BorderLayout.EAST);
		
		container.add(southElements, BorderLayout.SOUTH);
		
		add(container, BorderLayout.CENTER);
	}
	
	public void activateCompletedTitle() {
		System.out.println("estilo completada");
		title = completedTitle;
	}
	
	public void activatePendingTitle() {
		System.out.println("estilo pendiente");
		title = taskTitle;
	}
	
	private void createCompletedTitle() {
		completedTitle = new Label("<html><body><s>" + task.getTitle() + "</s></body></html>", 14, true, AppColors.taskInfo);
		completedTitle.setAlignmentX(Component.CENTER_ALIGNMENT);   
		completedTitle.setMaximumSize(new Dimension(Integer.MAX_VALUE, completedTitle.getPreferredSize().height));
		completedTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
	}
	
	private void createTaskTitle() {
		taskTitle = new Label(task.getTitle(), 14, true);
	    taskTitle.setAlignmentX(Component.CENTER_ALIGNMENT);   
	    taskTitle.setMaximumSize(new Dimension(Integer.MAX_VALUE, taskTitle.getPreferredSize().height));
	    taskTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
	}
	
	private SubjectButton createTaskSubject() {
		SubjectButton taskSubject = new SubjectButton(subject.getName(), 12);
		taskSubject.setToolTipText(subject.getName());
		taskSubject.setBackground(subject.getSubColor());
		taskSubject.setForeground(subject.getColor());
		
		taskSubject.setModel(new javax.swing.DefaultButtonModel() {
	        @Override public boolean isPressed() { return false; }
	        @Override public boolean isRollover() { return false; }
	        @Override public boolean isArmed() { return false; }
	        @Override public void setPressed(boolean b) {}
	        @Override public void setRollover(boolean b) {}
	    });
				
		int width = taskSubject.getPreferredSize().width;
	    int height = taskSubject.getPreferredSize().height;
	    
	    width += 10;
	    
	    if (width > 140) {
	    	width = 140;
	    }
	    
	    Dimension size = new Dimension(width, height);
	    taskSubject.setPreferredSize(size);
	    taskSubject.setMaximumSize(size);
		return taskSubject;
	}
	
	private Label createTaskDeadline() {
		Label deadline = new Label(date, 13, true);
		deadline.setForeground(AppColors.subtitle);
		deadline.setAlignmentX(Component.CENTER_ALIGNMENT);
		deadline.setIcon(IconLoader.getIcon("/assets/img/date.svg", 10, 12));
		deadline.setIconTextGap(4);
	    return deadline;
	}
	
	private JTextArea createTaskDescription() {
	    JTextArea description = new JTextArea(task.getDescription());

	    Dimension size = new Dimension(300, 70);

	    description.setPreferredSize(size);
	    description.setMaximumSize(size);
	    description.setMinimumSize(size);
	    description.setFont(CreateFont.DEFAULT.deriveFont(13f));
	    description.setForeground(AppColors.subtitle);
	    description.setLineWrap(true);
	    description.setWrapStyleWord(true);
	    description.setEditable(false);
	    description.setOpaque(false);
	    description.setFocusable(false);
	    description.setMargin(new java.awt.Insets(0, 0, 0, 0));
	    description.setBorder(null);

	    return description;
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        RoundRectangle2D.Double roundRect = new RoundRectangle2D.Double(0, 0, width - 1, height - 1, cornerRadius, cornerRadius);

        g2.setColor(Color.WHITE);
        g2.fill(roundRect);

        g2.setColor(AppColors.iceGrey);
        g2.setStroke(new BasicStroke(1));
        g2.draw(roundRect);

        g2.dispose();
    }

}
