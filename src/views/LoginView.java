package views;

import javax.swing.JFrame;
import javax.swing.JPanel;

import utils.AppColors;
import utils.CreateFont;
import utils.InputField;
import utils.Label;
import utils.TextPrompt;

import javax.swing.SwingConstants;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Image;

import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private InputField emailTextField;
	private JPasswordField passwordField;
	private Label emailError = createErrorLabel("");
	private Label passwordError = createErrorLabel("");

	public LoginView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("Inicio de sesión");
		
		initializeComponents();
	}
	
	private void initializeComponents() {
		contentPane = createMainPanel();
		setContentPane(contentPane);
		
		JPanel appLogo = createAppLogo("UniTasks", 30);
		contentPane.add(appLogo);
		createSpace(60, contentPane);
		
		JPanel descText = createAlignPanel();
		
		Label welcomeMessage = new Label ("Bienvenid@", 18, false);
		descText.add(welcomeMessage);
		createSpace(15, contentPane);
		
		descText.add(Box.createHorizontalStrut(100));
		
		Label message = new Label ("Inicia sesión para continuar", 14, true);
		message.setForeground(AppColors.subtitle);
		descText.add(message);
		
		contentPane.add(descText);
		createSpace(25, contentPane);
		
		createLabel(contentPane, "Correo electrónico", 14, 180);
		
 		emailTextField = createEmailField("estudiante@alu.uabcs.mx");
		contentPane.add(emailTextField);
		
		contentPane.add(emailError);
		
		createSpace(10, contentPane);
		
		createLabel(contentPane, "Contraseña", 14, 220);
		
		JPasswordField passwordField = createPasswordField();
	
		contentPane.add(passwordField);
		
		contentPane.add(passwordError);
		
		createSpace(6, contentPane);
		
		JPanel secondaryOptionPanel = createSecondaryOptionPanel();
		
		contentPane.add(secondaryOptionPanel);
		
		createSpace(25, contentPane);
		
		JButton loginButton = createButton(contentPane, "Iniciar sesión", 300, 30, AppColors.primaryAccent, Color.WHITE);

		loginButton.addActionListener(e -> login(contentPane));
		
		createSpace(10, contentPane);
		
		JButton registerButton = createRegisterBtn();
	}
	
	private JPanel createMainPanel() {
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		contentPane.setBorder(BorderFactory.createEmptyBorder(150, 0, 0, 0));
		contentPane.setBackground(AppColors.background);
		return contentPane;
	}
	
	private JPanel createAppLogo(String appName, int fontSize) {
		JPanel appLogo = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
		createLogoImage(appLogo);
		createTitle(appLogo, appName, fontSize);
		appLogo.setMaximumSize(new Dimension(330, 50));
		appLogo.setPreferredSize(new Dimension(330, 50));
		appLogo.setBackground(AppColors.background);
		return appLogo;
	}
	
	private void createLogoImage(JPanel container) {
		JLabel lblLogo = new JLabel();
		lblLogo.setBounds(145, 50, 100, 100);
		lblLogo.setIcon(uploadIcon("/assets/img/logo.png", 30, 30));
		container.add(lblLogo);
	}
	
	private JPanel createAlignPanel() {
		JPanel alignPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 16, 0));
		alignPanel.setMaximumSize(new Dimension(330, 50));
		alignPanel.setPreferredSize(new Dimension(330, 50));
		alignPanel.setBackground(AppColors.background);
		return alignPanel;
	}
	
	public void createLabel(JPanel container, String containerName, float fontSize, int rightBorder) {
		JLabel lbl = new JLabel(containerName);
		lbl.setFont(CreateFont.DEFAULT.deriveFont(fontSize));		
		lbl.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, rightBorder));
		lbl.setAlignmentX(CENTER_ALIGNMENT);
		container.add(lbl);
	}
	
	public void createTitle(JPanel container, String name, float fontSize) {
		JLabel appName = new JLabel(name);
		appName.setToolTipText("");
		appName.setFont(CreateFont.DEFAULT_BOLD.deriveFont(fontSize));		
		appName.setAlignmentX(CENTER_ALIGNMENT);
		container.add(appName);
	}
	
	private InputField createEmailField(String textPrompt) {
		InputField emailTextField = new InputField();
		TextPrompt promptEmail = new TextPrompt(textPrompt, emailTextField);
		promptEmail.setForeground(AppColors.subtitle);
		return emailTextField;
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
	
	private JCheckBox createRememberMeChk(String text) {
		JCheckBox chkRememberMe = new JCheckBox(text, true);
		return chkRememberMe;
	}
	
	private void createResetPasswordBtn(JPanel panel) {
		JButton resetPasswordButton = createButton(panel, "¿Olvidaste tu contraseña?", 160, 25, AppColors.background, AppColors.primaryAccent);
		resetPasswordButton.setHorizontalAlignment(SwingConstants.RIGHT);
		resetPasswordButton.setBorder(null);
		
		resetPasswordButton.addActionListener(e ->{
			new ResetPasswordView().setVisible(true);
			dispose();
		});
	}
	
	private JPanel createSecondaryOptionPanel() {
		JPanel secondaryOptionPanel = new JPanel();
		secondaryOptionPanel.setMaximumSize(new Dimension(330, 35));
		secondaryOptionPanel.setPreferredSize(new Dimension(330, 35));
		secondaryOptionPanel.setBackground(AppColors.background);
				
		JCheckBox chkRememberMe = createRememberMeChk("Recuérdame");
		secondaryOptionPanel.add(chkRememberMe);
		
		secondaryOptionPanel.add(Box.createHorizontalStrut(38));
		
		createResetPasswordBtn(secondaryOptionPanel);
		
		return secondaryOptionPanel;
	}
	
	private JButton createRegisterBtn() {
		JButton registerButton = createButton(contentPane, "¿No tienes una cuenta? Regístrate aquí", 300, 30, AppColors.background, AppColors.primaryAccent);
		
		registerButton.addActionListener(e ->{
			new RegisterView().setVisible(true);
			dispose();
		});
		return registerButton;
	}
	
	public Label createErrorLabel(String text) {
		Label errorLabel = new Label(text, 12, false);
		errorLabel.setForeground(Color.RED);
		errorLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 110));
		errorLabel.setAlignmentX(CENTER_ALIGNMENT);
		return errorLabel;
	}
	
	public void createSpace(int pixels, JPanel container) {
		container.add(Box.createVerticalStrut(pixels));
	}
	
	public JButton createButton(JPanel container, String name, int width, int length, Color background, Color foreground) {
		JButton button = new JButton(name);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setMaximumSize(new Dimension(width, length));
		button.setPreferredSize(new Dimension(width, length));
		button.setBackground(background);
		button.setForeground(foreground);
		button.setFocusPainted(false);  
		button.setBorderPainted(false);
		button.setAlignmentX(CENTER_ALIGNMENT);
		container.add(button);
		return button;
	}
	
	private ImageIcon uploadIcon(String ruta, int w, int h) {

		try {
			Image icono = ImageIO.read(getClass().getResource(ruta));
			icono = icono.getScaledInstance(w, h, Image.SCALE_SMOOTH);
			return new ImageIcon(icono);
		} catch(Exception ex) {
			System.out.println("Image not found");
		}
		
		return null;
	}
	
	
	private void resetErrorLabel(JLabel errorLabel) {
		errorLabel.setText("");
		errorLabel.setFont(CreateFont.DEFAULT.deriveFont(1f));		
	}
	
	private void login(JPanel panel) {
		resetErrorLabel(emailError);
		resetErrorLabel(passwordError);
		
		if (validateLogin()) {
			new HomeView().setVisible(true);;
			dispose();
		}
	};
	
	private boolean validateLogin() {
		if (!emailTextField.getText().trim().equals("") && !String.valueOf(passwordField.getPassword()).trim().isEmpty()) {
			return true;
		}
		
		if (emailTextField.getText().trim().equals("")){
			emailError.setText("El correo es obligatorio");
			emailError.setFont(CreateFont.DEFAULT.deriveFont(12f));		
		}
		
		if (String.valueOf(passwordField.getPassword()).trim().isEmpty()) {
			passwordError.setText("La contraseña es obligatoria");
			passwordError.setFont(CreateFont.DEFAULT.deriveFont(12f));		
		}
		
		return false;
	}
	
}