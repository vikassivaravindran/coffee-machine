/**
 * 
 */
package io.pivotal.coffeemachine;

import java.util.HashMap;
import java.util.Map;

import io.pivotal.coffeemachine.exception.IngredientNotFoundException;
import io.pivotal.coffeemachine.exception.StockUnavailableException;

/**
 * @author Vikas Siva Ravindran
 *
 */

public class InventoryImplService implements Inventory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.pivotal.coffeemachine.Inventory#getIngredients()
	 */
	public Map<String, Integer> ingredients;

	public InventoryImplService() {
		this.ingredients = new HashMap<String, Integer>();
		this.ingredients.put("coffee", 10);
		this.ingredients.put("sugar", 10);
		this.ingredients.put("cream", 10);
	}

	public Map<String, Integer> getIngredients() {
		// TODO Auto-generated method stub
		return this.ingredients;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.pivotal.coffeemachine.Inventory#deduct(java.lang.String,
	 * java.lang.Integer)
	 */
	public void deduct(String name, Integer amount) throws IngredientNotFoundException, StockUnavailableException {
		// TODO Auto-generated method stub

		if (!this.ingredients.containsKey(name)) {
			throw new IngredientNotFoundException("Ingredient is not available");
		} else if (this.ingredients.get(name) >= amount) {
			int quantity = this.ingredients.get(name) - amount;
			this.ingredients.put(name, quantity);
		} else {
			throw new StockUnavailableException("Stock Unavailable");

		}
	}
}
