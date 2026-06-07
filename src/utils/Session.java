package utils;

import java.util.List;
import javax.swing.JFrame;

import controllers.HomeController;
import models.GroupSubject;
import models.Subject;
import models.User;

public class Session {
	
	private static JFrame mainFrame;
	
	private static List<GroupSubject> currentSubjects;
	
	private static Subject currentSubjectSection;

    private static User currentUser;
    
    private static HomeController appController;
        
    public static JFrame getMainFrame() {
		return mainFrame;
	}

	public static void setMainFrame(JFrame main) {
		Session.mainFrame = main;
	}

	public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

	public static List<GroupSubject> getCurrentSubjects() {
		return currentSubjects;
	}

	public static void setCurrentSubjects(List<GroupSubject> currentSubjects) {
		Session.currentSubjects = currentSubjects;
	}

	public static Subject getCurrentSubjectSection() {
		return currentSubjectSection;
	}

	public static void setCurrentSubjectSection(Subject currentSubjectSection) {
		Session.currentSubjectSection = currentSubjectSection;
	}

	public static HomeController getAppController() {
		return appController;
	}

	public static void setAppController(HomeController appController) {
		Session.appController = appController;
	}

	public static void logout() {
        currentUser = null;
        currentSubjects.clear();
        appController = null;
        currentSubjectSection = null;
    }
	    
}
