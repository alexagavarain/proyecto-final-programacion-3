package controllers;

import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import models.User;
import repository.UserRepository;
import tablemodel.UserTableModel;
import views.HomeView;

public class HomeController {

	private HomeView view;
	private UserController userController;
	
	public HomeController(HomeView view) {
		this.view = view;
		view.showView(HomeView.TASKS);
		registerListeners();
		
	}
	
	public void registerListeners( ) {
		view.tasksBtn.addActionListener(e -> {
			view.showView(HomeView.TASKS);
		});
		
		view.classesBtn.addActionListener(e -> {
			view.showView(HomeView.CLASSES);
		});
		
		view.profileBtn.addActionListener(e -> {
			view.showView(HomeView.PROFILE);
		});
	}
	
//	private void showUsers() {	
//		if(userController == null) {
//			userController = new UserController(view.usersPanel);
//		}
//			
//		userController.loadUsers();
//		
//		view.showView(HomeView.USERS);
////		updateMenuState(HomeView.USERS);
//	}
	
//	private void updateMenuState(String viewName) {
//		view.btnUsers.setEnabled(!viewName.equals(HomeView.USERS));
//		view.btnHome.setEnabled(!viewName.equals(HomeView.HOME));
//	}
	
	
	
	
	private void handleClose() {
		/*int option = view.confirmExit();
		System.out.println(option);

		if (option == JOptionPane.YES_OPTION) {
			new LoginController(new LoginWindow().getLoginView());*/
			view.dispose();
		//}
	}
	
}
