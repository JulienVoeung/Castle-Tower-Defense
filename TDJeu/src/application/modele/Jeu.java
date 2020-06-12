package application.modele;

import java.util.HashMap;

import application.config;
import application.controleur.EcouteurMap;
import application.exception.CreditException;
import application.exception.GameOverException;
import application.modele.BFS.DeplacementBFS;
import application.modele.Case.Carte;
import application.modele.Case.Case;
import application.modele.Case.Mine;
import application.modele.Monstre.Monstre;
import application.modele.Monstre.Vague;
import application.modele.Tir.Tir;
import application.modele.Tir.normalTir;
import application.modele.Tir.ralentirTir;
import application.modele.Tir.zoneTir;
import application.modele.Tourelle.Tourelle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;

public class Jeu {

	private Carte map;

	private IntegerProperty vie;

	private HashMap<Integer, Tourelle> TourellesEnJeu = new HashMap<>();

	private HashMap<Integer, Mine> MinesEnJeu = new HashMap<>();

	private IntegerProperty credits;

	private Vague vague;

	private DeplacementBFS bfs;


	public Jeu() {
		this.vie = new SimpleIntegerProperty(config.vieInitiale);
		this.map = new Carte();
		this.vague = new Vague();
		this.credits = new SimpleIntegerProperty(500);
		this.bfs = new DeplacementBFS(this.map.getListe());
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
		this.credits.set(this.credits.getValue() + valeur);
	}

	public void removeCredits(int valeur) throws CreditException {
		if ((this.credits.getValue().intValue() - valeur) >= 0) {
			this.credits.set(this.credits.getValue().intValue() - valeur);
		} else {
			throw new CreditException();
		}
	}

	/***
	 * Gestion des mines
	 */
	public void placerMine(Mine mine, int indice) {
		this.map.getListe().set(indice, mine);
		this.MinesEnJeu.put(indice, mine);
	}

	public HashMap<Integer, Mine> getMineEnJeu() {
		return this.MinesEnJeu;
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
		if (this.map.getListe().get(indiceCurrentCase).getId() == 248) {
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

	public Boolean canBuyUpgrade(Tourelle tourelle) {
		return this.credits.getValue() - tourelle.getPrice().getValue() >= 0;
	}

	public void acheterUpgrade(Tourelle tourelle) throws CreditException {
		if (!this.canBuyUpgrade(tourelle)) {
			throw new CreditException();
		}
		this.credits.set(this.credits.getValue() - tourelle.getPrice().getValue());
	}

	public void finDeMap(Monstre currentMonstre) {
		if (currentMonstre.getY() == config.CASE_END_Y
				&& (currentMonstre.getX() == config.CASE_END_X1 || currentMonstre.getX() == config.CASE_END_X2)) {
			this.retirerMonstre(currentMonstre);
			try {
				this.decrementerVie();
			} catch (GameOverException e) {
				this.vague.viderListe();
			}
		}
	}

	public void rotationEtAttaqueDesTourelles(EcouteurMap ecouteurMap, Pane pane) {
		for (Tourelle tourelle : this.TourellesEnJeu.values()) {
			Monstre monstre = tourelle.detectMonstres();
			if (monstre != null) {
				ecouteurMap.rotationTourelleSurMonstre(monstre, tourelle);
				tourelle.decrementerCooldown();
				if (tourelle.canAttack()) {
					Tir tir = getTirByTourelle(tourelle, monstre, pane); 
					tir.deplacement();
					tir.agit();
					this.vague.mortMonstre();
				}
			}
		}
	}
	
	public Tir getTirByTourelle(Tourelle tourelle, Monstre monstre,  Pane pane) {
		Tir tir = null;
		switch (tourelle.getId()) {
		case 1:
			tir = new normalTir(monstre,tourelle, pane);
			break;
		case 2 :
			tir = new ralentirTir(monstre,tourelle, pane);
			break;
		case 3 :
			tir = new zoneTir(monstre,tourelle, pane);
			break;
		}
		return tir;
	}

	public Tourelle getTourelleByIndice(int indice) {
		return this.TourellesEnJeu.get(indice);
	}

	public Mine getMineByIndice(int indice) {
		return this.MinesEnJeu.get(indice);
	}

	
	public void gainCredit() {
		if (this.vague.getListMonstre().size() > 0) {
			for (Mine mine : this.MinesEnJeu.values()) {
				mine.decrementerCooldown();
				if (mine.getCooldownCredit() == 0) {
					mine.setCooldownCredit(config.cooldownCreditMine);
					this.credits.set(this.credits.getValue() + config.gainMine);
				}
			}
		}
	}

	/***
	 * Gestion du BFS et déplacement
	 */
	public DeplacementBFS getBFS() {
		return this.bfs;
	}
	
	public void gestionVitesseMonstre(Monstre monstre) {
		monstre.decrementeCooldownVitesse();
		if (monstre.getCooldownVitesse() == 0) {
			monstre.resetVitesse();
		}
	}

	public void deplaceMonstre() {

		for (int i = 0; i < this.vague.getListMonstre().size(); i++) {
			Monstre m = this.vague.getListMonstre().get(i);
			Case c = this.map.getListe().get((this.map.Indice(m.getX(), m.getY())));
			
			gestionVitesseMonstre(m);
			int indiceCasePere = this.map.getListe().indexOf(this.bfs.getCasePere(c));
			int indiceCaseSuivante = this.map.Indice(m.getX(), m.getY());
			if (indiceCasePere == indiceCaseSuivante + 1) {
				m.deplacement(2);
			} 
			else if (indiceCasePere == indiceCaseSuivante - 1) {
				m.deplacement(1);
			}
			else if (indiceCasePere == indiceCaseSuivante + 28) {
				m.deplacement(3);
			} 
			else if (indiceCasePere == indiceCaseSuivante - 28) {
				m.deplacement(4);
			}				
			finDeMap(m);
		}
	}
}
