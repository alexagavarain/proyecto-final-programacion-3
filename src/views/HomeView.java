package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utils.CreateFont;

public class HomeView extends JFrame{
	
	public JButton btnUsers;
	
	public HomeView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("Uni Tasks");
		
		initializeComponents();
	}
	
	public void initializeComponents() {
		JPanel contentPane = new JPanel();
		createTitle(contentPane, "Home", 30);
				
		add(contentPane);
		
		btnUsers = new JButton("Ver usuarios");
		contentPane.add(btnUsers);
	}
	
	public void createTitle(JPanel container, String name, float fontSize) {
		JLabel appName = new JLabel(name);
		appName.setToolTipText("");
		appName.setFont(CreateFont.DEFAULT_BOLD.deriveFont(fontSize));		
		appName.setAlignmentX(CENTER_ALIGNMENT);
		container.add(appName);
	}
	
	
	

}
