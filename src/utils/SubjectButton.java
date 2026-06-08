package utils;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JButton;

import models.Subject;

public class SubjectButton extends JButton {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Subject subject;
	private int radius;

    public SubjectButton(Subject subject, int radius) {
        super(subject.getName());
        this.subject = subject;
        this.radius = radius;
        
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
    }
    
    public SubjectButton(String text, int radius) {
        super(text);
        this.subject = null;
        this.radius = radius;
        
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
    }

    public Subject getSubject() {
		return subject;
	}

	@Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (getModel().isArmed()) {
            g2.setColor(getBackground().brighter());
        } else {
            g2.setColor(getBackground());
        }

        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

        super.paintComponent(g2);
        g2.dispose();
    }
}
