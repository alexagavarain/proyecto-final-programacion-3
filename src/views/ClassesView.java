package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import utils.AppColors;
import utils.InvisibleScrollBar;
import utils.Label;
import utils.WrapLayout;

public class ClassesView extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel classesPnl;
	private Label totalClasses;
	
	public ClassesView() { 
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(30, 20, 0, 20));
		createHeader();
		createClassesContainer();
	}
	
	public JPanel getClassesPnl() {
		return classesPnl;
	}
	
	public Label getTotalClasses() {
		return totalClasses;
	}
	
	
	private void createHeader() {
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
		titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
		
		Label title = new Label("Mis Clases", 23, false);
		totalClasses = new Label("", 14, true, AppColors.menuItem);
		
		title.setAlignmentX(LEFT_ALIGNMENT);
		totalClasses.setAlignmentX(LEFT_ALIGNMENT);
		
		titlePanel.add(title);
		titlePanel.add(Box.createVerticalStrut(5));
		titlePanel.add(totalClasses);
		
		add(titlePanel, BorderLayout.NORTH);
	}
	
	private void createClassesContainer() {
	    JPanel classesContainer = new JPanel(new GridBagLayout());
	    classesContainer.setOpaque(false);
	    
	    java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
	    gbc.gridx = 0;
	    gbc.gridy = java.awt.GridBagConstraints.RELATIVE; 
	    gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
	    gbc.weightx = 1.0; 
	    gbc.weighty = 0.0;
	    
	    WrapLayout classesLayout = new WrapLayout(FlowLayout.LEFT, 12, 12);
	    classesLayout.setAlignOnBaseline(false);
	    
	    classesPnl = new JPanel(classesLayout);    
	    classesPnl.setBorder(BorderFactory.createEmptyBorder(0, -12, 0, -12));
	    
	    classesContainer.add(classesPnl, gbc);
	    
	    gbc.weighty = 1.0;
	    classesContainer.add(new JPanel() {{ setOpaque(false); }}, gbc);
	    
	    JScrollPane scroll = new JScrollPane(classesContainer);
	    scroll.setBorder(null);
	    scroll.getVerticalScrollBar().setUI(new InvisibleScrollBar());
	    scroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
	    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    scroll.getVerticalScrollBar().setUnitIncrement(16);
	    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    scroll.setOpaque(false);
	    scroll.getViewport().setOpaque(false);

	    add(scroll, BorderLayout.CENTER);
	}


}
