/**
 * 
 */
package uk.co.jemos.podam.test.dto.annotations;

import java.io.Serializable;

import uk.co.jemos.podam.common.PodamLongValue;

/**
 * POJO to test that {@link PodamLongValue#numValue()} which contains a non
 * parseable value triggers exception.
 * 
 * @author mtedone
 * 
 */
public class LongValueWithErrorPojo implements Serializable {

	// ------------------->> Constants

	private static final long serialVersionUID = 1L;
	// ------------------->> Instance / Static variables

	@PodamLongValue(numValue = "afhafhakflh")
	private long longFieldWithErrorInAnnotation;

	// ------------------->> Constructors

	// ------------------->> Public methods

	// ------------------->> Getters / Setters

	/**
	 * @return the longFieldWithErrorInAnnotation
	 */
	public long getLongFieldWithErrorInAnnotation() {
		return longFieldWithErrorInAnnotation;
	}

	/**
	 * @param longFieldWithErrorInAnnotation
	 *            the longFieldWithErrorInAnnotation to set
	 */
	public void setLongFieldWithErrorInAnnotation(
			long longFieldWithErrorInAnnotation) {
		this.longFieldWithErrorInAnnotation = longFieldWithErrorInAnnotation;
	}

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes

}
