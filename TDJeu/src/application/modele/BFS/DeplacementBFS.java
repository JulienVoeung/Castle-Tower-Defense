package application.modele.BFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import application.modele.Case.Case;
import javafx.collections.ObservableList;

public class DeplacementBFS {
	
	private ArrayList<Case> ListeCaseTraversable;
	private ArrayList<Case> ListeCasesNonMarquer;
	private HashMap<Case, Case> CoupleCase; 
	LinkedList<Case> file;
	
	public DeplacementBFS() {
		this.ListeCaseTraversable = new ArrayList<Case>();
		this.ListeCasesNonMarquer = new ArrayList<Case>();
		this.file = new LinkedList<Case>();
		this.CoupleCase = new HashMap<Case, Case>();
	} 
	
	public void initListCheminIndice(ObservableList<Case> Map) {
		for(int i = 0 ; i < Map.size(); i++) { 
			if(Map.get(i).getTraversable() == true)
				this.ListeCaseTraversable.add(Map.get(i));
		}
	}

	
	public void deplaceBFS(ObservableList<Case> Map) {
		Case CaseDeDepart = getCaseDepart(Map);
		CaseDeDepart.setMarquage(true); 
		file.addFirst(CaseDeDepart);		
		while(file.size() != 0) { 
			Case c = 	file.removeLast();
			for(Case casevoisin : this.ListeCasesNonMarquer) {
				if(casevoisin.getMarquage() == false) {
					this.CoupleCase.put(casevoisin, c);
					casevoisin.setMarquage(true);
					file.addFirst(casevoisin);
				}
			}
		}
		System.out.println(this.CoupleCase); 
	}
	
	public boolean CheminMonstreCaseMarquer() {
		for(int i = 0 ; i < ListeCaseTraversable.size(); i++) {
			if(ListeCaseTraversable.get(i).getMarquage() == false)
				return false;
		}
		return true;
	}
	
	public HashMap<Case, Case> getCoupleCase() {
		return this.CoupleCase;
	}
	
	public Case getCaseDepart(ObservableList<Case> Map) {
		for(int i = 0 ; i < Map.size(); i++) {
			if(i == 29)
				return Map.get(i);
		}
		return Map.get(Map.size() - 1);
	}
	
	public ArrayList<Case> getListeCasesNonMarquer() {
		for(int i = 0 ; i < this.ListeCaseTraversable.size(); i++) {
			if(this.ListeCaseTraversable.get(i).getMarquage() == false)
				this.ListeCasesNonMarquer.add(this.ListeCasesNonMarquer.get(i));
		}
		return this.ListeCasesNonMarquer;
	}
}