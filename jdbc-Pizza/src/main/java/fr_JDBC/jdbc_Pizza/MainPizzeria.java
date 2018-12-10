package fr_JDBC.jdbc_Pizza;

import java.util.Scanner;

import entites.Categorie;
import entites.Pizza;
import exceptions.CreatePizzaException;
import exceptions.DeletePizzaException;
import exceptions.UpdatePizzaException;
import model.PizzaDAO;

public class MainPizzeria {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		PizzaDAO pizzaDAO = new PizzaDAO();

		int choix = 0;
		int id = 0;
		String code = "";
		String designation = "";
		Double prix;

		while (choix != 99) {

			System.out.println("***** Pizzeria Administration *****\r\n" + "1. Lister les pizzas\r\n"
					+ "2. Ajouter une nouvelle pizza\r\n" + "3. Mettre à jour une pizza\r\n"
					+ "4. Supprimer une pizza\r\n" + "99. Sortir");

			choix = Integer.parseInt(sc.nextLine());

			/**
			 * Lister les pizzas
			 */

			if (choix == 1) {

				pizzaDAO.afficheListePizza();
				System.out.println();
			}

			/**
			 * Ajouter une pizza
			 */

			if (choix == 2) {

				try {
					System.out.println("Ajout d’une nouvelle pizza");

					System.out.println("Veuillez saisir le code");
					code = sc.nextLine();

					System.out.println("Veuillez saisir le nom (sans espace) :");
					designation = sc.nextLine();

					System.out.println("Veuillez saisir le prix :");
					prix = (double) Integer.parseInt(sc.nextLine());

					System.out.println("Veuillez saisir l'id de la catégorie :");
					pizzaDAO.afficheCategorie();
					int idCategorie = Integer.parseInt(sc.nextLine());

					Pizza pizzaNew = new Pizza(code, designation, prix, new Categorie(idCategorie));

					pizzaDAO.createPizza(pizzaNew);

				} catch (CreatePizzaException e) {
					System.out.println(e.getMessage());

				}
				pizzaDAO.afficheListePizza();
				System.out.println();
			}

			/**
			 * Modifier la pizza
			 */

			if (choix == 3) {

				System.out.println("Mise à jour d’une pizza");

				String newCode = "";
				String newDesignation = "";
				double newPrix;

				try {
					System.out.println("Veuillez choisir l'id de la pizza à modifier.");
					id = Integer.parseInt(sc.nextLine());

					System.out.println("Veuillez saisir le nouveau code");
					newCode = sc.nextLine();

					System.out.println("Veuillez saisir le nouveau nom (sans espace)");
					newDesignation = sc.nextLine();

					System.out.println("Veuillez saisir le nouveau prix");
					newPrix = (double) Integer.parseInt(sc.nextLine());

					System.out.println("Veuillez saisir la nouvelle catégorie");
					pizzaDAO.afficheCategorie();
					int idNewCategorie = Integer.parseInt(sc.nextLine().toUpperCase());

					Pizza pizzaUpdate = new Pizza(newCode, newDesignation, newPrix, new Categorie(idNewCategorie));

					pizzaDAO.updatePizza(id, pizzaUpdate);

				} catch (UpdatePizzaException e) {
					System.out.println(e.getMessage());
				}
				pizzaDAO.afficheListePizza();
				System.out.println();
			}

			if (choix == 4) {

				System.out.println("Suppression d’une pizza");
				try {
					System.out.println("Veuillez choisir l'id de la pizza à supprimer.");
					id = Integer.parseInt(sc.nextLine());

					pizzaDAO.deletePizza(id);

				} catch (DeletePizzaException e) {
					System.out.println(e.getMessage());
				}

				pizzaDAO.afficheListePizza();
				System.out.println();
			}

			if (choix == 99) {

				System.out.println("Au revoir et à bientôt");

			}
		}
	}
}
