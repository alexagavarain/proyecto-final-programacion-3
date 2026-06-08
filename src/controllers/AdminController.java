package controllers;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;
import utils.Session;
import views.AdminView;
import views.LoginView;

public class AdminController {

	private AdminView view;
	
	public AdminController(AdminView view) {
		this.view = view;
		
		view.addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosing(WindowEvent e) {
	        	handleClose();
	        }
	    });
		
	    logoutListener();
	}
	
	@SuppressWarnings("unused")
	private void logoutListener() {
		view.getLogoutBtn().addActionListener(e -> {
			int option = JOptionPane.showConfirmDialog(
	                view,
	                "¿Seguro que deseas cerrar sesión?",
	                "Cerrar sesión",
	                JOptionPane.YES_NO_OPTION
	            );

	            if (option == JOptionPane.YES_OPTION) {
	            	LoginView login = new LoginView();
	            	new LoginController(login);
	            	Session.logout();
	            	Session.setMainFrame(login);
	                view.dispose();
	            	login.setVisible(true);
	            }
		});
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
