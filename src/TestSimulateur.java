import Classes.*;
import Data.DonneesSimulation;
import gui.GUISimulator;
import gui.Rectangle;
import io.*;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

public class TestSimulateur {
    public static void main(String[] args) {

        String fichierDonnees = null;
        try {
            fichierDonnees = "cartes/carteSujet.map";
            // Load data from file
            DonneesSimulation donnees = LecteurDonnees.creeDonnees(fichierDonnees);
            int largeur = 800;
            int hauteur = 600;
            // Initialize GUI with calculated dimensions
            GUISimulator gui = new GUISimulator(largeur, hauteur, Color.WHITE);
            // Initialize and run simulation with loaded data

            Simulateur simulateur = new Simulateur(gui, donnees);
            System.out.println("End here");

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + fichierDonnees);
        } catch (DataFormatException e) {
            System.err.println("Data format error: " + e.getMessage());
        }
    }
}