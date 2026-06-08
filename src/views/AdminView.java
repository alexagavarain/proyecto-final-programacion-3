package views;

import java.awt.BorderLayout;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import controllers.UserController;
import utils.IconLoader;

public class AdminView extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JButton logoutBtn;
	
	public AdminView() {
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("Uni Tasks (Administrador)");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		ImageIcon icon = (ImageIcon) IconLoader.getIcon("/assets/img/logo.svg", 64, 64);
		
		if (icon != null) {
            Image logo = icon.getImage();
            setIconImage(logo);
        }
		
		
		add(createContainer());
	}

	public JButton getLogoutBtn() {
		return logoutBtn;
	}

	public JPanel createContainer() {
		JPanel container = new JPanel(new BorderLayout());
		container.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		
		UsersView usersView = new UsersView();
		new UserController(usersView);
		usersView.setVisible(true);
		
		JPanel btnContainer = new JPanel();
		btnContainer.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
		logoutBtn = new JButton("Cerrar sesión de administrador");
		btnContainer.add(logoutBtn);
		
		container.add(usersView, BorderLayout.CENTER);
		container.add(btnContainer, BorderLayout.SOUTH);
		
		return container;
	}
}