package controllers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import models.GroupSubject;
import models.Subject;
import models.SubjectProfessor;
import models.Task;
import models.User;
import repository.ClassesRepository;
import repository.TaskRepository;
import utils.IconLoader;
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
    	
    	List<GroupSubject> groupSubjects = new ArrayList<>();
    	
    	for (Subject subject : subjects) { 
    		assignSubjectColors(subject);
    		view.addSubjectBtn(subject);
    		
    		SubjectProfessor subjectProfessor = classesRepo.getSubjectProfessor(subject);
    		GroupSubject groupSubject = classesRepo.getGroupSubject(user, subjectProfessor);
    		
    		groupSubjects.add(groupSubject);
    	}

    	Session.setCurrentSubjects(groupSubjects);
    }
    
    public void subjectBtnListeners() {
    	List<SubjectButton> subjectBtns = view.getSubjectBtns();
    	    	
    	for (SubjectButton btn : subjectBtns) {
    		btn.addActionListener(e -> {
    			loadTasks(btn.getSubject());
    			view.activeSubjectBtn(btn);
    			Session.setCurrentSubjectSection(btn.getSubject());
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
            tasks = repo.getSubjectTasks(user, classesRepo.getSubjectProfessor(subject));
    	}
    	
    	if (tasks == null || tasks.isEmpty()) {
    		view.showView("EMPTY_TASKS");
    	} else {
    		view.showView("SHOW_TASKS");
    	}

        for(Task task : tasks) {
        	
        	assignSubjectColors(task.getGroupSubject().getSubjectProfessor().getSubject());
        	        
            TaskCard taskCard = new TaskCard(task);
                  
            if (!task.isCompleted()) {
                taskCard.getCheckBtn().setIcon(IconLoader.getIcon("/assets/img/checkbox.svg", 22, 22));
            } else {
                taskCard.getCheckBtn().setIcon(IconLoader.getIcon("/assets/img/checkedCheckbox.svg", 22, 22));
            }
            
            editBtnListener(taskCard, task);     
            checkListeners(taskCard, task);       
            
            if(task.getStatus().equals("Pendiente")) {
            	pendingTasks++;
                view.getPendingTasksPnl().add(taskCard);
            } else {
            	completedTasks++;
            	view.getCompletedTasksPnl().add(taskCard);
            }    
        }

        user.setPendingTasks(pendingTasks);
        user.setCompletedTasks(completedTasks);
            
        view.getTasksCount().setText(user.getPendingTasks() + " pendientes · " + user.getCompletedTasks() + " completadas");
        
        view.getPendingLbl().setText("PENDIENTES (" + pendingTasks + ")");
        view.getCompletedLbl().setText("COMPLETADAS (" + completedTasks + ")");
        
        view.getPendingTasksPnl().revalidate();
        view.getPendingTasksPnl().repaint();
        
        view.getCompletedTasksPnl().revalidate();
        view.getCompletedTasksPnl().repaint();
        
        view.revalidate();
        view.repaint();
    }
    
    public void reloadTasks(Subject subject) {
        view.getPendingTasksPnl().removeAll();
        view.getCompletedTasksPnl().removeAll();

        loadTasks(subject);

        view.getCompletedTasksPnl().revalidate();
        view.getCompletedTasksPnl().repaint();
        
        view.getPendingTasksPnl().revalidate();
        view.getPendingTasksPnl().repaint();
    }

    public void editBtnListener(TaskCard taskCard, Task task) {
    	taskCard.getEditBtn().addActionListener(e -> {
        	TaskDialog dialog = new TaskDialog(Session.getMainFrame(), task);
        	new TaskDialogController(dialog, TasksController.this);
        	dialog.setVisible(true);
        });
    }
    
    public void checkListeners(TaskCard taskCard, Task task) {
    	taskCard.getCheckBtn().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            	if(!task.isCompleted()) {
            		taskCard.getCheckBtn().setIcon(IconLoader.getIcon("/assets/img/hoverCheckbox.svg", 22, 22));
            	}
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	if (!task.isCompleted()) {
                taskCard.getCheckBtn().setIcon(IconLoader.getIcon("/assets/img/checkbox.svg", 22, 22));
            	}
            }
        });
        
        taskCard.getCheckBtn().addActionListener(e -> {
        	if (!task.isCompleted()) {
        		task.setStatus("Completada");
        		repo.updateTask(task, user);
        	} else {
        		task.setStatus("Pendiente");
        		repo.updateTask(task, user);
        	}
        	reloadTasks(Session.getCurrentSubjectSection());
        });
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
