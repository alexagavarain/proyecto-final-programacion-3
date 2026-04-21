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
	
	public HomeController(HomeView view) {
		this.view = view;
		registerListeners();
		
	}
	
	public void registerListeners( ) {
		view.btnUsers.addActionListener(e -> {
			showUsers();
		});
		
		view.btnHome.addActionListener(e -> view.showView(HomeView.HOME));
	}
	
	private void showUsers() {
		UserController controller = new UserController(view.usersPanel);
		UserRepository repository = new UserRepository();
		
		try {
			List<User> users = repository.getUsers();
			
			UserTableModel model = new UserTableModel(users);
			
			view.usersPanel.setTableModel(model);
			
			view.showView(HomeView.USERS);
			
		}catch (IOException ex) {
			JOptionPane.showMessageDialog(view, ex.getMessage());
		}
		
	}
	private void handleClose() {
		/*int option = view.confirmExit();
		System.out.println(option);

		if (option == JOptionPane.YES_OPTION) {
			new LoginController(new LoginWindow().getLoginView());*/
			view.dispose();
		//}
	}
	
}
