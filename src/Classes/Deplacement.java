package Classes;

import Exceptions.NoSuchNeighborException;

import java.util.ArrayList;

public class Deplacement extends Evenement{

    private Carte carte;
    private Robot robot;
    private TypesEnums.Direction direction;
    private Case destination;
    int index;

    public Deplacement(long date, Carte carte, ArrayList<Robot> robots,int indice, TypesEnums.Direction direction){
        super(date);
        this.carte = carte;
        this.index = indice;
        this.robot = robots.get(indice);
        this.direction = direction;
        this.destination = null;
    }

    public Deplacement(long date, Carte carte, ArrayList<Robot> robots,int indice, Case destination){
        super(date);
        this.carte = carte;
        this.index = indice;
        this.robot = robots.get(indice);
        this.direction = null;
        this.destination = destination;
    }

    public void reset(ArrayList<Robot> robots , Carte carte)
    {
        this.robot = robots.get(this.index);
        this.carte = carte;
    }


    @Override

    public void execute(){
        if(direction==null){
            executeWith(destination);
            return;
        }
        try {
            executeWith(direction);
        } catch (NoSuchNeighborException e) {
            throw new RuntimeException(e);
        }
    }

    public void executeWith(TypesEnums.Direction direction) throws NoSuchNeighborException {
        Case currentPos = robot.getPosition();
        System.out.println(currentPos);
        Case voisin = carte.getVoisin(currentPos, direction);  // Peut lancer NoSuchNeighborException si le voisin est hors carte
        TypesEnums.NatureTerrain nature = voisin.getNature();  // Utiliser `voisin` au lieu de `currentPos`
        TypesEnums.RobotType robotType = robot.getType();

        // Vérification des contraintes de déplacement en fonction du type de robot
        switch (robotType) {
            case ROUES -> {
                if (nature != TypesEnums.NatureTerrain.TERRAIN_LIBRE && nature != TypesEnums.NatureTerrain.HABITAT) {
                    throw new IllegalArgumentException("Robot à roues ne peut se déplacer que sur du terrain libre ou habitat");
                }
            }
            case CHENILLES -> {
                if (nature == TypesEnums.NatureTerrain.EAU || nature == TypesEnums.NatureTerrain.ROCHE) {
                    throw new IllegalArgumentException("Robot à chenilles ne peut pas se rendre sur de l’eau ou du rocher");
                }
            }
            case PATTES -> {
                if (nature == TypesEnums.NatureTerrain.EAU) {
                    throw new IllegalArgumentException("Robot à pattes ne peut pas se rendre sur de l’eau");
                }
            }
        }

        // Si toutes les conditions sont respectées, déplacer le robot
        robot.setPosition(voisin);
    }

    public void executeWith(Case destination){
        robot.setPosition(destination);
        //System.out.println("Robot déplacé à " + destination);
    }

    @Override

    public String toString(){
        if(destination != null) {
            return "Déplacement du robot "+ robot + "vers la case " + destination;
        }
        else return "Déplacement du robot" + robot + "suivant la direction" + direction;
    }

}
