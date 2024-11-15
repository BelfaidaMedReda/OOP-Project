package Classes;



import Data.DonneesSimulation;

import java.util.ArrayList;

public class Chef {

    private Carte carte;
    private ArrayList<Robot> robotList;
    private ArrayList<Case> WaterPoints;
    private ArrayList<Incendie> Incendies;
    private ArrayList<Integer> IncendieAssigned;
    private Simulateur simulateur;


    public Chef(Simulateur simulateur, DonneesSimulation donnees){
        this.simulateur = simulateur;
        this.carte = donnees.getCarte();
        this.robotList = donnees.getRobotList();
        this.Incendies = donnees.getIncendieList();
        this.WaterPoints = new ArrayList<>();
        int nbLignes  = this.carte.getnbLignes();
        int nbColonnes = this.carte.getnbColonnes();
        IncendieAssigned = new ArrayList<Integer>();
        for (int i= 0;i<this.Incendies.size();i++)
        {
            IncendieAssigned.add(-1);
        }

        for (int i=0;i<nbLignes;i++){
            for(int j=0;j<nbColonnes;j++){
                Case current = this.carte.getCase(i,j);
                if(current.getNature()== TypesEnums.NatureTerrain.EAU ){
                    WaterPoints.add(current);
                }
            }
        }
    }

    public void strategieElementaire(){
        /*for (int i= 0;i<this.Incendies.size();i++)
        {
            System.out.println(IncendieAssigned.get(i));
        }*/
        for (int i=0;i<Incendies.size();i++){
            Incendie current = Incendies.get(i);
            if (current.getIntensite() == 0)
            {
                continue;
            }
            if (IncendieAssigned.get(i) == -1 )
            {
                for(int j=0;j<robotList.size();j++){
                    Robot robot = robotList.get(j);
                    if (robot.state == TypesEnums.RobotState.FREE)
                    {
                        robot.state = TypesEnums.RobotState.MOVING;
                        //System.out.println(current.getPosition());
                        if (this.simulateur.planifierDeplacement(robotList,j,carte.getCase(current.getPosition().getLigne(),current.getPosition().getColonne())) )
                        {
                            IncendieAssigned.set(i,j);
                            break;
                        }
                    }
                }
            }else {

                Robot robot = robotList.get(IncendieAssigned.get(i));
                if (robot.state == TypesEnums.RobotState.MOVING)
                {
                    this.simulateur.planifierDeplacement(robotList,IncendieAssigned.get(i),carte.getCase(current.getPosition().getLigne(),current.getPosition().getColonne()));
                    robot.state= TypesEnums.RobotState.WAIT;
                }
                if (robot.getPosition().equals(current.getPosition()) && robot.state == TypesEnums.RobotState.WAIT) {
                    int vol = Math.min(current.getIntensite(),robot.eau);
                    this.simulateur.ajouteExtinction(IncendieAssigned.get(i),vol);
                }
                if (robot.state == TypesEnums.RobotState.MOVERECHARGE) {
                    Case Eau = null;
                    double dist = Double.POSITIVE_INFINITY;
                    for (int j=0;j<WaterPoints.size();j++){
                        double distance = Math.pow(WaterPoints.get(j).getLigne()- robot.position.getLigne(),2) + Math.pow(WaterPoints.get(j).getColonne()- robot.position.getColonne(),2);
                        if(distance < dist){
                            Eau = WaterPoints.get(j);
                            dist = distance;
                        }
                    }
                    if (robot.getType() != TypesEnums.RobotType.DRONE)
                    {
                        Eau = robot.seRecharger(carte,WaterPoints);

                    }
                    this.simulateur.planifierDeplacement(robotList,IncendieAssigned.get(i),Eau);
                    if (robot.getPosition().equals(Eau)){
                    robot.state = TypesEnums.RobotState.RECHARGING;}
                }
                if (robot.state == TypesEnums.RobotState.RECHARGING) {
                        this.simulateur.ajouteRemplissage(IncendieAssigned.get(i),this.carte);
                        robot.state = TypesEnums.RobotState.WAIT;
                        //this.simulateur.planifierDeplacement(robotList,IncendieAssigned.get(i),carte.getCase(current.getPosition().getLigne(),current.getPosition().getColonne()));

                        //int vol = Math.max(current.getIntensite(),robot.eau);
                        //this.simulateur.ajouteExtinction(IncendieAssigned.get(i),vol);
                        //robot.state = TypesEnums.RobotState.MOVING;

                }
                System.out.println(robot.state);
            }
        }

    }



}
