package views;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

import controllers.LoginController;
import utils.CreateFont;

public class Main {

	public static void main(String[] args) {
		
		try {
	        FlatLightLaf.setup();
	        UIManager.put("Panel.background", Color.WHITE);
	    } catch (Exception e) {
	        System.err.println("No se pudo inicializar FlatLaf");
	    }
					
		CreateFont.loadFont();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {				
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
