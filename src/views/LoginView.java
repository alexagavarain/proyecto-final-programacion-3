package views;

import javax.swing.JFrame;
import javax.swing.JPanel;

import utils.AppColors;
import utils.CreateFont;
import utils.IconLoader;
import utils.InputField;
import utils.Label;
import utils.TextPrompt;

import javax.swing.SwingConstants;
import javax.swing.text.JTextComponent;


import controllers.RegisterController;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.JLabel;

import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class LoginView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private InputField emailTextField;
	private JPasswordField passwordField;
	private Label emailError;
	private Label passwordError;
	private JButton loginButton;

	public LoginView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("Inicio de sesión");
		
		ImageIcon icon = (ImageIcon) IconLoader.getIcon("/assets/img/logo.svg", 64, 64);
        
        if (icon != null) {
            Image logo = icon.getImage();
            setIconImage(logo);
        }
		
		initializeComponents();
	}
	
	public JPanel getContentPane() {
		return contentPane;
	}

	public InputField getEmailTextField() {
		return emailTextField;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public Label getEmailError() {
		return emailError;
	}

	public Label getPasswordError() {
		return passwordError;
	}

	public JButton getLoginButton() {
		return loginButton;
	}

	private void initializeComponents() {
		contentPane = createMainPanel();
		setContentPane(contentPane);
		
		JPanel appLogo = createAppLogo("UniTasks", 28);
		contentPane.add(appLogo);
		createSpace(50, contentPane);
		
		JPanel descText = createAlignPanel();
		
		Label welcomeMessage = new Label ("Bienvenid@", 16, false);
		descText.add(welcomeMessage);
		createSpace(15, contentPane);
		
		descText.add(Box.createHorizontalStrut(100));
		
		Label message = new Label ("Inicia sesión para continuar", 13, true);
		message.setForeground(AppColors.subtitle);
		descText.add(message);
		
		contentPane.add(descText);
		createSpace(25, contentPane);
		
		emailError = createErrorLabel("", 174);
		passwordError = createErrorLabel("", 147);
		
		createLoginFields(contentPane);
		
		createSpace(12, contentPane);
		
		JPanel secondaryOptionPanel = createSecondaryOptionPanel();
		
		contentPane.add(secondaryOptionPanel);
		
		createSpace(25, contentPane);
		
		loginButton = createLoginButton();
		
		createSpace(10, contentPane);
		
		createRegisterBtn();
	}
	
	private JPanel createMainPanel() {
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		contentPane.setBorder(BorderFactory.createEmptyBorder(150, 0, 0, 0));
		contentPane.setBackground(AppColors.background);
		return contentPane;
	}
	
	private JPanel createAppLogo(String appName, int fontSize) {
		JPanel appLogo = new JPanel(new FlowLayout(FlowLayout.LEFT, 13, 0));
		createLogoImage(appLogo);
		createTitle(appLogo, appName, fontSize);
		appLogo.setMaximumSize(new Dimension(330, 50));
		appLogo.setPreferredSize(new Dimension(330, 50));
		appLogo.setBackground(AppColors.background);
		return appLogo;
	}
	
	private void createLogoImage(JPanel container) {
	    JLabel lblLogo = new JLabel();
	    lblLogo.setIcon(IconLoader.getIcon("/assets/img/logo.svg", 45, 45));  
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
		lbl.setBorder(BorderFactory.createEmptyBorder(0, 0, 3, rightBorder));
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
	
	private JPanel createLoginFields(JPanel container) {
		createLabel(container, "Correo electrónico", 12, 193);
 		emailTextField = createEmailField("estudiante@alu.uabcs.mx");
		container.add(emailTextField);
		container.add(emailError);
		
		createSpace(10, container);
		
		createLabel(container, "Contraseña", 12, 233);
		passwordField = createPasswordField();
		container.add(passwordField);
		container.add(passwordError);
		
		fieldFocus(passwordField);
		fieldFocus(emailTextField);
		
		return container;
	}
	
	private JCheckBox createRememberMeChk(String text) {
		JCheckBox chkRememberMe = new JCheckBox(text, true);
		return chkRememberMe;
	}
	
	private void createResetPasswordBtn(JPanel panel) {
		JButton resetPasswordButton = createButton(panel, "¿Olvidaste tu contraseña?", 160, 25, AppColors.background, AppColors.primaryAccent);
		resetPasswordButton.setHorizontalAlignment(SwingConstants.RIGHT);
		resetPasswordButton.setBorder(null);
		resetPasswordButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		resetPasswordButton.addActionListener(e ->{
			new ResetPasswordView().setVisible(true);
			dispose();
		});
		
		resetPasswordButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				resetPasswordButton.setText("<html><u>¿Olvidaste tu contraseña?</u></html>");
			}
			
			public void mouseExited(MouseEvent e) {
				resetPasswordButton.setText("¿Olvidaste tu contraseña?");
			}
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
	
	private JButton createLoginButton() {
		JButton loginButton = createButton(contentPane, "Iniciar sesión", 300, 30, AppColors.primaryAccent, Color.WHITE);
		loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		loginButton.setContentAreaFilled(true);
				
		loginButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				loginButton.setBackground(AppColors.background);
				loginButton.setForeground(AppColors.primaryAccent);
				loginButton.setBorderPainted(true);
				loginButton.setBorder(BorderFactory.createLineBorder(AppColors.primaryAccent, 1));
			}
			
			public void mouseExited(MouseEvent e) {
				loginButton.setBackground(AppColors.primaryAccent);
				loginButton.setForeground(Color.WHITE);
				loginButton.setBorderPainted(false);
			}
		});
		
		return loginButton;
	}
	
	private JButton createRegisterBtn() {
		JButton registerButton = createButton(contentPane, "¿No tienes una cuenta? Regístrate aquí", 300, 30, AppColors.background, AppColors.primaryAccent);
		registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		registerButton.addActionListener(e ->{
		    RegisterView view = new RegisterView();
		    new RegisterController(view);
		    view.setVisible(true);
		    dispose();
		});
		
		registerButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				registerButton.setText("<html><u>¿No tienes una cuenta? Regístrate aquí</u></html>");
			}
			
			public void mouseExited(MouseEvent e) {
				registerButton.setText("¿No tienes una cuenta? Regístrate aquí");
			}
		});
		return registerButton;
	}
	
	public Label createErrorLabel(String text, int align) {
		Label errorLabel = new Label(text, 11, true);
		errorLabel.setForeground(new Color(200, 10, 10));
		errorLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, align));
		errorLabel.setAlignmentX(CENTER_ALIGNMENT);
		return errorLabel;
	}
	
	public void createSpace(int pixels, JPanel container) {
		container.add(Box.createVerticalStrut(pixels));
	}
	
	public JButton createButton(JPanel container, String name, int width, int length, Color background, Color foreground) {
		JButton button = new JButton(name);
		button.setMaximumSize(new Dimension(width, length));
		button.setPreferredSize(new Dimension(width, length));
		button.setBackground(background);
		button.setForeground(foreground);
		button.setFocusPainted(false);  
		button.setBorderPainted(false);
		button.setAlignmentX(CENTER_ALIGNMENT);
		button.setContentAreaFilled(false);
		button.setOpaque(true);
		container.add(button);
		return button;
	}
	
	private void fieldFocus(JTextComponent field) {
		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				field.requestFocusInWindow();
			}
		});
		
		field.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				field.selectAll();
		        field.setBorder(BorderFactory.createLineBorder(AppColors.primaryAccent, 1, true));
			}
			
			public void focusLost(FocusEvent e) {
		        field.setBorder(BorderFactory.createLineBorder(AppColors.subtleAccent, 1, true));
			}
		});
	}
	

	
}