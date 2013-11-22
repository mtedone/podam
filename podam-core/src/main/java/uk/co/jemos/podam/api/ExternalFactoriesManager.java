package uk.co.jemos.podam.api;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages interaction with externally registered {@link AttributeStrategy}'s.
 */
public class ExternalFactoriesManager {

	/**
	 * Map of global class to AttributeStrategy's
	 */
	private Map<Class, AttributeStrategy> factoryMap = new HashMap();

	private ReflectionUtils reflectionUtils = new ReflectionUtils();

	public void registerFactory(Class attributeClass, AttributeStrategy attributeStrategy) {
		if (factoryMap.containsKey(attributeClass))
			throw new IllegalStateException("Duplicate factories for a given type not allowed.");
		factoryMap.put(attributeClass, attributeStrategy);
	}

	public boolean existsMatchingFactoryFor(Class<?> attributeType) {
		return factoryMap.containsKey(attributeType);
	}

	public Object getValue(Class<?> attributeType) {
		AttributeStrategy attributeStrategy = factoryMap.get(attributeType);
		Object value = attributeStrategy.getValue();
		return value;
	}

	public Object getValue(Class<?> attributeType, Method setter) {
		Type genericParameterType = reflectionUtils.getGenericParameterTypeFromSetter(setter);
		return getValue(attributeType, genericParameterType);
	}

	/**
	 * Using the factory generics factory strategy registered against {@code parameterType}, use it to
	 * get a type of {@code genericParameterType}.
	 *
	 * @param parameterType        the key for the registered factory
	 * @param genericParameterType the type to request
	 * @return
	 */
	public Object getValue(Class<?> parameterType, Type genericParameterType) {
		if (parameterType == genericParameterType) {
			// if the runtime provides no extra generic info, then it's not a generic param
			return getValue(parameterType);
		}
		AttributeStrategy attributeStrategy = factoryMap.get(parameterType);
		Object value;
		if (attributeStrategy instanceof AttributeStrategyWithGenerics) {
			AttributeStrategyWithGenerics genericsAttributeStrategy = (AttributeStrategyWithGenerics) attributeStrategy;
			value = genericsAttributeStrategy.getValue(genericParameterType);
		} else {
			throw new IllegalStateException("You cannot get a value that has generics without an "
					+ AttributeStrategyWithGenerics.class.getName() + " type of " + AttributeStrategy.class.getName());
		}
		return value;
	}
}
