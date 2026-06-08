package controllers;

import models.User;
import utils.Session;
import views.ProfileView;
import views.UserFormDialog;

public class ProfileController {
	
	private ProfileView view;
	private User user;
	
	public ProfileController(ProfileView view) {
		this.view = view;
		this.user = Session.getCurrentUser();
		
		loadData();
		editBtnListener();
	}
	
	public void reloadProfileData() {
		this.user = Session.getCurrentUser();	
		loadData();
		view.revalidate();
		view.repaint();
	}
	
	@SuppressWarnings("unused")
	public void editBtnListener() {
		view.getEditBtn().addActionListener(e -> {
			UserFormDialog dialog = new UserFormDialog(Session.getMainFrame(), user);
			new UserFormDialogController(dialog);
			dialog.setVisible(true);
		});
	}
	
	public void loadData() {	
		view.getAvatar().setText(getInitials(user.getName()));
		view.getNameLbl().setText(user.getName());
		view.getCareerLbl().setText(user.getGroup().getCareer().getName());
		
		view.getNamePrompt().setText(user.getName());
		view.getEmailPrompt().setText(user.getEmail());
		view.getCareerPrompt().setText(user.getGroup().getCareer().getName());
		view.getSemesterPrompt().setText(user.getGroup().getSemester() + "");
		view.getGroupPrompt().setText(user.getGroup().getName());
		view.getShiftPrompt().setText(user.getGroup().getShift());
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