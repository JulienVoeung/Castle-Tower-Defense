package application.modele.Case;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.TilePane;


public class Carte {

	private ObservableList<Case> obsListe = FXCollections.observableArrayList();
	
	private int largeur;
	
	
	public Carte() {}
	
	public Carte(ObservableList<Case> obliste) {
		this.obsListe = obliste;

	}


	public void lectureFichier(TilePane terrain) {
		File file = new File("MAP.csv");
		BufferedReader buffuredReader = null;
		int compteur = 0;

		
		try {
			FileReader fileReader = new FileReader(file);
			buffuredReader = new BufferedReader(fileReader);

			String line;
			while ((line = buffuredReader.readLine()) != null) {

				String[] map = line.split(",");
				String valeur;

				compteur++;

				for (int i = 0; i < map.length; i++) {
					valeur = map[i];
					creeTerrain(valeur, terrain);
				}
			}

		} catch (FileNotFoundException e) {
			System.err.printf("Le fichier %s n'a pas ete trouve , la map ne peut pu etre charge.", file.toString());
		} catch (IOException e) {
			System.err.println("Impossible de lire le fichier");
		}

		try {
			buffuredReader.close();
		} catch (IOException e) {
			System.err.println("Impossible de fermer le fichier");
		} catch (NullPointerException e) {
			System.err.println("Impossible d'ouvrir le fichier");
		}
		this.largeur = compteur;
	}

	public void creeTerrain(String valeur, TilePane terrain) {
		Case b;
			switch(Integer.parseInt(valeur)) {
			  case 248:
				  b = new SolsMonstre();
			    break;
			  case 100:
				  b = new Parcours();
				    break;
			  case 40:
				  b = new EmplacementTourelle();
				    break;
			  case 21:
				  b = new MurNord();
				    break;
			  case 59:
				  b = new MurSud();
				    break;
			  case 41:
				  b = new MurEst();
				    break;
			  case 39:
				  b = new MurOuest();
				    break;
			  case 401:
				  b = new Facade();
				    break;
			  case 50:
				  b = new EmplacementMine();
				    break;
			  case 51:
				  b = new Mine();
				    break;
			  default:
				  b = new Planche();
			}
		obsListe.add(b);
	}

	public ObservableList<Case> getListe() {
		return obsListe;
	}

	public int getLargeur() {
		return this.largeur;
	}

	public int getHauteur() {
		return this.obsListe.size() / this.largeur;
	}
	
	//Renvoie l'indice du block dans la liste observable 
	public int Indice(double x , double y) {
		return ((int) (y/32)*28+ (int) (x/32));	
	}
	
	//Renvoie l'indice du block dans la liste observable 
	public int coorXByIndice(int indice) {
		return indice%28*32;	
	}
		
	//Renvoie l'indice du block dans la liste observable 
	public int coorYByIndice(int indice) {
		return indice/28*32;
	}
}