package views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import utils.AppColors;
import utils.CreateFont;

public class HomeView extends JFrame{
		
	public static final String PROFILE = "PROFILE";
	public static final String CLASSES = "CLASSES";
	public static final String TASKS = "TASKS";
	
	public JButton tasksBtn;
	public JButton classesBtn;
	public JButton profileBtn;
	public ProfileView profileView;
	public ClassesView classesView;
	public TasksView tasksView;
	private CardLayout cardLayout;
	private JPanel container;
	
	public HomeView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBackground(AppColors.primaryAccent);
		setTitle("Uni Tasks");
		
		createSideBar();
		createViews();
	}
	
	public void createSideBar() {
		JPanel sideBar = new JPanel();
		sideBar.setBackground(AppColors.background);
		sideBar.setPreferredSize(new Dimension (200, 200));
		sideBar.setLayout(new BoxLayout (sideBar, BoxLayout.Y_AXIS));
		sideBar.setBorder(
			    BorderFactory.createCompoundBorder(
			        BorderFactory.createLineBorder(AppColors.subtleAccent, 1, true),
			        BorderFactory.createEmptyBorder(5, 5, 5, 5)
			    )
			);
		sideBar.add(createAppLogo("UniTasks", 20));
		sideBar.add(tasksBtn());
		sideBar.add(classesBtn());
		sideBar.add(profileBtn());
		
		add(sideBar, BorderLayout.WEST);
	}
	
	public void createTitle(JPanel container, String name, float fontSize) {
		JLabel appName = new JLabel(name);
		appName.setToolTipText("");
		appName.setFont(CreateFont.DEFAULT_BOLD.deriveFont(fontSize));		
		appName.setAlignmentX(CENTER_ALIGNMENT);
		container.add(appName);
	}
	
	private JPanel createAppLogo(String appName, int fontSize) {
		JPanel appLogo = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
		createLogoImage(appLogo);
		createTitle(appLogo, appName, fontSize);
		appLogo.setMaximumSize(new Dimension(330, 50));
		appLogo.setPreferredSize(new Dimension(330, 50));
		appLogo.setBackground(AppColors.background);
		return appLogo;
	}
	
	private void createLogoImage(JPanel container) {
		JLabel lblLogo = new JLabel();
		lblLogo.setBounds(145, 50, 100, 100);
		lblLogo.setIcon(uploadIcon("/assets/img/logo.png", 30, 30));
		container.add(lblLogo);
	}
	
	private ImageIcon uploadIcon(String ruta, int w, int h) {
		try {
			Image icono = ImageIO.read(getClass().getResource(ruta));
			icono = icono.getScaledInstance(w, h, Image.SCALE_SMOOTH);
			return new ImageIcon(icono);
		} catch(Exception ex) {
			System.out.println("Image not found");
		}
		
		return null;
	}	

	public JButton tasksBtn() {
		tasksBtn = new JButton("Tareas");
		return tasksBtn;
	}
	
	public JButton classesBtn() {
		classesBtn = new JButton("Clases");
		return classesBtn;
	}
	
	public JButton profileBtn() {
		profileBtn = new JButton("Mi perfil");
		return profileBtn;
	}
	
	private void createViews() {
		cardLayout = new CardLayout();
		container = new JPanel(cardLayout);
		
		profileView = new ProfileView();
		classesView = new ClassesView();
		tasksView = new TasksView();
		
		container.add(profileView, PROFILE);
		container.add(classesView, CLASSES);
		container.add(tasksView, TASKS);
		
		add(container, BorderLayout.CENTER);
	}
	
	public void showView(String view) {
		cardLayout.show(container, view);
	}
	
	
	
	
	
	
	
	
//	public void createNavBar() {
//		JPanel navbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
//		
//		btnHome = new JButton("Inicio");
//		btnUsers = new JButton("Usuarios");
//		
//		navbar.add(btnHome);
//		navbar.add(btnUsers);
//		
//		add(navbar, BorderLayout.NORTH);
//	}
//	
//	
//	public void setMenu() {
//
//	    JMenuBar mb = new JMenuBar();
//	    setJMenuBar(mb);
//
//	    JMenu menuFile = new JMenu("File");
//	    menuFile.setMnemonic(KeyEvent.VK_F);
//	    mb.add(menuFile);
//
//	    JMenuItem mItemOpen = new JMenuItem("Open");
//	    mItemOpen.setMnemonic(KeyEvent.VK_O);
//	    menuFile.add(mItemOpen);
//
//	    JMenuItem mItemSave = new JMenuItem("Save");
//	    mItemSave.setMnemonic(KeyEvent.VK_S);
//	    menuFile.add(mItemSave);
//
//	    menuFile.addSeparator();
//
//	    mItemExit = new JMenuItem("Exit");
//	    mItemExit.setMnemonic(KeyEvent.VK_E);
//	    menuFile.add(mItemExit);
//
//	    JMenu menuOtherOption = new JMenu("Other Option");
//	    menuOtherOption.setMnemonic(KeyEvent.VK_O);
//	    mb.add(menuOtherOption);
//
//	    JMenu menuOption1 = new JMenu("Option 1");
//	    menuOtherOption.add(menuOption1);
//
//	    JMenuItem mItemOption3 = new JMenuItem("Option 3");
//	    menuOption1.add(mItemOption3);
//
//	    JMenuItem mItemOption2 = new JMenuItem("Option 2");
//	    menuOtherOption.add(mItemOption2);
//
//	}
//	
//
//	
//	public void createTitle(JPanel container, String name, float fontSize) {
//		JLabel appName = new JLabel(name);
//		appName.setToolTipText("");
//		appName.setFont(CreateFont.DEFAULT_BOLD.deriveFont(fontSize));		
//		appName.setAlignmentX(CENTER_ALIGNMENT);
//		container.add(appName);
//	}
	
	
	

}
