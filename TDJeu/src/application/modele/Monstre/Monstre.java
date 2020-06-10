package application.modele.Monstre;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Monstre {

    private int vie;
    private int gain;
    
    private DoubleProperty x;
    private DoubleProperty y;
    private double deplacementy;
    private double deplacementx;
    
    private String nom;
    

    public Monstre(String nom, int vitesse, int vie, int gain, double x, double y) {
    	this.nom = nom;
        this.vie = vie;
        this.setGain(gain);
        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(y);
        this.deplacementx = vitesse;
        this.deplacementy = vitesse;
    }

	public int getGain() {
		return this.gain;
	}

	public void setGain(int gain) {
		this.gain = gain;
	}

    public int getPv() {
        return this.vie; 
    }

    public DoubleProperty getXProperty() {
        return this.x;
    }

    public double getX() {
        return this.x.getValue();
    }

    public void setXProperty(double x) {
        this.x.set(x);
    }

    public DoubleProperty getYProperty() {
        return this.y;
    }

    public double getY() {
        return this.y.getValue();
    }

    public void setYProperty(double y) {
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
    	return "Nom : "+ this.nom +"x : "+ this.getX() + " y : " + this.getY();
    }
    
    public void decrementerVie(double damage) {
    	if (this.vie - damage > 0) {
    		this.vie -= damage;
		} else {
			this.vie = 0;
		}
    }
}
