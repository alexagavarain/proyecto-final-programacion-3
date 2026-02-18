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

public class InicioView extends JFrame{

	public InicioView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		createSpace(100, contentPane);
		
		showAppName(contentPane, "Registrarse");
		createSpace(60, contentPane);
		
		createLabel(contentPane, "Nombre", 12, 5);
		JTextField nombre = new JTextField();
		nombre.setAlignmentX(CENTER_ALIGNMENT);
		contentPane.add(nombre);
		nombre.setColumns(1);
		nombre.setBorder(null);
		createSpace(15, contentPane);
		
		createLabel(contentPane, "Carrera", 14, 10);
		JComboBox <String>listaCarreras = new JComboBox<String>();
		listaCarreras.addItem("IDS");
		listaCarreras.addItem("LATI");
		
		createSpace(3, contentPane);
		
		
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
	
	
}
