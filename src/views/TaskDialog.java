package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;

import models.Session;
import models.Subject;
import models.Task;
import utils.AppColors;
import utils.CreateFont;

public class TaskDialog extends JDialog {
	
	private Task task;
	private JTextArea description;	
	private JTextField title;
	private JComboBox<Subject> subjectList;
	private List<Subject> subjects;
	private JButton saveButton;
	private JSpinner dateSpinner;
	
	public TaskDialog(JFrame parent, Task task) {
		super(parent, true); 
		this.task = task;
		String title = task == null ? "Agregar tarea" : "Editar tarea";

		setSize(400, 550);
    	setTitle(title);
		setLocationRelativeTo(parent); 
		setLayout(new BorderLayout()); 
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); 
		add(createTitlePanel(title, 20), BorderLayout.NORTH); 
		add(createFormPanel(), BorderLayout.CENTER);
	}
	
	private JPanel createTitlePanel(String title, float fontSize) { 
		JPanel panel = new JPanel(); 
		JLabel titleLbl = new JLabel(title);		
		titleLbl.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		titleLbl.setFont(CreateFont.DEFAULT_BOLD.deriveFont(fontSize));	
		titleLbl.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(titleLbl); 
		return panel; 
	} 
	
	private JScrollPane createFormPanel() {
		JPanel panel = new JPanel(); 
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); 
		panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 15, 20)); 
		
		JScrollPane scroll = new JScrollPane(panel); 
		scroll.setBorder(null); 
		scroll.setHorizontalScrollBar(null); 
		
		Dimension fieldSize = new Dimension(300, 30); 
		Dimension descFieldSize = new Dimension(300, 100); 
		
		title = new JTextField(); 
		title.setMaximumSize(fieldSize);
		
		description = new JTextArea();

		description.setLineWrap(true);
		description.setWrapStyleWord(true);

		description.setBorder(
		    BorderFactory.createEmptyBorder(5, 5, 5, 5)
		);

		JScrollPane descriptionScroll =
		    new JScrollPane(description);

		descriptionScroll.setPreferredSize(descFieldSize);
		descriptionScroll.setMaximumSize(descFieldSize);
		descriptionScroll.setMinimumSize(descFieldSize);
		
		subjects = Session.getCurrentSubjects();
		subjectList = new JComboBox<>();

	    for (Subject subject : subjects) {
	        subjectList.addItem(subject);
	    }
	    subjectList.setMaximumSize(fieldSize);
	    
	    dateSpinner = new JSpinner(new SpinnerDateModel());
	    
	    JSpinner.DateEditor editor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy HH:mm");

	    dateSpinner.setEditor(editor);
	    dateSpinner.setMaximumSize(fieldSize);
	    
		panel.add(createField("Titulo:", title)); 
		panel.add(createField("Descripcion:", descriptionScroll));
		panel.add(createField("Materia:", subjectList));
		panel.add(createField("Fecha de entrega:", dateSpinner));
		panel.add((Box.createVerticalStrut(30)));
		panel.add(createSaveButton());
			 
		return scroll; 
	}
	
	private JPanel createField(String labelText, Component field) { 
		Dimension fieldSize = new Dimension(300, 30); 
		
		JPanel panel = new JPanel(); 
		panel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0)); 
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); 
		panel.setAlignmentX(Component.CENTER_ALIGNMENT); 
		
		JLabel label = new JLabel(labelText); 
		label.setMaximumSize(fieldSize); 
		label.setHorizontalAlignment(SwingConstants.LEFT); 
		label.setAlignmentX(Component.CENTER_ALIGNMENT); 
		
		panel.add(label); 
		panel.add(field); 
		
		return panel; 
	}
	
	private JButton createSaveButton() {
		saveButton = new JButton("Guardar");
		saveButton.setMaximumSize(new Dimension(300, 30));
		saveButton.setPreferredSize(new Dimension(300, 30));
		saveButton.setBackground(AppColors.primaryAccent);
		saveButton.setForeground(Color.WHITE);
		saveButton.setAlignmentX(CENTER_ALIGNMENT);
		return saveButton;		
	}

	public JButton getSaveButton() {
		return saveButton;
	}

	public void setSaveButton(JButton saveButton) {
		this.saveButton = saveButton;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public JTextArea getDescription() {
		return description;
	}

	public void setDescription(JTextArea description) {
		this.description = description;
	}

	public JTextField getTitleField() {
		return title;
	}

	public void setTitle(JTextField title) {
		this.title = title;
	}

	public JComboBox<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(JComboBox<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	public JSpinner getDateSpinner() {
		return dateSpinner;
	}

	public void setDateSpinner(JSpinner dateSpinner) {
		this.dateSpinner = dateSpinner;
	}
	
	
	
	
}
