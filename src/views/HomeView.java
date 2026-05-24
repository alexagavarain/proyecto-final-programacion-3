package views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import utils.AppColors;
import utils.CreateFont;

public class HomeView extends JFrame{
		
	public static final String PROFILE = "PROFILE";
	public static final String CLASSES = "CLASSES";
	public static final String TASKS = "TASKS";
	
	private JButton tasksBtn;
	private JButton classesBtn;
	private JButton profileBtn;
	private ProfileView profileView;
	private ClassesView classesView;
	private TasksView tasksView;
	private CardLayout cardLayout;
	private JPanel container;
	
	public HomeView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("Uni Tasks");
		
		createSideBar();
		createViews();
	}
	
	public void createSideBar() {
	    JPanel sideBar = new JPanel();
	    sideBar.setLayout(new BorderLayout());
	    sideBar.setPreferredSize(new Dimension(250, getHeight()));
	    sideBar.setBorder(
			    BorderFactory.createCompoundBorder(
			        BorderFactory.createLineBorder(AppColors.subtleAccent, 1, true),
			        BorderFactory.createEmptyBorder(30, 10, 0, 10)
			    )
			);

	    JPanel btnPanel = new JPanel();
	    btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.Y_AXIS));

	    tasksBtn = createBtn("Tareas");
	    classesBtn = createBtn("Clases");
	    profileBtn = createBtn("Mi perfil");

	    createSpace(20, btnPanel);
	    btnPanel.add(tasksBtn);
	    createSpace(10, btnPanel);
	    btnPanel.add(classesBtn);
	    createSpace(10, btnPanel);
	    btnPanel.add(profileBtn);

	    sideBar.add(createAppLogo("UniTasks", 26), BorderLayout.NORTH);
	    sideBar.add(btnPanel, BorderLayout.CENTER);

	    add(sideBar, BorderLayout.WEST);
	}
	
	public JButton createBtn(String text) {
	    JButton button = new JButton(text);
	    button.setHorizontalAlignment(SwingConstants.LEFT);
	    button.setPreferredSize(new Dimension(200, 30));
	    button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
	    button.setMinimumSize(new Dimension(0, 30));

	    button.setAlignmentX(Component.LEFT_ALIGNMENT);

	    return button;
	}
	
	public void createSpace(int pixels, JPanel container) {
		container.add(Box.createVerticalStrut(pixels));
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

	public JButton getTasksBtn() {
		return tasksBtn;
	}

	public void setTasksBtn(JButton tasksBtn) {
		this.tasksBtn = tasksBtn;
	}

	public JButton getClassesBtn() {
		return classesBtn;
	}

	public void setClassesBtn(JButton classesBtn) {
		this.classesBtn = classesBtn;
	}

	public JButton getProfileBtn() {
		return profileBtn;
	}

	public void setProfileBtn(JButton profileBtn) {
		this.profileBtn = profileBtn;
	}

	public ProfileView getProfileView() {
		return profileView;
	}

	public void setProfileView(ProfileView profileView) {
		this.profileView = profileView;
	}

	public ClassesView getClassesView() {
		return classesView;
	}

	public void setClassesView(ClassesView classesView) {
		this.classesView = classesView;
	}

	public TasksView getTasksView() {
		return tasksView;
	}

	public void setTasksView(TasksView tasksView) {
		this.tasksView = tasksView;
	}

	public CardLayout getCardLayout() {
		return cardLayout;
	}

	public void setCardLayout(CardLayout cardLayout) {
		this.cardLayout = cardLayout;
	}

	public JPanel getContainer() {
		return container;
	}

	public void setContainer(JPanel container) {
		this.container = container;
	}
	

}
