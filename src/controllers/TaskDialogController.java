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
import utils.Session;
import models.GroupSubject;
import models.Subject;
import models.SubjectProfessor;
import models.Task;
import views.TaskDialog;

public class TaskDialogController {
	
	private ClassesRepository classesRepo;
	private TaskRepository taskRepo;
	private TasksController tasksController;
	private TaskDialog view;
	private User user;
	private Task task;
		
	public TaskDialogController(TaskDialog view, TasksController tasksController) {
		this.view = view;
		this.tasksController = tasksController;
		
		classesRepo = new ClassesRepository();
		taskRepo = new TaskRepository();
		user = Session.getCurrentUser();
		task = view.getTask();
		saveListener();
		deleteListener();
		loadSubjects();
		assignListeners();
		loadTaskData();
	}
	
	@SuppressWarnings("unused")
	private void saveListener() {
		view.getSaveButton().addActionListener(e -> {
			if (validate()) {
				if (save()) {
					JOptionPane.showMessageDialog(view, "Se ha guardado la tarea");
		            view.dispose();
		            tasksController.reloadTasks(Session.getCurrentSubjectSection());
				} else {
					JOptionPane.showMessageDialog(view, "No se pudo guardar la tarea");
				}
			} else {
			}
		});
	}
	
	@SuppressWarnings("unused")
	private void deleteListener() {
		view.getDeleteBtn().addActionListener(e -> {
			int option = JOptionPane.showConfirmDialog(
	                view,
	                "¿Seguro que deseas eliminar?",
	                "Eliminar tarea",
	                JOptionPane.YES_NO_OPTION
	            );

	            if (option == JOptionPane.YES_OPTION) {
	            	if (taskRepo.deleteTask(task)) {
						JOptionPane.showMessageDialog(view, "Se ha eliminado la tarea");
			            view.dispose();
			            tasksController.reloadTasks(Session.getCurrentSubjectSection());
					} else {
						JOptionPane.showMessageDialog(view, "No se pudo eliminar la tarea");
					}
	            }
		});
	}
	
	private void loadSubjects() {
		List<Subject> subjects= classesRepo.getClasses(user);
		
		Subject placeholder = new Subject(0, "Selecciona una materia");

        view.getSubjectList().addItem(placeholder);

	    for (Subject subject : subjects) {
	    	
	        view.getSubjectList().addItem(subject);

	    	if (Session.getCurrentSubjectSection() != null && Session.getCurrentSubjectSection().getName().equals(subject.getName())) {
	    		view.getSubjectList().setSelectedItem(subject);
	    	}
	    }
	}
	
	private void loadTaskData() {
		if (task != null) {
			view.getTitleField().setText(task.getTitle());
			view.getDescription().setText(task.getDescription());
			view.getSubjectList().setSelectedItem(task.getGroupSubject().getSubjectProfessor().getSubject());			
			
			LocalDateTime fecha = task.getDeadline();
			Date date = Date.from(
			    fecha.atZone(ZoneId.systemDefault()).toInstant()
			);
			view.getDateSpinner().setValue(date);
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
				
		if (task == null) {
			Task newTask = new Task(
				title,
				description,
				dateTime,
				"Pendiente",
				user,
				groupSubject
				);
			return taskRepo.saveTask(newTask, user);
		} else {
			task.setTitle(title);
			task.setDescription(description);
			task.setDeadline(dateTime);
			task.setSubject(groupSubject);
			return taskRepo.updateTask(task, user);
		}
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
	
	@SuppressWarnings("unused")
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
	
//	private void handleClose() {
//		int option = JOptionPane.showConfirmDialog(
//                view,
//                "¿Seguro que deseas cancelar?",
//                "Cancelar tarea",
//                JOptionPane.YES_NO_OPTION
//            );
//
//            if (option == JOptionPane.YES_OPTION) {
//                view.dispose();
//            }
//	}
//	

}
