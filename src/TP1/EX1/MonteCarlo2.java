package TP1.EX1;

import TP1.Util.Drawer;
import TP1.Util.Point2D;

import java.awt.*;

public class MonteCarlo2 {
    public static double main(double n) {
        double result = monteCarlo2(n,10);
        return result;
    }

    public static double monteCarlo2(double n, double r){
        Drawer drawer = new Drawer(r);
        double c = 0;
        Color color = null;
        for (int i = 0; i < n; i++) {
            color = Color.LIGHT_GRAY;
            double x = Math.random()*r;
            double y = Math.random()*r;
            if(x*x + y*y <= r*r){
                c++;
                color = Color.cyan;
            }
            drawer.draw(new Point2D(x, y), color);
        }
       return 4*c/n;
    }
}
