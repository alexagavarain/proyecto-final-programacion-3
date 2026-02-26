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
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import java.awt.Image;

import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	boolean error;

	public LoginView() {
		error = false;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("Inicio de sesión");
				
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		contentPane.setBorder(BorderFactory.createEmptyBorder(150, 0, 0, 0));
		contentPane.setBackground(AppColors.background);
		
		JPanel appLogo = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
		createLogo(appLogo);
		showAppName(appLogo, "UniTasks", 30);
		appLogo.setMaximumSize(new Dimension(330, 50));
		appLogo.setPreferredSize(new Dimension(330, 50));
		appLogo.setBackground(AppColors.background);
		contentPane.add(appLogo);
		createSpace(60, contentPane);
		
		JPanel descText = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
		descText.setMaximumSize(new Dimension(330, 50));
		descText.setPreferredSize(new Dimension(330, 50));
		descText.setBackground(AppColors.background);
		
		Label showText = new Label ("Bienvenid@", 18, false);
		descText.add(showText);
		createSpace(15, contentPane);
		
		Label showText2 = new Label ("Inicia sesión para continuar", 14, true);
		showText2.setForeground(AppColors.subtitle);
		descText.add(showText2);
		
		contentPane.add(descText);
		createSpace(25, contentPane);
		
		createLabel(contentPane, "Correo electrónico", 14, 180);
		
 		InputField emailTextField = new InputField();
		TextPrompt promptEmail = new TextPrompt("estudiante@alu.uabcs.mx", emailTextField);
		promptEmail.setForeground(AppColors.subtitle);
		contentPane.add(emailTextField);
		
		createSpace(10, contentPane);
		
		createLabel(contentPane, "Contraseña", 14, 220);
		
		JPasswordField passwordField = new JPasswordField();
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
		contentPane.add(passwordField);
				
				
		if (error) {
			createSpace(4, contentPane);
			showError(contentPane);
		}
		
		createSpace(6, contentPane);
		
		JPanel secondaryOptionPanel = new JPanel();
		secondaryOptionPanel.setMaximumSize(new Dimension(330, 35));
		secondaryOptionPanel.setPreferredSize(new Dimension(330, 35));
		secondaryOptionPanel.setBackground(AppColors.background);
		
		JCheckBox chkRememberMe = new JCheckBox("Recuérdame", true);
		secondaryOptionPanel.add(chkRememberMe);
		
		secondaryOptionPanel.add(Box.createHorizontalStrut(38));
		
		JButton resetPasswordButton = createButton(secondaryOptionPanel, "¿Olvidaste tu contraseña?", 160, 25, AppColors.background, AppColors.primaryAccent);
		resetPasswordButton.setHorizontalAlignment(SwingConstants.RIGHT);
		resetPasswordButton.setBorder(null);
		
		contentPane.add(secondaryOptionPanel);
		
		createSpace(25, contentPane);
		
		JButton loginButton = createButton(contentPane, "Iniciar sesión", 300, 30, AppColors.primaryAccent, Color.WHITE);
		
		createSpace(10, contentPane);
		
		JButton registerButton = createButton(contentPane, "¿No tienes una cuenta? Regístrate aquí", 300, 30, AppColors.background, AppColors.primaryAccent);
		
		registerButton.addActionListener(e ->{
			new RegisterView().setVisible(true);
			dispose();
		});
		
//		pack();

	}
	
	private void createLogo(JPanel container) {
		JLabel lblLogo = new JLabel();
		lblLogo.setBounds(145, 50, 100, 100);
		lblLogo.setIcon(uploadIcon("/assets/logo.png", 30, 30));
		container.add(lblLogo);
	}
	
	private ImageIcon uploadIcon(String ruta, int w, int h) {

		try {
			Image icono = ImageIO.read(getClass().getResource(ruta));
			icono = icono.getScaledInstance(w, h, Image.SCALE_SMOOTH);
			return new ImageIcon(icono);
		}catch(Exception ex) {
			System.out.println("Image not found");
		}
		
		return null;
	}
	
	public void createLabel(JPanel container, String containerName, float fontSize, int rightBorder) {
		JLabel emailLabel = new JLabel(containerName);
		emailLabel.setFont(CreateFont.DEFAULT.deriveFont(fontSize));		
		emailLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, rightBorder));
		emailLabel.setAlignmentX(CENTER_ALIGNMENT);
		container.add(emailLabel);
	}
	
	public void showAppName(JPanel container, String name, float fontSize) {
		JLabel appName = new JLabel(name);
		appName.setToolTipText("");
		appName.setFont(CreateFont.DEFAULT_BOLD.deriveFont(fontSize));		
		appName.setAlignmentX(CENTER_ALIGNMENT);
		container.add(appName);
	}
	
	public void showError(JPanel container) {
		JLabel errorLabel = new JLabel("*Credenciales incorrectas");
		errorLabel.setFont(CreateFont.DEFAULT.deriveFont(12f));		
		errorLabel.setForeground(Color.RED);
		errorLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 110));
		errorLabel.setAlignmentX(CENTER_ALIGNMENT);
		container.add(errorLabel);
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
	
}