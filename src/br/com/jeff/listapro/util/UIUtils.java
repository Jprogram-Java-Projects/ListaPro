package br.com.jeff.listapro.util;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class UIUtils {
	
	private static final String ICON_PATH = "resources/icons/";
	
    private UIUtils() {}
    
    // Método para estilizar botões
    public static void styleButton(JButton button, Color backgroundColor) {
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setPreferredSize(new Dimension(120, 35));
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    // Método para carregar ícones dimensionados
    public static ImageIcon loadIcon(String fileName) {
        ImageIcon icon = new ImageIcon(ICON_PATH + fileName);
        Image img = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

}
