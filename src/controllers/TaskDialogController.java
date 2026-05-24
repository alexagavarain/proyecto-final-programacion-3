package controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import models.User;
import repository.ClassesRepository;
import repository.TaskRepository;
import models.Career;
import models.GroupSubject;
import models.Session;
import models.Subject;
import models.SubjectProfessor;
import models.Task;
import views.HomeView;
import views.TaskDialog;

public class TaskDialogController {
	
	private ClassesRepository classesRepo;
	private TaskRepository taskRepo;
	private TaskDialog view;
	private User user;
		
	public TaskDialogController(TaskDialog view) {
		this.view = view;
		classesRepo = new ClassesRepository();
		taskRepo = new TaskRepository();
		user = Session.getCurrentUser();
		saveListener();
		loadSubjects();
		assignListeners();
	}
	
	private void saveListener() {
		view.getSaveButton().addActionListener(e -> {
			if (validate()) {
				if (save()) {
					JOptionPane.showMessageDialog(view, "Se ha guardado la tarea");
		            view.dispose();
				} else {
					JOptionPane.showMessageDialog(view, "No se pudo guardar la tarea");
				}
			} else {
			}
		});
	}
	
	private void loadSubjects() {
		List<Subject> subjects= classesRepo.getClasses(user);
		
		Subject placeholder = new Subject(0, "Selecciona una materia");

        view.getSubjectList().addItem(placeholder);

	    for (Subject subject : subjects) {
	        view.getSubjectList().addItem(subject);
	    }
	}
	
	
	private boolean save() {
		JTextField titleField = view.getTitleField();
		JTextArea descriptionField = view.getDescription();
		JComboBox<Subject> subjectsList = view.getSubjectList();
		JSpinner dateSpinner = view.getDateSpinner();
		
		String title = titleField.getText();
		String description = descriptionField.getText();
		Subject subject = (Subject) subjectsList.getSelectedItem();
		Date selectedDate = (Date) dateSpinner.getValue();
		LocalDateTime dateTime = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		
		SubjectProfessor subjectProfessor = classesRepo.getSubjectProfessor(subject);
		GroupSubject groupSubject = classesRepo.getGroupSubject(user, subjectProfessor);
		
		System.out.println("");
		Task task = new Task(
				title,
				description,
				dateTime,
				"Pendiente",
				user,
				groupSubject
				);
		
		return taskRepo.saveTask(task, user);
	}
	
	private boolean validate() {
		view.getTitleError().setText("");
		view.getSubjectError().setText("");
		
		boolean valid = true;
		
		
		if (!validateTitle() | !validateSubject()) {
			valid = false;
		}
		
		return valid;
	}
	
	private boolean validateTitle() {
		if (view.getTitleField().getText().trim().isEmpty()) {
	        view.getTitleError().setText("El título es obligatorio");
	        return false;
	    }
        view.getTitleError().setText("");
	    return true;
	}
	
	private boolean validateSubject() {
		if (view.getSubjectList().getSelectedIndex() == 0) {
			view.getSubjectError().setText("Seleccione una materia");
			return false;
		}
		view.getSubjectError().setText("");
		return true;
	}
	
	private void assignListeners() {
		 view.getTitleField().getDocument().addDocumentListener(new DocumentListener() {
		        public void insertUpdate(DocumentEvent e) {
		            validateTitle();
		        }

		        public void removeUpdate(DocumentEvent e) {
		            validateTitle();
		        }

		        public void changedUpdate(DocumentEvent e) {
		            validateTitle();
		        }
		    });
		 
		 view.getSubjectList().addActionListener(e -> {
	                validateSubject();
		 });
	}
	

}
