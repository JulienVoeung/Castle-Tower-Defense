package application.vue;

import application.modele.Jeu;
import application.modele.Monstre.Monstre;
import application.modele.Monstre.Pig;
import javafx.collections.ListChangeListener;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class VueMonstre implements ListChangeListener<Monstre>{

	private Pane parcour;
	
	private Jeu jeu;
	
	public VueMonstre(Jeu jeu, Pane parcour) {
		this.jeu = jeu;
		this.parcour = parcour;
	}
		
	public void creerMonstres() {
		for (Monstre currentMonstre : jeu.getVague().getListMonstre()) {
			ImageView imgMonstre = null;
			
			if (currentMonstre instanceof Pig) {
				imgMonstre = new ImageView("file:src/assets/pig.png");
			}
			parcour.getChildren().add(imgMonstre);
			
			imgMonstre.xProperty().bind(currentMonstre.getXProperty());
			imgMonstre.yProperty().bind(currentMonstre.getYProperty());
			
		}
	}
	
	public void afficherMonstre(Monstre monstre) {
		
	}

	@Override
	public void onChanged(Change<? extends Monstre> c) {
		afficherMonstre(jeu.getVague().getListMonstre().get(c.getFrom()));
	}
}
