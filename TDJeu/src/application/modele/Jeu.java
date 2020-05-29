package application.modele;

import java.util.ArrayList;

import application.exception.CreditException;
import application.modele.Case.Carte;
import application.modele.Monstre.Monstre;
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
	
	public Carte getMap() {
		return this.map;
	}
	public Vague getVague() {
		return vague;
	}
	
	/***
	 * Gestion des credits
	 */
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
	
	/***
	 * Gestion des tourelles
	 */
	public void ajouterTourelle(Tourelle tourelle) {
		TourellesEnJeu.add(tourelle);
	}

	public void placerTourelle(Tourelle tourelle, int indice) {
		this.map.getListe().set(indice, tourelle);
	}

	/***
	 * Gestion des Monstres
	 */
	public void spawnMonstre() {
		this.vague.getListMonstre();
	}
	
	public boolean deplacementAutoriser(Monstre monstre) {
		int indiceCurrentCase = map.Indice(monstre.getX(), monstre.getY());
		if (this.map.getListe().get(indiceCurrentCase).getId() == 100) {
			System.out.println("OKE");
			return true;
		}
		return false;
	}
	
}
