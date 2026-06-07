package controllers;

import javax.swing.JOptionPane;

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
	
	public void editBtnListener() {
		view.getEditBtn().addActionListener(e -> {
			System.out.println("click bitch");
			UserFormDialog dialog = new UserFormDialog(null, user);
			new UserFormDialogController(dialog);
			dialog.setVisible(true);
		});
	}
	
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
	
//	public void openForm(User user) {
//		UserFormDialog dialog = new UserFormDialog(null, user);
//		new UserFormDialogController(dialog);
//		dialog.setVisible(true);
//		
//		if(dialog.isSaved()) {
//			User savedUser = dialog.getUser();
//			
//			try {
//				if(user == null) {
//					repo.save(savedUser);
//				}else {
//					int row = view.getSelectedRow();
//					repo.update(row, savedUser);
//				}
//				
//				loadUsers();
//			}catch(Exception e) {
//				e.printStackTrace();
//				JOptionPane.showMessageDialog(view, e.getMessage());
//			}
//			
//		}
//	}

}
