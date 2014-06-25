/**
 * 
 */
package uk.co.jemos.podam.test.strategies;

import java.util.Calendar;

import uk.co.jemos.podam.common.AttributeStrategy;
import uk.co.jemos.podam.exceptions.PodamMockeryException;
import uk.co.jemos.podam.test.utils.PodamTestUtils;

/**
 * @author mtedone
 * 
 */
public class MyBirthdayStrategy implements AttributeStrategy<Calendar> {

	/**
	 * It returns a {@link Calendar} object set with the exact date of my
	 * birthday.
	 * 
	 * {@inheritDoc}
	 */
	public Calendar getValue() throws PodamMockeryException {

		Calendar myBirthday = PodamTestUtils.getMyBirthday();

		return myBirthday;
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
