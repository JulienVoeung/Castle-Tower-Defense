package application.vue;

import application.modele.Monstre.Monstre;
import application.modele.Monstre.Pig;
import javafx.collections.ListChangeListener;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class VueMonstre implements ListChangeListener<Monstre>{

	private Pane parcour;

	private ImageView monstre;
	
	public VueMonstre(Pane parcour) {
		this.parcour = parcour;
	}
	
	public void afficherMonstre(Monstre currentMonstre) {
		System.err.println("a " + currentMonstre);
		if (currentMonstre instanceof Pig) {
			monstre = new ImageView("file:src/assets/pig.png");
		}
		parcour.getChildren().add(monstre);	
		
		monstre.xProperty().bind(currentMonstre.getXProperty());
		monstre.yProperty().bind(currentMonstre.getYProperty());
	}

	@Override
	public void onChanged(Change<? extends Monstre> c) {
		while (c.next()) {
			
			for (int i = 0; i < c.getList().size(); i++) {
				afficherMonstre(c.getList().get(i));
			}
			
		}
	}
}
