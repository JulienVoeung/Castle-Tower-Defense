package application.testsJUnit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import application.config;
import application.exception.CreditException;
import application.modele.Jeu;
import application.modele.Monstre.Monstre;
import application.modele.Monstre.Pig;
import application.modele.Tourelle.Slots;
import application.modele.Tourelle.Tourelle;
import javafx.scene.layout.TilePane;

public class TourelleTests {

	/**
	 * Test de détection des monstres par les tourelles (avec la range)
	 */
	@Test
	public void testDetection() {
		Jeu jeu = new Jeu();
		// Tourelle avec 3 unités de range (environ 3 * 32 pixels d'écart)
		Tourelle tourelle = new Tourelle("TestTourelle", 1, 5, 10, 50, 5, jeu);
		Monstre monstre = new Pig("TestMonstre", config.CASE_X, config.CASE_Y);
		TilePane terrain = new TilePane();
		jeu.getVague().ajouterMonstre(monstre);
		jeu.getMap().lectureFichier(terrain);
		
		tourelle.setX(529);
		tourelle.setY(854);
		assertEquals(monstre, tourelle.detectMonstres());
		
		tourelle.setX(300);
		assertEquals(null, tourelle.detectMonstres());
	}
	
	/**
	 * Test d'achat des tourelles (gestion des crédits)
	 */
	@Test
	public void testAchatTourelle() {
		Jeu jeu = new Jeu();
		jeu.setCredits(100);
		Tourelle tourelle = new Tourelle("TestTourelle", 1, 5, 10, 50, 3, jeu);
		Slots slot = new Slots(jeu);
		
		try {
			slot.achatTourelleBoutique(tourelle);
		} catch (CreditException e) {
			e.printStackTrace();
		}
		
		assertEquals(95, jeu.getCredits());
	}
}
