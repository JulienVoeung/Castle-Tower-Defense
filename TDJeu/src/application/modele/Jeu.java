package application.modele;

import application.exception.CreditException;
import application.modele.Case.Carte;
import application.modele.tourelle.Tourelle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Jeu {

	private Carte map;
	private ObservableList<Tourelle> TourellesEnJeu = FXCollections.observableArrayList();
	
	private IntegerProperty credits;

	public Jeu() {

		this.map = new Carte();
		this.credits = new SimpleIntegerProperty();
		credits.set(500);
		
		
	}

	public IntegerProperty getCreditsProperty() {
		return this.credits;
	}
	
	public int getCredits() {
		return this.credits.getValue().intValue();
	}
	
	public void setCredits(int valeur) {
		this.credits.set(valeur);
	}
	
	public void addCredits(int valeur) {
		this.credits.add(valeur);
	}
	
	public void removeCredits(int valeur) throws CreditException {
		if ((this.credits.getValue().intValue() - valeur) >= 0) {
			this.credits.set(this.credits.getValue().intValue() - valeur);;
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
