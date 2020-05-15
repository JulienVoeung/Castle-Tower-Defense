package application.modele.Monstre;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Monstre {

    private int vitesse;
    private int pv;
    private IntegerProperty x;
    private IntegerProperty y;
    private int deplacementy;
    private int deplacementx;

    public Monstre(int v, int pv, int x, int y) {
        this.vitesse = v;
        this.pv = pv;
        this.x = new SimpleIntegerProperty();
        this.y = new SimpleIntegerProperty();
        this.deplacementx = 12;
        this.deplacementy = 12;
    }

    public int getVitesse() {
        return this.vitesse;
    }

    public int getPv() {
        return this.pv; 
    }

    public IntegerProperty getXProperty() {
        return this.x;
    }

    public int getX() {
        return this.x.get();
    }

    public void setXProperty(int x) {
        this.x.set(x);
    }

    public IntegerProperty getYProperty() {
        return this.y;
    }

    public int getY() {
        return this.y.get();
    }

    public void setYProperty(int y) {
        this.y.set(y);
    }

    public void deplacerGauche() {
        this.setXProperty(-this.deplacementx + this.getX());
    }

    public void deplacerDroite() {
        this.setXProperty(this.deplacementx + this.getX());
    }

    public void deplacerBas() {
        this.setYProperty(this.deplacementy + this.getY());
    }

    public void deplacerHaut() {
        this.setYProperty(-this.deplacementy + this.getY());
    }
    
    public String toString() {
    	return "x : "+ this.getX() + " y : " + this.getY();
    }
}
