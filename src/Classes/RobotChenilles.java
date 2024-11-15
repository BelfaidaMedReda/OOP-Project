package Classes;

import gui.GUISimulator;
import gui.ImageElement;
import gui.Rectangle;

import java.awt.*;

public class RobotChenilles extends Robot{
    double vitesseMax = 80;
    public RobotChenilles(double vitesse) {
        this.vitesse = Math.min(vitesse,vitesseMax);
        this.eauMax= 2000;
        this.eau = this.eauMax;
        this.tempsRemplissageComplet = 5*60;
        this.tempsIntervention=8;
        this.volumeIntervention=100;
        this.state = TypesEnums.RobotState.FREE;
    }
    public RobotChenilles() {
        this.vitesse = 60;
        this.eauMax= 2000;
        this.eau = this.eauMax;
        this.tempsRemplissageComplet = 5*60;
        this.tempsIntervention=8;
        this.volumeIntervention=100;
        this.state = TypesEnums.RobotState.FREE;
    }
    @Override
    public void setPosition(Case position) {
        switch (position.getNature()) {
            case ROCHE -> {
                throw new IllegalArgumentException("La case contient de la roche, le robot ne peut pas y aller.");
            }
            case EAU -> {
                throw new IllegalArgumentException("La case contient de l'eau, le robot ne peut pas y aller.");
            }
            default -> {
                this.position = position;
            }
        }
    }
        @Override
    public double getVitesse(TypesEnums.NatureTerrain natureTerrain) {
        switch (natureTerrain) {
            case FORET -> {
                return this.vitesse/2;
            }
            case ROCHE -> {
                throw new IllegalArgumentException("La case contient de la roche, le robot ne peut pas y aller.");
            }
            case EAU -> {
                throw new IllegalArgumentException("La case contient de l'eau, le robot ne peut pas y aller.");
            }
            default -> {
                return this.vitesse;
            }
        }
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
        gui.addGraphicalElement(new ImageElement(xPosition-robotSize/2,yPosition- robotSize/2,"img/chenille.png", robotSize,robotSize, ob));

    }
}
