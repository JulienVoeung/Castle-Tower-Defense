package application.modele.BFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import application.modele.Case.Case;
import javafx.collections.ObservableList;

public class DeplacementBFS {
	
	private ArrayList<Case> ListeCaseTraversable;
	private HashMap<Case, Case> CoupleCase; 
	LinkedList<Case> file;
	private ObservableList<Case> map;
	
	public DeplacementBFS(ObservableList<Case> Map) {
		this.ListeCaseTraversable = new ArrayList<Case>();
		this.file = new LinkedList<Case>();
		this.CoupleCase = new HashMap<Case, Case>();
		this.map = Map; 
		
	} 
	
	public ArrayList<Case> initListChemin() {
		for(int i = 0 ; i < this.map.size(); i++) { 
			if(this.map.get(i).getId() == 248)
				this.ListeCaseTraversable.add(this.map.get(i));
		}
		return this.ListeCaseTraversable;
	}

	
	public void algoBFS() {  
		this.ListeCaseTraversable = initListChemin();
		Case CaseDeDepart = map.get(392);
		CaseDeDepart.setMarquage(true); 
		file.addFirst(CaseDeDepart);
		while(!file.isEmpty()) {
			Case CasePere = file.removeLast();			
			for(Case CaseFils : this.CaseVoisin(CasePere)) {
				if(CaseFils.getMarquage() == false) {
					CoupleCase.put(CaseFils, CasePere);
					CaseFils.setMarquage(true);
					file.addFirst(CaseFils);
				}
			}			
		}
	}
	
	public HashMap<Case, Case> getCoupleCase() {
		return this.CoupleCase;
	}

	public ArrayList<Case> CaseVoisin(Case c) {
		ArrayList<Case> listCaseVoisine = new ArrayList<Case>();
		int indiceCase = map.indexOf(c);
		
		Case CaseVoisine = map.get(indiceCase+1);
		if(this.ListeCaseTraversable.contains(CaseVoisine)) 
			listCaseVoisine.add(CaseVoisine);
		
		CaseVoisine = map.get(indiceCase-1);
		if(this.ListeCaseTraversable.contains(CaseVoisine)) 
			listCaseVoisine.add(CaseVoisine); 
		
		if (indiceCase <= 28*28-28) {
			CaseVoisine = map.get(indiceCase+28);
			if(this.ListeCaseTraversable.contains(CaseVoisine))
				listCaseVoisine.add(CaseVoisine);	
		}
		
		CaseVoisine = map.get(indiceCase-28);
		if(this.ListeCaseTraversable.contains(CaseVoisine))
			listCaseVoisine.add(CaseVoisine);
		return listCaseVoisine; 
	}
	
	public Case getCasePere(Case c) { 
		return this.CoupleCase.get(c); 
	}
}