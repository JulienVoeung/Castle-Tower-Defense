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
			case 51:
				jeu.removeCredits(500);
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
