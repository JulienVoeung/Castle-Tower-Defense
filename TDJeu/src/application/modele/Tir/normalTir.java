package application.modele.Tir;

import application.modele.Monstre.Monstre;
import application.modele.Tourelle.Tourelle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class normalTir extends Tir{

	private Pane pane;
	public normalTir(Monstre m, Tourelle tourelle, Pane pane) {
		super(m,tourelle);
		this.pane = pane;
	}

	@Override
	public void deplacement() {

		Circle circle = new Circle();
		circle.setRadius(5);
		circle.setTranslateX(super.getTourelle().getX());
		circle.setTranslateY(super.getTourelle().getY());
		circle.setFill(Color.GREEN);
		circle.toFront();
		pane.getChildren().add(circle);

		TranslateTransition translate = new TranslateTransition(Duration.millis(100), circle);
		translate.setFromX(super.getTourelle().getX());
		translate.setToX(super.getMonstre().getX());
		translate.setFromY(super.getTourelle().getY());
		translate.setToY(super.getMonstre().getY());
		translate.play();
		
		translate.onFinishedProperty().set(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				circle.setVisible(false);
				pane.getChildren().remove(circle);
				
			}
		});

	}

	@Override
	public void agit() {
		super.getMonstre().decrementerVie(super.getTourelle().getDegats().getValue());
	}

}
