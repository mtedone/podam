/**
 * 
 */
package uk.co.jemos.podam.api;

import uk.co.jemos.podam.exceptions.PodamMockeryException;

/**
 * A default Object strategy, just to provide a default to
 * {@link PodamCollection#collectionElementStrategy()}.
 * 
 * @author mtedone
 * 
 */
public class ObjectStrategy implements AttributeStrategy<Object> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getValue() throws PodamMockeryException {
		return new Object();
	}

	// ------------------->> Constants

	// ------------------->> Instance / Static variables

	// ------------------->> Constructors

	// ------------------->> Public methods

	// ------------------->> Getters / Setters

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes

}
