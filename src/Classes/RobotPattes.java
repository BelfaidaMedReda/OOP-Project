package Classes;

import gui.GUISimulator;
import gui.ImageElement;
import gui.Rectangle;

import java.awt.*;

public class RobotPattes extends Robot{
    public RobotPattes(){
        this.vitesse=30;
        this.eauMax= (int) Double.POSITIVE_INFINITY;
        this.eau = this.eauMax;
        this.tempsRemplissageComplet = 0;
        this.tempsIntervention=1;
        this.volumeIntervention=10;
        this.state = TypesEnums.RobotState.FREE;
    }

    @Override
    public void setPosition(Case position) {
        switch (position.getNature()) {
            case EAU -> {
                throw new IllegalArgumentException("La case contient de l'eau, le robot ne peut pas y aller.");
            }
            case ROCHE -> {
                this.vitesse = 10;
                this.position = position;
                break;
            }
            default -> {
                this.vitesse = 30;
                this.position = position;
                break;
            }
        }
    }
    @Override
    public double getVitesse(TypesEnums.NatureTerrain natureTerrain) {
        switch(natureTerrain){
            case EAU ->
            {
                throw new IllegalArgumentException("La case contient de l'eau, le robot ne peut pas y aller.");
            }
            case ROCHE ->
            {
                this.vitesse=10;
                return 10;
            }
            default ->
            {
                this.vitesse=30;
                return 30;
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
        gui.addGraphicalElement(new ImageElement(xPosition-robotSize/2,yPosition- robotSize/2,"img/pattes.png", robotSize,robotSize, ob));

    }

}
