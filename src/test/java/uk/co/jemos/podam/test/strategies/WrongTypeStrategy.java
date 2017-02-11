/**
 * 
 */
package uk.co.jemos.podam.test.strategies;

import java.lang.annotation.Annotation;
import java.util.List;

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
	@Override
	public OneDimensionalTestPojo getValue(Class<?> attrType, List<Annotation> annotations) throws PodamMockeryException {
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
