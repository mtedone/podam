/**
 * 
 */
package uk.co.jemos.podam.api;

import uk.co.jemos.podam.common.AttributeStrategy;
import uk.co.jemos.podam.typeManufacturers.TypeManufacturer;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * This interface defines the contact for PODAM data providers.
 * <p>
 * PODAM is a tool to fill POJOs with data. There are different requirements
 * when it comes to which data POJOs should be filled with. The default strategy
 * adopted by PODAM is to fill POJOs with random data. However other
 * requirements might dictate to assign deterministic data using sequences, or
 * other predictable sources of data. In order to do so, clients of PODAM will
 * have to provide an implementation of this interface and pass it to the
 * constructor of the {@link PodamFactoryImpl} class.
 * </p>
 * 
 * @author mtedone
 * 
 * @since 1.0.0
 * 
 */
public interface DataProviderStrategy {

    /**
     * Registers @TypeManufacturer implementation, which will be used to
     * instantiate objects of a specified type. Use this to alter factory
     * behaviour.
     *
     * @param <T> type of objects to be produced by the manufacturer
     * @param type
     *            the specific class type the specified manufacturer
     *            will instantiate.
     * @param typeManufacturer
     *            type manufacturer implementation to be registered
     * @return itself
     */
    <T> DataProviderStrategy addOrReplaceTypeManufacturer(
            Class<? extends T> type, TypeManufacturer<T> typeManufacturer);

    /**
     * Remove binding of a class type to a specific
     * implementation of type manufacturer
     *
     * @param <T> type of objects to be produced by the manufacturer
     * @param type
     *            the specific class type to remove binding
     * @return itself
     */
    <T> DataProviderStrategy removeTypeManufacturer(
           Class<T> type);

    /**
     * Obtains a type value
     *
     * @param <T> type of returned object
     * @param attributeMetadata The AttributeMetadata information
     * @param genericTypesArgumentsMap The generic attribute type argument types
     * @param pojoType The class of the requested type
     * @return The type value
     */
    <T> T getTypeValue(AttributeMetadata attributeMetadata,
            Map<String, Type> genericTypesArgumentsMap, Class<T> pojoType);

    /**
     * Bind an interface/abstract class to a specific implementation. If the
     * strategy previously contained a binding for the interface/abstract class,
     * the old value will be replaced by the new value.
     * If you want to implement more sophisticated binding strategy, override this class.
     *
     * @param <T> return type
     * @param abstractClass
     *            the interface/abstract class to bind
     * @param specificClass
     *            the specific class implementing or extending
     *            {@code abstractClass}.
     * @return itself
     */
	<T> DataProviderStrategy addOrReplaceSpecific(
			Class<T> abstractClass, Class<? extends T> specificClass);

	/**
     * Remove binding of an interface/abstract class to a specific
     * implementation
     *
     * @param <T> return type
     * @param abstractClass
     *            the interface/abstract class to remove binding
     * @return itself
     */
	<T> DataProviderStrategy removeSpecific(
			Class<T> abstractClass);

	/**
	 * Specifies how to sort constructors
	 */
	public enum Order {
		/**
		 * Constructors with more parameters have precedence
		 */
		HEAVY_FIRST,
		/**
		 * Constructors with less parameters have precedence
		 */
		LIGHT_FIRST
	};

	/**
	 * Returns the number of default collection elements.
	 * <p>
	 * Implementations of this interface need to provide this value.
	 * </p>
	 * 
	 * @param type
	 *            Type of collection's elements
	 * @return The number of default collection elements
	 */
	int getNumberOfCollectionElements(Class<?> type);

	/**
	 * Sets the new default number of default collection elements.
	 *
	 * @param newNumberOfCollectionElements
	 *            The new number of collection elements.
	 */
	public void setDefaultNumberOfCollectionElements(int newNumberOfCollectionElements);

	/**
	 * Returns how many times it is allowed to PODAM to create an instance
	 * of the same class in a recursive hierarchy
	 * <p>
	 * Implementations of this interface need to provide this value.
	 * </p>
	 * 
	 * @param type
	 *            Type of POJO to be created
	 * @return How many times it is allowed to create an instance
	 *         of the same class in a recursive hierarchy
	 */
	int getMaxDepth(Class<?> type);

	/**
	 * Returns whether the memoization setting is enabled.
	 *
	 * When memoization is enabled, only one object will be created for each type. Every next property of the same type
	 * will be a reference to the same object.
	 * This can dramatically improve performance but with the expense of not having objects with different values.
	 *
	 * <p>
	 * Implementations of this interface need to provide this value.
	 * </p>
	 *
	 * @return Whether the memoization setting is enabled.
	 */
	boolean isMemoizationEnabled();

	/**
	 * Sets whether memoization is enabled
	 * @param isMemoizationEnabled whether memoization is enabled
	 */
	void setMemoization(boolean isMemoizationEnabled);

	/**
	 * Obtains object from memoization cache
	 *
	 * @param attributeMetadata
	 *            attribute metadata for instance to be fetched
	 * @return object instance from memoization cache
	 */
	Object getMemoizedObject(AttributeMetadata attributeMetadata);

	/**
	 * Saves object in memoization cache
	 *
	 * @param attributeMetadata
	 *            attribute metadata for instance being memoized
	 * @param instance
	 *            object instance to save in memoization cache
	 */
	void cacheMemoizedObject(AttributeMetadata attributeMetadata, Object instance);

	/**
	 * Clears memoization cache
	 */
	void clearMemoizationCache();

	/**
	 * Rearranges POJO's constructors in order they will be tried to
	 * produce the POJO.
	 * Default strategy consists of putting constructors with less parameters
	 * to be tried first.
	 *
	 * @param constructors
	 *            Array of POJO's constructors
	 * @param order
	 *            {@link Order} how to sort constructors
	 */
	void sort(Constructor<?>[] constructors, Order order);

	/**
	 * Rearranges POJO's methods in order they will be tried to
	 * produce the POJO.
	 * Default strategy consists of putting factory methods with more parameters
	 * to be tried first.
	 * 
	 * @param methods
	 *            Array of POJO's methods
	 * @param order
	 *            {@link Order} how to sort constructors
	 */
	void sort(Method[] methods, Order order);

	/**
	 * Resolves factory classes for abstract classes and interfaces.
	 * <p>
	 * Should return factory class type, which can be used to instantiate
	 * an abstract class type or interface.
	 * </p>
	 * 
	 * @param nonInstantiatableClass
	 *            Abstract class type or interface
	 * @return Non-abstract factory class type to instantiate
	 *         {@code nonInstantiatableClass}.
	 */
	Class<?> getFactoryClass(Class<?> nonInstantiatableClass);

	/**
	 * Resolves abstract classes and interfaces.
	 * <p>
	 * Should return specific class type, which can be instantiated and assigned
	 * to abstract class type or interface.
	 * </p>
	 * 
	 * @param <T>
	 *            The type of class being resolved
	 * @param nonInstantiatableClass
	 *            Abstract class type or interface
	 * @return Non-abstract class type derived from
	 *         {@code nonInstantiatableClass}.
	 */
	<T> Class<? extends T> getSpecificClass(Class<T> nonInstantiatableClass);

	/**
	 * Finds attribute strategies for annotations.
	 * <p>
	 * Searches for mapping between annotations and attribute strategies,
	 * which will be used then for populating fields or constructor parameters.
	 * </p>
	 * 
	 * @param annotationClass
	 *        Annotation class to inspect
	 * @return attribute strategy associated with given annotation
	 */
	Class<AttributeStrategy<?>> getStrategyForAnnotation(Class<? extends Annotation> annotationClass);

}
