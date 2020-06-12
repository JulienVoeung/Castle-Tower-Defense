package application.modele.Monstre;

import java.util.ArrayList;

import application.config;
import application.modele.Jeu;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Vague {

	private ObservableList<Monstre> listMonstreEnJeu = FXCollections.observableArrayList();
	
	private ArrayList<Monstre> listMonstresStockés = new ArrayList<>();
	
	
	private IntegerProperty niveau;
	
	public Vague() {
		this.niveau = new SimpleIntegerProperty(0);
	}
		
	public void ajouterMonstre(Monstre monstre) {
		 this.listMonstreEnJeu.add(monstre);
	}
	
	public void ajouterMonstres(ArrayList<Monstre> monstres) {
		this.listMonstreEnJeu.addAll(monstres);
	}
	
	public void incrementerNiveau() {
		int newNiveau = this.niveau.get() + 1;
		this.niveau.set(newNiveau);
		this.viderListe();
		this.listMonstresStockés.addAll(config.getMonstreByNiveau(this.niveau.getValue()));
	}
	
	public void spawnMonstres() {
		if (this.listMonstresStockés.size() > 0) {
			this.listMonstreEnJeu.add(this.listMonstresStockés.get(0));
			this.listMonstresStockés.remove(0);	
		}
	}
	
	public void viderListe() {
		this.listMonstreEnJeu.clear();
	}

	public IntegerProperty getNiveau() {
		return this.niveau;
	}
	
	public void setNiveau(int valeur) {
		this.niveau.set(valeur);
	}
	
	public ObservableList<Monstre> getListMonstre(){
		return this.listMonstreEnJeu;
	}

	public boolean isFinished() {
		int sizeListMonstre = this.listMonstreEnJeu.size();
		int sizeListMonstreStock = this.listMonstresStockés.size();
		return sizeListMonstre == 0 && sizeListMonstreStock == 0;
	}
	
	 public void gainFinVague(Jeu jeu) {
    	if (isFinished()) {
    		jeu.addCredits(250);
		}
	}
	
	 public void mortMonstre() {
		ArrayList<Monstre> monstresEnJeu = new ArrayList<Monstre>();
		monstresEnJeu.addAll(this.listMonstreEnJeu);
		for(Monstre m : monstresEnJeu) {
			if (m.getPv() == 0) {
				this.listMonstreEnJeu.remove(m);
				if (m instanceof RoiPig) {
					for (int i = 0; i < 2; i++) {
						Monstre newMonstre = new Pig("Pig" + i, m.getX(), m.getY());	
						this.listMonstreEnJeu.add(newMonstre);
					}
				}
			}
			
		}
	}
}
