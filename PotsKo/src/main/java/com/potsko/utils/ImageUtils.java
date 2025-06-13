package com.potsko.utils;

import java.awt.Image;

import javax.swing.ImageIcon;

// Util class for loading images and scalling 
public class ImageUtils {
    
    public static ImageIcon loadImage(String path) {
        java.net.URL iconUrl = ImageUtils.class.getResource(path);
        // conditionals 

        if (iconUrl != null) {
            return new ImageIcon(iconUrl);
        } else {
            System.err.println("Icon / Image not found: " + path);
            return null;
        }
    }
 
    public static ImageIcon scaledIcon(String path, int width, int height) {
        ImageIcon icon = loadImage(path);
        // condtionals 

        if (icon != null) {
            Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        }
        return null;
    }
}