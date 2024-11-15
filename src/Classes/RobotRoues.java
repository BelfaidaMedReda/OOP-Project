package Classes;

import gui.GUISimulator;
import gui.ImageElement;

import java.awt.*;

public class RobotRoues extends Robot{
    public RobotRoues(){
        this.vitesse = 80;
        this.eauMax= 5000;
        this.eau = this.eauMax;
        this.tempsRemplissageComplet = 10*60;
        this.tempsIntervention=5;
        this.volumeIntervention=100;
        this.state = TypesEnums.RobotState.FREE;
    }
    public RobotRoues(double vitesse){
        this.vitesse = vitesse;
        this.eauMax= 5000;
        this.eau = this.eauMax;
        this.tempsRemplissageComplet = 10*60;
        this.tempsIntervention=5;
        this.volumeIntervention=100;
        this.state = TypesEnums.RobotState.FREE;
    }
    @Override
    public void setPosition(Case position) {
        switch (position.getNature()) {
            case TERRAIN_LIBRE, HABITAT -> {
                this.position = position;
            }
            default -> {
                throw new IllegalArgumentException("Le robot ne peut pas aller sur cette case.");
            }
        }
    }

    @Override
    public double getVitesse(TypesEnums.NatureTerrain natureTerrain) {
        return this.vitesse;
    }

    @Override
    public void draw(GUISimulator gui, int tailleCase, int dx, int dy, java.awt.image.ImageObserver ob)
    {

        int robotSize = tailleCase / 2;
        Color borderColor = Color.BLACK;
        // Calculate position to center the robot in its cell
        int xPosition = dx + (position.getColonne()+1) * tailleCase;
        int yPosition = dy + (position.getLigne()+1) * tailleCase;

        // Draw the robot rectangle at the calculated position
        gui.addGraphicalElement(new ImageElement(xPosition-robotSize/2,yPosition- robotSize/2,"img/roues.png", robotSize,robotSize, ob));

    }

}
