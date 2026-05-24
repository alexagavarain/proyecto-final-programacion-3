package controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import models.User;
import models.Subject;
import views.TaskDialog;

public class TaskDialogController {
	
	private TaskDialog view;
	private User user;
	
	public TaskDialogController() {
		
	}
	
	private void saveListener() {
		view.getSaveButton().addActionListener(e -> save());
	}
	
	private void save() {
		JTextField titleField = view.getTitleField();
		JTextArea descriptionField = view.getDescription();
		JComboBox<Subject> subjectsList = view.getSubjectList();
		JSpinner dateSpinner = view.getDateSpinner();
		
		String title = titleField.getText();
		String description = descriptionField.getText();
		Subject subject = (Subject) subjectsList.getSelectedItem();
		Date selectedDate = (Date) dateSpinner.getValue();
		LocalDateTime dateTime = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		
		
		
	}

}
