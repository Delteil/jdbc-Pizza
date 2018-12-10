package model;

import java.util.ArrayList;

import entites.Pizza;
import exceptions.CreatePizzaException;
import exceptions.DeletePizzaException;
import exceptions.StockageException;
import exceptions.UpdatePizzaException;

public interface IPizzaDAO {

	
	ArrayList<Pizza> findAllPizzas() throws StockageException;

	void createPizza(Pizza pizza) throws CreatePizzaException; 

	void updatePizza(int id,Pizza pizza) throws UpdatePizzaException;

	void deletePizza(int id) throws DeletePizzaException;
	
	Pizza findPizzaById(int id) throws StockageException;

	boolean pizzaExists(int id) throws StockageException;
}
