package controllers;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import utils.Session;
import views.HomeView;
import views.LoginView;

public class HomeController {

	private HomeView view;
	
	public HomeController(HomeView view) {
		this.view = view;
		view.showView(HomeView.TASKS);
		view.activeBtnStyle(view.getTasksBtn());
		
		view.addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosing(WindowEvent e) {
	        	view.dispose();
//	        	handleClose();
	        }
	    });
		
		new TasksController(view.getTasksView());
		new ClassesController(view.getClassesView());
		new ProfileController(view.getProfileView());
		
		menuListeners();	
		pfpBtnListeners();
	}
	
	public void pfpBtnListeners() {
		view.getPfpContainer().addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent e) {
		        view.showView(HomeView.PROFILE);
		        view.activeBtnStyle(view.getProfileBtn());
		        //TODO profileController
		    }
		});
	}
	
	public void menuListeners( ) {
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
