/**
 * 
 */
package uk.co.jemos.podam.common;

import uk.co.jemos.podam.exceptions.PodamMockeryException;

/**
 * Generic contract for attribute-level data provider strategies.
 * 
 * @author mtedone
 * 
 */
public interface AttributeStrategy<T> {

	/**
	 * It returns a value of the given type
	 * 
	 * @return A value of the given type
	 * 
	 * @throws PodamMockeryException
	 *             If an exception occurred while assigning the value specified
	 *             by this strategy
	 */
	T getValue();

}
