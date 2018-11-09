/**
 * 
 */
package io.pivotal.coffeemachine;

/**
 * @author Vikas Siva Ravindran
 *
 */
public class InventoryImplementationTests extends InventoryTests{

	/* (non-Javadoc)
	 * @see io.pivotal.coffeemachine.InventoryTests#getInventory()
	 */
	@Override
	protected Inventory getInventory() {
		// TODO Auto-generated method stub
		return new InventoryImplService();
	}

}
