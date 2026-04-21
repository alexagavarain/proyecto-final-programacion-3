package controllers;

import repository.UserRepository;
import tablemodel.UserTableModel;
import views.RegisterView;
import views.UsersView;

public class UserController {

	private UsersView view;
	private UserRepository repo;
	private UserTableModel model;
	
	public UserController(UsersView view) {
		this.view = view;
		repo = new UserRepository();
		
		view.getBtnAdd().addActionListener(e -> {
			RegisterView registro = new RegisterView();
		    new RegisterController(registro);
		    registro.setVisible(true);
		});
	}
	
}


