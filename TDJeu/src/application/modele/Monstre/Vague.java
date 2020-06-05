package application.modele.Monstre;

import java.util.ArrayList;

import application.config;
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
	
	public void attaqueEtMortDuMonstre(Monstre monstre) {
		monstre.decrementerVie();
		if (monstre.getPv() == 0) {
			this.listMonstreEnJeu.remove(monstre);
		}
	}
}
