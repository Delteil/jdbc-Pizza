package entites;

public class Categorie {

	private int id = 0;
	private String nom = "";

	public Categorie(int id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}

	public Categorie(int id) {
		this.id = id;
	}
	

	@Override
	public String toString() {
		return "Categorie " + id + ", nom = " + nom;
	}

	public int getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

}
