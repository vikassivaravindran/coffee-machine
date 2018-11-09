package io.pivotal.coffeemachine;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Tests for {@link CoffeeService}.
 */
public class CoffeeServiceTests {

	private CoffeeService machine;

	private Inventory inventory;
	
	private Map<String, Integer> ingredients;

	@Before
	public void setUp() {
		this.inventory = mock(Inventory.class);
		this.machine = new CoffeeService(this.inventory);
	}

	@Test
	public void getMenu() {
		Map<String, Double> menu = this.machine.getMenu();
		assertThat(menu).contains(entry("coffee", 2.75));
		assertThat(menu).contains(entry("cappuccino", 2.90));
		assertThat(menu).contains(entry("caffe mocha", 3.90));
	}

	@Test
	public void makeDrink() throws Exception {
		this.machine.makeDrink("cappuccino");
		verify(this.inventory).deduct("coffee", 2);
		verify(this.inventory).deduct("sugar", 1);
		verify(this.inventory).deduct("cream", 2);  // I have changed the cream quantity to 2 as the question in the github says cream quantity to be 2.
	}
	
	@Test
	public void addDrink() throws Exception{
		
		Drink d = new Drink();
		d.setName("coca-cola");
		d.setCost(1.90);
		this.ingredients = new HashMap<>();
		this.ingredients.put("coffee", 1);
		this.ingredients.put("sugar", 1);
		this.ingredients.put("cream", 1);
		d.setIngredients(this.ingredients);
		this.machine.addDrinktoMenu(d);
		Map<String, Double> menu = this.machine.getMenu();
		assertThat(menu).contains(entry("coca-cola",1.90));
		
	}

}