package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import controllers.HomeController;
import controllers.LoginController;
import controllers.RegisterController;
import models.Group;
import models.Session;
import models.User;
import repository.UserRepository;
import utils.CreateFont;

public class Main {

	public static void main(String[] args) {
		
		UserRepository repo = new UserRepository();
		
		FlatLightLaf.setup();
		UIManager.put("Panel.background", Color.WHITE);
		
		CreateFont.loadFont();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					RegisterView frame = new RegisterView();
//					new RegisterController(frame);
//					Session.setCurrentUser(repo.getUser(1));
//					System.out.println(Session.getCurrentUser().getGroup().getId());
//					
//					
//					HomeView frame = new HomeView();
//					new HomeController(frame);
					LoginView frame = new LoginView();
					new LoginController(frame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

}
