package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import utils.CreateFont;

public class ClassesView extends JPanel{
	
	private int col = 0;
	private int row = 0;
	private final int MAX_COLS = 4;
	
	private JPanel container;
	
	public ClassesView() { 
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(30, 20, 0, 20));
		createTitle("Clases", 23);
		createPanel();
	}
	
	public void createTitle(String name, float fontSize) {
		JLabel title = new JLabel(name);
	    title.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
		title.setFont(CreateFont.DEFAULT_BOLD.deriveFont(fontSize));		
		title.setAlignmentX(LEFT_ALIGNMENT);
		add(title, BorderLayout.NORTH);
	}
	
	public void createPanel() {
		container = new JPanel();
		container = new JPanel(new java.awt.GridBagLayout());
		
		JScrollPane scroll = new JScrollPane(container);

	    scroll.setBorder(null);
	    scroll.getVerticalScrollBar().setUnitIncrement(16);

	    add(scroll, BorderLayout.CENTER);
	}
	
	public void addCard(SubjectCard card) {

	    GridBagConstraints gbc = new GridBagConstraints();

	    gbc.gridx = col;
	    gbc.gridy = row;

	    gbc.insets = new java.awt.Insets(10, 10, 0, 10);

	    gbc.anchor = GridBagConstraints.NORTHWEST;

	    gbc.weightx = 1;
	    gbc.weighty = 1;

	    gbc.fill = GridBagConstraints.NONE;

	    card.setPreferredSize(new Dimension(200, 120));
	    card.setMinimumSize(new Dimension(200, 120));
	    card.setMaximumSize(new Dimension(200, 120));

	    container.add(card, gbc);

	    col++;

	    if (col >= MAX_COLS) {
	        col = 0;
	        row++;
	    }

	    container.revalidate();
	    container.repaint();
	}

	public JPanel getContainer() {
		return container;
	}

	public void setContainer(JPanel container) {
		this.container = container;
	}
	
	

}
