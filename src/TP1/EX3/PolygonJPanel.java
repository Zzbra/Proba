package TP1.EX3;

import java.awt.*;
import javax.swing.JPanel;

public class PolygonJPanel extends JPanel {
    private PolygoneListe polygoneListe;
    int width, height;

    public PolygonJPanel(PolygoneListe polygoneListe, int width, int height){
        super();
        this.polygoneListe = polygoneListe;
        this.width = width;
        this.height = height;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        int xValues[] = new int[polygoneListe.size()];
        int yValues[] = new int[polygoneListe.size()];
        for (int i = 0; i < polygoneListe.size(); i++) {
            xValues[i] = (int)(polygoneListe.getSommet(i).getX()* width/polygoneListe.getValeurMax());
            yValues[i] = (int)(polygoneListe.getSommet(i).getY()*height/polygoneListe.getValeurMax());
        }
        Polygon polygon = new Polygon( xValues, yValues, polygoneListe.size());
        g.fillPolygon(polygon);

    }
}
