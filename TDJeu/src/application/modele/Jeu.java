package application.modele;

import java.util.HashMap;

import application.config;
import application.controleur.EcouteurMap;
import application.exception.CreditException;
import application.exception.GameOverException;
import application.modele.Case.Carte;
import application.modele.Monstre.Monstre;
import application.modele.Monstre.Vague;
import application.modele.Tourelle.Tourelle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class Jeu {

	private Carte map;
	
	private IntegerProperty vie;
	
	private HashMap<Integer, Tourelle> TourellesEnJeu = new HashMap<>();
	
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
		this.TourellesEnJeu.put(indice, tourelle);
	}
	
	public HashMap<Integer, Tourelle> getTourellesEnJeu() {
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
	
	public void rotationEtAttaqueDesTourelles(EcouteurMap ecouteurMap) {
		for (Tourelle tourelle : this.TourellesEnJeu.values()) {
			Monstre monstre = tourelle.detectMonstres();
			if (monstre != null) {
				ecouteurMap.rotationTourelleSurMonstre(monstre, tourelle);
				tourelle.decrementerCooldown();
				if (tourelle.canAttack()) {
					this.vague.attaqueEtMortDuMonstre(monstre);
				}
			}
		}
	}
	
	
}
