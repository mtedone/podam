/**
 *
 */
package uk.co.jemos.podam.test.dto.issue323;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;

import java.io.Serializable;
import java.time.OffsetDateTime;

/**
 * POJO to test the {@link Future},
 * {@link FutureOrPresent},
 * {@link PastOrPresent} or
 * {@link Past} annotations
 *
 * @author liam on 02/01/2024.
 * @since 8.0.1.RELEASE
 */
public class OffsetDateTimeValuePojo implements Serializable {

	// ------------------->> Constants

	private static final long serialVersionUID = 1L;

	// ------------------->> Instance / Static variables

	@Future
	private OffsetDateTime offsetDateTimeFieldWithFutureValue;

	@FutureOrPresent
	private OffsetDateTime offsetDateTimeFieldWithFutureOrPresentValue;

	@PastOrPresent
	private OffsetDateTime offsetDateTimeFieldWithPastOrPresentValue;

	@Past
	private OffsetDateTime offsetDateTimeFieldWithPastValue;

	// ------------------->> Constructors

	// ------------------->> Public methods

	// ------------------->> Getters / Setters

	/**
	 * @return the floatFieldWithMinValueOnly
	 */
	public OffsetDateTime getOffsetDateTimeFieldWithFutureValue() {
		return offsetDateTimeFieldWithFutureValue;
	}

	/**
	 * @param offsetDateTimeFieldWithFutureValue
	 *            the offsetDateTimeFieldWithFutureValue to set
	 */
	public void setOffsetDateTimeFieldWithFutureValue(OffsetDateTime offsetDateTimeFieldWithFutureValue) {
		this.offsetDateTimeFieldWithFutureValue = offsetDateTimeFieldWithFutureValue;
	}

	/**
	 * @return the offsetDateTimeFieldWithFutureOrPresentValue
	 */
	public OffsetDateTime getOffsetDateTimeFieldWithFutureOrPresentValue() {
		return offsetDateTimeFieldWithFutureOrPresentValue;
	}

	/**
	 * @param offsetDateTimeFieldWithFutureOrPresentValue
	 *            the offsetDateTimeFieldWithFutureOrPresentValue to set
	 */
	public void setOffsetDateTimeFieldWithFutureOrPresentValue(OffsetDateTime offsetDateTimeFieldWithFutureOrPresentValue) {
		this.offsetDateTimeFieldWithFutureOrPresentValue = offsetDateTimeFieldWithFutureOrPresentValue;
	}

	/**
	 * @return the offsetDateTimeFieldWithPastOrPresentValue
	 */
	public OffsetDateTime getOffsetDateTimeFieldWithPastOrPresentValue() {
		return offsetDateTimeFieldWithPastOrPresentValue;
	}

	/**
	 * @param offsetDateTimeFieldWithPastOrPresentValue
	 *            the offsetDateTimeFieldWithPastOrPresentValue to set
	 */
	public void setOffsetDateTimeFieldWithPastOrPresentValue(
			OffsetDateTime offsetDateTimeFieldWithPastOrPresentValue) {
		this.offsetDateTimeFieldWithPastOrPresentValue = offsetDateTimeFieldWithPastOrPresentValue;
	}

	/**
	 * @return the offsetDateTimeFieldWithPastValue
	 */
	public OffsetDateTime getOffsetDateTimeFieldWithPastValue() {
		return offsetDateTimeFieldWithPastValue;
	}

	/**
	 * @param offsetDateTimeFieldWithPastValue
	 *            the offsetDateTimeFieldWithPastValue to set
	 */
	public void setOffsetDateTimeFieldWithPastValue(
			OffsetDateTime offsetDateTimeFieldWithPastValue) {
		this.offsetDateTimeFieldWithPastValue = offsetDateTimeFieldWithPastValue;
	}

	/**
	 * Constructs a <code>String</code> with all attributes
	 * in name = value format.
	 *
	 * @return a <code>String</code> representation 
	 * of this object.
	 */
	public String toString()
	{
	    final String TAB = "    ";
	
	    return new StringBuilder().append("OffsetDateTimeRangeValuesPojo ( ")
	        	.append("offsetDateTimeFieldWithFutureValue = ")
				.append(this.offsetDateTimeFieldWithFutureValue)
				.append(TAB)
	        	.append("offsetDateTimeFieldWithFutureOrPresentValue = ")
				.append(this.offsetDateTimeFieldWithFutureOrPresentValue)
				.append(TAB)
	        	.append("offsetDateTimeFieldWithPastOrPresentValue = ")
				.append(this.offsetDateTimeFieldWithPastOrPresentValue)
				.append(TAB)
	        	.append("offsetDateTimeFieldWithPastValue = ")
				.append(this.offsetDateTimeFieldWithPastValue)
				.append(TAB)
	        	.append(" )")
	    		.toString();
	}

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes
}
