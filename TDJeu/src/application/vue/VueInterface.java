package application.vue;

import application.modele.Jeu;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class VueInterface {
	
	private Jeu jeu;
	
	@FXML
    private TextField creditId;
	
	public VueInterface(Jeu jeu, TextField credits) {
		this.jeu = jeu;
		this.creditId = credits;
		
	}
	
	public void refreshInterface() {
		this.creditId.setText(String.valueOf(jeu.getCredits()));
	}
	
	
	
	
	
	
}
