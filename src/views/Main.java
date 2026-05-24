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
import models.Session;
import models.User;
import utils.CreateFont;

public class Main {

	public static void main(String[] args) {
		
		FlatLightLaf.setup();
		UIManager.put("Panel.background", Color.WHITE);
		
		CreateFont.loadFont();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					RegisterView frame = new RegisterView();
//					new RegisterController(frame);
//					Session.setCurrentUser(new User (1));
					
					
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
