package application;

import java.util.ArrayList;

import application.modele.Monstre.Monstre;
import application.modele.Monstre.Pig;

public class configMonstres {
	
	public static final int CASE_X = 576;
	public static final int CASE_Y = 864;
	public static final int CASE_2_X = 608;
	public static final int CASE_2_Y = 864;
	
	public static ArrayList<Monstre> getMonstreByNiveau(int niveau) {
		ArrayList<Monstre> monstres = new ArrayList<>();
		//Monstre pig = new Pig(CASE_X, CASE_Y);
		switch (niveau) {
		case 1:
			for (int i = 0; i < 2; i++) {
				Monstre pig = new Pig(CASE_X, CASE_Y);
				Monstre pig2 = new Pig(CASE_2_X, CASE_2_Y);
				monstres.add(pig);
				monstres.add(pig2);
			}
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		}
		return monstres;
	}
}
