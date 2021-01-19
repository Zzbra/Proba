package TP1.EX3;

import java.util.ArrayList;

public class PolygoneListe {
    private ArrayList<Point2D> sommets;
    private double valeurMax;
    public PolygoneListe(int nbSommets, double valeurMax){
        this.valeurMax = valeurMax;
        sommets = new ArrayList<>();
        for (int i = 0; i < nbSommets; i++) {
            sommets.add(new Point2D(Math.random(), Math.random()));
        }
    }

    public double getValeurMax() {
        return valeurMax;
    }

    public PolygoneListe(double[] valeursX, double[] valeursY, double valeurMax){
        this.valeurMax = valeurMax;
        sommets = new ArrayList<>();
        for (int i = 0; i < valeursX.length; i++) {
            sommets.add(new Point2D(valeursX[i], valeursY[i]));
        }
    }

    public Point2D getSommet(int i){
        return sommets.get(i%sommets.size());
    }

    public int size(){
        return sommets.size();
    }

    public boolean contient(Point2D p){
        Point2D extreme = new Point2D(valeurMax, p.getY());
        int count = 0, i = 0;
        do{
            if(intersect(getSommet(i), getSommet(i+1), p, extreme)){
                if(orientation(getSommet(i), p, getSommet(i+1)) == 0){
                    return estSurSegment(getSommet(i), p, getSommet(i+1));
                }
                count++;
            }
            i++;
        }while(i < sommets.size());
        return (count%2 == 1);
    }

    private int orientation(Point2D p, Point2D q, Point2D r){
        double val = (q.getY() - p.getY()) * (r.getX() - q.getX())
                - (q.getX() - p.getX()) * (r.getY() - q.getY());
        if(val == 0){
            return 0;
        }
        return (val > 0)? 1 : 2; // clock or counterclock wise
    }

    private boolean estSurSegment(Point2D p, Point2D q, Point2D r){
        if (q.getX() <= Math.max(p.getX(), r.getX()) &&
                q.getX() >= Math.min(p.getX(), r.getX()) &&
                q.getY() <= Math.max(p.getY(), r.getY()) &&
                q.getY() >= Math.min(p.getY(), r.getY()))
        {
            return true;
        }
        return false;
    }

    private boolean intersect(Point2D p1, Point2D q1, Point2D p2, Point2D q2){
        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);

        if (o1 != o2 && o3 != o4)
        {
            return true;
        }

        if (o2 == 0 && estSurSegment(p1, q2, q1))
        {
            return true;
        }

        if (o3 == 0 && estSurSegment(p2, p1, q2))
        {
            return true;
        }

        if (o4 == 0 && estSurSegment(p2, q1, q2))
        {
            return true;
        }

        return false;
    }

}
