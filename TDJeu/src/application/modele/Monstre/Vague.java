package application.modele.Monstre;

import java.util.ArrayList;

import application.configMonstres;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Vague {

	private ObservableList<Monstre> listMonstre = FXCollections.observableArrayList();
	
	private ArrayList<Monstre> listMonstresStockés = new ArrayList<>();
	
	private Monstre monstre;
	
	private IntegerProperty niveau;
	
	public Vague() {
		
		this.monstre = new Pig(configMonstres.CASE_X, configMonstres.CASE_Y);
		this.niveau = new SimpleIntegerProperty(0);
	}
		
	public void ajouterMonstre(Monstre monstre) {
		 this.listMonstre.add(monstre);
	}
	
	public void ajouterMonstres(ArrayList<Monstre> monstres) {
		this.listMonstre.addAll(monstres);
	}
	
	public void incrementerNiveau() {
		int newNiveau = this.niveau.get() + 1;
		this.niveau.set(newNiveau);
		this.viderListe();
		System.err.println(this.niveau.get());
		this.listMonstresStockés.addAll(configMonstres.getMonstreByNiveau(this.niveau.getValue()));
	}
	
	public void spawnMonstres() {
		if (this.listMonstresStockés.size() > 0) {
			this.listMonstre.add(this.listMonstresStockés.get(0));
			this.listMonstresStockés.remove(0);	
		}
	}
	
	public void viderListe() {
		this.listMonstre.clear();
	}

	public IntegerProperty getNiveau() {
		return this.niveau;
	}
	
	public void setNiveau(int valeur) {
		this.niveau.set(valeur);
	}
	
	public ObservableList<Monstre> getListMonstre(){
		return this.listMonstre;
	}

	public Monstre getMonstre() {
		return this.monstre;
	}
}
