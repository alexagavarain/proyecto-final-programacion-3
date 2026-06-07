package controllers;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import models.User;
import utils.Session;
import views.HomeView;
import views.LoginView;

public class HomeController {

	private TasksController tasksController;
	private ClassesController classesController;
	private ProfileController profileController;
	
	private User user;
	private HomeView view;
	
	public HomeController(HomeView view) {
		this.view = view;
		this.user = Session.getCurrentUser();
		
		view.showView(HomeView.TASKS);
		view.activeBtnStyle(view.getTasksBtn());
		
		view.addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosing(WindowEvent e) {
	        	handleClose();
	        	view.dispose();
	        }
	    });
		
		tasksController = new TasksController(view.getTasksView());
		classesController = new ClassesController(view.getClassesView());
		profileController = new ProfileController(view.getProfileView());
		
		loadUserSummary();
		menuListeners();	
		pfpBtnListeners();
	}
	
	public void reloadApp() {
		this.user = Session.getCurrentUser();
		loadUserSummary();
		
		if (this.tasksController != null) {
	        this.tasksController.reloadTasksData();
	    }
	    
	    if (this.classesController != null) {
	        this.classesController.reloadClassesData();
	    }
	    
	    if (this.profileController != null) {
	        this.profileController.reloadProfileData();
	    }
	    
	    view.revalidate();
	    view.repaint();
	}
	
	private void pfpBtnListeners() {
		view.getPfpContainer().addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent e) {
		        view.showView(HomeView.PROFILE);
		        view.activeBtnStyle(view.getProfileBtn());
		    }
		});
	}
	
	private void menuListeners( ) {
		view.getTasksBtn().addActionListener(e -> {
			view.showView(HomeView.TASKS);
			view.activeBtnStyle(view.getTasksBtn());
		});
		
		view.getClassesBtn().addActionListener(e -> {
			view.showView(HomeView.CLASSES);
			view.activeBtnStyle(view.getClassesBtn());

		});
		
		view.getProfileBtn().addActionListener(e -> {
			view.showView(HomeView.PROFILE);
			view.activeBtnStyle(view.getProfileBtn());
		});
		
		view.getLogoutBtn().addActionListener(e -> {
			logout();		
		});
	}
	
	private void logout() {
		int option = JOptionPane.showConfirmDialog(
                view,
                "¿Seguro que deseas cerrar sesión?",
                "Cerrar sesión",
                JOptionPane.YES_NO_OPTION
            );

            if (option == JOptionPane.YES_OPTION) {
            	LoginView login = new LoginView();
            	new LoginController(login);
            	Session.setCurrentUser(null);
                view.dispose();
            	login.setVisible(true);
            }
	}
	
	public void loadUserSummary() {
		this.user = Session.getCurrentUser();
		view.getUsername().setText(user.getName());
		view.getUserCareer().setText(user.getGroup().getCareer().getAbb() + " · Sem. " + Session.getCurrentUser().getGroup().getSemester());
		view.getAvatar().setText(getInitials(user.getName()));
		
		view.revalidate();
		view.repaint();
	}
	
	private String getInitials(String name) {
        if (name == null || name.isBlank()) return "?";
        String[] parts = name.trim().split("\\s+");
        String ini = String.valueOf(parts[0].charAt(0));
        
        if (parts.length > 1) {
        	ini += parts[1].charAt(0);
        }
        return ini.toUpperCase();
    }
	
	private void handleClose() {
		int option = JOptionPane.showConfirmDialog(
                view,
                "¿Seguro que deseas salir?",
                "Cerrar aplicación",
                JOptionPane.YES_NO_OPTION
            );

            if (option == JOptionPane.YES_OPTION) {
                view.dispose();
            }
	}
	
}
