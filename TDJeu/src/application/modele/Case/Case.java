package application.modele.Case;

public abstract class Case{
	
	private boolean traversable;
	private String nom;
	private int id;

	public Case(String nomBlock, int id, boolean traversable) {
		this.nom = nomBlock;
		this.id = id;
		this.traversable = traversable;
	}

	public boolean getTraversable() {
		return this.traversable;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getNom() {
		return this.nom;
	}

}
