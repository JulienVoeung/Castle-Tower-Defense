package application.modele.Tourelle;

import application.exception.CreditException;
import application.modele.Jeu;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Slots {

	private Jeu jeu;
	
	public Slots() {}
	
	public Slots(Jeu jeu) {
		this.jeu = jeu;
	}
	
	/***
	 * Retrait de cr�dits 
	 */
	public void achatTourelleBoutique(Tourelle tourelle) throws CreditException {
		try {
			jeu.removeCredits(tourelle.getPrix());
		} catch (CreditException e) {
			Alert alerte = new Alert(AlertType.ERROR);
			alerte.setContentText("Cr�dits insuffisants");
			alerte.setHeaderText(null);
			alerte.showAndWait();
			throw new CreditException();
		}
	}
	
	/***
	 * Retrait de cr�dits 
	 */
	public void achatMineBoutique() throws CreditException {
		try {
			jeu.removeCredits(500);
		} catch (CreditException e) {
			Alert alerte = new Alert(AlertType.ERROR);
			alerte.setContentText("Cr�dits insuffisants");
			alerte.setHeaderText(null);
			alerte.showAndWait();
			throw new CreditException();
		}
	}
	
}
