package views;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import controllers.HomeController;
import controllers.LoginController;
import models.Session;
import repository.UserRepository;
import utils.CreateFont;

public class Main {

	public static void main(String[] args) {
		
		try {
	        FlatLightLaf.setup();
	        UIManager.put("Panel.background", Color.WHITE);
	    } catch (Exception e) {
	        System.err.println("No se pudo inicializar FlatLaf");
	    }
		
		UserRepository repo = new UserRepository();
			
		CreateFont.loadFont();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					RegisterView frame = new RegisterView();
//					new RegisterController(frame);
					
					Session.setCurrentUser(repo.getUser(1));
//					System.out.println(Session.getCurrentUser().getGroup().getCareer());
//					
//					
					HomeView frame = new HomeView();
					new HomeController(frame);
//					LoginView frame = new LoginView();
//					new LoginController(frame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

}
