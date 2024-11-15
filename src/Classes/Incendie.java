package Classes;

import gui.GUISimulator;
import gui.ImageElement;
import gui.Rectangle;
import gui.Text;

import java.awt.*;
import java.awt.image.ImageObserver;

public class Incendie implements Cloneable{
    private Case position;
    private int intensite;

    public Incendie(Case position, int intensite){
        this.position = position;
        this.intensite = intensite;
    }

    public void eteindre(int volume) {
        this.intensite -= volume;
    }

    public Case getPosition() {
        return position;
    }
    public int getIntensite() {
        return intensite;
    }

    public void draw(GUISimulator gui, int tailleCase, int dx, int dy, ImageObserver ob) {
        if (this.intensite <= 0)
        {
            return;
        }
        int xPosition = dx +  position.getColonne() * tailleCase+ tailleCase;
        int yPosition = dy +tailleCase + position.getLigne() * tailleCase;
        gui.addGraphicalElement(new ImageElement(xPosition-tailleCase/2,yPosition- tailleCase/2,"img/fire.png", tailleCase,tailleCase, ob));
        /*gui.addGraphicalElement(new Text( xPosition , yPosition,
                Color.RED, String.valueOf(intensite)));*/
    }

    @Override
    public Incendie clone() throws CloneNotSupportedException {
        return (Incendie) super.clone();
    }

    @Override
    public String toString(){
        return "Info incendie : ligne : "+position.getLigne()+" colonne : "+position.getColonne()+" intensité : "+intensite;
    }


}
