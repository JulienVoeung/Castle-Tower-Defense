package application.controleur;

import application.modele.Jeu;
import application.modele.Case.Case;
import application.modele.Monstre.Monstre;
import application.modele.Tourelle.Tourelle;
import application.utils.utils;
import javafx.collections.ListChangeListener;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;


public class EcouteurMap implements ListChangeListener<Case>  {
	
	private TilePane terrain;
	
	private Jeu jeu;
	
	public EcouteurMap(Jeu jeu, TilePane terrain) {
		this.jeu = jeu;
		this.terrain = terrain;
	}
	
	@Override
	public void onChanged(Change<? extends Case> e) {
		ImageView img = new ImageView("file:src/assets/BlockTour.png");

		while (e.next()) {
			if(e.wasReplaced()) {
				img = choixTourellesOuMine(e.getFrom());
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
	
	public void setRotation(Tourelle tourelle, double angle) {
		int indice = 0;
		for (int key : jeu.getTourellesEnJeu().keySet()) {
			if (tourelle.equals(jeu.getTourellesEnJeu().get(key))) {
				indice = key;
			}
		}
		
		ImageView img = choixTourellesOuMine(indice);
		img.setRotate(angle);
		terrain.getChildren().set(indice, img);
	}
	
	public ImageView choixTourellesOuMine(int indice) {
		ImageView img = new ImageView("file:src/assets/BlockTour.png");
		switch (jeu.getMap().getListe().get(indice).getId()) {
		case 1:
			img = new ImageView("file:src/assets/tower1.png");
			break;
		case 2:
			img = new ImageView("file:src/assets/tower2.png");
			break;
		case 3:
			img = new ImageView("file:src/assets/tower3.png");
			break;
		case 51:
			img = new ImageView("file:src/assets/mine.png");
			break;
		}
		return img;
	}
	
	public void rotationTourelleSurMonstre(Monstre monstre, Tourelle tourelle) {
		double angle = utils.getAngle(monstre.getX(), monstre.getY(), tourelle.getX(), tourelle.getY());
		this.setRotation(tourelle, angle);
	}
}
