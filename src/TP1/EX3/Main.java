package TP1.EX3;

public class Main {
    public static void main(String[] args) {
        double valeurMax = 10;
        double valeursX[] = {0.25 * valeurMax, 0.25 * valeurMax, 0.75 * valeurMax, 0.75 * valeurMax};
        double valeursY[] = {0.25 * valeurMax, 0.75 * valeurMax, 0.75 * valeurMax, 0.25 * valeurMax};
        double valeursX2[] = {2.5, 2.5, 7.5, 7.5};
        double valeursY2[] = {2.5, 7.5, 7.5, 2.5};

//        PolygoneListe polygoneListe = new PolygoneListe(valeursX, valeursY, valeurMax);
        PolygoneListe polygoneListe = SimplePolygonGenerator.genetatePolygon(50,1);
        DrawPolygon.draw(polygoneListe);

//        System.out.println(polygoneListe.contient(new Point2D(0, 0)));
//        System.out.println(polygoneListe.contient(new Point2D(2.5, 5)));
//        System.out.println(polygoneListe.contient(new Point2D(5, 5)));
//        System.out.println(polygoneListe.contient(new Point2D(9, 1)));

        System.out.println(monteCarlo(polygoneListe, valeurMax, 10_000_000));
        System.out.println(formuleEnLacet(polygoneListe));
    }

    private static double monteCarlo(PolygoneListe polygoneListe, double valeurMax, double nbIterations){
        double c = 0;
        for (int i = 0; i < nbIterations; i++) {
            Point2D point = new Point2D(Math.random() * valeurMax, Math.random() * valeurMax);
            if(polygoneListe.contient(point)){
                c++;
            }
        }

        return 10*c*valeurMax/nbIterations;
    }

    private static double formuleEnLacet(PolygoneListe polygoneListe){
        double somme = 0;
        for (int i = 0; i < polygoneListe.size(); i++) {
            somme += polygoneListe.getSommet(i).getX() * polygoneListe.getSommet(i+1).getY();
        }
        for (int i = 0; i < polygoneListe.size(); i++) {
            somme -= polygoneListe.getSommet(i).getY() * polygoneListe.getSommet(i+1).getX();
        }
        // Ici, sans le *-1 (-2.0) on obtient -(l'aire). C'est surement du au sens de rotation de l'ordre des sommets
        return somme/2.0;
    }
}
