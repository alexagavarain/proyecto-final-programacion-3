package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
import utils.Label;

public class TaskDialog extends JDialog {
	
	private Task task;
	
	private JTextField title;
	private JTextArea description;	
	private JComboBox<Subject> subjectList;
	private JSpinner dateSpinner;

	private JButton saveButton;
	
	private Label titleError;
	private Label subjectError;
	
	public TaskDialog(JFrame parent, Task task) {
		super(parent, true); 
		this.task = task;
		System.out.println(task == null);
		String title = task == null ? "Agregar tarea" : "Editar tarea";

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		setSize(400, 600);
    	setTitle(title);
		setLocationRelativeTo(parent); 
		setLayout(new BorderLayout()); 
		add(createTitlePanel(title, 20), BorderLayout.NORTH); 
		add(createFormPanel(), BorderLayout.CENTER);
	}
	
	public JButton getSaveButton() {
		return saveButton;
	}

	public Task getTask() {
		return task;
	}

	public JTextArea getDescription() {
		return description;
	}

	public JTextField getTitleField() {
		return title;
	}

	public JComboBox<Subject> getSubjectList() {
		return subjectList;
	}

	public JSpinner getDateSpinner() {
		return dateSpinner;
	}
	
	public Label getTitleError() {
		return titleError;
	}

	public Label getSubjectError() {
		return subjectError;
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

		titleError = createErrorLabel();
		
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
		
		subjectList = new JComboBox<>();
	    subjectList.setMaximumSize(fieldSize);
	    
		subjectError = createErrorLabel();
	    
	    dateSpinner = new JSpinner(new SpinnerDateModel());
	    
	    JSpinner.DateEditor editor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy HH:mm");

	    dateSpinner.setEditor(editor);
	    dateSpinner.setMaximumSize(fieldSize);
	    	    
		panel.add(createField("Titulo:", title)); 
		panel.add(titleError);
		panel.add(createField("Descripcion:", descriptionScroll));
		panel.add(createField("Materia:", subjectList));
		panel.add(subjectError);
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
	
	public Label createErrorLabel() {
		Label errorLabel = new Label("", 12, false);
		errorLabel.setForeground(Color.RED);
		errorLabel.setMaximumSize(new Dimension(300, 16));
		errorLabel.setAlignmentX(CENTER_ALIGNMENT);
		return errorLabel;
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

	
}
