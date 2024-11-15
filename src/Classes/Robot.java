package Classes;

import Exceptions.NoSuchNeighborException;
import gui.*;
import gui.Rectangle;

import java.awt.*;
import java.util.ArrayList;

public abstract class Robot implements Cloneable {
    protected Case position;
    protected TypesEnums.RobotState state;
    protected int eau;
    protected int eauMax;
    protected int tempsRemplissageComplet;
    protected int tempsIntervention;
    protected int volumeIntervention;
    protected double vitesse;


    public Case getPosition() {
        return position;
    }
    public abstract void setPosition(Case position);

    public abstract double getVitesse(TypesEnums.NatureTerrain natureTerrain);


    public void deverserEau(int vol) {
        if (this.eau < vol)
        {
            throw new IllegalArgumentException("Le volume à verser est plus grand que le volume disponible.");
        }
        else
        {
            this.eau =Math.max(0,this.eau-vol);
        }
    }

    public void remplirReservoir() {
        this.eau = this.eauMax;
    }

    public void draw(GUISimulator gui, int tailleCase,int dx,int dy,java.awt.image.ImageObserver ob) {
            Color color = this instanceof Drone ? Color.CYAN :
                    this instanceof RobotRoues ? Color.MAGENTA :
                            this instanceof RobotChenilles ? Color.ORANGE : Color.PINK;

            // Set the size of the robot rectangle to be smaller than the cell size
            int robotSize = tailleCase / 2;
        Color borderColor = Color.BLACK;
        // Calculate position to center the robot in its cell
            int xPosition = dx + (position.getColonne()+1) * tailleCase;
            int yPosition = dy + (position.getLigne()+1) * tailleCase;

            // Draw the robot rectangle at the calculated position
            gui.addGraphicalElement(new Rectangle(xPosition, yPosition, borderColor, color, robotSize));
    }

    public TypesEnums.RobotType getType(){
        return this instanceof Drone ? TypesEnums.RobotType.DRONE :
                this instanceof RobotRoues ? TypesEnums.RobotType.ROUES :
                        this instanceof RobotChenilles ? TypesEnums.RobotType.CHENILLES : TypesEnums.RobotType.PATTES;
    }

    public void changeState(TypesEnums.RobotState other_state){
        state = other_state;
    }

    public TypesEnums.RobotState getState(){
        return state;
    }

    @Override
    public Robot clone() throws CloneNotSupportedException {
        return (Robot) super.clone();
    }
    public boolean canTraverse(Case caseCible) {
        TypesEnums.NatureTerrain terrain = caseCible.getNature();

        if (this instanceof Drone) {
            return true;
        } else if (this instanceof RobotRoues) {
            return terrain == TypesEnums.NatureTerrain.TERRAIN_LIBRE || terrain == TypesEnums.NatureTerrain.HABITAT;
        } else if (this instanceof RobotChenilles) {
            return terrain != TypesEnums.NatureTerrain.EAU && terrain != TypesEnums.NatureTerrain.ROCHE;
        } else if (this instanceof RobotPattes) {
            return terrain != TypesEnums.NatureTerrain.EAU;
        }
        return false;
    }

    public double getTempsTraverse(Case voisin) {
        TypesEnums.NatureTerrain terrain = voisin.getNature();
        double vitesse = this.getVitesse(terrain); //
        if (vitesse == 0) {
            return Double.POSITIVE_INFINITY; //
        }
        return 1 / vitesse;
    }

    public Case seRecharger(Carte carte,ArrayList<Case> Waterpoints){
         Case mstafa = null;
         double dist = Double.POSITIVE_INFINITY;

        for(Case wp : Waterpoints){
            Case[] neighbors = new Case[4];
            int index = 0;
            for(TypesEnums.Direction dir:TypesEnums.Direction.values()){
                try {
                    neighbors[index] = carte.getVoisin(wp, dir);
                } catch (NoSuchNeighborException e) {
                    neighbors[index] = null;
                }
                index++;
            }
            for(int i=0;i<neighbors.length;i++){
                if(neighbors[i] ==null){
                 continue;
                }
                double distance = Math.pow(neighbors[i].getLigne()- position.getLigne(),2) + Math.pow(neighbors[i].getColonne()- position.getColonne(),2);
                if(canTraverse(neighbors[i]) && distance < dist){
                    mstafa = neighbors[i];
                    dist = distance;
                    //return neighbors[i];
                }
            }
        }
        return mstafa;
    }


    @Override
    public String toString(){
        return "Info Robot : Ligne : "+position.getLigne()+" Colonne : "+position.getColonne();
    }

}
