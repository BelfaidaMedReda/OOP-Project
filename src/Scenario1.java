import Classes.*;
import Classes.Robot;
import Data.DonneesSimulation;
import gui.GUISimulator;
import io.LecteurDonnees;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.zip.DataFormatException;

public class Scenario1 {
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
        for(int i=1;i<5;i++){
            Evenement e = new Deplacement(i,carte,RobotList,0, TypesEnums.Direction.NORD);
            simulateur.ajouteEvenement(e);
        }

    }
}
