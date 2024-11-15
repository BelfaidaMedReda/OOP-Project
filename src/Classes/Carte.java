package Classes;

import Exceptions.NoSuchNeighborException;
import gui.GUISimulator;

import java.util.ArrayList;
import java.util.List;

public class Carte implements Cloneable {

    private int tailleCases;
    private Case[][] matrice;

    public Carte(int nbLignes, int nbColonnes,int tailleCases){
        matrice = new Case[nbLignes][nbColonnes];
        for(int i=0;i<nbLignes;i++) {
            for (int j = 0; j < nbColonnes; j++) {
                matrice[i][j] = new Case(i,j);
            }
        }
        this.tailleCases = tailleCases;
    }

    // Getters Implementation

    public int getnbLignes(){
        return matrice.length;
    }

    public int getnbColonnes(){
        return matrice[0].length;
    }

    public int getTailleCases() {
        return tailleCases;
    }

    public Case getCase(int i, int j){
        return matrice[i][j];
    }

    boolean voisinExiste(Case src, TypesEnums.Direction dir){
        int n = getnbLignes();
        int m = getnbColonnes();
        int i = src.getLigne();
        int j = src.getColonne();
        switch (dir){
            case OUEST ->{
                return j!=0;
            }
            case SUD -> {
                return i!=n-1;
            }
            case NORD -> {
                return i!=0;
            }
            case EST -> {
                return j!=m-1;
            }
            default ->{
                return false;
            }
        }
    }

    public Case getVoisin( Case src, TypesEnums.Direction dir) throws  NoSuchNeighborException{

        int i = src.getLigne();
        int j = src.getColonne();

        if(!voisinExiste(src,dir)){
            throw new NoSuchNeighborException("Il n'y a pas de voisin suivant cette direction ou est en dehors des limites de la carte.");
        }

        switch (dir){

            case OUEST -> {
                return matrice[i][j-1];
            }

            case EST -> {
                return matrice[i][j+1];
            }

            case NORD -> {
                return matrice[i-1][j];
            }

            case SUD -> {
                return matrice[i+1][j];
            }

            default -> {
                return null;
            }

        }
    }


    @Override

    public String toString(){
        return "Carte : "+getnbLignes()+"x "+getnbColonnes();
    }

    @Override
    public Carte clone() throws CloneNotSupportedException {
        return (Carte) super.clone();
    }
    public List<Case> getVoisins(Case currentCase) {
        List<Case> voisins = new ArrayList<>();
        int ligne = currentCase.getLigne();
        int colonne = currentCase.getColonne();

        if (ligne > 0) {
            voisins.add(getCase(ligne - 1, colonne));
        }
        if (ligne < this.getnbLignes() - 1) {
            voisins.add(getCase(ligne + 1, colonne));
        }
        if (colonne > 0) {
            voisins.add(getCase(ligne, colonne - 1));
        }
        if (colonne < this.getnbColonnes() - 1) {
            voisins.add(getCase(ligne, colonne + 1));
        }

        return voisins;
    }

}
