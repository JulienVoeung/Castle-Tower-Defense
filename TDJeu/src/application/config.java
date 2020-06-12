package application;

import java.util.ArrayList;

import application.modele.Monstre.Monstre;
import application.modele.Monstre.Pig;
import application.modele.Monstre.RoiPig;

public class config {
	
	public static final int CASE_X = 576;
	public static final int CASE_Y = 832;
	public static final int CASE_2_X = 608;
	public static final int CASE_2_Y = 832;
	
	
	
	public static final int CASE_END_X1 = 24;
	public static final int CASE_END_X2 = 20;
	public static final int CASE_END_Y = 456;

	public static final int vieInitiale = 4;
	
	public static final int minimalCooldown = 2;
	
	public static final int cooldownCreditMine = 60;
	public static final int gainMine = 200;
	public static final int slowTime = 5 ;


		
	public static ArrayList<Monstre> getMonstreByNiveau(int niveau) {
		ArrayList<Monstre> monstres = new ArrayList<>();
		switch (niveau) {
		case 1:
			Monstre RoiPig = new RoiPig("Roi Pig", CASE_X, CASE_Y);
			monstres.add(RoiPig);	
			break;
		case 2:
			for (int i = 0; i < 1; i++) {
				Monstre pig = new Pig("Pig " + i + "G", CASE_X, CASE_Y);
				//Monstre pig2 = new Pig("Pig " + i + "D", CASE_2_X, CASE_2_Y);
				monstres.add(pig);
				//monstres.add(pig2);
			}	
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
