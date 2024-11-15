package Classes;
import Classes.TypesEnums.NatureTerrain;
import gui.GUISimulator;
import gui.ImageElement;
import gui.Rectangle;
import java.awt.*;
import java.util.Objects;


public class Case {

    private int ligne;
    private int colonne;
    private NatureTerrain nature;

    public Case(int ligne,int colonne){
        this.ligne = ligne;
        this.colonne = colonne;
        this.nature = NatureTerrain.TERRAIN_LIBRE;
    }



    public Case(int ligne, int colonne,NatureTerrain nature){
        this.ligne = ligne;
        this.colonne = colonne;
        this.nature = nature;
    }

    // Getters
    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public boolean equals(Case other) {
        return other.colonne == colonne && other.ligne == ligne;
    }

    public NatureTerrain getNature() {
        return nature;
    }

    // Setters
    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    public void setColonne(int colonne) {
        this.colonne = colonne;
    }

    public void setNature(NatureTerrain nature) {
        this.nature = nature;
    }

    @Override

    public String toString(){
        return "Info de la case : ligne "+ligne+" colonne "+colonne+" nature : "+nature;
    }

    public void draw(GUISimulator gui,int x, int y, int size,java.awt.image.ImageObserver ob) {

        Color fillColor = switch (nature) {
            case TERRAIN_LIBRE -> Color.YELLOW;
            case FORET -> Color.GREEN;
            case ROCHE -> Color.GRAY;
            case EAU -> Color.BLUE;
            case HABITAT -> Color.ORANGE;
        };



        Color borderColor = Color.BLACK; // or any color you prefer
        //System.out.println(ligne+ ':' +y);
        switch (nature) {
            case EAU -> gui.addGraphicalElement(new ImageElement(x-size/2,y- size/2,"img/water.jpeg", size,size, ob));
            case FORET -> gui.addGraphicalElement(new ImageElement(x-size/2,y- size/2,"img/grass.jpg", size,size, ob));
            case ROCHE -> gui.addGraphicalElement(new ImageElement(x-size/2,y- size/2,"img/rock.png", size,size, ob));
            case HABITAT -> gui.addGraphicalElement(new ImageElement(x-size/2,y- size/2,"img/habitat.png", size,size, ob));
            case TERRAIN_LIBRE -> gui.addGraphicalElement(new ImageElement(x-size/2,y- size/2,"img/terrain_libre.png", size,size, ob));
            default -> gui.addGraphicalElement(new Rectangle(
                    x,
                    y,
                    borderColor,
                    fillColor,
                    size
            ));
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Case other = (Case) obj;
        return ligne == other.ligne && colonne == other.colonne;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ligne, colonne);
    }


}
