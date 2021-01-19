package TP1.EX3;

import javax.swing.*;

public class DrawPolygon {
    public static void draw(PolygoneListe polygoneListe) {
        JFrame frame = new JFrame("Dessiner Polygone");
        int width = 500;
        int height = 500;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        PolygonJPanel polygonJPanel = new PolygonJPanel(polygoneListe, width, height);
        frame.add(polygonJPanel);
        frame.setSize(width, height);
        frame.setVisible(true);
    }
}
