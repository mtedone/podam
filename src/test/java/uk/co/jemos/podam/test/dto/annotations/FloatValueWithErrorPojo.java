/**
 * 
 */
package uk.co.jemos.podam.test.dto.annotations;

import java.io.Serializable;

import uk.co.jemos.podam.common.PodamFloatValue;

/**
 * POJO to test that a {@link PodamFloatValue#numValue()} is non parseable.
 * 
 * @author mtedone
 * 
 */
public class FloatValueWithErrorPojo implements Serializable {

	// ------------------->> Constants
	private static final long serialVersionUID = 1L;

	// ------------------->> Instance / Static variables

	@PodamFloatValue(numValue = "fafhakljhf")
	private float floatFieldWithErrorInAnnotation;

	// ------------------->> Constructors

	// ------------------->> Public methods

	// ------------------->> Getters / Setters

	/**
	 * @return the floatFieldWithErrorInAnnotation
	 */
	public float getFloatFieldWithErrorInAnnotation() {
		return floatFieldWithErrorInAnnotation;
	}

	/**
	 * @param floatFieldWithErrorInAnnotation
	 *            the floatFieldWithErrorInAnnotation to set
	 */
	public void setFloatFieldWithErrorInAnnotation(
			float floatFieldWithErrorInAnnotation) {
		this.floatFieldWithErrorInAnnotation = floatFieldWithErrorInAnnotation;
	}

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes

}
