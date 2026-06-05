package controllers;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
	private TasksController tasksController;
	private TaskDialog view;
	private User user;
	private Task task;
		
	public TaskDialogController(TaskDialog view, TasksController tasksController) {
		this.view = view;
		this.tasksController = tasksController;
		
		view.addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosing(WindowEvent e) {
	        	handleClose();
	        	System.out.println("aqui");
	        }
	    });
		
		classesRepo = new ClassesRepository();
		taskRepo = new TaskRepository();
		user = Session.getCurrentUser();
		task = view.getTask();
		saveListener();
		loadSubjects();
		assignListeners();
		loadTaskData();
	}
	
	private void saveListener() {
		view.getSaveButton().addActionListener(e -> {
			if (validate()) {
				if (save()) {
					JOptionPane.showMessageDialog(view, "Se ha guardado la tarea");
		            view.dispose();
		            tasksController.reloadTasks();
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
	
	private void loadTaskData() {
		if (task != null) {
			System.out.println("load");
			view.getTitleField().setText(task.getTitle());
			view.getDescription().setText(task.getDescription());
			view.getSubjectList().setSelectedItem(task.getGroupSubject().getSubject());
			view.getStatusList().setSelectedItem(task.getStatus());
			
			
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
		JComboBox<String> statusList = view.getStatusList();
		
		String title = titleField.getText();
		String description = descriptionField.getText();
		Subject subject = (Subject) subjectsList.getSelectedItem();
		Date selectedDate = (Date) dateSpinner.getValue();
		LocalDateTime dateTime = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		String status = (String) statusList.getSelectedItem();
		
		SubjectProfessor subjectProfessor = classesRepo.getSubjectProfessor(subject);
		GroupSubject groupSubject = classesRepo.getGroupSubject(user, subjectProfessor);
				
		if (task == null) {
			Task newTask = new Task(
				title,
				description,
				dateTime,
				status,
				user,
				groupSubject
				);
			return taskRepo.saveTask(newTask, user);
		} else {
			task.setTitle(title);
			task.setDescription(description);
			task.setDeadline(dateTime);
			task.setSubject(groupSubject);
			task.setStatus(status);
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
	
	private void handleClose() {
		int option = JOptionPane.showConfirmDialog(
                view,
                "¿Seguro que deseas cancelar?",
                "Cancelar tarea",
                JOptionPane.YES_NO_OPTION
            );

            if (option == JOptionPane.YES_OPTION) {
                view.dispose();
            }
	}
	

}
