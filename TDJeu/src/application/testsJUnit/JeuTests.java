package application.testsJUnit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import application.modele.Jeu;
import application.modele.Monstre.Monstre;
import application.modele.Monstre.Pig;

public class JeuTests {
	
	/***
	 * Test les crédits par défaut
	 */
	@Test
	public void testCredit() {
		Jeu jeu = new Jeu();		
		assertEquals(500, jeu.getCredits());
	}
	
	/***
	 * Test d'ajout et de suppression de monstre dans le jeu
	 */
	@Test
	public void testMonstre() {
		Jeu jeu = new Jeu();
		Monstre monstre = new Pig("TestPig", 0, 0);

		jeu.getVague().ajouterMonstre(monstre);
		assertEquals(monstre, jeu.getVague().getListMonstre().get(0));
		
		jeu.retirerMonstre(monstre);
		assertEquals(0, jeu.getVague().getListMonstre().size());
	}

}
