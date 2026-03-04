package views;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utils.CreateFont;

public class ResetPasswordView extends JFrame{

	public ResetPasswordView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("Restablecer contraseña");
		
		initializeComponents();
	}
	
	public void initializeComponents() {
		JPanel contentPane = new JPanel();
		createTitle(contentPane, "Restablecer contraseña", 30);
		
		add(contentPane);
	}
	
	public void createTitle(JPanel container, String name, float fontSize) {
		JLabel appName = new JLabel(name);
		appName.setToolTipText("");
		appName.setFont(CreateFont.DEFAULT_BOLD.deriveFont(fontSize));		
		appName.setAlignmentX(CENTER_ALIGNMENT);
		container.add(appName);
	}
	
}
