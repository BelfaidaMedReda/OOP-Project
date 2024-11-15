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
        String fichierDonnees = "cartes/spiralOfMadness-50x50.map";  // Chemin vers le fichier de carte
        DonneesSimulation donnees = LecteurDonnees.creeDonnees(fichierDonnees);

        int largeur = 1200;
        int hauteur = 1200;
        GUISimulator gui = new GUISimulator(largeur, hauteur, Color.WHITE);  // Initialisation de la GUI

        Simulateur simulateur = new Simulateur(gui, donnees);

    }

}
