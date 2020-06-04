package application.modele;

import java.util.ArrayList;

import application.config;
import application.exception.CreditException;
import application.exception.GameOverException;
import application.modele.Case.Carte;
import application.modele.Monstre.Monstre;
import application.modele.Monstre.Vague;
import application.modele.tourelle.Tourelle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class Jeu {

	private Carte map;
	
	private IntegerProperty vie;
	
	private ArrayList<Tourelle> TourellesEnJeu = new ArrayList<>();
	
	private IntegerProperty credits;
	
	private Vague vague;

	public Jeu() {
		this.vie = new SimpleIntegerProperty(config.vieInitiale);
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
	public void placerTourelle(Tourelle tourelle, int indice) {
		this.map.getListe().set(indice, tourelle);
		this.TourellesEnJeu.add(tourelle);
	}
	
	public ArrayList<Tourelle> getTourellesEnJeu() {
		return this.TourellesEnJeu;
	}

	/***
	 * Gestion des Monstres
	 */	
	public boolean deplacementAutoriser(Monstre monstre) {
		int indiceCurrentCase = map.Indice(monstre.getX(), monstre.getY());
		if (this.map.getListe().get(indiceCurrentCase).getId() == 248 ) {
			return true;
		}
		return false;
	}
	
    /***
     * Permet de détecter les monstres à portée de chaque tourelle
     */
    public void detectMonstres() {
		for (Tourelle tourelle : this.getTourellesEnJeu()) {
			int xa = tourelle.getX();
			int ya = tourelle.getY();
		   	int currentRange = 2;
		   	
		   	for (Monstre monstre : this.getVague().getListMonstre()) {
		   		int xb = monstre.getX();
		   		int yb = monstre.getY();
		   		
		   		// Différence entre deux points dans un plan cartésien :
		   		// Formule : Racine((xb - xa)² + (yb - ya)²)
		   		double difference = Math.sqrt(Math.pow(xb - xa, 2) + Math.pow(yb - ya, 2));
		   		//System.out.println(currentRange * 32 - difference);
		   		if (currentRange * 32 >= difference) {
		   			System.err.println("MONSTRE EN RANGE !");
		   		}
		   	}
		}
    }
    
	public void retirerMonstre(Monstre monstre) {
		int index = this.vague.getListMonstre().indexOf(monstre);
		this.vague.getListMonstre().remove(index);
	}

	public IntegerProperty getVie() {
		return this.vie;
	}

	public void decrementerVie() throws GameOverException {
		if (this.vie.getValue() > 0) {
			this.vie.set(this.vie.getValue() - 1);
		} else {
			Alert alerte = new Alert(AlertType.ERROR);
			alerte.setContentText("Game over !");
			alerte.setHeaderText(null);
			alerte.show();
			throw new GameOverException();
		}
	}
	
	public void verifyDeplacements(Monstre currentMonstre) {
		if (currentMonstre.getY() == config.CASE_END_Y && 
				(currentMonstre.getX() == config.CASE_END_X1 || currentMonstre.getX() == config.CASE_END_X2)) {
			this.retirerMonstre(currentMonstre);
			try {
				this.decrementerVie();
			} catch (GameOverException e) {
				this.vague.viderListe();
			}
		}
	}
}
