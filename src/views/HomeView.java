package views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import models.User;
import utils.AppColors;
import utils.CreateFont;
import utils.IconLoader;
import utils.Label;
import utils.RoundedButton;
import utils.Session;

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
	private JPanel pfpContainer;
	
	private User user;
	
	public HomeView() {
		this.user = Session.getCurrentUser();
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("Uni Tasks");
		
		ImageIcon icon = (ImageIcon) IconLoader.getIcon("/assets/img/logo.svg", 64, 64);
        
        if (icon != null) {
            Image logo = icon.getImage();
            setIconImage(logo);
        }
		
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
	
	public JPanel getPfpContainer() {
		return pfpContainer;
	}

	public void createSideBar() {
	    JPanel sideBar = new JPanel();
	    sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));
	    sideBar.setPreferredSize(new Dimension(250, getHeight()));
	    sideBar.setBackground(AppColors.sideBar);

	    createSpace(30, sideBar);
	    JPanel logo = createAppLogo();
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
	
	private JPanel createAppLogo() {
		JPanel container = new JPanel(new BorderLayout());
	    container.setAlignmentX(Component.LEFT_ALIGNMENT);
	    container.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
	    container.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
	    container.setOpaque(false);
	    container.add(createLogoImage(), BorderLayout.WEST);
	    container.add(createTitle(), BorderLayout.CENTER);
	    return container;
	}
	
	public JPanel createUserSummary() {
	    JPanel userSummary = new JPanel(new BorderLayout());
	    userSummary.setAlignmentX(Component.LEFT_ALIGNMENT);
	    userSummary.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
	    userSummary.setBorder(BorderFactory.createCompoundBorder(
	            BorderFactory.createMatteBorder(1, 0, 1, 0, AppColors.iceGrey), 
	            BorderFactory.createEmptyBorder(12, 5, 10, 0)));
	    userSummary.setOpaque(false);
	    
	    JPanel userInfo = new JPanel();
	    userInfo.setLayout(new BoxLayout(userInfo, BoxLayout.Y_AXIS));
	    userInfo.setOpaque(false);
	    
	    Label username = new Label(user.getName(), 12, true);
	    Label userCareer = new Label(user.getGroup().getCareer().getAbb() + " · Sem. " + Session.getCurrentUser().getGroup().getSemester(), 12, true, AppColors.menuItem);
	    username.setAlignmentX(Component.LEFT_ALIGNMENT);
	    userCareer.setAlignmentX(Component.LEFT_ALIGNMENT);
	    
	    userInfo.add(username);
	    userInfo.add(Box.createVerticalStrut(4));
	    userInfo.add(userCareer);
	    userInfo.add(Box.createVerticalStrut(4));
	    
	    pfpContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
		pfpContainer.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    pfpContainer.setOpaque(false);
	    
	    JPanel pfp = createAvatar();
	    pfpContainer.add(pfp);
	    	     
	    userSummary.add(pfpContainer, BorderLayout.WEST);
	    userSummary.add(userInfo, BorderLayout.CENTER);
	    return userSummary;
	}

	private JPanel createAvatar() {
	    String initials = user != null ? getInitials(user.getName()) : "??";
	    JLabel avatar = new JLabel(initials, SwingConstants.CENTER);
	    
	    avatar.setForeground(new Color(0x2563EB));
	    avatar.setFont(CreateFont.DEFAULT_BOLD.deriveFont(13f));
	    
	    JPanel avatarWrap = new JPanel(new BorderLayout()) {
	        @Override 
	        protected void paintComponent(Graphics g) {
	            Graphics2D g2 = (Graphics2D) g.create();
	            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	                                RenderingHints.VALUE_ANTIALIAS_ON);
	            g2.setColor(new Color(0xBFD3F6));
	            g2.fillOval(0, 0, getWidth() - 1, getHeight() - 1);
	            g2.dispose();
	            
	            super.paintComponent(g);
	        }
	    };
	    
	    avatarWrap.setOpaque(false);
	    
	    Dimension avatarSize = new Dimension(40, 40);
	    avatarWrap.setPreferredSize(avatarSize);
	    avatarWrap.setMaximumSize(avatarSize);
	    avatarWrap.setMinimumSize(avatarSize);
	    
	    avatar.setOpaque(false);
	    avatarWrap.add(avatar, BorderLayout.CENTER);
	    
	    return avatarWrap;
	}
	
	private String getInitials(String name) {
        if (name == null || name.isBlank()) return "?";
        String[] parts = name.trim().split("\\s+");
        String ini = String.valueOf(parts[0].charAt(0));
        
        if (parts.length > 1) {
        	ini += parts[1].charAt(0);
        }
        return ini.toUpperCase();
    }
	
	public JButton createBtn(String text, String iconPath) {
	    JButton button = new RoundedButton(text, 20);
	    button.setHorizontalAlignment(SwingConstants.LEFT);
	    Dimension fixedSize = new Dimension(240, 40);
	    button.setPreferredSize(fixedSize);
	    button.setMaximumSize(fixedSize);
	    button.setMinimumSize(fixedSize);
	    button.setAlignmentX(Component.CENTER_ALIGNMENT);
	    button.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
	    button.setBackground(AppColors.sideBar);
	    button.setForeground(AppColors.menuItem);
	    button.setFont(CreateFont.DEFAULT_BOLD.deriveFont(14f));
	    button.setIcon(IconLoader.getIcon(iconPath, 17, 17));
	    button.putClientProperty("JButton.buttonType", "roundRect");
	    button.putClientProperty("JButton.arc", 12);
	    button.setIconTextGap(12);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    return button;
	}

	public JPanel createBtnPanel() {
	    JPanel btnPanel = new JPanel();
	    btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.Y_AXIS)); 
	    btnPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
	    btnPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
	    btnPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
	    btnPanel.setOpaque(false);

	    tasksBtn = createBtn("Tareas", "/assets/img/tasks.svg");
	    classesBtn = createBtn("Clases", "/assets/img/classes.svg");
	    profileBtn = createBtn("Mi perfil", "/assets/img/profile.svg");
	    logoutBtn = createBtn("Cerrar sesión", "/assets/img/logout.svg");
	    logoutBtn.setBorder(BorderFactory.createCompoundBorder(
	            BorderFactory.createMatteBorder(1, 0, 0, 0, AppColors.iceGrey),
	            BorderFactory.createEmptyBorder(0, 15, 0, 15)
	        ));
	    btnPanel.add(tasksBtn);
	    createSpace(5, btnPanel);
	    btnPanel.add(classesBtn);
	    createSpace(5, btnPanel);
	    btnPanel.add(profileBtn);
	    btnPanel.add(Box.createVerticalGlue());
	    btnPanel.add(logoutBtn);
	    createSpace(20, btnPanel);

	    return btnPanel;
	}
	
	public void createSpace(int pixels, JPanel container) {
		container.add(Box.createVerticalStrut(pixels));
	}
	
	private JPanel createTitle() {
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		container.setOpaque(false);
		
		Label appName = new Label("UniTasks", 17, false);
		Label slogan = new Label("Gestión académica", 13, true, AppColors.menuItem);
	    appName.setAlignmentX(Component.LEFT_ALIGNMENT);
	    slogan.setAlignmentX(Component.LEFT_ALIGNMENT);
	    
		container.add(appName);
		container.add(slogan);
		return container;
	}
	
	private JLabel createLogoImage() {
	    JLabel lblLogo = new JLabel();
	    lblLogo.setIcon(IconLoader.getIcon("/assets/img/logo.svg", 37, 37));  
	    lblLogo.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
	    return lblLogo;
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
	
	public void activeBtnStyle(JButton activeBtn) {
		JButton[] btns = {tasksBtn, classesBtn, profileBtn};

	    for (JButton btn : btns) {
	        if (btn == activeBtn) {
	            btn.setBackground(AppColors.primaryAccent);
	            btn.setForeground(Color.WHITE);
	            
	            if (btn == tasksBtn) {
	        	    btn.setIcon(IconLoader.getIcon("/assets/img/activeTasks.svg", 17, 17));
	    	    } else if (btn == classesBtn) {
	        	    btn.setIcon(IconLoader.getIcon("/assets/img/activeClasses.svg", 17, 17));
	    	    } else if (btn == profileBtn) {
	        	    btn.setIcon(IconLoader.getIcon("/assets/img/activeProfile.svg", 17, 17));
	    	    }
	        } else {
	            btn.setBackground(AppColors.sideBar);
	            btn.setForeground(AppColors.menuItem);
	            
	            if (btn == tasksBtn) {
	        	    btn.setIcon(IconLoader.getIcon("/assets/img/tasks.svg", 17, 17));
	    	    } else if (btn == classesBtn) {
	        	    btn.setIcon(IconLoader.getIcon("/assets/img/classes.svg", 17, 17));
	    	    } else if (btn == profileBtn) {
	        	    btn.setIcon(IconLoader.getIcon("/assets/img/profile.svg", 17, 17));
	    	    }
	        }
	    }
	    
	    
	}
	
}
