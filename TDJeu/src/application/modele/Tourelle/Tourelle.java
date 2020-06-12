package application.modele.Tourelle;

import application.config;
import application.modele.Jeu;
import application.modele.Case.Case;
import application.modele.Monstre.Monstre;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Tourelle extends Case {
	
	private int prix;
	private DoubleProperty degats;
	private IntegerProperty upgradePrice;
	private IntegerProperty defaultCooldown;
	private int currentCooldown;
	private int range;
	private int x;
	private int y;
	private int niveau;
	
	
	private Jeu jeu;
	
	public Tourelle(String nom, int id, int prix, double degats, int defaultCooldown, int range, Jeu jeu) {
		super(nom, id, false);
		this.prix = prix;
		this.degats = new SimpleDoubleProperty(degats);
		this.defaultCooldown = new SimpleIntegerProperty(defaultCooldown);
		this.currentCooldown = 0;
		this.range = range;
		this.jeu=jeu;
		this.upgradePrice = new SimpleIntegerProperty(50);
		this.niveau = 1;
	}
	
	public int getPrix() {
		return this.prix;
	}
	
	public void setPrix(int prix) {
		this.prix = prix;
	}
	
	public IntegerProperty getPrice() {
		return this.upgradePrice;
	}

	public DoubleProperty getDegats() {
		return this.degats;
	}

	public void setDegats(int degats) {
		this.degats.set(degats);
	}

	public IntegerProperty getDefaultCooldown() {
		return this.defaultCooldown;
	}

	public void setDefaultCooldown(int defaultCooldown) {
		this.defaultCooldown.set(defaultCooldown);
	}
	
	public int getCurrentCooldown() {
		return this.currentCooldown;
	}

	public void setCurrentCooldown(int currentCooldown) {
		this.currentCooldown = currentCooldown;
	}

	public int getRange() {
		return this.range;
	}

	public void setRange(int range) {
		this.range = range;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getX() {
		return this.x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getNiveau() {
		return this.niveau;
	}

	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}
	
	public boolean canAttack() {
		return currentCooldown == 0;
	}
	
	public void decrementerCooldown() {
		if (this.currentCooldown == 0) {
			this.currentCooldown = this.defaultCooldown.getValue();
		}else {
			this.currentCooldown -= 1;
		}
	}
	
	public void incrementerNiveau() {
		if (this.niveau < 3) {
			this.niveau++;
			if (config.minimalCooldown < this.defaultCooldown.getValue()) {
				this.defaultCooldown.set(this.defaultCooldown.getValue() - 1);
			}
			
			this.degats.set(this.degats.getValue() + 2);
			this.upgradePrice.set(this.upgradePrice.getValue() + 100);
		}
	}
	
	/***
    * Permet de détecter les monstres à portée de chaque tourelle
    */
    public Monstre detectMonstres() {
		int xa = this.x;
		int ya = this.y;
	   	int currentRange = this.range;
	   	
	   	for (Monstre monstre : this.jeu.getVague().getListMonstre()) {
	   		double xb = monstre.getX();
	   		double yb = monstre.getY();
	   		
	   		// Différence entre deux points dans un plan cartésien :
	   		// Formule : Racine((xb - xa)² + (yb - ya)²)
	   		double difference = Math.sqrt(Math.pow(xb - xa, 2) + Math.pow(yb - ya, 2));
	   		if (currentRange * 32 >= difference) {
	   			return monstre;
	   		}
	   	}
		return null;
    } 
    
}
