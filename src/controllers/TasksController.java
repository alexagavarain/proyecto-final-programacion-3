package controllers;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.Box;

import models.Session;
import models.Task;
import models.User;
import repository.TaskRepository;
import views.TaskDialog;
import views.TaskCard;
import views.TasksView;

public class TasksController {

    private TasksView view;
    private TaskRepository repo;

    public TasksController(TasksView view) {

        this.view = view;

        repo = new TaskRepository();

        addBtnListener();
        loadTasks();
    }
    
    public void addBtnListener() {
    	view.getAddBtn().addActionListener(e -> {
			TaskDialog dialog = new TaskDialog(null, null);
			new TaskDialogController(dialog);
			dialog.setVisible(true);
		});
    }

    public void loadTasks() {
        User currentUser = Session.getCurrentUser();

        List<Task> tasks = repo.getTasks(currentUser);

        for(Task task : tasks) {
        	        	
        	task.setSubjectColor(new Color(121, 121, 121));

            TaskCard component = new TaskCard(task);
            
            component.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    TaskDialog dialog = new TaskDialog(null, task);
                    dialog.setVisible(true);
                }
            });

            view.getMonColumn().add(Box.createVerticalStrut(10));
            view.getMonColumn().add(component);
            
        }

        view.revalidate();
        view.repaint();
    }

}
