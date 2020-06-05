package application.controleur;

import java.net.URL;
import java.util.ResourceBundle;

import application.exception.CreditException;
import application.modele.Jeu;
import application.modele.BFS.DeplacementBFS;
import application.modele.Case.Case;
import application.modele.Monstre.Monstre;
import application.modele.Tourelle.Slots;
import application.modele.Tourelle.Tourelle;
import application.vue.VueMap;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.util.Duration;


public class JeuControleur implements Initializable {
	
	private Jeu jeu;
	
	@FXML
	private Timeline gameLoop;
	private int temps;
	
	@FXML
	private TilePane terrain;
	
    @FXML
    private Pane parcour;

	@FXML
    private ImageView slot1;
	
    @FXML
    private ImageView slot2;
    
    @FXML
    private ImageView slot3;
    
	@FXML
	private ImageView vueMonstre;
    
    @FXML
    private TextField creditId;
    
    @FXML
    private Text vagueId;
    
    @FXML
    private Button dbVague;

    @FXML
    private Text vieId;

    
    private Slots slot;
    
    private Tourelle selectedTourelle;
    
	private EcouteurMap ecouteurMap;
	/***
	 * Methode qui initialise tout l'affichage.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		jeu = new Jeu();
		jeu.getMap().lectureFichier(terrain);
		DeplacementBFS bfs = new DeplacementBFS();
		bfs.deplaceBFS(jeu.getMap().getListe());
		//jeu.getVague().incrementerNiveau();
		
		VueMap vMap = new VueMap(jeu, terrain);
		vMap.afficherMap();
		ecouteurMap = new EcouteurMap(jeu, terrain);
		
		// Taille de la map
		terrain.setPrefWidth(28 * 32);
		terrain.setPrefHeight(28 * 32);
		
		// Game loop
		initLoop();
		gameLoop.play();
		
		//Bind & Listener
		creditId.textProperty().bind(jeu.getCreditsProperty().asString());
		vagueId.textProperty().bind(jeu.getVague().getNiveau().asString());
		vieId.textProperty().bind(jeu.getVie().asString());
		
		jeu.getVague().getListMonstre().addListener((ListChangeListener<? super Monstre>) new EcouteurMonstre(parcour));	
		jeu.getMap().getListe().addListener((ListChangeListener<? super Case>) new EcouteurMap(jeu, terrain));
		
	}
	
	/***
	 * Gain de crédits 
	 */
    @FXML
    void dbProchaineVague(MouseEvent event) {
    	if (jeu.getVague().isFinished() && jeu.getVie().getValue() > 0) {
			jeu.getVague().incrementerNiveau();
			gainFinVague();
		}
    }
    
    public void gainFinVague() {
		jeu.addCredits(250);
	}
	
		
	/***
	 * Clique sur slot pour effectuer un achat
	 */
    @FXML
    void AchatSlot1(MouseEvent event) {
    	achatSlot("T1", 1, 100, 40, 3, 3);
    }
    
    @FXML
    void AchatSlot2(MouseEvent event) {
    	achatSlot("T2", 2, 200, 40, 3, 3);
    }
    
    @FXML
    void AchatSlot3(MouseEvent event) {
    	achatSlot("T3", 3, 300, 150, 5, 5);
    }
    
    public void achatSlot(String nom, int tourelleId, int prix, int degats, int cooldown, int range) {
    	if (selectedTourelle == null) {
    		Tourelle tourelle = new Tourelle(nom, tourelleId, prix, degats, cooldown, range, this.jeu);
    		slot = new Slots(jeu);
        	try {
        		slot.achatBoutique(tourelleId);
            	selectedTourelle = tourelle;
        	} catch (CreditException e) {}
    	}
    }
	
    /***
     * Evenement lors du clique sur le terrain pour le placement d'une tourelle
     * Permet de définir une tourelle lors du clic sur une case emplacement
     * @param event
     */
    @FXML
    void CliqueTerrain(MouseEvent event) {
    	int indice = jeu.getMap().Indice((int) event.getX(), (int) event.getY());
		Case currentCase = jeu.getMap().getListe().get(indice);
    	if (currentCase.getId() == 40 && selectedTourelle != null) {
    		jeu.placerTourelle(selectedTourelle, indice);
    		selectedTourelle.setX((int) event.getX());
    		selectedTourelle.setY((int) event.getY());
    		selectedTourelle = null;
    	}
    }
	
	/***
	 * Boucle du jeu
	 */
	public void initLoop() {
		gameLoop = new Timeline();
		gameLoop.setCycleCount(Timeline.INDEFINITE);
		
		KeyFrame frames = new KeyFrame(
				// on definit les FPS (nombre de frames par secondes)
				Duration.seconds(0.500),
				// on definit ce qui se passe a chaque frame
				// c'est un eventHandler d'ou le lambda
				(ev -> {
					if (temps % 2 == 0) {	
						jeu.getVague().spawnMonstres();
						jeu.rotationEtAttaqueDesTourelles(ecouteurMap); 
						
						for (int j = 0; j < jeu.getVague().getListMonstre().size(); j++) {
							Monstre currentMonstre = jeu.getVague().getListMonstre().get(j);
							if (jeu.deplacementAutoriser(currentMonstre)) {
								currentMonstre.deplacerHaut();
								jeu.verifyDeplacements(currentMonstre);
							}
						}
					}
					temps++;
				}));
		gameLoop.getKeyFrames().add(frames);
	}
}


