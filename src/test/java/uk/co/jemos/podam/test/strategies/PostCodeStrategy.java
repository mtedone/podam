/**
 * 
 */
package uk.co.jemos.podam.test.strategies;

import uk.co.jemos.podam.common.AttributeStrategy;
import uk.co.jemos.podam.exceptions.PodamMockeryException;
import uk.co.jemos.podam.test.utils.PodamTestConstants;

/**
 * A test strategy to manufacture UK-like post codes.
 * 
 * @author mtedone
 * 
 */
public class PostCodeStrategy implements AttributeStrategy<String> {

	// ------------------->> Constants

	// ------------------->> Instance / Static variables

	/**
	 * It returns an English post code.
	 * <p>
	 * This is just an example. More elaborated code could the implemented by
	 * this method. This is just to proof the point.
	 * </p>
	 * 
	 * {@inheritDoc}
	 */
	public String getValue() throws PodamMockeryException {
		return PodamTestConstants.POST_CODE;
	}

	// ------------------->> Constructors

	// ------------------->> Public methods

	// ------------------->> Getters / Setters

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes

}
