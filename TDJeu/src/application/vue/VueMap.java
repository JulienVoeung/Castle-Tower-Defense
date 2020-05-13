package application.vue;

import application.modele.Jeu;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class VueMap {
	
	private TilePane terrain;
	
	private Jeu jeu;
	
	public VueMap(Jeu jeu, TilePane terrain) {
		this.jeu=jeu;
		this.terrain=terrain;
	}
	
	/***
	 * Methode qui permet l'affichage de la map
	 */
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
	
	public void refreshCurrentCase(int indice, int idTourelle, int orientation) {
		ImageView img = new ImageView("file:src/assets/BlockTour.png");;
		switch (idTourelle) {
		case 1:
			img = new ImageView("file:src/assets/tower1.png");
			break;
		case 2:
			img = new ImageView("file:src/assets/tower2.png");
			break;
		case 3:
			img = new ImageView("file:src/assets/tower3.png");
			break;
		}
		img.setRotate(this.getRotationAngleImage(orientation));
		terrain.getChildren().set(indice, img);
	}
	
	public int getRotationAngleImage(int orientation) {
		int angle = 0;
		switch (orientation) {
		case 2:
			angle = 90;
			break;
		case 3:
			angle = 180;
			break;
		case 4:
			angle = 270;
			break;
		default:
			break;
		}
		return angle;
	}
}
