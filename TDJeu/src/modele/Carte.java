package modele;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Carte {

	//private ObservableList<> obsListe = FXCollections.observableArrayList();
	
	private int largeur;
	
	public Carte() {
		
	}
	/*public Carte(ObservableList<> obliste) {
		this.obsListe = obliste;

	}*/


	public void lectureFichier() {
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
					creeTerrain(valeur);
				}
				

			}

		} catch (FileNotFoundException e) {
			System.err.printf("Le fichier %s n'a pas ete trouve , la map ne peut �tre charg�e.", file.toString());
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





	public void creeTerrain(String valeur) {
		/*if (Integer.parseInt(valeur) == 0) {
			block = new Air();
			obsListe.add(block);
		}
		if (Integer.parseInt(valeur) == 1) {
			block = new Herbe();
			obsListe.add(block);
		}
		if (Integer.parseInt(valeur) == 2) {
			block = new Terre();
			obsListe.add(block);
		}
		if (Integer.parseInt(valeur) == 3) {
			block = new Pierre();
			obsListe.add(block);
		}*/
	}



	/*public ObservableList<Blocks> getListe() {
		return obsListe;
		
	}

	public int getLargeur() {
		return this.largeur;
	}

	public int getHauteur() {
		return this.obsListe.size() / this.largeur;
	}

	public Blocks supprimerBlock(int x, int y) {
		
		Blocks bloc = changerBlockAir(x,y);
		return bloc;
	}
	public int indiceDellBlock(int x , int y) {
		return(int) (y/32)*100+(x/32);
		  
	}
	
	public void ajouterBlock(int x, int y, int id, Slot slot) {
		switch (id) {
		case 1:
			slot.enleveStock();
			obsListe.set(indiceDellBlock(x,y), new Herbe());
			break;
		case 2:
			slot.enleveStock();
			obsListe.set(indiceDellBlock(x,y), new Terre());
			break;
		case 3:
			slot.enleveStock();
			obsListe.set(indiceDellBlock(x,y), new Pierre());
		break;
		default:
			break;
		}
		
		
	}
	// Renvoie l'indice du block dans la liste observable 
	public int Indice(int x , int y) {
		return(int) (y/32+1)*100+(x/32+1);
			
	}
	
	public Blocks changerBlockAir(int x , int y) {
		return obsListe.set(indiceDellBlock(x, y), new Air());
	}
	
	public Blocks recupIdBlock(Blocks b) {
		for (int i = 0 ; i < obsListe.size() ; i++) {
			if (obsListe.get(i).getId()== b.getId()) {
				return obsListe.get(i);
			}
		}
		return null;
	}
	*/
}