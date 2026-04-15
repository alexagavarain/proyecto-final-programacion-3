package controllers;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import models.User;
import repository.UserRepository;
import views.LoginView;
import views.HomeView;

public class HomeController {

	private HomeView view;
	
	public HomeController(HomeView view) {
		this.view = view;
		registerListeners();
		
	}
	
	public void registerListeners( ) {
		view.btnUsers.addActionListener(e -> {
			UserRepository repository = new UserRepository();
			try {
				List<User> users = repository.getUsers();
				
				for(User user : users) {
					System.out.println(user);
					System.out.println("---------------");
				}
				
				System.out.println("Entro aqui");
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(view, ex.getMessage());
			}
		});
	}
	
	
}
