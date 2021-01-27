package TP1.EX3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SimplePolygonGenerator {
    public static PolygoneListe genetatePolygon(int nbSommets, double valMax){
        double[] xValues =  new double[nbSommets];
        double[] yValues = new double[nbSommets];
        ArrayList<Double> radValues = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < nbSommets; i++) {
            double rad = (10 * r.nextDouble())%(2*Math.PI);
            radValues.add(rad);
        }
        Collections.sort(radValues);
        for (int i = 0; i < nbSommets; i++) {
            double x = Math.cos(radValues.get(i));
            double y = Math.sin(radValues.get(i));
            xValues[i] = x;
            yValues[i] = y;
        }
        return  new PolygoneListe(xValues, yValues, 1);
    }
}
