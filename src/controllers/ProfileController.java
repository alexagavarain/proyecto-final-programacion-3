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
	
//	public void saveBtnListener() {
//		view.getEditBtn().addActionListener(e -> {
//			
//		});
//	}
	
	public void loadData() {
		view.getAvatar().setText(getInitials(user.getName()));
		view.getNameLbl().setText(user.getName());
		view.getCareerLbl().setText(user.getGroup().getCareer().getName());
		
		view.loadPrompt(view.getNameField(), user.getName());
		view.loadPrompt(view.getEmailField(), user.getEmail());
		view.loadPrompt(view.getCareerField(), user.getGroup().getCareer().getName());
		view.loadPrompt(view.getSemesterField(), user.getGroup().getSemester() + "");
		view.loadPrompt(view.getGroupField(), user.getGroup().getName());
		view.loadPrompt(view.getShiftField(), user.getGroup().getShift());

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

}
