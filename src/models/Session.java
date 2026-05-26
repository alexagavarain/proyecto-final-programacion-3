package models;

import java.util.List;
import javax.swing.JFrame;

public class Session {
	
	private static JFrame mainFrame;

    private static User currentUser;
    
    private static List<Subject> currentSubjects;
    
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

	public static void logout() {
        currentUser = null;
        currentSubjects.clear();
    }
	    
}
