package Classes;
import Data.DonneesSimulation;
import gui.*;

import java.awt.*;
import java.util.*;
import java.awt.image.ImageObserver;
import java.util.List;


public class Simulateur implements Simulable {
    private GUISimulator gui;
    private Carte carte;
    private ArrayList<Robot> robots;
    private ArrayList<Incendie> incendies;
    private long Currentdate ;
    private ArrayList<Evenement> evenements;
    private DonneesSimulation InitialData;
    private Chef chef;
    //int pasTemps = 1000;


    public Simulateur(GUISimulator gui,DonneesSimulation donnees) {
        this.gui = gui;
        gui.setSimulable(this);				// association a la gui!
        this.chef = new Chef(this,donnees);
        // Step 1: Load data from file using LecteurDonnees
        this.carte = donnees.getCarte();
        this.robots = donnees.getRobotList();
        this.incendies = donnees.getIncendieList();
        this.evenements = new ArrayList<Evenement>();
        this.Currentdate = 0;
        try{
            this.InitialData = donnees.clone();
        }
        catch (CloneNotSupportedException e){
            System.out.println("Les données ne peut pas être copiées");
        }

        // Step 2: Initialize GUI and draw elements
        drawElements();
    }



    private void drawElements() {
        gui.reset();// Get dynamic case size from Carte
        int nbLignes = carte.getnbLignes();
        int nbColonnes = carte.getnbColonnes();
        int scale_x = gui.getPanelWidth() / nbColonnes;
        int scale_y = gui.getPanelHeight() / nbLignes;
        int size = Math.min(scale_x, scale_y);

        int space_x = (scale_x < scale_y)?  0 : (gui.getPanelWidth()-size*nbColonnes)/2;
        int space_y = (scale_x < scale_y)? (gui.getPanelHeight()-size*nbLignes)/2 : 0;
        ImageObserver myObserver = new ImageObserver() {
            @Override
            public boolean imageUpdate(
                    Image image, int flags, int x, int y, int width, int height)
            {
                if ( (flags & HEIGHT) !=0 )
                    System.out.println("Image height = " + height );
                if ( (flags & WIDTH ) !=0 )
                    System.out.println("Image width = " + width );
                if ( (flags & FRAMEBITS) != 0 )
                    System.out.println("Another frame finished.");
                if ( (flags & SOMEBITS) != 0 )
                    System.out.println("Image loading");
                if ( (flags & ALLBITS) != 0 )
                    System.out.println("Image finished!");
                if ( (flags & ABORT) != 0 )
                    System.out.println("Image load aborted...");
                return true;
            }
        };
        for (int i=0;i<nbLignes;i++){
            for(int j=0;j<nbColonnes;j++){
                Case current = carte.getCase(i,j);
                current.draw(gui,space_x + size * (j+1) ,space_y + size * (i+1),size,myObserver);
            }
        }
        for(Incendie ic:incendies){
            ic.draw(gui,size,space_x,space_y,myObserver);
        }

        for(Robot robot:robots){
            robot.draw(gui,size,space_x,space_y,myObserver);
        }

    }

    // Méthode pour calculer le plus court chemin et planifier les déplacements
    public boolean planifierDeplacement(ArrayList<Robot> robots,int index, Case destination) {

        Dijkstra dijkstra = new Dijkstra(carte);
        List<Case> chemin = dijkstra.recherchePlusCourtChemin(robots.get(index), robots.get(index).getPosition(), destination);

        if (chemin.isEmpty()) {
            System.out.println("Aucun chemin trouvé !");
            return false;
        }
        for (int i = 1; i < chemin.size(); i++) {
            Case nextCase = chemin.get(i);
            Evenement deplacement = new Deplacement((long) (Currentdate + (int)(i* 3.6*carte.getTailleCases()/robots.get(index).vitesse)),this.carte,robots,index, nextCase);
            ajouteEvenement(deplacement);
        }
        return true;
    }
    public void ajouteExtinction(int index,int volume)
    {

        Evenement e = new Extinction(Currentdate+ Math.max((int)(robots.get(index).tempsIntervention * volume / robots.get(index).volumeIntervention),1), this.incendies,this.robots,index);
        ajouteEvenement(e);
    }
    public void ajouteRemplissage(int index,Carte carte)
    {
        System.out.println(robots.get(index).tempsRemplissageComplet);
        Evenement e = new Remplissage(Currentdate+robots.get(index).tempsRemplissageComplet,carte,this.robots,index);
        ajouteEvenement(e);
    }
    public void ajouteEvenement(Evenement e){
        if(e.getDate() > Currentdate){
            evenements.add(e);
        }
        else{
            throw  new IllegalArgumentException("Impossible d'ajouter une date antérieure à la date de simulation courante");
        }
    }

    public void  incrementeDate(){
        Currentdate++;
    }


    public boolean simulationTerminee(){
        return evenements.isEmpty();
    }



    @Override
    public void next() {// Met à jour l'affichage de la GUI
        chef.strategieElementaire();
        incrementeDate();
        // Exécute tous les événements correspondant à la date courante
        //System.out.println("Current date : "+Currentdate);
        if(!simulationTerminee()) {
            for (Evenement elem : evenements) {
                if(Currentdate== elem.getDate()){
                    elem.execute();
                }
            }
        }
        drawElements();
    }

    @Override
    public void restart() {
        try{
            DonneesSimulation data = InitialData.clone();
            this.carte = data.getCarte();
            this.robots = data.getRobotList();
            this.incendies = data.getIncendieList();
        }
        catch (CloneNotSupportedException e){
            System.out.println("Les données ne peut pas être copiées");
        }
        // Réinitialise la simulation avec les données clonées

        for (Evenement evenement : evenements)
        {
            if(evenement instanceof Deplacement)
            {
                ((Deplacement) evenement).reset(this.robots,carte);
            } else if (evenement instanceof Extinction) {
                ((Extinction) evenement).reset(this.robots,this.incendies);
            } else if (evenement instanceof Remplissage) {
                ((Remplissage) evenement).reset(this.carte,this.robots);

            }
        }

        // Réinitialiser l'état
        Currentdate = 0;

        // Redessiner la GUI
        drawElements();
    }


}

