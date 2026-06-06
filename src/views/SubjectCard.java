package views;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import models.GroupSubject;
import models.Subject;
import utils.AppColors;
import utils.CreateFont;
import utils.IconLoader;
import utils.Label;
import utils.RoundedButton;
import utils.SubjectButton;

public class SubjectCard extends JPanel{
	
	private GroupSubject groupSubject;
	private Subject subject;
	
	private int cornerRadius = 20;
	
	public SubjectCard(GroupSubject groupSubject) {
		this.groupSubject = groupSubject;
		this.subject = groupSubject.getSubjectProfessor().getSubject();
		
	    setLayout(new BorderLayout());
	    setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

	    Dimension size = new Dimension (320, 150);
		setPreferredSize(size);
		setMaximumSize(size);
        setMinimumSize(size);

        setOpaque(false);

	    northElements();
	    centerElements();
	}
	
	public void northElements() {
		JPanel northElements = new JPanel(new BorderLayout());
		northElements.setOpaque(false);
		northElements.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
		
		northElements.add(createSubject(), BorderLayout.WEST);
		northElements.add(createSemester(), BorderLayout.EAST);
		
		add(northElements, BorderLayout.NORTH);
	}
	
	public SubjectButton createSubject() {
		SubjectButton subjectName = new SubjectButton(subject.getName(), 12);
		subjectName.setToolTipText(subject.getName());
		subjectName.setFont(CreateFont.DEFAULT_BOLD);
		subjectName.setBackground(subject.getSubColor());
		subjectName.setForeground(subject.getColor());	 
		
		subjectName.setModel(new javax.swing.DefaultButtonModel() {
	        @Override public boolean isPressed() { return false; }
	        @Override public boolean isRollover() { return false; }
	        @Override public boolean isArmed() { return false; }
	        @Override public void setPressed(boolean b) {}
	        @Override public void setRollover(boolean b) {}
	    });
				
		int width = subjectName.getPreferredSize().width;
	    int height = subjectName.getPreferredSize().height;
	    
	    width += 10;
	    
	    if (width > 170) {
	    	width = 170;
	    }
	    
	    Dimension size = new Dimension(width, height);
	    subjectName.setPreferredSize(size);
	    subjectName.setMaximumSize(size);
		return subjectName;
	}
	
	public RoundedButton createSemester() {
		RoundedButton semester = new RoundedButton("Semestre " + groupSubject.getGroup().getSemester(), 15);
		semester.setBackground(new Color(241, 245, 249));
		semester.setForeground(AppColors.subtitle);	    
	    
	    Dimension size = new Dimension(100, 30);
	    semester.setPreferredSize(size);
	    semester.setMaximumSize(size);
		return semester;
	}
	
	public void centerElements() {
		JPanel centerElements = new JPanel();
		centerElements.setLayout(new BoxLayout(centerElements, BoxLayout.Y_AXIS));
		centerElements.setBorder(BorderFactory.createEmptyBorder(0, 5, 0 ,5));
		
		centerElements.add(createProfessor());
		centerElements.add(Box.createVerticalStrut(10));
		centerElements.add(createCareer());
		
		add(centerElements, BorderLayout.CENTER);
	}
	
	public Label createProfessor() {
		Label professor = new Label(groupSubject.getSubjectProfessor().getProfessor().getName(), 12, true, AppColors.subtitle);
		professor.setIcon(IconLoader.getIcon("/assets/img/profile.svg", 12, 12));
		professor.setIconTextGap(10);
		professor.setAlignmentX(LEFT_ALIGNMENT);
		return professor;
	}
	
	public Label createCareer() {
		Label career = new Label(groupSubject.getGroup().getCareer().getName(), 12, true, AppColors.subtitle);
		career.setIcon(IconLoader.getIcon("/assets/img/classes.svg", 12, 12));
		career.setIconTextGap(10);
		career.setAlignmentX(LEFT_ALIGNMENT);
		return career;
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        RoundRectangle2D.Double roundRect = new RoundRectangle2D.Double(0, 0, width - 1, height - 1, cornerRadius, cornerRadius);

        g2.setColor(Color.WHITE);
        g2.fill(roundRect);

        g2.setColor(AppColors.iceGrey);
        g2.setStroke(new BasicStroke(1));
        g2.draw(roundRect);

        g2.dispose();
    }
}
