package TP1;

import TP1.EX1.ComparaisonEx1;
import TP1.EX1.MonteCarlo1;
import TP1.EX1.MonteCarlo2;
import TP1.EX2.CalculE1;
import TP1.EX2.CalculE2;
import TP1.EX2.ComparaisonEx2;
import TP1.EX3.EX3;

public class Main {
    // Les compaisons sont commentées car elles prennent un peu de temps
    public static void main(String[] args) throws InterruptedException {
        System.out.println("\nRésultats du TP\n");
        System.out.println("Exercice 1:");
        testExercice1();
//        ComparaisonEx1.main(null);
        System.out.println("\nExercice 2:");
        testExercice2();
//        ComparaisonEx2.main(null);
        System.out.println("\nExercice 3:");
        testExercice3();
    }

    private static void testExercice1() throws InterruptedException {
        double n = 10_000_000;
        System.out.printf("Valeur de Pi avec 1ère méthode:\t");
        System.out.println(MonteCarlo1.main(n, true));
        System.out.printf("Valeur de Pi avec 2ème méthode:\t");
        System.out.println(MonteCarlo2.main(n, true));
    }

    private static void testExercice2(){
        double n = 10_000_000;
        System.out.printf("Valeur de e avec 1ère méthode:\t");
        System.out.println(CalculE1.main(n));
        System.out.printf("Valeur de e avec 2ème méthode:\t");
        System.out.println(CalculE2.main(n));
    }
    // Pour l'exercice 3, on peut faire varier la valeur max (xMax et yMax)
    // et le nombre de sommets dans le cas du polygone
    private static void testExercice3() throws InterruptedException {
        System.out.printf("Méhode Monté Carlo et Lancet pour un carré:\n");
        EX3.main(true, 100, 0, 10_000_000);
        System.out.printf("Méhode Monté Carlo et Lancet pour un polygone simple généré aléatoirement:\n");
        EX3.main(false, 100, 50, 10_000_000);
    }
}
