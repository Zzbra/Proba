package TP1.EX2;

public class CalculE {
    public static void main(String[] args) {
        System.out.println(estimerE(10_000_000));
    }

    private static double estimerE(double N){
        double somme = 0;
        for (int i = 0; i < N; i++) {
            somme += findN();
        }
        return somme/N;
    }

    private static int findN(){
        double somme = 0;
        int n = 0;
        while(somme <= 1){
            double r = Math.random();
            somme += r;
            n++;
        }
        return n;
    }
}
