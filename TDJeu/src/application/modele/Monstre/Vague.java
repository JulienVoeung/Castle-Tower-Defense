package application.modele.Monstre;

import java.util.ArrayList;

import application.configMonstres;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Vague {

	private ObservableList<Monstre> listMonstre = FXCollections.observableArrayList();
	
	private Monstre monstre;
	
	private IntegerProperty niveau;
	public Vague() {
		
		this.monstre = new Pig(configMonstres.CASE_X, configMonstres.CASE_Y);
		this.niveau = new SimpleIntegerProperty(1);
	}
		
	public void ajouterMonstre(Monstre monstre) {
		 listMonstre.add(monstre);
	}
	
	public void ajouterMonstres(ArrayList<Monstre> monstres) {
		listMonstre.addAll(monstres);
	}
	
	public void incrementerNiveau() {
		this.niveau.add(1);
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
