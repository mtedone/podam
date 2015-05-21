package uk.co.jemos.podam.common;

import java.util.HashMap;
import java.util.Map;

import uk.co.jemos.podam.api.DataProviderStrategy.Order;

/**
 * Object to hold manufacturing related data
 * 
 * @author daivanov
 * 
 */
public class ManufacturingContext {

	/** Constructors sorting order */
	private Order constructorOrdering = Order.LIGHT_FIRST;

	/** Map with production counts of objects per type, required
	 * for loop detection */
	private Map<Class<?>, Integer> pojos = new HashMap<Class<?>, Integer>();

	/**
	 * Getter for constructor ordering
	 * @return constructor ordering
	 */
	public Order getConstructorOrdering() {
		return constructorOrdering;
	}

	/**
	 * Setter for constructor ordering
	 * @param constructorOrdering
	 *        constructor ordering
	 */
	public void setConstructorOrdering(Order constructorOrdering) {
		this.constructorOrdering = constructorOrdering;
	}

	/**
	 * Getter for map with production counts of objects per type
	 * @return map with production counts of objects per type
	 */
	public Map<Class<?>, Integer> getPojos() {
		return pojos;
	}

}
