package application.modele.Tir;

import application.modele.Monstre.Monstre;
import application.modele.Tourelle.Tourelle;

public abstract class Tir {
	
	private Tourelle tourelle;
	private Monstre monstre;

	public Tir(Monstre m, Tourelle tourelle) {
		this.monstre = m;	
		this.tourelle = tourelle;

	}
	
	public Tourelle getTourelle() {
		return this.tourelle;
	}
	
	public Monstre getMonstre() {
		return this.monstre;
	}
	
	public abstract void deplacement();
	
	public abstract void agit();
	
}
