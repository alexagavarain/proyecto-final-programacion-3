package views;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import utils.AppColors;
import utils.CreateFont;
import utils.CustomComboBox;
import utils.IconLoader;
import utils.InputField;
import utils.Label;
import utils.PasswordField;
import utils.RoundedButton;
import utils.TextPrompt;
import models.Career;

public class RegisterView extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private InputField nameField;
	private InputField emailTextField;
	private PasswordField passwordField;
			
	private JComboBox<String> groups;
	private JComboBox<Career> careers;
	private JComboBox<Integer> semesters;
	private JComboBox<String> shifts;
	
	private Label emailError = createErrorLabel("");
	private Label passwordError = createErrorLabel("");
	private Label nameError = createErrorLabel("");
	private Label careerError;
	
	private RoundedButton registerButton;
	private JLabel backButton;
	
	private Dimension fieldSize;
	private JPanel mainContent;

	public JPanel getMainContent() {
		return mainContent;
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

	public JComboBox<Integer> getSemesters() {
		return semesters;
	}

	public Label getCareerError() {
		return careerError;
	}

	public JButton getRegisterButton() {
		return registerButton;
	}
	
	public String getName() {
		return nameField.getText();
	}
	
	public InputField getNameField() {
		return nameField;
	}
	
	public InputField getEmailTextField() {
		return emailTextField;
	}
	
	public PasswordField getPasswordField() {
		return passwordField;
	}
	
	public JComboBox<String> getGroups() {
		return groups;
	}
	
	public JComboBox<String> getShifts() {
		return shifts;
	}
	
	public JComboBox<Career> getCareers() {
		return careers;
	}
	
	public JLabel getBackButton() {
		return backButton;
	}
	
	public RegisterView() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("Registro");
		
		ImageIcon icon = (ImageIcon) IconLoader.getIcon("/assets/img/logo.svg", 64, 64);
        
        if (icon != null) {
            Image logo = icon.getImage();
            setIconImage(logo);
        }
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int option = JOptionPane.showConfirmDialog(
					RegisterView.this,
					"¿Seguro que deseas salir?",
					"Confirmar",
					JOptionPane.YES_NO_OPTION
				);

				if(option == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});
		
		initializeComponents();
	}
	
	public void initializeComponents() {
		mainContent = new JPanel(new BorderLayout());
		mainContent.setBackground(AppColors.background);
		mainContent.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
				
		mainContent.add(createHeader(), BorderLayout.NORTH);
		
		JPanel formPanel = new JPanel();
		formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
		formPanel.setOpaque(false);
		
		fieldSize = new Dimension(300, 32);
		
		createSpace(30, formPanel);
		
		createLabel(formPanel, "Nombre completo", 14, 183);
		nameField = createNameField();
		formPanel.add(nameField);
		formPanel.add(nameError);
		
		createSpace(6, formPanel);
		
		createLabel(formPanel, "Correo electrónico", 14, 180);
		emailTextField = createEmailField();
		formPanel.add(emailTextField);
		formPanel.add(emailError);

		createSpace(6, formPanel);
		
		createLabel(formPanel, "Contraseña", 14, 220);
		passwordField = createPasswordField();
		formPanel.add(passwordField);
		formPanel.add(passwordError);
				
		createSpace(6, formPanel);
		
		careerError = createErrorLabel("");
		createLabel(formPanel, "Carrera", 14, 246);		
		careers = createList(formPanel);
		formPanel.add(careerError);
		
		createLabel(formPanel, "Semestre", 14, 237);
		semesters = createList(formPanel);
		
		createLabel(formPanel, "Grupo", 14, 253);
		groups = createList(formPanel);
		
		createLabel(formPanel, "Turno", 14, 255);
		shifts = createList(formPanel);
	
		createSpace(20, formPanel);
		
		registerButton = createRegisterButton();
		formPanel.add(registerButton);
		
		mainContent.add(formPanel, BorderLayout.CENTER);
		
		add(mainContent);
	}
	
	public JPanel createHeader() {
	    JPanel headerPanel = new JPanel(new BorderLayout());
	    headerPanel.setBackground(AppColors.background);
	    headerPanel.setOpaque(true);
	    headerPanel.setPreferredSize(new Dimension(JFrame.MAXIMIZED_BOTH, 60));
	    
	    headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 20));
	    
	    headerPanel.add(createBackBtn(), BorderLayout.WEST);
	    headerPanel.add(createTitle(), BorderLayout.CENTER);
	    return headerPanel;
	}

	public JLabel createBackBtn() {
	    backButton = new JLabel("");
	    backButton.setIcon(IconLoader.getIcon("/assets/img/backArrow.svg", 40, 40));
	    
	    backButton.setPreferredSize(new Dimension(60, 60));
	    backButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

	    return backButton;
	}
	
	public Label createTitle() {
		Label title = new Label("Registro", 26, true);
		title.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 80));
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setVerticalAlignment(JLabel.CENTER);
		return title;
	}
	
	public <T> JComboBox<T> createList(JPanel targetPanel) {
	    JComboBox<T> list = new CustomComboBox<>();
	    list.setMaximumSize(new Dimension(300, 35));
	    list.setAlignmentX(CENTER_ALIGNMENT);
	    list.setBackground(Color.WHITE);

	    targetPanel.add(list);
	    createSpace(6, targetPanel);

	    return list;
	}

	public void createLabel(JPanel targetPanel, String containerName, float fontSize, int rightBorder) {
		JLabel label = new JLabel(containerName);
		label.setFont(CreateFont.DEFAULT.deriveFont(fontSize));		
		label.setBorder(BorderFactory.createEmptyBorder(0, 0, 3, rightBorder));
		label.setAlignmentX(CENTER_ALIGNMENT);
		targetPanel.add(label);
	}
	
	private PasswordField createPasswordField() {
		passwordField = new PasswordField();
		passwordField.setMaximumSize(fieldSize);
		passwordField.setAlignmentX(CENTER_ALIGNMENT);
		passwordField.setBorder(
			BorderFactory.createCompoundBorder(
			    BorderFactory.createLineBorder(AppColors.subtleAccent, 1, true),
			    BorderFactory.createEmptyBorder(5, 5, 5, 5)
			)
		);
		TextPrompt promptPassword = new TextPrompt("••••••••", passwordField);
		promptPassword.setForeground(AppColors.subtleAccent);
		return passwordField;
	}
	
	public void createSpace(int pixels, JPanel targetPanel) {
		targetPanel.add(Box.createVerticalStrut(pixels));
	}
	
	private RoundedButton createRegisterButton() {
		registerButton = new RoundedButton("Registrarme", 12);
		registerButton.setMaximumSize(new Dimension(300, 30));
		registerButton.setPreferredSize(new Dimension(300, 30));
		registerButton.setBackground(AppColors.primaryAccent);
		registerButton.setForeground(Color.WHITE);
		registerButton.setAlignmentX(CENTER_ALIGNMENT);
		registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		return registerButton;
	}
	
	public Label createErrorLabel(String text) {
		Label errorLabel = new Label(text, 12, true);
		errorLabel.setForeground(Color.RED);
		errorLabel.setMaximumSize(new Dimension(300, 20));
		errorLabel.setAlignmentX(CENTER_ALIGNMENT);
		return errorLabel;
	}
	
	public InputField createEmailField() {
		emailTextField = new InputField();
		emailTextField.setMaximumSize(fieldSize);
		TextPrompt promptEmail = new TextPrompt("estudiante@alu.uabcs.mx", emailTextField);
		promptEmail.setForeground(AppColors.subtleAccent);
		return emailTextField;
	}
	
	public InputField createNameField() {
		nameField = new InputField();
		nameField.setMaximumSize(fieldSize);
		TextPrompt namePrompt = new TextPrompt("Nombre(s) Apellido(s)", nameField);
		namePrompt.setForeground(AppColors.subtleAccent);
		return nameField;
	}
}