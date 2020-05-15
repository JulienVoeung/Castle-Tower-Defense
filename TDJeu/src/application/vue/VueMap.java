package application.vue;

import application.modele.Jeu;
import application.modele.Case.Case;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.collections.ListChangeListener;


public class VueMap implements ListChangeListener<Case>  {
	
	private TilePane terrain;
	
	private Jeu jeu;
	
	private int angle;
	
	public VueMap(Jeu jeu, TilePane terrain) {
		this.jeu=jeu;
		this.terrain=terrain;
		this.angle=0;
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
	
	@Override
	public void onChanged(Change<? extends Case> e) {
		ImageView img = new ImageView("file:src/assets/BlockTour.png");
		while (e.next()) {
			if(e.wasReplaced()) {
				
				switch (jeu.getMap().getListe().get(e.getFrom()).getId()) {
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
			}
			img.setRotate(this.angle);
			terrain.getChildren().set(e.getFrom(), img);
		}	
	}
	
	public void setRotationAngleImage(int orientation) {
		int currentAngle = 0;
		switch (orientation) {
		case 2:
			currentAngle = 90;
			break;
		case 3:
			currentAngle = 180;
			break;
		case 4:
			currentAngle = 270;
			break;
		default:
			break;
		}
		this.angle=currentAngle;
	}

}
