package application.vue;

import application.modele.Jeu;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class VueMap extends TilePane{

	private TilePane terrain;

	private Jeu jeu;

	public VueMap(Jeu jeu, TilePane terrain) {
		this.jeu=jeu;
		this.terrain=terrain;
	}

	public void afficherMap() {
		ImageView img;
		for (int i = 0; i < jeu.getMap().getLargeur() * jeu.getMap().getHauteur(); i++) {

			switch(jeu.getMap().getListe().get(i).getId()) {
			  case 248:
				  img = new ImageView("file:src/assets/SolMonstre.png");
			    break;
			  case 100:
				  img = new ImageView("file:src/assets/Parcour.png");
				    break;
			  case 40:
				  img = new ImageView("file:src/assets/BlockTour.png");
				    break;

			  case 21:
				  img = new ImageView("file:src/assets/MurNord.png");
				    break;
			  case 59:
				  img = new ImageView("file:src/assets/MurGauche.png");

				    break;
			  case 41:
				  img = new ImageView("file:src/assets/MurDroite.png");
				    break;
			  case 39:
				  img = new ImageView("file:src/assets/MurSud.png");
				    break;

			  case 401:
				  img = new ImageView("file:src/assets/FacadeMur.png");
				    break;
			  default:
				  img = new ImageView("file:src/assets/BlockPlanche.png");
			}

			terrain.getChildren().add(img);
		}
	}

}
