/**
 * 
 */
package uk.co.jemos.podam.test.dto.annotations;

import java.io.Serializable;

import uk.co.jemos.podam.common.PodamIntValue;

/**
 * POJO to test that {@link PodamIntValue#numValue()} with a non parseable value
 * triggers an error.
 * 
 * @author mtedone
 * 
 */
public class IntegerValueWithErrorPojo implements Serializable {

	// ------------------->> Constants
	private static final long serialVersionUID = 1L;

	// ------------------->> Instance / Static variables

	@PodamIntValue(numValue = "hfajkfhalfh")
	private int intFieldWithErrorInAnnotation;

	// ------------------->> Constructors

	// ------------------->> Public methods

	// ------------------->> Getters / Setters

	/**
	 * @return the intFieldWithErrorInAnnotation
	 */
	public int getIntFieldWithErrorInAnnotation() {
		return intFieldWithErrorInAnnotation;
	}

	/**
	 * @param intFieldWithErrorInAnnotation
	 *            the intFieldWithErrorInAnnotation to set
	 */
	public void setIntFieldWithErrorInAnnotation(
			int intFieldWithErrorInAnnotation) {
		this.intFieldWithErrorInAnnotation = intFieldWithErrorInAnnotation;
	}

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes

}
