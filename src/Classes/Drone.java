package Classes;

import gui.GUISimulator;
import gui.ImageElement;
import gui.Rectangle;

import java.awt.*;
import java.awt.image.ImageObserver;

public class Drone extends Robot{

    public Drone()
    {
        this.eauMax =10000;
        this.vitesse = 100;
        this.tempsRemplissageComplet=30*60;
        this.tempsIntervention = 30;
        this.volumeIntervention=10000;
        this.state = TypesEnums.RobotState.FREE;
    }
    public Drone(int vitesse)
    {
        this.eauMax =10000;
        this.eau = this.eauMax;
        this.vitesse = vitesse;
        this.tempsRemplissageComplet=30*60;
        this.tempsIntervention = 30;
        this.volumeIntervention=10000;
        this.state = TypesEnums.RobotState.FREE;
    }

    @Override
    public void setPosition(Case position) {
        this.position = position;
    }

    @Override
    public double getVitesse(TypesEnums.NatureTerrain natureTerrain) {
        return vitesse;
    }

    @Override
    public void deverserEau(int vol) {
        this.eau -= Math.min(vol,this.eau);
    }

    @Override
    public void remplirReservoir() {
        if (this.position.getNature() == TypesEnums.NatureTerrain.EAU) {
            this.eau = this.eauMax;
        }
        else
        { throw new IllegalArgumentException("La case ne contient pas d'eau, le drone ne peut pas remplir son reservoir.");}
    }
    @Override
    public void draw(GUISimulator gui, int tailleCase, int dx, int dy,java.awt.image.ImageObserver ob)
    {

        int robotSize = tailleCase / 2;
        Color borderColor = Color.BLACK;
        // Calculate position to center the robot in its cell
        int xPosition = dx + (position.getColonne()+1) * tailleCase;
        int yPosition = dy + (position.getLigne()+1) * tailleCase;

        // Draw the robot rectangle at the calculated position
        gui.addGraphicalElement(new ImageElement(xPosition-robotSize/2,yPosition- robotSize/2,"img/drone.png", robotSize,robotSize, ob));

    }
}
