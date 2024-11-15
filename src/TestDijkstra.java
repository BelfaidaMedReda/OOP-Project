import Classes.Carte;
import Classes.Case;
import Classes.Robot;
import Classes.Simulateur;
import Data.DonneesSimulation;
import gui.GUISimulator;
import io.LecteurDonnees;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.zip.DataFormatException;

public class TestDijkstra {
    public static void main(String[] args) throws DataFormatException, FileNotFoundException {
        String fichierDonnees = "cartes/carteSujet.map";  // Chemin vers le fichier de carte
        DonneesSimulation donnees = LecteurDonnees.creeDonnees(fichierDonnees);

        int largeur = 800;
        int hauteur = 600;
        GUISimulator gui = new GUISimulator(largeur, hauteur, Color.WHITE);  // Initialisation de la GUI

        Simulateur simulateur = new Simulateur(gui, donnees);

        Carte carte = donnees.getCarte();
        ArrayList<Robot> robotList = donnees.getRobotList(); // Liste des robots


        Case destination = carte.getCase(7, 0);

        //simulateur.planifierDeplacement(robotList,2, destination);

    }
}


