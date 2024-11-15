package Classes;

import Exceptions.NoSuchNeighborException;
import Exceptions.RobotStateException;

import java.util.ArrayList;

public class Remplissage extends Evenement {

    private Robot robot;
    private Carte carte;
    int index;

    public Remplissage(long date, Carte carte, ArrayList<Robot> robots, int indice){
        super(date);
        this.index = indice;
        this.robot = robots.get(indice);
        this.carte = carte;
    }
    public void reset(Carte carte, ArrayList<Robot> robots)
    {
        this.robot = robots.get(this.index);
        this.carte = carte;
    }

    @Override
    public void execute() throws RobotStateException {
        // Vérification de l'état du robot
        /*if (robot.getState() != TypesEnums.RobotState.FREE) {
            throw new RobotStateException("Le robot doit être libre pour faire le remplissage.");
        }*/

        // Vérification du type de robot (exclure les robots à pattes)
        if (robot.getType() == TypesEnums.RobotType.PATTES) {
            throw new RobotStateException("Le robot à pattes ne peut pas se recharger.");
        }
        switch (robot.getType()) {
            case DRONE -> {
                if (robot.getPosition().getNature() == TypesEnums.NatureTerrain.EAU) {
                    robot.remplirReservoir();
                }
            }
            case CHENILLES, ROUES -> {
                try {
                    if (hasAdjacentWater(robot.getPosition())) {
                        robot.remplirReservoir();
                    }
                } catch (NoSuchNeighborException e) {
                    throw new RuntimeException("Erreur lors de la vérification des voisins pour le remplissage : " + e.getMessage(), e);
                }
            }
        }

        System.out.println("Remplissage : " + robot.eau);
        robot.changeState(TypesEnums.RobotState.MOVING);
    }

    /**
     * Vérifie si une des cases adjacentes contient de l'eau.
     *
     * @param position La position actuelle du robot.
     * @return true si un voisin est de type EAU, false sinon.
     * @throws NoSuchNeighborException si un des voisins n'existe pas.
     */
    private boolean hasAdjacentWater(Case position) throws NoSuchNeighborException {
        for (TypesEnums.Direction direction : TypesEnums.Direction.values()) {
            Case voisin = carte.getVoisin(position, direction);
            if (voisin.getNature() == TypesEnums.NatureTerrain.EAU) {
                return true;
            }
        }
        return false;
    }
}


