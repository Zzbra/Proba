package TP1.EX3;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        double valeurMax = 1;
        double valeursX[] = {0.25 * valeurMax, 0.25 * valeurMax, 0.75 * valeurMax, 0.75 * valeurMax};
        double valeursY[] = {0.25 * valeurMax, 0.75 * valeurMax, 0.75 * valeurMax, 0.25 * valeurMax};

        PolygoneListe polygoneListe = new PolygoneListe(valeursX, valeursY, valeurMax);
//        PolygoneListe polygoneListe = SimplePolygonGenerator.genetatePolygon(50,1);
        Drawer drawer = new Drawer();
        drawer.draw(polygoneListe);

        System.out.println("Monte Carlo: " + monteCarlo(polygoneListe, valeurMax, 10_000_000, drawer));
        System.out.println("Lacet: " + formuleEnLacet(polygoneListe));
    }

    private static double monteCarlo(PolygoneListe polygoneListe, double valeurMax, double nbIterations, Drawer drawer){
        double c = 0;
        for (int i = 0; i < nbIterations; i++) {
            Point2D point = new Point2D(Math.random() * valeurMax, Math.random() * valeurMax);
            Color color = Color.RED;
            if(polygoneListe.contient(point)){
                c++;
                color = Color.GREEN;
            }
            drawer.draw(point, color);
        }

        return c*valeurMax/nbIterations;
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
