package TP2.EX1;

import org.graphstream.graph.implementations.MultiGraph;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Graphe {
    private HashMap<Integer, Sommet> sommets;
    private Sommet sommetCourant;
    private MultiGraph graph;
    private boolean isDisplayGraph;

    public Graphe() throws FileNotFoundException {
        isDisplayGraph = true;
        sommets = new HashMap<>();
        this.graph = new MultiGraph("Graphe");
        Scanner sc = new Scanner(new File("src/TP2/EX1/graph.css"));
        StringBuilder sb = new StringBuilder();
        while(sc.hasNext()){
            sb.append(sc.next());
        }
        graph.setAttribute("ui.stylesheet", sb.toString());
    }
    public Graphe(boolean isDisplayGraph) throws FileNotFoundException {
        this.isDisplayGraph = isDisplayGraph;
        sommets = new HashMap<>();
//        this.graph = new MultiGraph("Graphe");
        Scanner sc = new Scanner(new File("src/TP2/EX1/graph.css"));
        StringBuilder sb = new StringBuilder();
        while(sc.hasNext()){
            sb.append(sc.next());
        }
//        graph.setAttribute("ui.stylesheet", sb.toString());
    }

    public void addSommet(Sommet sommet){
        sommets.put(sommet.getIndex(), sommet);
        if(isDisplayGraph)
            graph.addNode(sommet.getIndex() + "");
    }

    public Sommet getSommet(int index){
        return sommets.get(index);
    }

    public boolean isDisplayGraph(){ return isDisplayGraph;}

    public static Graphe genererGraphe(int nbSommets, double densite) throws FileNotFoundException {
        Graphe graphe = new Graphe();
        return generateGrapheContent(graphe, nbSommets, densite);
    }
    public static Graphe genererGraphe(int nbSommets, double densite, boolean isDisplayGraph) throws FileNotFoundException {
        Graphe graphe = new Graphe(isDisplayGraph);

        return generateGrapheContent(graphe, nbSommets, densite);
    }

    private static Graphe generateGrapheContent(Graphe graphe, int nbSommets, double densite){

        for (int i = 0; i < nbSommets; i++) {
            Sommet sommet = new Sommet(i);
            graphe.addSommet(sommet);
        }
        for (int i = 0; i < nbSommets; i++) {
            Sommet sommet = graphe.getSommet(i);
            for (int j = 0; j < nbSommets; j++) {
                if(i != j){
                    double rand = ThreadLocalRandom.current().nextDouble();
                    if(rand <= densite){
                        sommet.addVoisin(graphe.getSommet(j));
                        if(graphe.isDisplayGraph()){
                            String iS = i+"";
                            String jS = j+"";
                            graphe.getGraph().addEdge(iS+"_"+jS, iS, jS);
                        }
                    }
                }
            }
        }
        return graphe;

    }

    public void colorNode(int index, boolean start){
        if(start)
            graph.getNode(index+"").setAttribute("ui.class", "start");
        else
            graph.getNode(index+"").setAttribute("ui.class", "destination");
    }

    public void resetNodeStyle(int index){
        graph.getNode(index+"").setAttribute("ui.class", "default");
    }

    private void resetEdgeStyle(String edgeName){
        graph.getEdge(edgeName).setAttribute("ui.class", "default");
    }

    private void colorEdge(String edgeName){
        if(graph.getEdge(edgeName) != null)
            graph.getEdge(edgeName).setAttribute("ui.class", "colored");
    }

    public MultiGraph getGraph(){ return graph;}

    public void print(){
        for (int i = 0; i < sommets.size(); i++) {
            System.out.println(sommets.get(i));
        }
    }

    public int tempsCouverture(){
        sommetCourant = sommets.get(0);
        HashSet<Sommet> parcourus = new HashSet<>();
        parcourus.add(sommetCourant);
        int nbIt = 0;
        while(parcourus.size() != sommets.size()){
            sommetCourant = sommetCourant.getRandomVoisin();
            parcourus.add(sommetCourant);
            nbIt++;
        }
        return nbIt;
    }

    public int marcheAleatoire(int indexDest) throws InterruptedException {
        sommetCourant = sommets.get(0);
        if(isDisplayGraph){
            colorNode(0, true);
            colorNode(indexDest, false);
        }
        boolean found = false;
        int nbIt = 0;
        while(!found){
            nbIt++;
            Sommet suivant = sommetCourant.getRandomVoisin();
            if(suivant.getIndex() == indexDest){
                found = true;
            }
            String edgeName = sommetCourant.getIndex()+"_"+ suivant.getIndex();
            Thread.sleep(50);
            if(isDisplayGraph)
                colorEdge(edgeName);
            sommetCourant = suivant;
        }
        return nbIt;
    }

    public void afficherGraphe(){
        graph.display();
    }
}
