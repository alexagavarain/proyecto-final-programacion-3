package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import models.Task;
import utils.AppColors;
import utils.CreateFont;

public class TaskCard extends JPanel{
	
	private JLabel subjectLbl;
	private JLabel taskTitle;
	private JLabel deadline;
	private JTextArea description;
	private Color subjectColor;
	
	public TaskCard(Task task) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		Dimension size = new Dimension (200, 170);
		setPreferredSize(size);
		setMaximumSize(size);
        setMinimumSize(size);
        
		setBackground(AppColors.gray);
		setVisible(true);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		String date = task.getDeadline().format(formatter);
		
		subjectColor = task.getSubjectColor();
		subjectHeader(task.getSubject().getName());
		taskTitle(task.getTitle());
		deadline(date);
		description(task.getDescription());
	}
	
	public void subjectHeader(String subject) {
		subjectLbl = new JLabel(subject, SwingConstants.LEFT);
	    subjectLbl.setFont(CreateFont.DEFAULT_BOLD.deriveFont(13f));
	    subjectLbl.setForeground(Color.WHITE);
	    subjectLbl.setOpaque(true);
	    subjectLbl.setBackground(subjectColor);

	    subjectLbl.setBorder(
	        BorderFactory.createCompoundBorder(
	            BorderFactory.createLineBorder(subjectColor),
	            BorderFactory.createEmptyBorder(5, 10, 5, 0)
	        )
	    );

	    subjectLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
	    subjectLbl.setMaximumSize(new Dimension(Integer.MAX_VALUE,
	    		subjectLbl.getPreferredSize().height));

	    add(subjectLbl);
	}
	
	public void taskTitle(String title) {
		taskTitle = new JLabel(title, SwingConstants.LEFT);
	    taskTitle.setFont(CreateFont.DEFAULT.deriveFont(13f));
	    taskTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
	    
	    taskTitle.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 0));
	    
	    taskTitle.setMaximumSize(new Dimension(Integer.MAX_VALUE,
	    		taskTitle.getPreferredSize().height));

	    add(taskTitle);
	}
	
	public void deadline(String date) {
		deadline = new JLabel(date, SwingConstants.LEFT);
		deadline.setFont(CreateFont.DEFAULT.deriveFont(13f));
		deadline.setForeground(AppColors.subtitle);
		deadline.setAlignmentX(Component.CENTER_ALIGNMENT);
	    
		deadline.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 0));
	    
		deadline.setMaximumSize(new Dimension(Integer.MAX_VALUE,
				deadline.getPreferredSize().height));

	    add(deadline);
	}
	
	public void description(String desc) {
	    description = new JTextArea(desc);

	    description.setFont(CreateFont.DEFAULT.deriveFont(13f));
	    description.setForeground(AppColors.subtitle);

	    description.setLineWrap(true);
	    description.setWrapStyleWord(true);
	    description.setEditable(false);
	    description.setOpaque(false);
	    description.setFocusable(false);

	    description.setBorder(
	        BorderFactory.createEmptyBorder(5, 10, 10, 10)
	    );

	    description.setMaximumSize(new Dimension(200, Integer.MAX_VALUE));

	    add(description);
	}

}
