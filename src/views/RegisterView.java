package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import utils.TextPrompt;

public class RegisterView extends JFrame{
	boolean error = false;

	public RegisterView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		Color subtitleColor = new Color(100, 116, 139);
		
		JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		createSpace(100, contentPane);
		
		showAppName(contentPane, "Registrarse");
		createSpace(60, contentPane);
		
		createLabel(contentPane, "Nombre", 15, 5);
		JTextField nombre = new JTextField();
		nombre.setMaximumSize(new Dimension(300, 28));
		nombre.setAlignmentX(CENTER_ALIGNMENT);
		nombre.setFont(new Font ("Arial", Font.PLAIN, 12));
		nombre.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 3));
		contentPane.add(nombre);
		
		
		createSpace(10, contentPane);
		
		createLabel(contentPane, "Correo electrónico", 14, 180);
		
		JTextField emailTextField = new JTextField();
		emailTextField.setMaximumSize(new Dimension(300, 28));
		emailTextField.setAlignmentX(CENTER_ALIGNMENT);
		emailTextField.setFont(new Font ("Arial", Font.PLAIN, 15));

		emailTextField.setBorder(
			    BorderFactory.createCompoundBorder(
				    BorderFactory.createLineBorder(Color.GRAY, 1, true),
				    BorderFactory.createEmptyBorder(5, 5, 5, 5)
			    )
		);
		
		TextPrompt promptEmail = new TextPrompt("estudiante@alu.uabcs.mx", emailTextField);
		promptEmail.setForeground(Color.BLACK);
		contentPane.add(emailTextField);
		
		
		createSpace(10, contentPane);
		createLabel(contentPane, "Contraseña", 14, 220);
		JPasswordField passwordField = new JPasswordField();
		passwordField.setMaximumSize(new Dimension(300, 28));
		passwordField.setAlignmentX(CENTER_ALIGNMENT);
		passwordField.setFont(new Font ("Arial", Font.PLAIN, 15));

		passwordField.setBorder(
			    BorderFactory.createCompoundBorder(
			        BorderFactory.createLineBorder(subtitleColor, 1, true),
			        BorderFactory.createEmptyBorder(5, 5, 5, 5)
			    )
			);
		
		TextPrompt promptPassword = new TextPrompt("•••••••••••", passwordField);
		promptPassword.setForeground(subtitleColor);
		contentPane.add(passwordField);
				
				
		if (error) {
			createSpace(4, contentPane);
			showError(contentPane);
		}
		
		
		createSpace(10, contentPane);
		createLabel(contentPane, "Carrera", 14, 10);

		String[] carreras = {
		    "Ingeniería en Desarrollo de Software (IDS)",
		    "Licenciatura en Tecnologías de la Información (LATI)",
		    "Ingeniería en Tecnologia Computacional (ITC)",
		    "Ingeniería en Ciberseguridad"
		};

		JComboBox<String> listaCarreras = new JComboBox<>(carreras);
		listaCarreras.setMaximumSize(new Dimension(300, 30));
		listaCarreras.setAlignmentX(CENTER_ALIGNMENT);
		listaCarreras.insertItemAt("Selecciona tu carrera", 0);
		listaCarreras.setSelectedIndex(0);
		contentPane.add(listaCarreras);
	
		
		

		
		
	}
	
	public void createLabel(JPanel container, String containerName, int fontSize, int rightBorder) {
		JLabel emailLabel = new JLabel(containerName);
		emailLabel.setFont(new Font("Tahoma", Font.PLAIN, fontSize));
		emailLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, rightBorder));
		emailLabel.setAlignmentX(CENTER_ALIGNMENT);
		container.add(emailLabel);
	}
	
	public void showText(JPanel container, String text, int fontSize) {
		JLabel loginText = new JLabel(text);
		loginText.setToolTipText("");
		loginText.setFont(new Font("Arial", Font.PLAIN, fontSize));
		loginText.setAlignmentX(CENTER_ALIGNMENT);
		container.add(loginText);
	}
	
	public void showAppName(JPanel container, String name) {
		JLabel appName = new JLabel(name);
		appName.setToolTipText("");
		appName.setFont(new Font("Arial", Font.BOLD, 30));
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
	public void showError(JPanel container) {
		JLabel errorLabel = new JLabel("*Credenciales incorrectas");
		errorLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		errorLabel.setForeground(Color.RED);
		errorLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 110));
		errorLabel.setAlignmentX(CENTER_ALIGNMENT);
		container.add(errorLabel);
	}
	
	
	
}
