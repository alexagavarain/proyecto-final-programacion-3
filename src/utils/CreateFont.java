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
                CreateFont.class.getResourceAsStream("/assets/segoeuithis.ttf")
            ).deriveFont(13f);

            DEFAULT_BOLD = DEFAULT.deriveFont(Font.BOLD);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	

}
