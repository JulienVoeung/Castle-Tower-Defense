package application.modele;

import java.util.ArrayList;

import application.configMonstres;
import application.exception.CreditException;
import application.modele.Case.Carte;
import application.modele.Monstre.Vague;
import application.modele.tourelle.Tourelle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


public class Jeu {

	private Carte map;
	
	private ArrayList<Tourelle> TourellesEnJeu = new ArrayList<>();
	
	private IntegerProperty credits;
	
	private Vague vague;

	public Jeu() {
		this.map = new Carte();
		this.vague = new Vague();
		this.credits = new SimpleIntegerProperty(500);
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
			this.credits.set(this.credits.getValue().intValue() - valeur);
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

	public Vague getVague() {
		return vague;
	}
	
	public void setMonstresByNiveau() {
		this.vague.ajouterMonstres(configMonstres.getMonstreByNiveau(this.vague.getNiveau().get()));
	}			
}
