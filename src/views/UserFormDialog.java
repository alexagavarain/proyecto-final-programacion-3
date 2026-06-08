package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension; 
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout; 
import javax.swing.JButton; 
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog; 
import javax.swing.JFrame; 
import javax.swing.JPanel;
import javax.swing.JPasswordField; 
import javax.swing.JTextField; 

import models.Career;
import models.User;
import utils.AppColors;
import utils.CustomComboBox;
import utils.InputField;
import utils.Label;
import utils.PasswordField;
import utils.RoundedButton; 

public class UserFormDialog extends JDialog {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField nameField;
	private JTextField emailField;
	private JPasswordField passwordField;
	
	private JComboBox<String> groups;
	private JComboBox<Career> careers;
	private JComboBox<Integer> semesters;
	private JComboBox<String> shifts;
	
	private JButton saveButton; 
	
	private User user;
	
	private Dimension fieldSize;
	
	private Label emailError = createErrorLabel("");
	private Label passwordError = createErrorLabel("");
	private Label nameError = createErrorLabel("");
	private Label careerError = createErrorLabel("");
	
	public UserFormDialog(JFrame parent, User user) { 
		super(parent, true); 
		this.user = user; 
		
		String title = user == null ? "Agregar usuario" : "Editar perfil";
		
		setSize(400, 650);
    	setTitle(title);
		setLocationRelativeTo(parent); 
		setLayout(new BorderLayout()); 
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); 
			
		add(createTitlePanel(title, 18), BorderLayout.NORTH); 
		add(createFormPanel(), BorderLayout.CENTER); 
		add(createSaveButton(), BorderLayout.SOUTH); 		
	} 
	
	public JTextField getNameField() {
		return nameField;
	}

	public JTextField getEmailField() {
		return emailField;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public JComboBox<String> getGroups() {
		return groups;
	}

	public JComboBox<Career> getCareers() {
		return careers;
	}

	public JComboBox<Integer> getSemesters() {
		return semesters;
	}

	public JComboBox<String> getShifts() {
		return shifts;
	}

	public JButton getSaveButton() {
		return saveButton;
	}

	public User getUser() {
		return user;
	}

	public Label getEmailError() {
		return emailError;
	}

	public Label getPasswordError() {
		return passwordError;
	}

	public Label getNameError() {
		return nameError;
	}

	public Label getCareerError() {
		return careerError;
	}
	
	private JPanel createTitlePanel(String title, float fontSize) { 
		JPanel panel = new JPanel(); 
		Label titleLbl = new Label(title, fontSize, true);		
		titleLbl.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		titleLbl.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(titleLbl); 
		return panel; 
	} 
	
	private JPanel createFormPanel() { 
		JPanel contentPane = new JPanel(); 
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS)); 
		contentPane.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25)); 
		
		fieldSize = new Dimension(340, 32); 

		nameField = new InputField();
		setupComponent(nameField);
		contentPane.add(createLabel("Nombre completo"));
		contentPane.add(nameField);
		contentPane.add(nameError);
		
		contentPane.add(Box.createVerticalStrut(4));
				
		emailField = new InputField();
		setupComponent(emailField);
		contentPane.add(createLabel("Correo electrónico"));
		contentPane.add(emailField);
		contentPane.add(emailError);
		
		contentPane.add(Box.createVerticalStrut(4));

		passwordField = new PasswordField();
		setupComponent(passwordField);
		contentPane.add(createLabel("Contraseña"));
		contentPane.add(passwordField);
		contentPane.add(passwordError);
				
		contentPane.add(Box.createVerticalStrut(4));
				
		careerError = createErrorLabel("");

		careers = createList();
		contentPane.add(createLabel("Carrera"));
		contentPane.add(careers);
		contentPane.add(careerError);
		
		semesters = createList();
		contentPane.add(createLabel("Semestre"));
		contentPane.add(semesters);
		contentPane.add(Box.createVerticalStrut(12));
		
		groups = createList();
		contentPane.add(createLabel("Grupo"));
		contentPane.add(groups);
		contentPane.add(Box.createVerticalStrut(12));
		
		shifts = createList();
		contentPane.add(createLabel("Turno"));
		contentPane.add(shifts);
			 
		return contentPane; 
	}
	
	private void setupComponent(Component c) {
		c.setMaximumSize(fieldSize);
		c.setPreferredSize(fieldSize);
		c.setMinimumSize(fieldSize);
		((JComponent) c).setAlignmentX(Component.LEFT_ALIGNMENT);
	}
	
	public <T> JComboBox<T> createList() {
	    JComboBox<T> list = new CustomComboBox<>();
	    setupComponent(list);
	    list.setBackground(Color.WHITE);
	    return list;
	}
	
	public Label createLabel(String containerName) {
		Label label = new Label(containerName, 14, true);
		label.setAlignmentX(LEFT_ALIGNMENT);
		return label;
	}
	
	public Label createErrorLabel(String text) {
		Label errorLabel = new Label("", 12, true);
		errorLabel.setForeground(Color.RED);
		errorLabel.setBackground(Color.BLUE);		
		errorLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		errorLabel.setMaximumSize(new Dimension(340, 20));
		errorLabel.setAlignmentX(LEFT_ALIGNMENT);
		return errorLabel;
	}
	
	private JPanel createSaveButton() {
		JPanel btnContainer = new JPanel();
		btnContainer.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
		
		saveButton = new RoundedButton("Guardar", 12);
		saveButton.setMaximumSize(new Dimension(340, 30));
		saveButton.setPreferredSize(new Dimension(340, 30));
		saveButton.setBackground(AppColors.primaryAccent);
		saveButton.setForeground(Color.WHITE);
		saveButton.setAlignmentX(CENTER_ALIGNMENT);
		saveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		btnContainer.add(saveButton);
		return btnContainer;
	}
}