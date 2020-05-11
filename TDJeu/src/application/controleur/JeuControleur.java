package application.controleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import application.modele.Jeu;
import application.vue.VueMap;


public class JeuControleur implements Initializable {

	private Jeu jeu;
	private int temps;
	private VueMap vue;
	
	@FXML
	private TilePane terrain;

	@FXML
	private Timeline gameLoop;
	
	
	/***
	 * Methode qui initialise tout l'affichage.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// Taille de la map
		terrain.setPrefWidth(28 * 32);
		terrain.setPrefHeight(28 * 32);
		
		// Game loop
		initLoop();
		gameLoop.play();
		
		jeu = new Jeu();
		jeu.getMap().lectureFichier();
		
		vue = new VueMap(jeu, terrain);
		vue.afficherMap();

	}

	
	/*
	 * Boucle du jeu
	 */
	public void initLoop() {
		gameLoop = new Timeline();
		gameLoop.setCycleCount(Timeline.INDEFINITE);

		KeyFrame kf = new KeyFrame(
				// on definit les FPS (nombre de frames par secondes)
				Duration.seconds(0.017),
				// on definit ce qui se passe a chaque frame
				// c'est un eventHandler d'ou le lambda
				(ev -> {
					if (temps % 2 == 0) {
						
					}
					temps++;
				}));
		gameLoop.getKeyFrames().add(kf);
	}


}
