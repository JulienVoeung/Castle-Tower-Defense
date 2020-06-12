package application.modele.Monstre;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Monstre {

    private int vie;
    private int gain;
    
    private DoubleProperty x;
    private DoubleProperty y;
    private int currentDirection;
    private double vitesse;
    private int cooldownVitesse;
    private double defaultVitesse;
    
    private String nom;
    

    public Monstre(String nom, int vitesse, int vie, int gain, double x, double y) {
    	this.nom = nom;
        this.vie = vie;
        this.setGain(gain);
        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(y);
        this.vitesse = vitesse;
        this.defaultVitesse = vitesse;
        this.currentDirection = 4;
        this.cooldownVitesse = 0;
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
    
    public int getCurrentDirection() {
        return this.currentDirection;
    }
    
    public void setCurrentDirection(int direction) {
    	this.currentDirection = direction;
    }
    
    public void deplacement(int direction) {
    	switch (direction) {
		case 1:
			this.setXProperty(-this.vitesse + this.getX()); 
			break;
		case 2:
			this.setXProperty(this.vitesse + this.getX());
			break;
		case 3:
			this.setYProperty(this.vitesse + this.getY());
			break;
		case 4:
			this.setYProperty(-this.vitesse + this.getY());
			break;
		}
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
    
    public void decrementerVitesse(int Slow) {
    	this.vitesse -= Slow;
    	this.cooldownVitesse = 5;
    }
    
    public void decrementeCooldownVitesse() {
    	if (this.cooldownVitesse > 0) {
        	this.cooldownVitesse--;
    	}
    }
    
    public int getCooldownVitesse() {
    	return this.cooldownVitesse;
    }
    
    public void resetVitesse() {
    	this.vitesse = this.defaultVitesse;
    }
    
}
