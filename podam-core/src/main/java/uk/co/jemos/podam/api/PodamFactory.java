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
	 * @param genericTypeArgs
	 *            The generic Type arguments for a generic class instance
	 * @return An instance of <T> filled with dummy values
	 * 
	 * @throws PodamMockeryException
	 *             if a problem occurred while creating a POJO instance or while
	 *             setting its state
	 */
	public <T> T manufacturePojo(Class<T> pojoClass, Type... genericTypeArgs);

	/**
	 * It returns the strategy for this factory.
	 * 
	 * @return the strategy
	 */
	public DataProviderStrategy getStrategy();

	void registerFactory(Class attributeClass, AttributeStrategy podamOptional);
}
