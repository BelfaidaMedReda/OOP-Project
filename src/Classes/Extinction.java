package Classes;
//import Data.DonneesSimulation;

import java.util.ArrayList;

public class Extinction extends Evenement{
    ArrayList<Incendie> incendies;
    Robot robot;
    int index;
    Incendie incendie;
    public Extinction(long date, ArrayList<Incendie> incendies,ArrayList<Robot> robots,int indice)
    {
        super(date);
        this.incendies = incendies;
        this.index = indice;
        this.robot = robots.get(indice);
        this.robot.changeState(TypesEnums.RobotState.FIREFIGHTING);
    }
    public void reset(ArrayList<Robot> robots,ArrayList<Incendie> incendies)
    {
        this.robot = robots.get(this.index);
        this.incendies=incendies;
    }

    @Override
    public void execute() {

        Incendie target = null;
        for (Incendie incendie : incendies)
        {
            if (incendie.getPosition().equals(robot.getPosition()))
            {
                target = incendie;
                this.incendie = incendie;
                break;
            }
        }

        if (target != null)
        {
            System.out.println("volume : " + this.robot.eau + " fuego : " + target.getIntensite());
            int vol = target.getIntensite();
            if ( this.robot.eau >= vol)
            {
                this.robot.deverserEau(vol);
                target.eteindre(target.getIntensite());
                this.robot.changeState(TypesEnums.RobotState.FREE);
            }
            else
            {
                System.out.println(this.robot.eau);
                target.eteindre(this.robot.eau);
                this.robot.deverserEau(this.robot.eau);
                this.robot.changeState(TypesEnums.RobotState.MOVERECHARGE);
            }
        }
    }

    @Override

    public String toString(){
        return "Extinction de l'incendie "+incendie+"par le robot : robot";
    }



}
