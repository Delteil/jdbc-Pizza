package entites;

import exceptions.StockageException;

public class Pizza {

	private int id;
	private String code = "";
	private String designation = "";
	private Double prix = null;
	private Categorie categorie;

	public Pizza(int id, String code, String designation, Double prix, Categorie categorie) {
		super();
		this.id = id;
		this.code = code;
		this.designation = designation;
		this.prix = prix;
		this.categorie = categorie;
	}
	
	public Pizza(String code, String designation, Double newPrix, Categorie categorie) {
		super();
		this.code = code;
		this.designation = designation;
		this.prix = newPrix;
		this.categorie = categorie;
	}

	@Override
	public String toString() {
		return "id = " + id + ", code = " + code + ", designation = " + designation + ", prix = " + prix;
	}

	// controle des exceptions

	public void DataControl() throws StockageException {

		String message = "";

		if (code.length() > 3 || code.isEmpty()) {

			message = "Code non renseigné ou supérieur à 3 caractères";
		}
		if (designation.trim().length() < 1 || designation.isEmpty()) {

			message = "Champs de désignation null ou mal renseigné";
		}
		if (prix > 20 || prix < 0) {

			message = "Prix renseigné null, trop elevé ou trop bas";
		}
		if (message != "") {

			throw new StockageException(message);
		}
	}

	public int getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	public Categorie getCategorie() {
		return categorie;
	}
}
