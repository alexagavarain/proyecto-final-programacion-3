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

    public TasksController(TasksView view) {
        this.view = view;
        repo = new TaskRepository();

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

            view.getTaskContainer().add(component);
            
        }

        view.revalidate();
        view.repaint();
    }
    
    public void reloadTasks() {
        view.getTaskContainer().removeAll();

        loadTasks();

        view.getTaskContainer().revalidate();
        view.getTaskContainer().repaint();
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
