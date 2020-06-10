package application.controleur;

import application.modele.Monstre.Monstre;
import application.modele.Monstre.Pig;
import application.modele.Monstre.RoiPig;
import javafx.collections.ListChangeListener;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class EcouteurMonstre implements ListChangeListener<Monstre>{

	private Pane parcour;

	private ImageView monstre;
	
	public EcouteurMonstre(Pane parcour) {
		this.parcour = parcour;
	}
	
	public void afficherMonstre(Monstre currentMonstre) {
		if (currentMonstre instanceof Pig) {
			monstre = new ImageView("file:src/assets/pig.png");
		}
		if (currentMonstre instanceof RoiPig) {
			monstre = new ImageView("file:src/assets/RoiPig.png");
		}
		this.parcour.getChildren().add(monstre);	
		
		this.monstre.xProperty().bind(currentMonstre.getXProperty());
		this.monstre.yProperty().bind(currentMonstre.getYProperty());
	}

	@Override
	public void onChanged(Change<? extends Monstre> c) {
		while (c.next()) { 
			if (c.wasAdded()) {
				afficherMonstre(c.getList().get(c.getFrom()));
			}
			if (c.wasRemoved()) {
				if (c.getRemovedSize() > 1) {
					for (int i = 0; i < c.getRemoved().size(); i++) {
						this.parcour.getChildren().remove(c.getFrom() + 1);
					}
				} else {
					this.parcour.getChildren().remove(c.getFrom() + 1);
				}
			}
		}
	}
	
}
