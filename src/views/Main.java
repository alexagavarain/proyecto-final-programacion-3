package views;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

import controllers.HomeController;
import controllers.LoginController;
import utils.CreateFont;

public class Main {

	public static void main(String[] args) {
		
		FlatLightLaf.setup();
		
		CreateFont.loadFont();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
