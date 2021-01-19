package TP1.EX1;

public class MonteCarlo1 {
    public static void main(String[] args) {
        double s = 0;
        double n = 10_000_000;
        for (int i = 0; i < n; i++) {
            double x = Math.random();
            double y = Math.sqrt(1 - x*x);
            s += y;
        }
        System.out.println(4*s/n);
    }
}
