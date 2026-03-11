package views;

import javax.swing.JFrame;
import javax.swing.JPanel;

import utils.AppColors;
import utils.CreateFont;
import utils.InputField;
import utils.Label;
import utils.TextPrompt;

import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Image;

import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
		assignListeners();
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
		
		createLoginFields(contentPane);
		
		createSpace(6, contentPane);
		
		JPanel secondaryOptionPanel = createSecondaryOptionPanel();
		
		contentPane.add(secondaryOptionPanel);
		
		createSpace(25, contentPane);
		
		JButton loginButton = createLoginButton();
		
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
	
	private JPanel createLoginFields(JPanel container) {
		createLabel(container, "Correo electrónico", 14, 175);
 		emailTextField = createEmailField("estudiante@alu.uabcs.mx");
 		validateEmailCharacters(emailTextField);
		container.add(emailTextField);
		container.add(emailError);
		
		createSpace(10, container);
		
		createLabel(container, "Contraseña", 14, 220);
		passwordField = createPasswordField();
		container.add(passwordField);
		container.add(passwordError);
		
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
		
		loginButton.addActionListener(e -> login(contentPane));
		
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
			new RegisterView().setVisible(true);
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
	
	public Label createErrorLabel(String text) {
		Label errorLabel = new Label(text, 12, false);
		errorLabel.setForeground(new Color(200, 10, 10));
		errorLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 150));
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
		button.setContentAreaFilled(false);
		button.setOpaque(true);
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
	}
	
	private void login(JPanel panel) {
		resetErrorLabel(emailError);
		resetErrorLabel(passwordError);
		
		if (validateLogin()) {
			new HomeView().setVisible(true);;
			dispose();
		}
	};

	private void assignListeners() {
	    emailTextField.getDocument().addDocumentListener(new DocumentListener() {
	        public void insertUpdate(DocumentEvent e) {
	            validateEmail();
	        }

	        public void removeUpdate(DocumentEvent e) {
	            validateEmail();
	        }

	        public void changedUpdate(DocumentEvent e) {
	            validateEmail();
	        }
	    });

	    passwordField.getDocument().addDocumentListener(new DocumentListener() {
	        public void insertUpdate(DocumentEvent e) {
	            validatePassword();
	        }

	        public void removeUpdate(DocumentEvent e) {
	            validatePassword();
	        }

	        public void changedUpdate(DocumentEvent e) {
	            validatePassword();
	        }
	    });
	}
	
	private void validateEmailCharacters(InputField field) {
		field.addKeyListener(new KeyAdapter() {
			
			public void keyTyped(KeyEvent e) {
				if ( Character.isDigit( e.getKeyChar() ) || !Character.isAlphabetic( e.getKeyChar() ) && e.getKeyChar() != '@') {
					e.consume();
				}
				
				if ( field.getText().length() >= 30 ) {
					e.consume();
				}
			};	
			
		});
	}
	
	
	
	private boolean validateLogin() {
	    boolean emailValid = validateEmail();
	    boolean passwordValid = validatePassword();

	    return emailValid && passwordValid;
	}
	
	private boolean validatePassword() {
		if (String.valueOf(passwordField.getPassword()).trim().isEmpty()) {
			passwordError.setText("La contraseña es obligatoria");
			return false;
		}
		
		if (String.valueOf(passwordField.getPassword()).length() < 6) {
	        passwordError.setText("La contraseña debe tener mínimo 6 caracteres");
	        return false;
	    }

	    passwordError.setText("");
	    return true;
	}
	
	private boolean validateEmail() {
		if (emailTextField.getText().trim().equals("")){
			emailError.setText("El correo es obligatorio");
			return false;
		}
		
		if (!emailTextField.getText().contains("@")) {
	        emailError.setText("Correo inválido");
	        return false;
	    }

	    emailError.setText("");
	    return true;
	}
	
}