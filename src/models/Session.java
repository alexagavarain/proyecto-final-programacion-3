package models;

import java.util.List;

import views.HomeView;

public class Session {
	
	private static HomeView mainFrame;

    private static User currentUser;
    
    private static List<Subject> currentSubjects;
    
    public static HomeView getMainFrame() {
		return mainFrame;
	}

	public static void setMainFrame(HomeView main) {
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
