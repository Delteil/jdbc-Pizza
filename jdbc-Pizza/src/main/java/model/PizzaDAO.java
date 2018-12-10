package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import entites.Categorie;
import entites.Pizza;
import exceptions.CreatePizzaException;
import exceptions.DeletePizzaException;
import exceptions.StockageException;
import exceptions.UpdatePizzaException;
import utils.ConnectionPizzeria;

public class PizzaDAO implements IPizzaDAO {

	ArrayList<Pizza> list = new ArrayList<Pizza>();
	ArrayList<Categorie> listeCategorie = new ArrayList<Categorie>();

	Connection connect = null;
	Statement statement = null;
	PreparedStatement prepareStatement = null;
	ResultSet resultSet = null;
	int result = 0;

	public ArrayList<Pizza> findAllPizzas() throws StockageException {
		list = new ArrayList<Pizza>();
		try {
			connect = ConnectionPizzeria.getConnection();

			statement = connect.createStatement();

			resultSet = statement
					.executeQuery("SELECT * FROM pizza JOIN categorie ON pizza.id_categorie = categorie.id ");

			System.out.println("\r\n====");

			while (resultSet.next()) {

				list.add(new Pizza(resultSet.getInt("id"), resultSet.getString("code"),
						resultSet.getString("designation"), resultSet.getDouble("prix"),
						new Categorie(resultSet.getInt("id_categorie"), resultSet.getString("nom"))));
			}

			System.out.println();

		} catch (Exception e) {
			e.printStackTrace();
			throw new StockageException("la pizza n'a pas été trouvé");

		} finally {
			try {
				resultSet.close();
				statement.close();
				connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public void afficheListePizza() {
		try {
			ArrayList<Pizza> listepizza = findAllPizzas();
			for (Pizza pizza : listepizza) {
				System.out.println(pizza);
			}
		} catch (StockageException e) {

		}
	}

	public void afficheCategorie() {

		try {
			connect = ConnectionPizzeria.getConnection();
			statement = connect.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM categorie");

			while (resultSet.next()) {

				listeCategorie.add(new Categorie(resultSet.getInt("id"), resultSet.getString("nom")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
				statement.close();
				connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		for (Categorie categorie : listeCategorie) {
			System.out.println(categorie);
		}
	}

	public void createPizza(Pizza pizza) throws CreatePizzaException {

		try {

			connect = ConnectionPizzeria.getConnection();

			String query = "INSERT INTO pizza (code, designation, prix, id_categorie) VALUES (?,?,?,?)";

			prepareStatement = connect.prepareStatement(query);

			prepareStatement.setString(1, pizza.getCode());
			prepareStatement.setString(2, pizza.getDesignation());
			prepareStatement.setDouble(3, pizza.getPrix());
			prepareStatement.setInt(4, pizza.getCategorie().getId());

			result = prepareStatement.executeUpdate();

			if (result == 0) {

				System.out.println("la création n'a pas abouti");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new CreatePizzaException(e.getMessage() + "la création a échoué");

		} finally {
			try {
				prepareStatement.close();
				connect.close();
			} catch (SQLException e) {
			}
		}

	}

	public void updatePizza(int id, Pizza pizza) throws UpdatePizzaException {

		try {

			connect = ConnectionPizzeria.getConnection();

			String query = "UPDATE pizza SET code = ?, designation = ?, prix = ?, id_categorie = ? WHERE id = ?";
			prepareStatement = connect.prepareStatement(query);

			prepareStatement.setString(1, pizza.getCode());
			prepareStatement.setString(2, pizza.getDesignation());
			prepareStatement.setDouble(3, pizza.getPrix());
			prepareStatement.setInt(4, pizza.getCategorie().getId());
			prepareStatement.setInt(5, id);

			result = prepareStatement.executeUpdate();

			if (result == 0) {
				System.out.println("la modification n'a pas aboutie");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new UpdatePizzaException(e.getMessage() + "la modification a échoué");

		} finally {
			try {
				prepareStatement.close();
				connect.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void deletePizza(int id) throws DeletePizzaException {

		try {
			connect = ConnectionPizzeria.getConnection();
			String query = "DELETE FROM pizza WHERE id = ?";
			prepareStatement = connect.prepareStatement(query);

			prepareStatement.setInt(1, id);
			result = prepareStatement.executeUpdate();

			if (result == 0) {
				System.out.println("La pizza n'existe pas");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DeletePizzaException(e.getMessage() + "la suppression a échoué");

		} finally {
			try {
				prepareStatement.close();
				connect.close();
			} catch (SQLException e) {
			}
		}
	}

	public Pizza findPizzaById(int id) throws StockageException {
		boolean exist = true;

		try {
			connect = ConnectionPizzeria.getConnection();
			statement = connect.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM categorie WHERE id =" + id);

			if (!resultSet.isBeforeFirst()) {

				exist = false;
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				resultSet.close();
				statement.close();
				connect.close();
			} catch (SQLException e) {
			}
		}
		return null;
	}

	public boolean pizzaExists(int id) throws StockageException {

		if (findPizzaById(id) != null) {

			return true;
		}
		return false;
	}

}
