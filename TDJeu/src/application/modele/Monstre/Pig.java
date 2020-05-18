package application.modele.Monstre;

public class Pig extends Monstre {

	private String nom;
	
	public Pig(int x, int y) {
		super(12, 150, 15, x, y);
		this.nom = "Pig";
	}

	public String getNom() {
		return nom;
	}
	

	
}
