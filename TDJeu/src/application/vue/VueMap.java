package application.vue;

import application.modele.Jeu;
import application.modele.Case.Case;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.collections.ListChangeListener;


public class VueMap implements ListChangeListener<Case>  {
	
	private TilePane terrain;
	
	private Jeu jeu;
	
	public VueMap(Jeu jeu, TilePane terrain) {
		this.jeu = jeu;
		this.terrain = terrain;
	}
	
	@Override
	public void onChanged(Change<? extends Case> e) {
		ImageView img = new ImageView("file:src/assets/BlockTour.png");;

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
			terrain.getChildren().set(e.getFrom(), getImgWithRotation(img, e.getFrom()));
		}	
	}
	
	public ImageView getImgWithRotation(ImageView img, int indice) {
		// Orientation des tourelles lors du placement 
		int atLeft = jeu.getMap().getListe().get(indice - 1).getId();
		int atRight = jeu.getMap().getListe().get(indice + 1).getId();
		int atBottom = jeu.getMap().getListe().get(indice + 28).getId();
		
		if (atRight == 100) {
			img.setRotate(90.0);
		} else if (atBottom == 100) {
			img.setRotate(180.0);
		} else if (atLeft == 100) {
			img.setRotate(270.0);
		}
		return img;
	}
}
