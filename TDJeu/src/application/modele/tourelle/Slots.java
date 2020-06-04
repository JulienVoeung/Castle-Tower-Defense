package application.modele.tourelle;

import application.exception.CreditException;
import application.modele.Jeu;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Slots {

	private Jeu jeu;
	private Tourelle tourelle;
	
	public Slots() {}
	
	public Slots(Tourelle tourelle, Jeu jeu) {
		this.tourelle = tourelle;
		this.jeu = jeu;
	}
	
	/***
	 * Retrait de crédits 
	 */
	public void achatBoutique(int slot) throws CreditException {
		try {
			switch (slot) {
			case 2:
				jeu.removeCredits(200);
				break;
			case 3:
				jeu.removeCredits(300);
				break;
			default:
				jeu.removeCredits(100);
				break;
			}
		} catch (CreditException e) {
			Alert alerte = new Alert(AlertType.ERROR);
			alerte.setContentText("Crédits insuffisants");
			alerte.setHeaderText(null);
			alerte.showAndWait();
			throw new CreditException();
		}
	}
}
