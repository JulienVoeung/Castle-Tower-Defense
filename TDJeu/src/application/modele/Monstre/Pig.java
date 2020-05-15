package application.modele.Monstre;

public class Pig extends Monstre {

	private String nom;
	
	public Pig(String nom, int v, int pv, int x, int y) {
		super(12, 150, x, y);
		this.nom="Pig";
	}

	public String getNom() {
		return nom;
	}

	
}
