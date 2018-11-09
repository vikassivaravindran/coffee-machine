package io.pivotal.coffeemachine;

import java.util.HashMap;
import java.util.Map;

import io.pivotal.coffeemachine.exception.StockUnavailableException;

public class CoffeeService {

	private Inventory inventory;
	private Map<String, Double> menu;
	private Map<String, Integer> ingredients;

	public CoffeeService(Inventory inventory) {
		this.inventory = inventory;
		this.menu = new HashMap<String, Double>();
		this.menu.put("coffee", 2.75);
		this.menu.put("cappuccino", 2.90);
		this.menu.put("caffe mocha", 3.90);

		Drink d = new Drink();
		d.setName("cappuccino");
		this.ingredients = new HashMap<>();
		this.ingredients.put("coffee", 2);
		this.ingredients.put("sugar", 1);
		this.ingredients.put("cream", 2);
	}

	/**
	 * Returns the menu for this coffee machine.
	 *
	 * @return a map of drink name to drink cost
	 */
	public Map<String, Double> getMenu() {
		return this.menu;
	}

	/**
	 * Make a drink using the given name. Ingredients for the drink are deducted
	 * from the inventory.
	 *
	 * @param name
	 *            the name of the drink
	 * @throws Exception
	 */
	public Drink makeDrink(String name) throws Exception {

		Drink drink = new Drink();

		if (!this.menu.containsKey(name)) {
			throw new Exception("Drink Not Found");
		} else {
			for (Map.Entry<String, Integer> m : ingredients.entrySet()) {
				inventory.deduct(m.getKey(), m.getValue());
			}
			drink.setCost(this.menu.get(name));
			drink.setIngredients(this.ingredients);
		}
		return drink;
	}

	/*
	 * 
	 * addDrinktoMenu() checks the ingredients quantity with the Inventory
	 * Quantity, and adds it to the Menu
	 */
	public void addDrinktoMenu(Drink drink) throws StockUnavailableException {

		Drink newDrink = new Drink();
		newDrink.setName(drink.getName());
		newDrink.setCost(drink.getCost());

		Map<String, Integer> newIngredients = drink.getIngredients();
		Map<String, Integer> inv = new InventoryImplService().getIngredients();

		for (Map.Entry<String, Integer> ingredient : newIngredients.entrySet()) {

			// if Condition to check with the available number of Ingredients in
			// Inventory
			if (inv.containsKey(ingredient.getKey()) && inv.get(ingredient.getKey()) > ingredient.getValue()) {
				drink.getIngredients().put(ingredient.getKey(), ingredient.getValue());
			} else {
				throw new StockUnavailableException("Stock Unavailable");
			}
			this.menu.put(newDrink.getName(),newDrink.getCost());

		}

	}

}
