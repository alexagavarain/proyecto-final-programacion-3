package utils;

import com.formdev.flatlaf.extras.FlatSVGIcon;

public class IconLoader {
    
    private IconLoader() {}

    public static FlatSVGIcon getIcon(String path, int w, int h) {
        java.net.URL url = IconLoader.class.getResource(path);
        
        if (url != null) {
            FlatSVGIcon icon = new FlatSVGIcon(url);
            return icon.derive(w, h);
        } else {
            System.err.println("[IconLoader] ERROR: No se encontró el archivo en: " + path);
            String cleanPath = path.startsWith("/") ? path.substring(1) : path;
            return new FlatSVGIcon(cleanPath).derive(w, h);
        }
    }
}