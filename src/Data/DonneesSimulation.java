package Data;

import Classes.Carte;
import Classes.Incendie;
import Classes.Robot;

import java.util.ArrayList;

public class DonneesSimulation implements Cloneable {

    private Carte carte ;
    private ArrayList<Incendie> IncendieList;
    private ArrayList<Robot> RobotList;

    public DonneesSimulation(Carte carte){
        this.carte = carte;
        IncendieList = new ArrayList<Incendie>();
        RobotList = new ArrayList<Robot>();
    }

    public ArrayList<Incendie> getIncendieList(){
        return IncendieList;
    }

    public ArrayList<Robot> getRobotList(){
        return RobotList;
    }

    public Carte getCarte(){
        return carte;
    }

    public void addIncendie(Incendie elem){
        IncendieList.add(elem);
    }

    public void removeIncendie(Incendie elem) {IncendieList.remove(elem);}
    public void addRobot(Robot elem){
        RobotList.add(elem);
    }

    public void afficher(){
        String ch = carte.toString();
        System.out.println(ch);
        System.out.println("nombre de robots: "+RobotList.size()+"nombre d'incendies : " +IncendieList.size());
    }

    @Override
    public DonneesSimulation clone() throws CloneNotSupportedException {
        DonneesSimulation cloned = (DonneesSimulation) super.clone();

        // Clone la carte
        cloned.carte = (carte != null) ? carte.clone() : null;

        // Clone les robots
        cloned.RobotList = new ArrayList<>();
        for (Robot robot : this.RobotList) {
            cloned.getRobotList().add(robot.clone());
        }

        // Clone les incendies
        cloned.IncendieList = new ArrayList<>();
        for (Incendie incendie : this.IncendieList) {
            cloned.IncendieList.add(incendie.clone());
        }

        return cloned;
    }

}
