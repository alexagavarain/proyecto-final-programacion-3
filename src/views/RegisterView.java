package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

import controllers.RegisterController;
import models.Career;
import models.Subject;
import utils.AppColors;
import utils.CreateFont;
import utils.InputField;
import utils.Label;
import utils.Session;
import utils.TextPrompt;

public class RegisterView extends JFrame{
	
	private InputField nameField;
	private InputField emailTextField;
	private JPasswordField passwordField;
			
	private JComboBox<String> groups;
	private JComboBox<Career> careers;
	private JComboBox<Integer> semesters;
	private JComboBox<String> shifts;
	
	private Label emailError = createErrorLabel("");
	private Label passwordError = createErrorLabel("");
	private Label nameError = createErrorLabel("");
	
	private Label careerError;
	
	private JButton registerButton;
	private JButton backButton;
	
	private Dimension fieldSize;
	
	private JPanel contentPane;

	
	public JPanel getContentPane() {
		return contentPane;
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
	
	public JPasswordField getPasswordField() {
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
	
	public JButton getBackButton() {
		return backButton;
	}
	
	
	public RegisterView() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("Registro");
		
		addWindowListener(new WindowAdapter() {
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
		contentPane = createMainPanel();
		setContentPane(contentPane);
		
		createSpace(60, contentPane);
		
		showAppName(contentPane, "Registro", 30);
		createSpace(40, contentPane);
		
		fieldSize = new Dimension (300, 40);
		
		createLabel(contentPane, "Nombre", 14, 245);
		nameField = createNameField();
		contentPane.add(nameField);
		contentPane.add(nameError);
		
		createSpace(2, contentPane);
		
		createLabel(contentPane, "Correo electrónico", 14, 180);
		
		emailTextField = createEmailField();
		contentPane.add(emailTextField);
		contentPane.add(emailError);

		createSpace(2, contentPane);
		createLabel(contentPane, "Contraseña", 14, 220);
		passwordField = createPasswordField();
		contentPane.add(passwordField);
		contentPane.add(passwordError);
				
		
		createSpace(2, contentPane);
		
		careerError = createErrorLabel("");

		createLabel(contentPane, "Carrera", 14, 246);		
		careers = createList(contentPane);
		contentPane.add(careerError);
		createLabel(contentPane, "Semestre", 14, 237);
		semesters = createList(contentPane);
		createLabel(contentPane, "Grupo", 14, 253);
		groups = createList(contentPane);
		createLabel(contentPane, "Turno", 14, 255);
		shifts = createList(contentPane);
	
		createSpace(20, contentPane);
		registerButton = createRegisterButton();
		createSpace(10, contentPane);
		
		backButton = createBackButton();
		
		createSpace(50, contentPane);
		
	}
	
	public <T> JComboBox<T> createList(JPanel container) {
	    JComboBox<T> list = new JComboBox<>();
	    list.setMaximumSize(new Dimension(300, 60));
	    list.setAlignmentX(CENTER_ALIGNMENT);
	    list.setBackground(Color.WHITE);

	    container.add(list);
	    createSpace(5, container);

	    return list;
	}

	private JPanel createMainPanel() {
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		contentPane.setBackground(AppColors.background);
		return contentPane;
	}

	public void createLabel(JPanel container, String containerName, float fontSize, int rightBorder) {
		JLabel label = new JLabel(containerName);
		label.setFont(CreateFont.DEFAULT.deriveFont(fontSize));		
		label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, rightBorder));
		label.setAlignmentX(CENTER_ALIGNMENT);
		container.add(label);
	}
	
	private JPasswordField createPasswordField() {
		passwordField = new JPasswordField();
		passwordField.setMaximumSize(new Dimension(300, 28));
		passwordField.setAlignmentX(CENTER_ALIGNMENT);
		passwordField.setBorder(
			    BorderFactory.createCompoundBorder(
			        BorderFactory.createLineBorder(AppColors.subtleAccent, 1, true),
			        BorderFactory.createEmptyBorder(5, 5, 5, 5)
			    )
			);
		TextPrompt promptPassword = new TextPrompt("•••••••••••", passwordField);
		promptPassword.setForeground(AppColors.subtitle);
		return passwordField;
	}
	
	public void showText(JPanel container, String text, int fontSize) {
		JLabel loginText = new JLabel(text);
		loginText.setToolTipText("");
		loginText.setAlignmentX(CENTER_ALIGNMENT);
		container.add(loginText);
	}
	
	public void showAppName(JPanel container, String name, float fontSize) {
		JLabel appName = new JLabel(name);
		appName.setToolTipText("");
		appName.setFont(CreateFont.DEFAULT_BOLD.deriveFont(fontSize));		
		appName.setAlignmentX(CENTER_ALIGNMENT);
		container.add(appName);
	}
	
	public void createSpace(int pixels, JPanel container) {
		container.add(Box.createVerticalStrut(pixels));
	}
	
	public JButton createButton(JPanel container, String name, int width, int length) {
		JButton button = new JButton(name);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setMaximumSize(new Dimension(width, length));
		button.setBackground(Color.BLACK);
		button.setForeground(Color.WHITE);
		button.setFocusPainted(false);  
		button.setBorderPainted(false);
		button.setAlignmentX(CENTER_ALIGNMENT);
		container.add(button);
		return button;
	}
	
	private JButton createRegisterButton() {
		JButton registerButton = createButton(contentPane, "Registrarme", 300, 50, AppColors.primaryAccent, Color.WHITE);
		registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		registerButton.setContentAreaFilled(true);
		
		registerButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				registerButton.setBackground(AppColors.background);
				registerButton.setForeground(AppColors.primaryAccent);
				registerButton.setBorderPainted(true);
				registerButton.setBorder(BorderFactory.createLineBorder(AppColors.primaryAccent, 1));
			}
			
			public void mouseExited(MouseEvent e) {
				registerButton.setBackground(AppColors.primaryAccent);
				registerButton.setForeground(Color.WHITE);
				registerButton.setBorderPainted(false);
			}
		});
		
		return registerButton;
	}
	
	private JButton createBackButton() {
		JButton btnReturn = createButton(contentPane, "Regresar", 100, 30, AppColors.primaryAccent, Color.WHITE);
		
		btnReturn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btnReturn.setBackground(AppColors.background);
				btnReturn.setForeground(AppColors.primaryAccent);
				btnReturn.setBorderPainted(true);
				btnReturn.setBorder(BorderFactory.createLineBorder(AppColors.primaryAccent, 1));
			}
			
			public void mouseExited(MouseEvent e) {
				btnReturn.setBackground(AppColors.primaryAccent);
				btnReturn.setForeground(Color.WHITE);
				btnReturn.setBorderPainted(false);
			}
		});
		
		return btnReturn;
	}
		
	public JButton createButton(JPanel container, String name, int width, int length, Color background, Color foreground) {
		JButton button = new JButton(name);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setMaximumSize(new Dimension(width, length));
		button.setBackground(background);
		button.setForeground(foreground);
		button.setFocusPainted(false);  
		button.setBorderPainted(false);
		button.setAlignmentX(CENTER_ALIGNMENT);
		container.add(button);
		return button;
	}
	
	public Label createErrorLabel(String text) {
		Label errorLabel = new Label(text, 12, false);
		errorLabel.setForeground(Color.RED);
		errorLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		errorLabel.setMaximumSize(new Dimension(300,20));
		errorLabel.setAlignmentX(CENTER_ALIGNMENT);
		return errorLabel;
	}
	
	
	public JPanel createSecondaryPanel() {
		JPanel secondaryPanel = new JPanel();
		secondaryPanel.setOpaque(false);
		secondaryPanel.setMaximumSize(new Dimension(300, 80));
		secondaryPanel.setLayout(new GridLayout(1, 2, 20, 0));
		return secondaryPanel;
	}
	
	public InputField createEmailField() {
		emailTextField = new InputField();
		emailTextField.setMaximumSize(fieldSize);
		TextPrompt promptEmail = new TextPrompt("estudiante@alu.uabcs.mx", emailTextField);
		promptEmail.setForeground(AppColors.subtitle);
		return emailTextField;
	}
	
	public InputField createNameField() {
		nameField = new InputField();
		nameField.setMaximumSize(fieldSize);
		TextPrompt namePrompt = new TextPrompt("Nombre Apellido", nameField);
		namePrompt.setForeground(AppColors.subtitle);
		return nameField;
	}
	
}
