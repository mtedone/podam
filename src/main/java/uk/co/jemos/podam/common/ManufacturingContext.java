package uk.co.jemos.podam.common;

import java.lang.reflect.Type;
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

	/** Map relating the generic class arguments ("&lt;T, V&gt;" for
	 *  example) with their actual types */
	private Map<String, Type> typeArgsMap = new HashMap<String, Type>();

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

	/**
	 * Getter for map generic type mappings
	 * @return map relating the generic class arguments ("&lt;T, V&gt;" for
	 *         example) with their actual types
	 */
	public Map<String, Type> getTypeArgsMap() {
		return typeArgsMap;
	}

	/**
	 * Setter for map generic type mappings
	 * @param typeArgsMap
     *         relating the generic class arguments ("&lt;T, V&gt;" for
	 *         example) with their actual types
	 */
	public void setTypeArgsMap(Map<String, Type> typeArgsMap) {
		this.typeArgsMap = typeArgsMap;
	}
}
