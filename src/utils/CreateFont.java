package utils;

import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;
import javax.swing.UIManager;

public class CreateFont {
	
	public static Font DEFAULT;
	public static Font DEFAULT_BOLD;
	public static Font DEFAULT_UNDERLINE;
		
	public static void loadFont() {
        try {
            DEFAULT = Font.createFont(
                Font.TRUETYPE_FONT,
                CreateFont.class.getResourceAsStream("/assets/fonts/inter.ttf")
            ).deriveFont(13f);

            DEFAULT_BOLD = DEFAULT.deriveFont(Font.BOLD);
            
            Map<TextAttribute, Object> attributes = new HashMap<>();
            attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
            DEFAULT_UNDERLINE = DEFAULT.deriveFont(12f).deriveFont(attributes);
            
            UIManager.put("Button.font", DEFAULT.deriveFont(12f));
            UIManager.put("defaultFont", DEFAULT.deriveFont(12f));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}