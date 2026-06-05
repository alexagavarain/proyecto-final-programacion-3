package controllers;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import models.Session;
import models.User;
import repository.UserRepository;
import tablemodel.UserTableModel;
import views.HomeView;
import views.LoginView;
import views.TaskDialog;

public class HomeController {

	private HomeView view;
	
	public HomeController(HomeView view) {
		this.view = view;
		view.showView(HomeView.TASKS);
		
		view.addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosing(WindowEvent e) {
	        	view.dispose();
//	        	handleClose();
	        }
	    });
		
		new TasksController(view.getTasksView());
		new ClassesController(view.getClassesView());
		
		menuListeners();	
	}
	
	public void menuListeners( ) {
		view.getTasksBtn().addActionListener(e -> {
			view.showView(HomeView.TASKS);
		});
		
		view.getClassesBtn().addActionListener(e -> {
			view.showView(HomeView.CLASSES);

		});
		
		view.getProfileBtn().addActionListener(e -> {
			view.showView(HomeView.PROFILE);
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
