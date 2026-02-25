package utils;

import java.awt.Font;

import javax.swing.UIManager;

public class CreateFont {
	
	public static Font DEFAULT;
		
	public static void loadFont() {
        try {
            Font font = Font.createFont(
                Font.TRUETYPE_FONT,
                CreateFont.class.getResourceAsStream("/assets/segoeuithis.ttf")
            ).deriveFont(13f);

            UIManager.put("defaultFont", DEFAULT);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
