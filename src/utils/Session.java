package utils;

import java.util.List;
import javax.swing.JFrame;

import models.Subject;
import models.User;

public class Session {
	
	private static JFrame mainFrame;
	
	private static List<Subject> currentSubjects;

    private static User currentUser;
        
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

	public static List<Subject> getCurrentSubjects() {
		return currentSubjects;
	}

	public static void setCurrentSubjects(List<Subject> currentSubjects) {
		Session.currentSubjects = currentSubjects;
	}

	public static void logout() {
        currentUser = null;
        currentSubjects.clear();
    }
	    
}
