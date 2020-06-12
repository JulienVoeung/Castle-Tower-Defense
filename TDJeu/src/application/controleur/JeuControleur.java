package application.controleur;

import java.net.URL;
import java.util.ResourceBundle;

import application.exception.CreditException;
import application.modele.Jeu;
import application.modele.Case.Case;
import application.modele.Case.Mine;
import application.modele.Monstre.Monstre;
import application.modele.Tourelle.Slots;
import application.modele.Tourelle.Tourelle;
import application.vue.VueMap;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
    private ImageView slot4;

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
    
    private Tourelle clickedTourelle;
    
    private Mine selectedMine;
    
	private EcouteurMap ecouteurMap;
	
	@FXML
    private Pane upgradePane;

    @FXML
    private Text NomTourelle;

    @FXML
    private Text damage;

    @FXML
    private Text cooldown;

    @FXML
    private Text Price;
    
    @FXML
    private Text priceLabel;

    @FXML
    private Button upgrade;

	
	/***
	 * Methode qui initialise tout l'affichage.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		jeu = new Jeu();
		jeu.getMap().lectureFichier(terrain);
		jeu.getBFS().algoBFS();
		
		VueMap vMap = new VueMap(jeu, terrain);
		vMap.afficherMap();
		ecouteurMap = new EcouteurMap(jeu, terrain);
		
		// Taille de la map
		terrain.setPrefWidth(28 * 32);
		terrain.setPrefHeight(28 * 32);
		
		// Game loop
		initLoop();
		gameLoop.play();
		System.out.println(jeu.getMap().Indice(403, 240));

		//Bind & Listener
		creditId.textProperty().bind(jeu.getCreditsProperty().asString());
		vagueId.textProperty().bind(jeu.getVague().getNiveau().asString());
		vieId.textProperty().bind(jeu.getVie().asString());
		
		jeu.getVague().getListMonstre().addListener((ListChangeListener<? super Monstre>) new EcouteurMonstre(parcour));	
		jeu.getMap().getListe().addListener((ListChangeListener<? super Case>) new EcouteurMap(jeu, terrain));
		
	}
	
	/***
	 * Gain des vagues 
	 */
    @FXML
    void dbProchaineVague(MouseEvent event) {
    	if (this.jeu.getVague().isFinished() && this.jeu.getVie().getValue() > 0 ) {
			this.jeu.getVague().incrementerNiveau();
			this.jeu.getVague().gainFinVague(this.jeu);
		}
    }
    
   
	
		
	/***
	 * Gestion des tourelles
	 * Clique sur slot pour effectuer un achat
	 * Params (achatSlot) : Nom, tourelleId, prix, degats, cooldown, range
	 */
    @FXML
    void AchatSlot1(MouseEvent event) {
    	achatTourelle("T1", 1, 100, 1, 2, 3);
    }
    
    @FXML
    void AchatSlot2(MouseEvent event) {
    	achatTourelle("T2", 2, 200, 2, 4, 3);
    }
    
    @FXML
    void AchatSlot3(MouseEvent event) {
    	achatTourelle("T3", 3, 300, 5, 6, 6);
    }
    
    @FXML
    void AchatSlot4(MouseEvent event) {
    	achatMine();
    }
    
    public void achatTourelle(String nom, int tourelleId, int prix, int degats, int cooldown, int range) {
    	if (selectedTourelle == null) {
    		Tourelle tourelle = new Tourelle(nom, tourelleId, prix, degats, cooldown, range, this.jeu);
    		slot = new Slots(jeu);
        	try {
        		slot.achatTourelleBoutique(tourelle);
            	selectedTourelle = tourelle;
        	} catch (CreditException e) {}
    	}
    }
    
    public void achatMine() {
    	if (selectedMine == null) {
    		Mine mine = new Mine();
    		slot = new Slots(jeu);
        	try {
        		slot.achatMineBoutique();
        		selectedMine = mine;
        	} catch (CreditException e) {}
    	}
    }
    
    
	/***
	 * Permet l'amelioration de la tourelle cliquee 
	 * @param event
	 * @throws CreditException 
	 */
    @FXML
    void upgradeTourelle(MouseEvent event) {
    	try {
    		jeu.acheterUpgrade(clickedTourelle);
    		
        	clickedTourelle.incrementerNiveau();

    		if (clickedTourelle.getNiveau() >= 3) {
    			upgrade.setVisible(false);
    			Price.setVisible(false);
    			priceLabel.setVisible(false);
    		} 
    	} catch (CreditException ex) {
    		Alert alerte = new Alert(AlertType.ERROR);
			alerte.setContentText("Crédits insuffisants");
			alerte.setHeaderText(null);
			alerte.showAndWait();
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
		System.out.println(event.getX() + " " + event.getY());
		if (currentCase.getId() == 40 && selectedTourelle != null) {
			placerTourelleSurTerrain(currentCase, indice, (int) event.getX(), (int) event.getY());
    	}
		if (currentCase.getId() == 50 && selectedMine != null) {
			placerMineSurTerrain(currentCase, indice);
    	} 
    	if (currentCase.getId() >= 1 && currentCase.getId() <= 3) {
    		afficherInformationTourelle(currentCase, indice);
		}
    	
    	System.out.println(event.getX() + " " + event.getY() );
    }
    
    /***
     * Placement de la tourelle sur un indice precis
     * @param currentCase
     * @param indice
     * @param x
     * @param y
     */
    public void placerTourelleSurTerrain(Case currentCase, int indice, int x, int y) {
    	jeu.placerTourelle(selectedTourelle, indice);
		selectedTourelle.setX(x);
		selectedTourelle.setY(y);
		selectedTourelle = null;
    }
    
    /***
     * Placement de la mine sur un indice precis
     * @param currentCase
     * @param indice
     * @param x
     * @param y
     */
    public void placerMineSurTerrain(Case currentCase, int indice) {
    	jeu.placerMine(selectedMine, indice);
		selectedMine = null;
    }
    
    /***
     * Permet l'affichage des informations d'une tourelle cliquee
     * @param currentCase
     * @param indice
     */
    public void afficherInformationTourelle(Case currentCase, int indice) {
    	Tourelle currentClickedTourelle = jeu.getTourelleByIndice(indice);
    	
		upgradePane.setVisible(!upgradePane.isVisible() || clickedTourelle != currentClickedTourelle);

		clickedTourelle = currentClickedTourelle;
		
		NomTourelle.textProperty().bind(clickedTourelle.getNom());
		damage.textProperty().bind(clickedTourelle.getDegats().asString());
		cooldown.textProperty().bind(clickedTourelle.getDefaultCooldown().asString());
		Price.textProperty().bind(clickedTourelle.getPrice().asString());
		if (currentClickedTourelle.getNiveau() == 3) {
			upgrade.setVisible(false);
			Price.setVisible(false);
			priceLabel.setVisible(false);
		} else {
			upgrade.setVisible(true);
			Price.setVisible(true);
			priceLabel.setVisible(true);
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
				Duration.seconds(0.033),
				// on definit ce qui se passe a chaque frame
				// c'est un eventHandler d'ou le lambda
				(ev -> {
					if (temps % 2 == 0) {
						jeu.getVague().spawnMonstres();
						jeu.rotationEtAttaqueDesTourelles(ecouteurMap, this.parcour);
						jeu.gainCredit();
						jeu.deplaceMonstre();
					}
					temps++;
				}));
		gameLoop.getKeyFrames().add(frames);
	}
	
}


