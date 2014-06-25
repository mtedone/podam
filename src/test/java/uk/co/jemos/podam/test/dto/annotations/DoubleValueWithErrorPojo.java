/**
 * 
 */
package uk.co.jemos.podam.test.dto.annotations;

import java.io.Serializable;

import uk.co.jemos.podam.common.PodamDoubleValue;

/**
 * POJO to test that {@link PodamDoubleValue#numValue()} with non parsable
 * values produces an error.
 * 
 * @author mtedone
 * 
 */
public class DoubleValueWithErrorPojo implements Serializable {

	// ------------------->> Constants
	private static final long serialVersionUID = 1L;

	// ------------------->> Instance / Static variables
	@PodamDoubleValue(numValue = "fajhfakh")
	private double doubleFieldWithErrorInAnnotation;

	// ------------------->> Constructors

	// ------------------->> Public methods

	// ------------------->> Getters / Setters

	/**
	 * @return the doubleFieldWithErrorInAnnotation
	 */
	public double getDoubleFieldWithErrorInAnnotation() {
		return doubleFieldWithErrorInAnnotation;
	}

	/**
	 * @param doubleFieldWithErrorInAnnotation
	 *            the doubleFieldWithErrorInAnnotation to set
	 */
	public void setDoubleFieldWithErrorInAnnotation(
			double doubleFieldWithErrorInAnnotation) {
		this.doubleFieldWithErrorInAnnotation = doubleFieldWithErrorInAnnotation;
	}

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes

}
