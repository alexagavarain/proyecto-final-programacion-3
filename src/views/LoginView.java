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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		createSpace(100, contentPane);
		
		JLabel appName = new JLabel("Movies");
		appName.setToolTipText("");
		appName.setFont(new Font("Arial", Font.BOLD, 30));
		appName.setAlignmentX(CENTER_ALIGNMENT);
		contentPane.add(appName);
		createSpace(60, contentPane);
		
		JLabel loginText = new JLabel("Inicia sesión para acceder a nuestro catálago");
		loginText.setToolTipText("");
		loginText.setFont(new Font("Arial", Font.PLAIN, 16));
		loginText.setAlignmentX(CENTER_ALIGNMENT);
		contentPane.add(loginText);
		createSpace(40, contentPane);
		
		JLabel emailLabel = new JLabel("Correo electrónico");
		emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		emailLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 75));
		emailLabel.setAlignmentX(CENTER_ALIGNMENT);
		contentPane.add(emailLabel);
		
		emailTextField = new JTextField();
		emailTextField.setMaximumSize(new Dimension(250, 25));
		emailTextField.setAlignmentX(CENTER_ALIGNMENT);
		contentPane.add(emailTextField);
		emailTextField.setColumns(1);
		emailTextField.setBorder(null);
		createSpace(15, contentPane);

		JLabel passwordLabel = new JLabel("Contraseña");
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 117));
		passwordLabel.setAlignmentX(CENTER_ALIGNMENT);
		contentPane.add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setMaximumSize(new Dimension(250, 25));
		passwordField.setAlignmentX(CENTER_ALIGNMENT);
		passwordField.setBorder(null);
		contentPane.add(passwordField);
		
		createSpace(3, contentPane);
		
		if (error) {
			showError();
		}
		
		createSpace(40, contentPane);
		
		
		JButton loginButton = new JButton("Acceder");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		loginButton.setMaximumSize(new Dimension(250, 30));
		loginButton.setBackground(Color.BLACK);
		loginButton.setForeground(Color.WHITE);
		loginButton.setFocusPainted(false);  
		loginButton.setBorderPainted(false);
		loginButton.setAlignmentX(CENTER_ALIGNMENT);
		contentPane.add(loginButton);
	}
	
	public void showError() {
		JLabel errorLabel = new JLabel("*Credenciales incorrectas");
		errorLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		errorLabel.setForeground(Color.RED);
		errorLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 110));
		errorLabel.setAlignmentX(CENTER_ALIGNMENT);
		contentPane.add(errorLabel);
	}
	
	public void createSpace(int pixels, JPanel container) {
		container.add(Box.createVerticalStrut(pixels));
	}
	
}