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

public class TestStrategy {

    public static void main(String[] args) throws DataFormatException, FileNotFoundException {
        String fichierDonnees = "cartes/carteSujet.map";  // Chemin vers le fichier de carte
        DonneesSimulation donnees = LecteurDonnees.creeDonnees(fichierDonnees);

        int largeur = 600;
        int hauteur = 800;
        GUISimulator gui = new GUISimulator(largeur, hauteur, Color.BLACK);  // Initialisation de la GUI

        Simulateur simulateur = new Simulateur(gui, donnees);

    }

}
