package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
		error = true;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		createSpace(100, contentPane);
		
		showAppName(contentPane, "Mi agenda UABCS");
		createSpace(60, contentPane);
		
		showText(contentPane, "Inicia sesión con tu correo institucional para acceder", 16);
		createSpace(40, contentPane);
		
		createLabel(contentPane, "Correo electrónico", 14, 135);
		
		emailTextField = new JTextField();
		emailTextField.setMaximumSize(new Dimension(250, 25));
		emailTextField.setAlignmentX(CENTER_ALIGNMENT);
		contentPane.add(emailTextField);
		emailTextField.setColumns(1);
		emailTextField.setBorder(null);
		createSpace(15, contentPane);

		createLabel(contentPane, "Contraseña", 14, 178);
		
		passwordField = new JPasswordField();
		passwordField.setMaximumSize(new Dimension(250, 25));
		passwordField.setAlignmentX(CENTER_ALIGNMENT);
		passwordField.setBorder(null);
		contentPane.add(passwordField);
		
		createSpace(3, contentPane);
		
		if (error) {
			showError(contentPane);
		}
		
		createSpace(40, contentPane);
		
		JButton loginButton = createButton(contentPane, "Acceder", 250, 30);

	}
	
	public void showText(JPanel container, String text, int fontSize) {
		JLabel loginText = new JLabel(text);
		loginText.setToolTipText("");
		loginText.setFont(new Font("Arial", Font.PLAIN, fontSize));
		loginText.setAlignmentX(CENTER_ALIGNMENT);
		container.add(loginText);
	}
	
	public void createLabel(JPanel container, String containerName, int fontSize, int rightBorder) {
		JLabel emailLabel = new JLabel(containerName);
		emailLabel.setFont(new Font("Tahoma", Font.PLAIN, fontSize));
		emailLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, rightBorder));
		emailLabel.setAlignmentX(CENTER_ALIGNMENT);
		container.add(emailLabel);
	}
	
	public void showAppName(JPanel container, String name) {
		JLabel appName = new JLabel(name);
		appName.setToolTipText("");
		appName.setFont(new Font("Arial", Font.BOLD, 30));
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
	
}