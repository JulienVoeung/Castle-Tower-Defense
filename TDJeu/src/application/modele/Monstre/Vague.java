package application.modele.Monstre;

import java.util.ArrayList;

import application.config;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Vague {

	private ObservableList<Monstre> listMonstreEnJeu = FXCollections.observableArrayList();
	
	private ArrayList<Monstre> listMonstresStockÚs = new ArrayList<>();
	
	private Monstre monstre;
	
	private IntegerProperty niveau;
	
	public Vague() {
		
		this.monstre = new Pig(config.CASE_X, config.CASE_Y);
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
		this.listMonstresStockÚs.addAll(config.getMonstreByNiveau(this.niveau.getValue()));
	}
	
	public void spawnMonstres() {
		if (this.listMonstresStockÚs.size() > 0) {
			this.listMonstreEnJeu.add(this.listMonstresStockÚs.get(0));
			this.listMonstresStockÚs.remove(0);	
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

	public Monstre getMonstre() {
		return this.monstre;
	}
	
	public boolean isFinished() {
		int sizeListMonstre = this.listMonstreEnJeu.size();
		int sizeListMonstreStock = this.listMonstresStockÚs.size();
		return sizeListMonstre == 0 && sizeListMonstreStock == 0;
	}
}
