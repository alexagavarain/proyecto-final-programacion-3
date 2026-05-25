package views;

import java.awt.Component;
import java.awt.Dimension;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import models.Professor;
import models.Subject;
import utils.AppColors;
import utils.CreateFont;

public class SubjectCard extends JPanel{
	
	public SubjectCard(Subject subject) {
	    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

	    Dimension size = new Dimension (200, 170);
		setPreferredSize(size);
		setMaximumSize(size);
        setMinimumSize(size);

	    setBackground(AppColors.gray);

	    createNameLbl(subject.getName());
	    createProfessorLbl(subject.getProfessor().getName());
	}
	
	public void createNameLbl(String title) {
		JLabel nameLbl = new JLabel(title, SwingConstants.LEFT);
	    nameLbl.setFont(CreateFont.DEFAULT_BOLD.deriveFont(14f));
	    nameLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
	    nameLbl.setBorder(BorderFactory.createEmptyBorder(20, 5, 5, 5));
	    add(nameLbl);
	}
	
	public void createProfessorLbl(String title) {
		JLabel professorLbl = new JLabel(title, SwingConstants.LEFT);
	    professorLbl.setFont(CreateFont.DEFAULT.deriveFont(13f));
	    professorLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
	    professorLbl.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 0));

	    add(professorLbl);
	}

}
