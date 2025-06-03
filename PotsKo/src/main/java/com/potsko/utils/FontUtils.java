package com.potsko.utils;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

// Utility class that loads and cahe custom fonts 
public class FontUtils {
    private static final Map<String, Font> fontCache = new HashMap<>();

    // This load foants and caches it for reuse, its better for code efficiency
    public static Font getFont(String fontFileName, float size) {
        String cacheKey = fontFileName + "-" + size;
        if (fontCache.containsKey(cacheKey)) {
            return fontCache.get(cacheKey);
        }

        // Checks all of the files in /font inside src 
        try (InputStream is = FontUtils.class.getResourceAsStream("/font/" + fontFileName)) {
            if (is == null) {
                System.err.println("Font not found:" + fontFileName);
                return null;
            }

            Font font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(size);
            fontCache.put(cacheKey, font);
            return font;

        } catch (FontFormatException | IOException e) {
            System.err.println("Error loading font: " + fontFileName + "->" +  e.getMessage());
            return null;
        }
    }
}
