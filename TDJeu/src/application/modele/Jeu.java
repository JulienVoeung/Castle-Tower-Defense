package application.modele;

import application.exception.CreditException;
import application.modele.Case.Carte;
import application.modele.tourelle.Tourelle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Jeu {

	private Carte map;
	private ObservableList<Tourelle> TourellesEnJeu = FXCollections.observableArrayList();
	
	private int credits;

	public Jeu() {

		this.map = new Carte();
		this.credits = 500;
	}

	public int getCredits() {
		return this.credits;
	}
	
	public void setCredits(int valeur) {
		this.credits = valeur;
	}
	
	public void addCredits(int valeur) {
		this.credits += valeur;
	}
	
	public void removeCredits(int valeur) throws CreditException {
		if (this.credits-valeur>=0) {
			this.credits -= valeur;
		}
		else {
			throw new CreditException();
		}
		
	}
	
	public void ajouterTourelle(Tourelle tourelle) {
		TourellesEnJeu.add(tourelle);
	}

	public Carte getMap() {
		return this.map;
	}
	
	public void placerTourelle(Tourelle tourelle, int indice) {
		this.map.getListe().set(indice, tourelle);
	}
		
}
