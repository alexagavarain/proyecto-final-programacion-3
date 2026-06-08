package controllers;

import java.util.List;

import models.GroupSubject;
import utils.Session;
import views.ClassesView;
import views.SubjectCard;

public class ClassesController {

    private ClassesView view;

    public ClassesController(ClassesView view) {
        this.view = view;

        loadClasses();
    }
    
    public void reloadClassesData() {
        loadClasses(); 
        view.revalidate();
        view.repaint();
    }
    
    public void loadClasses() {
        List<GroupSubject> groupSubjects = Session.getCurrentSubjects();
        
        view.getClassesPnl().removeAll();
        
        int totalClasses = 0;
        
        for(GroupSubject groupSubject : groupSubjects) {
        	
        	totalClasses++;
        		        	
            SubjectCard card = new SubjectCard(groupSubject);
       
            view.getClassesPnl().add(card);
        }
        
        view.getTotalClasses().setText(totalClasses + " clases este semestre");
        
        view.getClassesPnl().revalidate();
        view.getClassesPnl().repaint();

        view.revalidate();
        view.repaint();
    }
    


}
