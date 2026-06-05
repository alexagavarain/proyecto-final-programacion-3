package controllers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
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
    private User user;

    public TasksController(TasksView view) {
        this.view = view;
        repo = new TaskRepository();
        user = Session.getCurrentUser();

        addBtnListener();
        loadTasks();
    }
    
    public void addBtnListener() {
    	view.getAddBtn().addActionListener(e -> {
			TaskDialog dialog = new TaskDialog(Session.getMainFrame(), null);
			new TaskDialogController(dialog, this);
			dialog.setVisible(true);
		});
    }

    public void loadTasks() {
        User currentUser = Session.getCurrentUser();

        List<Task> tasks = repo.getTasks(currentUser);
        
        int pendingTasks = 0;
        int completedTasks = 0;

        for(Task task : tasks) {
        	        	
        	task.setSubjectColor(new Color(121, 121, 121));

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
        
        System.out.println(pendingTasks + ", " + completedTasks);
        
        view.getTasksCount().setText(user.getPendingTasks() + " pendientes · " + user.getCompletedTasks() + " completadas");
        
        view.revalidate();
        view.repaint();
    }
    
    public void reloadTasks() {
        view.getPendingTasksPnl().removeAll();
        view.getCompletedTasksPnl().removeAll();

        loadTasks();

        view.getCompletedTasksPnl().revalidate();
        view.getCompletedTasksPnl().repaint();
        view.getPendingTasksPnl().revalidate();
        view.getPendingTasksPnl().repaint();
    }
    
    private void addListenerToAll(Component component, MouseListener listener) {
        component.addMouseListener(listener);

        if (component instanceof Container) {

            for (Component child : ((Container) component).getComponents()) {
                addListenerToAll(child, listener);
            }
        }
    }

}
