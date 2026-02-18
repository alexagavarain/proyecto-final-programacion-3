package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utils.Label;
import utils.TextPrompt;

import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField emailTextField;
	private JPasswordField passwordField;
	boolean error;

	public LoginView() {
		error = false;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		Color backgroundColor = new Color(238, 238, 238);
		Color accentColor = new Color(50, 53, 96);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		contentPane.setBorder(BorderFactory.createEmptyBorder(150, 0, 0, 0));
				
		showAppName(contentPane, "Mi agenda UABCS", 30);
		createSpace(60, contentPane);
		
		Label showText = new Label ("Inicia sesión", 18, false);
		contentPane.add(showText);
		createSpace(15, contentPane);
		
		Label showText2 = new Label ("Ingresa tus datos para acceder", 14, true);
		contentPane.add(showText2);
		createSpace(25, contentPane);
				
		emailTextField = new JTextField();
		emailTextField.setMaximumSize(new Dimension(300, 28));
		emailTextField.setAlignmentX(CENTER_ALIGNMENT);
		emailTextField.setFont(new Font ("Arial", Font.PLAIN, 15));
		emailTextField.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 3));
		TextPrompt promptEmail = new TextPrompt("correoinstitucional@alu.uabcs.mx", emailTextField);
		contentPane.add(emailTextField);
		
		createSpace(10, contentPane);
		
		passwordField = new JPasswordField();
		passwordField.setMaximumSize(new Dimension(300, 28));
		passwordField.setAlignmentX(CENTER_ALIGNMENT);
		passwordField.setFont(new Font ("Arial", Font.PLAIN, 15));
		passwordField.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 3));
		TextPrompt promptPassword = new TextPrompt("contraseña", passwordField);
		contentPane.add(passwordField);
				
		if (error) {
			createSpace(4, contentPane);
			showError(contentPane);
		}
		
		createSpace(10, contentPane);
		JButton registerButton = createButton(contentPane, "¿No tienes una cuenta? Regístrate", 300, 30, backgroundColor, Color.BLACK);
		
		
		createSpace(30, contentPane);
		
		JButton loginButton = createButton(contentPane, "Acceder", 300, 30, accentColor, Color.WHITE);
		
		pack();

	}
	
	
	
	
	public void createLabel(JPanel container, String containerName, int fontSize, int rightBorder) {
		JLabel emailLabel = new JLabel(containerName);
		emailLabel.setFont(new Font("Tahoma", Font.PLAIN, fontSize));
		emailLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, rightBorder));
		emailLabel.setAlignmentX(CENTER_ALIGNMENT);
		container.add(emailLabel);
	}
	
	public void showAppName(JPanel container, String name, int fontSize) {
		JLabel appName = new JLabel(name);
		appName.setToolTipText("");
		appName.setFont(new Font("Arial", Font.BOLD, fontSize));
		appName.setAlignmentX(CENTER_ALIGNMENT);
		container.add(appName);
	}
	
	public void showError(JPanel container) {
		JLabel errorLabel = new JLabel("*Credenciales incorrectas");
		errorLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
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
		button.setBackground(background);
		button.setForeground(foreground);
		button.setFocusPainted(false);  
		button.setBorderPainted(false);
		button.setAlignmentX(CENTER_ALIGNMENT);
		container.add(button);
		return button;
	}
	
}