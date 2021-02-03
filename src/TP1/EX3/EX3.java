package TP1.EX3;

import TP1.Util.Drawer;
import TP1.Util.Point2D;
import TP1.Util.PolygoneListe;
import TP1.Util.SimplePolygonGenerator;

import java.awt.*;

public class EX3 {
    public static void main(Boolean carre, double valeurMax, int nbSommets) throws InterruptedException {
       PolygoneListe polygoneListe = null;
        if(carre){
            double valeursX[] = {0.25 * valeurMax, 0.25 * valeurMax, 0.75 * valeurMax, 0.75 * valeurMax};
            double valeursY[] = {0.25 * valeurMax, 0.75 * valeurMax, 0.75 * valeurMax, 0.25 * valeurMax};
            polygoneListe = new PolygoneListe(valeursX, valeursY, valeurMax);
        }else{
            polygoneListe = SimplePolygonGenerator.genetatePolygon(50,valeurMax);
        }
        Drawer drawer = new Drawer(valeurMax);
        drawer.draw(polygoneListe);
        Thread.sleep(1000);
        System.out.println("Monte Carlo:\t" + monteCarlo(polygoneListe, valeurMax, 10_000_000, drawer));
        System.out.println("Lacet:\t\t\t" + formuleEnLacet(polygoneListe));
    }

    private static double monteCarlo(PolygoneListe polygoneListe, double valeurMax, double nbIterations, Drawer drawer){
        double c = 0;
        for (int i = 0; i < nbIterations; i++) {
            Point2D point = new Point2D(Math.random() * valeurMax, Math.random() * valeurMax);
            Color color = Color.LIGHT_GRAY;
            if(polygoneListe.contient(point)){
                c++;
                color = Color.cyan;
            }
            drawer.draw(point, color);
        }

        return c*valeurMax*valeurMax/nbIterations;
    }

    private static double formuleEnLacet(PolygoneListe polygoneListe){
        double somme = 0;
        for (int i = 0; i < polygoneListe.size(); i++) {
            somme += polygoneListe.getSommet(i).getX() * polygoneListe.getSommet(i+1).getY();
        }
        for (int i = 0; i < polygoneListe.size(); i++) {
            somme -= polygoneListe.getSommet(i).getY() * polygoneListe.getSommet(i+1).getX();
        }
        // Valeur absolue car le signe dépend du sens de rotation de l'ordre des sommets
        return Math.abs(somme/2.0);
    }
}
