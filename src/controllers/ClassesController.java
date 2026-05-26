package controllers;

import java.util.List;

import models.Session;
import models.Subject;
import models.User;
import repository.ClassesRepository;
import views.ClassesView;
import views.SubjectCard;

public class ClassesController {

    private ClassesView view;
    private ClassesRepository repo;
    private User currentUser;

    public ClassesController(ClassesView view) {
        this.view = view;

        repo = new ClassesRepository();
        currentUser = Session.getCurrentUser();

        loadClasses();
    }
    
    public void loadClasses() {
        List<Subject> classes = repo.getClasses(currentUser);
        
        for(Subject subject : classes) {
        		        	
            SubjectCard card = new SubjectCard(subject);

            view.getContainer().add(card);
        }

        view.revalidate();
        view.repaint();
    }
    


}
