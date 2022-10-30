package uk.co.jemos.podam.common;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

import uk.co.jemos.podam.api.DataProviderStrategy.Order;
import uk.co.jemos.podam.typeManufacturers.TypeManufacturerUtil;

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

	/** Backup stack of maps relating the generic class arguments ("&lt;T, V&gt;" for
	 *  example) with their actual types */
	/** Constructors sorting order */
	private Deque<Map<String, Type>> backupTypeArgsMaps = new ArrayDeque<Map<String, Type>>();

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

	/**
	 * Creates an empty typeArgsMap
	 * @return newly created map
	 */
	public Map<String, Type> createEmptyTypeArgsMap() {
        return new HashMap<String, Type>();
	}

	/**
	 * Clones and backups typeArgsMap and fills it with types for a new POJO
	 * @param pojoType
     *         class of a new POJO
	 * @param parameterizedPojoType
     *         parameterized type of a new POJO
	 * @param genericTypeArgs
	 *         The generic type arguments for the current generic class
	 *         instance
	 * @return
	 *         The updated generic type arguments for the current generic class
	 *         instance
	 */
	public Type[] cloneTypeArgsMap(Class<?> pojoType,
			ParameterizedType parameterizedPojoType,
			Type[] genericTypeArgs) {
		backupTypeArgsMaps.push(typeArgsMap);
		typeArgsMap = new HashMap<String, Type>(typeArgsMap);

		Type[] actualTypes = parameterizedPojoType.getActualTypeArguments();
		TypeManufacturerUtil.fillTypeArgMap(typeArgsMap,
				pojoType, actualTypes);
		return TypeManufacturerUtil.fillTypeArgMap(typeArgsMap,
				pojoType, genericTypeArgs);
	}

	/**
	 * Backups typeArgsMap and replace it with a new one
	 * @param typeArgsMap
	 *         relating the generic class arguments ("&lt;T, V&gt;" for
	 *         example) with their actual types
	 */
	public void backupTypeArgsMap(Map<String, Type> typeArgsMap) {
        backupTypeArgsMaps.push(this.typeArgsMap);
        this.typeArgsMap = typeArgsMap;
	}

	/**
	 * Restores typeArgsMap from backup
	 * @return previous map
	 */
	public Map<String, Type> restoreTypeArgsMap() {
        final Map<String, Type> oldTypeArgsMap = this.typeArgsMap;
        this.typeArgsMap = backupTypeArgsMaps.pop();
        return oldTypeArgsMap;
	}
}
