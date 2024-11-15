import Classes.*;
import Classes.Robot;
import Data.DonneesSimulation;
import gui.GUISimulator;
import io.LecteurDonnees;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.zip.DataFormatException;

public class Scenario2 {
    public static void main(String[] args) throws DataFormatException, FileNotFoundException {
        // On crée un simulateur
        String fichierDonnees = "cartes/carteSujet.map";
        // Load data from file
        DonneesSimulation donnees = LecteurDonnees.creeDonnees(fichierDonnees);
        int largeur = 800;
        int hauteur = 600;
        // Initialize GUI with calculated dimensions
        GUISimulator gui = new GUISimulator(largeur, hauteur, Color.WHITE);
        // Initialize and run simulation with loaded data

        Simulateur simulateur = new Simulateur(gui, donnees);

        Carte carte = donnees.getCarte();
        ArrayList<Robot> RobotList = donnees.getRobotList();
        // On fait bouger le 1er robot
        Evenement e = new Deplacement(1,carte,RobotList,1, TypesEnums.Direction.NORD);
        simulateur.ajouteEvenement(e);
        e = new Extinction(2,donnees.getIncendieList(),RobotList,1);
        simulateur.ajouteEvenement(e);
        e = new Deplacement(3,carte,RobotList,1, TypesEnums.Direction.OUEST);
        simulateur.ajouteEvenement(e);
        e = new Deplacement(4,carte,RobotList,1, TypesEnums.Direction.OUEST);
        simulateur.ajouteEvenement(e);
        e = new Remplissage(5,carte,RobotList,1);
        simulateur.ajouteEvenement(e);
        e = new Deplacement(6,carte,RobotList,1, TypesEnums.Direction.EST);
        simulateur.ajouteEvenement(e);
        e = new Deplacement(7,carte,RobotList,1, TypesEnums.Direction.EST);
        simulateur.ajouteEvenement(e);
        e = new Extinction(8,donnees.getIncendieList(),RobotList,1);
        simulateur.ajouteEvenement(e);
    }
}
