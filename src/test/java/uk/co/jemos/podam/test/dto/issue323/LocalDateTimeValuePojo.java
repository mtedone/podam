/**
 *
 */
package uk.co.jemos.podam.test.dto.issue323;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * POJO to test the {@link Future},
 * {@link FutureOrPresent},
 * {@link PastOrPresent} or
 * {@link Past} annotations
 *
 * @author liam on 02/01/2024.
 * @since 8.0.1.RELEASE
 */
public class LocalDateTimeValuePojo implements Serializable {

	// ------------------->> Constants

	private static final long serialVersionUID = 1L;

	// ------------------->> Instance / Static variables

	@Future
	private LocalDateTime localDateTimeFieldWithFutureValue;

	@FutureOrPresent
	private LocalDateTime localDateTimeFieldWithFutureOrPresentValue;

	@PastOrPresent
	private LocalDateTime localDateTimeFieldWithPastOrPresentValue;

	@Past
	private LocalDateTime localDateTimeFieldWithPastValue;

	// ------------------->> Constructors

	// ------------------->> Public methods

	// ------------------->> Getters / Setters

	/**
	 * @return the floatFieldWithMinValueOnly
	 */
	public LocalDateTime getLocalDateTimeFieldWithFutureValue() {
		return localDateTimeFieldWithFutureValue;
	}

	/**
	 * @param localDateTimeFieldWithFutureValue
	 *            the localDateTimeFieldWithFutureValue to set
	 */
	public void setLocalDateTimeFieldWithFutureValue(LocalDateTime localDateTimeFieldWithFutureValue) {
		this.localDateTimeFieldWithFutureValue = localDateTimeFieldWithFutureValue;
	}

	/**
	 * @return the localDateTimeFieldWithFutureOrPresentValue
	 */
	public LocalDateTime getLocalDateTimeFieldWithFutureOrPresentValue() {
		return localDateTimeFieldWithFutureOrPresentValue;
	}

	/**
	 * @param localDateTimeFieldWithFutureOrPresentValue
	 *            the localDateTimeFieldWithFutureOrPresentValue to set
	 */
	public void setLocalDateTimeFieldWithFutureOrPresentValue(LocalDateTime localDateTimeFieldWithFutureOrPresentValue) {
		this.localDateTimeFieldWithFutureOrPresentValue = localDateTimeFieldWithFutureOrPresentValue;
	}

	/**
	 * @return the localDateTimeFieldWithPastOrPresentValue
	 */
	public LocalDateTime getLocalDateTimeFieldWithPastOrPresentValue() {
		return localDateTimeFieldWithPastOrPresentValue;
	}

	/**
	 * @param localDateTimeFieldWithPastOrPresentValue
	 *            the localDateTimeFieldWithPastOrPresentValue to set
	 */
	public void setLocalDateTimeFieldWithPastOrPresentValue(
			LocalDateTime localDateTimeFieldWithPastOrPresentValue) {
		this.localDateTimeFieldWithPastOrPresentValue = localDateTimeFieldWithPastOrPresentValue;
	}

	/**
	 * @return the localDateTimeFieldWithPastValue
	 */
	public LocalDateTime getLocalDateTimeFieldWithPastValue() {
		return localDateTimeFieldWithPastValue;
	}

	/**
	 * @param localDateTimeFieldWithPastValue
	 *            the localDateTimeFieldWithPastValue to set
	 */
	public void setLocalDateTimeFieldWithPastValue(
			LocalDateTime localDateTimeFieldWithPastValue) {
		this.localDateTimeFieldWithPastValue = localDateTimeFieldWithPastValue;
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
	
	    return new StringBuilder().append("LocalDateTimeRangeValuesPojo ( ")
	        	.append("localDateTimeFieldWithFutureValue = ")
				.append(this.localDateTimeFieldWithFutureValue)
				.append(TAB)
	        	.append("localDateTimeFieldWithFutureOrPresentValue = ")
				.append(this.localDateTimeFieldWithFutureOrPresentValue)
				.append(TAB)
	        	.append("localDateTimeFieldWithPastOrPresentValue = ")
				.append(this.localDateTimeFieldWithPastOrPresentValue)
				.append(TAB)
	        	.append("localDateTimeFieldWithPastValue = ")
				.append(this.localDateTimeFieldWithPastValue)
				.append(TAB)
	        	.append(" )")
	    		.toString();
	}

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes
}
