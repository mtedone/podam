package uk.co.jemos.podam.common;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
	 * Checks if current generit type mappings are empty
	 * @return true, if type args map is empty
	 */
	public boolean isTypeArgsEmpty() {
		return typeArgsMap.isEmpty();
	}

	/**
	 * Getter for map generic type mappings
	 * @param typeName
	 *         type generic placeholder
	 * @return resolved actual type actual types
	 */
	public Type resolveType(String typeName) {
		return typeArgsMap.get(typeName);
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
		fillTypeArgsMap(this, pojoType, actualTypes);
		return fillTypeArgsMap(this, pojoType, genericTypeArgs);
	}

	/**
	 * Clones and backups typeArgsMap
	 */
	public void cloneTypeArgsMap() {
        backupTypeArgsMaps.push(this.typeArgsMap);
        this.typeArgsMap = new HashMap<String, Type>(typeArgsMap);
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

    /**
     * Fills type agruments map
     * <p>
     * This method places required and provided types for object creation into a
     * map, which will be used for type mapping.
     * </p>
     *
     * @param manufacturingCtx
     *            manufacturing context with a map to fill
     * @param pojoClass
     *            Typed class
     * @param genericTypeArgs
     *            Type arguments provided for a generics object by caller
     * @return Array of unused provided generic type arguments
     * @throws IllegalStateException
     *             If number of typed parameters doesn't match number of
     *             provided generic types
     */
    public static Type[] fillTypeArgsMap(final ManufacturingContext manufacturingCtx,
                                  final Class<?> pojoClass, final Type[] genericTypeArgs) {

        TypeVariable<?>[] typeArray = pojoClass.getTypeParameters();
        List<TypeVariable<?>> typeParameters = new ArrayList<TypeVariable<?>>(Arrays.asList(typeArray));
        List<Type> genericTypes = new ArrayList<Type>(Arrays.asList(genericTypeArgs));

        Iterator<TypeVariable<?>> iterator = typeParameters.iterator();
        Iterator<Type> iterator2 = genericTypes.iterator();
        while (iterator.hasNext()) {
            Type genericType = (iterator2.hasNext() ? iterator2.next() : null);
            /* Removing types, which are already in typeArgsMap */
            if (manufacturingCtx.typeArgsMap.containsKey(iterator.next().getName())) {
                iterator.remove();
                /* Removing types, which are type variables */
                if (genericType instanceof TypeVariable) {
                    iterator2.remove();
                }
            }
        }

        if (typeParameters.size() > genericTypes.size()) {
            String msg = pojoClass.getCanonicalName()
                    + " is missing generic type arguments, expected "
                    + Arrays.toString(typeArray) + ", provided "
                    + Arrays.toString(genericTypeArgs);
            throw new IllegalArgumentException(msg);
        }

        final Method[] suitableConstructors
                = TypeManufacturerUtil.findSuitableConstructors(pojoClass, pojoClass);
        for (Method constructor : suitableConstructors) {
            TypeVariable<Method>[] ctorTypeParams = constructor.getTypeParameters();
            if (ctorTypeParams.length == genericTypes.size()) {
                for (int i = 0; i < ctorTypeParams.length; i++) {
                    Type foundType = genericTypes.get(i);
                    manufacturingCtx.putTypeArg(ctorTypeParams[i].getName(), foundType);
                }
            }
        }

        for (int i = 0; i < typeParameters.size(); i++) {
            Type foundType = genericTypes.remove(0);
            manufacturingCtx.putTypeArg(typeParameters.get(i).getName(), foundType);
        }

        Type[] genericTypeArgsExtra;
        if (genericTypes.size() > 0) {
            genericTypeArgsExtra = genericTypes.toArray(new Type[genericTypes.size()]);
        } else {
            genericTypeArgsExtra = PodamConstants.NO_TYPES;
        }

		/* Adding types, which were specified during inheritance */
        Class<?> clazz = pojoClass;
        while (clazz != null) {
            Type superType = clazz.getGenericSuperclass();
            clazz = clazz.getSuperclass();
            if (superType instanceof ParameterizedType) {
                ParameterizedType paramType = (ParameterizedType) superType;
                Type[] actualParamTypes = paramType.getActualTypeArguments();
                TypeVariable<?>[] paramTypes = clazz.getTypeParameters();
                for (int i = 0; i < actualParamTypes.length
                        && i < paramTypes.length; i++) {
                    if (actualParamTypes[i] instanceof Class) {
                        manufacturingCtx.putTypeArg(paramTypes[i].getName(),
                                actualParamTypes[i]);
                    }
                }
            }
        }

        return genericTypeArgsExtra;
    }

	/**
	 * Setter for adding generic type mappings
	 * @param typeName
	 *            string generic type placeholder
	 * @param type
	 *            actual type
	 */
	private void putTypeArg(String typeName, Type type) {
		typeArgsMap.put(typeName, type);
	}

}
