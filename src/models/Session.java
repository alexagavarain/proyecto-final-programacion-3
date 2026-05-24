package models;

import java.util.List;

public class Session {

	    private static User currentUser;
	    
	    private static List<Subject> currentSubjects;
	    
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
