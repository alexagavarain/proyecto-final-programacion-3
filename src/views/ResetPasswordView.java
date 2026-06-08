package views;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utils.CreateFont;
import utils.IconLoader;

public class ResetPasswordView extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResetPasswordView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("Restablecer contraseña");
		
		ImageIcon icon = (ImageIcon) IconLoader.getIcon("/assets/img/logo.svg", 64, 64);
        
        if (icon != null) {
            Image logo = icon.getImage();
            setIconImage(logo);
        }
		
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
