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

import com.formdev.flatlaf.extras.FlatSVGIcon;

import models.Session;
import utils.AppColors;
import utils.CreateFont;
import utils.IconLoader;

public class HomeView extends JFrame{
		
	public static final String PROFILE = "PROFILE";
	public static final String CLASSES = "CLASSES";
	public static final String TASKS = "TASKS";

	private JButton tasksBtn;
	private JButton classesBtn;
	private JButton profileBtn;
	private JButton logoutBtn;
	
	private ProfileView profileView;
	private ClassesView classesView;
	private TasksView tasksView;
	private CardLayout cardLayout;
	private JPanel container;
	
	public HomeView() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("Uni Tasks");
		
		createSideBar();
		createViews();
	}
	
	public JButton getTasksBtn() {
		return tasksBtn;
	}

	public JButton getClassesBtn() {
		return classesBtn;
	}
	
	public JButton getLogoutBtn() {
		return logoutBtn;
	}

	public JButton getProfileBtn() {
		return profileBtn;
	}

	public ProfileView getProfileView() {
		return profileView;
	}


	public ClassesView getClassesView() {
		return classesView;
	}

	public TasksView getTasksView() {
		return tasksView;
	}

	public CardLayout getCardLayout() {
		return cardLayout;
	}
	
	public void createSideBar() {
	    JPanel sideBar = new JPanel();
	    sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));
	    sideBar.setPreferredSize(new Dimension(250, getHeight()));
	    sideBar.setBorder(BorderFactory.createLineBorder(AppColors.subtleAccent, 1, true));

	    createSpace(20, sideBar);
	    JPanel logo = createAppLogo("UniTasks", 26);
	    JPanel summary = createUserSummary();
	    JPanel buttons = createBtnPanel();

	    logo.setAlignmentX(Component.LEFT_ALIGNMENT);
	    summary.setAlignmentX(Component.LEFT_ALIGNMENT);
	    buttons.setAlignmentX(Component.LEFT_ALIGNMENT);

	    sideBar.add(logo);
	    createSpace(20, sideBar);
	    sideBar.add(summary);
	    createSpace(20, sideBar);
	    sideBar.add(buttons);

	    add(sideBar, BorderLayout.WEST);
	}
	
	public JPanel createUserSummary() {
	    JPanel userSummary = new JPanel();
	    userSummary.setLayout(new BoxLayout(userSummary, BoxLayout.Y_AXIS)); 
	    userSummary.setAlignmentX(Component.LEFT_ALIGNMENT);
	    userSummary.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
	    userSummary.setBorder(BorderFactory.createLineBorder(AppColors.subtleAccent, 1, true));
	    
	    JLabel username = new JLabel(Session.getCurrentUser().getName());
	    JLabel userSemester = new JLabel("Sem. " + Session.getCurrentUser().getGroup().getSemester());
	    JLabel userCareer = new JLabel(Session.getCurrentUser().getGroup().getCareer().getName());
	    
	    username.setAlignmentX(Component.LEFT_ALIGNMENT);
	    userSemester.setAlignmentX(Component.LEFT_ALIGNMENT);
	    userCareer.setAlignmentX(Component.LEFT_ALIGNMENT);
	    
	    userSummary.add(username);
	    userSummary.add(userSemester);
	    userSummary.add(userCareer);
	    return userSummary;
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
	
	public JButton createLogoutBtn(String text) {
		JButton button = createBtn(text);
	    button.setBackground(AppColors.primaryAccent);
	    button.setForeground(Color.WHITE);
	    button.setHorizontalAlignment(SwingConstants.CENTER);
	    return button;
	}
	
	public JPanel createBtnPanel() {
	    JPanel btnPanel = new JPanel();
	    btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.Y_AXIS));
	    btnPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
	    btnPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        btnPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));


	    tasksBtn = createBtn("Tareas");
	    classesBtn = createBtn("Clases");
	    profileBtn = createBtn("Mi perfil");
	    logoutBtn = createLogoutBtn("Cerrar sesión");

	    createSpace(20, btnPanel);
	    btnPanel.add(tasksBtn);
	    createSpace(10, btnPanel);
	    btnPanel.add(classesBtn);
	    createSpace(10, btnPanel);
	    btnPanel.add(profileBtn);
	    btnPanel.add(Box.createVerticalGlue());
	    btnPanel.add(logoutBtn);
	    createSpace(20, btnPanel);

	    return btnPanel;
	}
	
	public void createSpace(int pixels, JPanel container) {
		container.add(Box.createVerticalStrut(pixels));
	}
	
	public void createTitle(JPanel container, String name, float fontSize) {
		JLabel appName = new JLabel(name);
		appName.setToolTipText("");
		appName.setFont(CreateFont.DEFAULT_BOLD.deriveFont(fontSize));		
		container.add(appName);
	}
	
	private JPanel createAppLogo(String appName, int fontSize) {
	    JPanel appLogo = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
	    appLogo.setAlignmentX(Component.LEFT_ALIGNMENT);
	    
	    createLogoImage(appLogo);
	    createTitle(appLogo, appName, fontSize);
	    
	    appLogo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
	    appLogo.setBackground(AppColors.background);
	    
	    return appLogo;
	}
	
	private void createLogoImage(JPanel container) {
	    JLabel lblLogo = new JLabel();
	    lblLogo.setIcon(IconLoader.getIcon("/assets/img/logo.svg", 32, 32));  
	    container.add(lblLogo);
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
	
}
