package application.modele.tourelle;

import application.modele.Case.Case;

public class Tourelle extends Case{
	
	private int prix;
	private int degats;
	private int cooldown;
	private int range;
		
	public Tourelle(String nom, int id) {
		super(nom, id, false);
	}
	
	public Tourelle(int prix, int degats, int cooldown, int range, String nom, int id) {
		super(nom, id, false);
		this.prix = prix;
		this.degats = degats;
		this.cooldown = cooldown;
		this.range = range;
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

	public int getCooldown() {
		return this.cooldown;
	}

	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}

	public int getRange() {
		return this.range;
	}

	public void setRange(int range) {
		this.range = range;
	}
}
