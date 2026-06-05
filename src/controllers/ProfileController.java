package controllers;

import models.User;
import utils.Session;
import views.ProfileView;

public class ProfileController {
	
	private ProfileView view;
	private User user;
	
	public ProfileController(ProfileView view) {
		this.view = view;
		this.user = Session.getCurrentUser();
		loadData();
	}
	
	public void saveBtnListener() {
		view.getBtnSave().addActionListener(e -> {
			
		});
	}
	
	public void loadData() {
		view.getTxtName().setText(user.getName());
		view.getTxtEmail().setText(user.getEmail());
		//TODO: terminar con los otros campos (traidos del registro)
	}

}
