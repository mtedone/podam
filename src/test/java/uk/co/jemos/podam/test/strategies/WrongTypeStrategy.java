/**
 * 
 */
package uk.co.jemos.podam.test.strategies;

import uk.co.jemos.podam.common.AttributeStrategy;
import uk.co.jemos.podam.common.PodamStrategyValue;
import uk.co.jemos.podam.exceptions.PodamMockeryException;
import uk.co.jemos.podam.test.dto.OneDimensionalTestPojo;

/**
 * A dummy strategy to proof the point that when given as type for
 * {@link PodamStrategyValue} it will trigger an exception.
 * 
 * @author mtedone
 * 
 */
public class WrongTypeStrategy
		implements
			AttributeStrategy<OneDimensionalTestPojo> {

	/**
	 * {@inheritDoc}
	 */
	public OneDimensionalTestPojo getValue() throws PodamMockeryException {
		return new OneDimensionalTestPojo();
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
