/**
 * 
 */
package uk.co.jemos.podam.test.dto.annotations;

import java.io.Serializable;

import uk.co.jemos.podam.common.PodamShortValue;

/**
 * POJO to test that {@link PodamShortValue#numValue()} with non parseable value
 * throws exception.
 * 
 * @author mtedone
 * 
 */
public class ShortValueWithErrorPojo implements Serializable {

	// ------------------->> Constants
	private static final long serialVersionUID = 1L;

	// ------------------->> Instance / Static variables

	@PodamShortValue(numValue = "fajkfhaf")
	private short shortFieldWithErrorInAnnotation;

	// ------------------->> Constructors

	// ------------------->> Public methods

	// ------------------->> Getters / Setters

	/**
	 * @return the shortFieldWithErrorInAnnotation
	 */
	public short getShortFieldWithErrorInAnnotation() {
		return shortFieldWithErrorInAnnotation;
	}

	/**
	 * @param shortFieldWithErrorInAnnotation
	 *            the shortFieldWithErrorInAnnotation to set
	 */
	public void setShortFieldWithErrorInAnnotation(
			short shortFieldWithErrorInAnnotation) {
		this.shortFieldWithErrorInAnnotation = shortFieldWithErrorInAnnotation;
	}

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes

}
