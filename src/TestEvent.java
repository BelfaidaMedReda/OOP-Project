import Classes.*;
import Data.DonneesSimulation;
import gui.GUISimulator;
import io.LecteurDonnees;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

public class TestEvent {
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
        // On poste un événement [PING] tous les deux pas de temps
        for (int i = 2 ; i <= 10 ; i += 2) {
            simulateur.ajouteEvenement(new EvenementMessage(i, " [PING]")) ;
        }
         // On poste un événement [PONG] tous les trois pas de temps
        for (int i = 3 ; i <= 9 ; i += 3) {
            simulateur.ajouteEvenement(new EvenementMessage(i, " [PONG]")) ;
        }

        // et on suppose que la simulation démarre


    }
}
