package utils;

import java.awt.Font;

import javax.swing.UIManager;

public class CreateFont {
	
	public static Font DEFAULT;
	public static Font DEFAULT_BOLD;
		
	public static void loadFont() {
        try {
            DEFAULT = Font.createFont(
                Font.TRUETYPE_FONT,
                CreateFont.class.getResourceAsStream("/assets/fonts/inter.ttf")
            ).deriveFont(13f);

            DEFAULT_BOLD = DEFAULT.deriveFont(Font.BOLD);
            
            UIManager.put("Button.font", DEFAULT.deriveFont(12f));
            UIManager.put("defaultFont", DEFAULT.deriveFont(12f));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	

}
