package application.modele.Tourelle;

import application.modele.Jeu;
import application.modele.Case.Case;
import application.modele.Monstre.Monstre;

public class Tourelle extends Case {
	
	private int prix;
	private int degats;
	private int defaultCooldown;
	private int currentCooldown;
	private int range;
	private int x;
	private int y;
	
	private Jeu jeu;
	
	public Tourelle(String nom, int id, int prix, int degats, int defaultCooldown, int range, Jeu jeu) {
		super(nom, id, false);
		this.prix = prix;
		this.degats = degats;
		this.defaultCooldown = defaultCooldown;
		this.currentCooldown = 0;
		this.range = range;
		this.jeu=jeu;
	}
	
	public int getPrix() {
		return this.prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}

	public int getDegats() {
		return this.degats;
	}

	public void setDegats(int degats) {
		this.degats = degats;
	}

	public int getDefaultCooldown() {
		return this.defaultCooldown;
	}

	public void setDefaultCooldown(int defaultCooldown) {
		this.defaultCooldown = defaultCooldown;
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
	
	public boolean canAttack() {
		return currentCooldown == 0;
	}
	
	public void decrementerCooldown() {
		if (this.currentCooldown == 0) {
			this.currentCooldown = this.defaultCooldown;
			System.err.println("CAN ATTACK ");
		}else {
			this.currentCooldown -= 1;
			System.err.println("CAN'T ATTACK ");
		}
		System.err.println(currentCooldown);
	}
	
	/***
    * Permet de détecter les monstres à portée de chaque tourelle
    */
    public Monstre detectMonstres() {
		int xa = this.x;
		int ya = this.y;
	   	int currentRange = this.range;
	   	
	   	for (Monstre monstre : this.jeu.getVague().getListMonstre()) {
	   		int xb = monstre.getX();
	   		int yb = monstre.getY();
	   		
	   		// Différence entre deux points dans un plan cartésien :
	   		// Formule : Racine((xb - xa)² + (yb - ya)²)
	   		double difference = Math.sqrt(Math.pow(xb - xa, 2) + Math.pow(yb - ya, 2));
	   		//System.out.println(currentRange * 32 - difference);
	   		if (currentRange * 32 >= difference) {
	   			return monstre;
	   		}
	   	}
		return null;
    }
    
    
}
