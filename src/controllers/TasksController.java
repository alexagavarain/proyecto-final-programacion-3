package controllers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;

import models.Subject;
import models.Task;
import models.User;
import repository.ClassesRepository;
import repository.TaskRepository;
import utils.Session;
import utils.SubjectButton;
import utils.SubjectColors;
import views.TaskDialog;
import views.TaskCard;
import views.TasksView;

public class TasksController {

    private TasksView view;
    private TaskRepository repo;
    private ClassesRepository classesRepo;
    private User user;

    public TasksController(TasksView view) {
        this.view = view;
        repo = new TaskRepository();
        classesRepo = new ClassesRepository();
        user = Session.getCurrentUser();

        addBtnListener();
        loadSubjectMenu();
        subjectBtnListeners();
        loadTasks(null);
    }
    
    public void addBtnListener() {
    	view.getAddBtn().addActionListener(e -> {
			TaskDialog dialog = new TaskDialog(Session.getMainFrame(), null);
			new TaskDialogController(dialog, this);
			dialog.setVisible(true);
		});
    }
    
    public void loadSubjectMenu() {
    	view.addAllTasksBtn();
    	
    	List<Subject> subjects = classesRepo.getClasses(user);
    	
    	for (Subject subject : subjects) { 
    		assignSubjectColors(subject);
    		view.addSubjectBtn(subject);
    	}
   
    	Session.setCurrentSubjects(subjects);

    }
    
    public void subjectBtnListeners() {
    	List<SubjectButton> subjectBtns = view.getSubjectBtns();
    	    	
    	for (SubjectButton btn : subjectBtns) {
    		btn.addActionListener(e -> {
    			loadTasks(btn.getSubject());
    		});
    	}
    }

    public void loadTasks(Subject subject) {
    	int pendingTasks = 0;
        int completedTasks = 0;
        
    	view.getPendingTasksPnl().removeAll();
    	view.getCompletedTasksPnl().removeAll();
    	
    	List<Task> tasks;

    	if (subject == null) {
            tasks = repo.getTasks(user);
    	} else {
            tasks = repo.getSubjectTasks(user, subject);
    	}

        for(Task task : tasks) {
        	        
            TaskCard component = new TaskCard(task);
                        
            MouseAdapter listener = new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    TaskDialog dialog = new TaskDialog(Session.getMainFrame(), task);
                    new TaskDialogController(dialog, TasksController.this);
                    dialog.setVisible(true);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    component.setCursor(
                        Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
                    );
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    component.setCursor(
                        Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR)
                    );
                }
            };

            addListenerToAll(component, listener);
            
            if(task.getStatus().equals("Pendiente")) {
            	pendingTasks++;
                view.getPendingTasksPnl().add(component);
            } else {
            	completedTasks++;
            	view.getCompletedTasksPnl().add(component);
            }    
        }

        user.setPendingTasks(pendingTasks);
        user.setCompletedTasks(completedTasks);
            
        view.getTasksCount().setText(user.getPendingTasks() + " pendientes · " + user.getCompletedTasks() + " completadas");
        view.revalidate();
        view.repaint();
    }
    
//    public void reloadTasks() {
//        view.getPendingTasksPnl().removeAll();
//        view.getCompletedTasksPnl().removeAll();
//
//        loadTasks();
//
//        view.getCompletedTasksPnl().revalidate();
//        view.getCompletedTasksPnl().repaint();
//        view.getPendingTasksPnl().revalidate();
//        view.getPendingTasksPnl().repaint();
//    }
    
    private void addListenerToAll(Component component, MouseListener listener) {
        component.addMouseListener(listener);

        if (component instanceof Container) {

            for (Component child : ((Container) component).getComponents()) {
                addListenerToAll(child, listener);
            }
        }
    }
    
    public void assignSubjectColors(Subject subject) {
		switch(subject.getName()) {
		case "Inglés IV":
			subject.setColor(SubjectColors.ENG_COLOR);
			subject.setSubColor(SubjectColors.ENG_SUBCOLOR);
			break;
		case "Estructura de Datos II":
			subject.setColor(SubjectColors.DS_COLOR);
			subject.setSubColor(SubjectColors.DS_SUBCOLOR);
			break;
		case "Interacción Humano-Computadora":
			subject.setColor(SubjectColors.HCI_COLOR);
			subject.setSubColor(SubjectColors.HCI_SUBCOLOR);
			break;
		case "Base de Datos I":
			subject.setColor(SubjectColors.DB_COLOR);
			subject.setSubColor(SubjectColors.DB_SUBCOLOR);
			break;
		case "Métodos Numéricos":
			subject.setColor(SubjectColors.NUM_COLOR);
			subject.setSubColor(SubjectColors.NUM_SUBCOLOR);
			break;
		case "Paradigmas de Programación":
			subject.setColor(SubjectColors.PDGM_COLOR);
			subject.setSubColor(SubjectColors.PDGM_SUBCOLOR);
			break;
		case "Programación III":
			subject.setColor(SubjectColors.PROG_COLOR);
			subject.setSubColor(SubjectColors.PROG_SUBCOLOR);
			break;
		}
	}

}
