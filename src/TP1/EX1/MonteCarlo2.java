package TP1.EX1;

public class MonteCarlo2 {
    public static void main(String[] args) {
        System.out.println(monteCarlo2(10));
    }

    public static double monteCarlo2(double r){
        double c = 0;
        int n = 10_000_000;
        for (int i = 0; i < n; i++) {
            double x = Math.random()*r;
            double y = Math.random()*r;
            if(x*x + y*y <= r*r){
                c++;
            }
        }
       return 4*c/n;
    }
}
