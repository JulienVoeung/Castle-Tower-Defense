package application.controleur;

import java.net.URL;
import java.util.ResourceBundle;

import application.exception.CreditException;
import application.modele.Jeu;
import application.modele.Case.Case;
import application.modele.tourelle.Slots;
import application.modele.tourelle.Tourelle;
import application.vue.VueInterface;
import application.vue.VueMap;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.util.Duration;


public class JeuControleur implements Initializable {

	private Jeu jeu;
	private VueMap vue;
	private VueInterface vueInterface;
	
	@FXML
	private Timeline gameLoop;
	private int temps;
	
	@FXML
	private TilePane terrain;
		
	
	@FXML
    private ImageView slot1;

    @FXML
    private ImageView slot2;

    @FXML
    private ImageView slot3;

    @FXML
    private TextField creditId;

    @FXML
    private Text vagueId;

    private Slots slot;
    
    private Tourelle selectedTourelle;
	
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
		
		vueInterface = new VueInterface(jeu, creditId);
		vue = new VueMap(jeu, terrain);
		vue.afficherMap();
		
	}
	
	/***
	 * Gain de crédits 
	 */
	public void gainFinVague() {
		jeu.addCredits(250);
	}
		
	/***
	 * Clique sur slot pour effectuer un achat
	 * TODO à optimiser
	 */
    @FXML
    void AchatSlot1(MouseEvent event) {
    	Tourelle t1 = new Tourelle("T1", 1);
    	slot = new Slots(t1, jeu);
    	try {
    		slot.achatBoutique(1);
        	selectedTourelle = t1;
    	} catch (CreditException e) {}
    }

    @FXML
    void AchatSlot2(MouseEvent event) {
    	Tourelle t2 = new Tourelle("T2", 2);
    	slot = new Slots(t2, jeu);
    	try {
    		slot.achatBoutique(2);
        	selectedTourelle = t2;
    	} catch (CreditException e) {}
    }

    @FXML
    void AchatSlot3(MouseEvent event) {
    	Tourelle t3 = new Tourelle("T3", 3);
    	slot = new Slots(t3, jeu);
    	try {
    		slot.achatBoutique(3);
        	selectedTourelle = t3;
    	} catch (CreditException e) {}
    }
	
    /***
     * Evenement lors du clique sur le terrain pour le placement d'une tourelle
     * Permet de définir une tourelle lors du clic sur une case emplacement
     * Définit également l'orientation de la tourelle
	 * TODO à optimiser
     * @param event
     */
    @FXML
    void CliqueTerrain(MouseEvent event) {
    	int indice = jeu.getMap().Indice((int) event.getX(), (int) event.getY());
		Case currentCase = jeu.getMap().getListe().get(indice);
    	if (currentCase.getId() == 40 && selectedTourelle != null) {
    		jeu.placerTourelle(selectedTourelle, indice);

    		// Orientation des tourelles lors du placement 
    		int orientation = 1;
			int atLeft = jeu.getMap().getListe().get(indice - 1).getId();
			int atRight = jeu.getMap().getListe().get(indice + 1).getId();
			int atBottom = jeu.getMap().getListe().get(indice + 28).getId();
			
			if (atRight == 100) {
				orientation = 2;
			} else if (atBottom == 100) {
				orientation = 3;
			} else if (atLeft == 100) {
				orientation = 4;
			}
			
    		vue.refreshCurrentCase(indice, selectedTourelle.getId(), orientation);
    		selectedTourelle = null;
    	}
    }
	
	/***
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
						vueInterface.refreshInterface();
					}
					temps++;
				}));
		gameLoop.getKeyFrames().add(kf);
	}


}
