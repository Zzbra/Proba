package TP1.EX3;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MyCanvas {
    JLabel view;
    BufferedImage surface;
    int width, height;
    public MyCanvas(int width, int height){
        this.width = width;
        this.height = height;
        surface = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        view = new JLabel(new ImageIcon(surface));
    }

    public void drawPolygon(PolygoneListe polygoneListe){
        Graphics g = surface.getGraphics();
        int[] xValues = new int[polygoneListe.size()];
        int[] yValues = new int[polygoneListe.size()];
        for (int i = 0; i < polygoneListe.size(); i++) {
            xValues[i] = (int)(polygoneListe.getSommet(i).getX()* width/polygoneListe.getValeurMax());
            yValues[i] = (int)(polygoneListe.getSommet(i).getY()*height/polygoneListe.getValeurMax());
        }
        Polygon polygon = new Polygon( xValues, yValues, polygoneListe.size());
        g.fillPolygon(polygon);
        g.dispose();
        view.repaint();
    }

    public void drawPoint(Point2D point, Color color){
        Graphics2D g2d = (Graphics2D) surface.getGraphics();
        g2d.setColor(color);
        int x = (int) (point.getX() * width);
        int y = (int) (point.getY() * height);
        g2d.drawLine(x, y, x, y);
        g2d.dispose();
        view.repaint();
    }

    public JLabel getView() {
        return view;
    }
}