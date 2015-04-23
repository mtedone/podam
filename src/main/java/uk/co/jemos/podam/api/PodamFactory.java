/**
 * 
 */
package uk.co.jemos.podam.api;

import java.lang.reflect.Type;

import uk.co.jemos.podam.exceptions.PodamMockeryException;

/**
 * Contract for PODAM factory
 * 
 * @author mtedone
 * 
 * @since 2.0.0
 * 
 */
public interface PodamFactory {

	/**
	 * Generic method which returns an instance of the given class filled with
	 * dummy values, using the default data provider strategy.
	 * 
	 * <p>
	 * This method uses {@link RandomDataProviderStrategy} as the default
	 * implementation.
	 * </p>
	 * 
	 * @param <T>
	 *            The type for which a filled instance is required
	 * @param pojoClass
	 *            The name of the class for which an instance filled with values
	 *            is required
	 * 
	 * @return An instance of &lt;T&gt; filled with dummy values
	 * 
	 * @throws PodamMockeryException
	 *             if a problem occurred while
	 *             creating a POJO instance or while
	 *             setting its state
	 */
	<T> T manufacturePojo(Class<T> pojoClass);

	/**
	 * Generic method which returns an instance of the given class filled with
	 * dummy values, using the default data provider strategy.
	 * 
	 * <p>
	 * This method uses {@link RandomDataProviderStrategy} as the default
	 * implementation.
	 * </p>
	 * 
	 * @param <T>
	 *            The type for which a filled instance is required
	 * @param pojoClass
	 *            The name of the class for which an instance filled with values
	 *            is required
	 * @param genericTypeArgs
	 *            The generic Type arguments for a generic class instance
	 * @return An instance of &lt;T&gt; filled with dummy values
	 * 
	 * @throws PodamMockeryException
	 *             if a problem occurred while creating a POJO instance or while
	 *             setting its state
	 */
	<T> T manufacturePojo(Class<T> pojoClass, Type... genericTypeArgs);

	/**
	 * Generic method which returns an instance of the given class filled with
	 * dummy values, using the default data provider strategy. In order to
	 * instantiate class, constructor with the most parameters is used.
	 * 
	 * <p>
	 * This method uses {@link RandomDataProviderStrategy} as the default
	 * implementation.
	 * </p>
	 * 
	 * @param <T>
	 *            The type for which a filled instance is required
	 * @param pojoClass
	 *            The name of the class for which an instance filled with values
	 *            is required
	 * @param genericTypeArgs
	 *            The generic Type arguments for a generic class instance
	 * @return An instance of &lt;T&gt; filled with dummy values
	 * 
	 * @throws PodamMockeryException
	 *             if a problem occurred while creating a POJO instance or while
	 *             setting its state
	 */
	<T> T manufacturePojoWithFullData(Class<T> pojoClass, Type... genericTypeArgs);

	/**
	 * It returns the strategy for this factory.
	 * 
	 * @return the strategy
	 */
	DataProviderStrategy getStrategy();

	/**
	 * It returns the class info strategy for this factory.
	 *
	 * @return the class info strategy for this factory
	 */
	ClassInfoStrategy getClassStrategy();

	/**
	 * Sets the class info strategy for this factory
	 *
	 * @param classInfoStrategy
	 *             A class info strategy to be used by this factory
	 * @return instance of the factory for chaining
	 */
	PodamFactory setClassStrategy(ClassInfoStrategy classInfoStrategy);
}
