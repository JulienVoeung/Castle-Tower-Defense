package application.testsJUnit;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import application.modele.Case.Carte;
import javafx.scene.layout.TilePane;

public class MapTests {

	@Test
	public void testLectureFichier() {
		Carte map = new Carte();
		TilePane terrain = new TilePane();
		
		map.lectureFichier(terrain);
		assertNotEquals(null, map.getListe());
	}
}
