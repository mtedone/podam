/**
 *
 */
package uk.co.jemos.podam.test.dto.issue323;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * POJO to test the {@link Future},
 * {@link FutureOrPresent},
 * {@link PastOrPresent} or
 * {@link Past} annotations
 *
 * @author liam on 02/01/2024.
 * @since 8.0.1.RELEASE
 */
public class LocalDateValuePojo implements Serializable {

	// ------------------->> Constants

	private static final long serialVersionUID = 1L;

	// ------------------->> Instance / Static variables

	@Future
	private LocalDate localDateFieldWithFutureValue;

	@FutureOrPresent
	private LocalDate localDateFieldWithFutureOrPresentValue;

	@PastOrPresent
	private LocalDate localDateFieldWithPastOrPresentValue;

	@Past
	private LocalDate localDateFieldWithPastValue;

	// ------------------->> Constructors

	// ------------------->> Public methods

	// ------------------->> Getters / Setters

	/**
	 * @return the floatFieldWithMinValueOnly
	 */
	public LocalDate getLocalDateFieldWithFutureValue() {
		return localDateFieldWithFutureValue;
	}

	/**
	 * @param localDateFieldWithFutureValue
	 *            the localDateFieldWithFutureValue to set
	 */
	public void setLocalDateFieldWithFutureValue(LocalDate localDateFieldWithFutureValue) {
		this.localDateFieldWithFutureValue = localDateFieldWithFutureValue;
	}

	/**
	 * @return the localDateFieldWithFutureOrPresentValue
	 */
	public LocalDate getLocalDateFieldWithFutureOrPresentValue() {
		return localDateFieldWithFutureOrPresentValue;
	}

	/**
	 * @param localDateFieldWithFutureOrPresentValue
	 *            the localDateFieldWithFutureOrPresentValue to set
	 */
	public void setLocalDateFieldWithFutureOrPresentValue(LocalDate localDateFieldWithFutureOrPresentValue) {
		this.localDateFieldWithFutureOrPresentValue = localDateFieldWithFutureOrPresentValue;
	}

	/**
	 * @return the localDateFieldWithPastOrPresentValue
	 */
	public LocalDate getLocalDateFieldWithPastOrPresentValue() {
		return localDateFieldWithPastOrPresentValue;
	}

	/**
	 * @param localDateFieldWithPastOrPresentValue
	 *            the localDateFieldWithPastOrPresentValue to set
	 */
	public void setLocalDateFieldWithPastOrPresentValue(
			LocalDate localDateFieldWithPastOrPresentValue) {
		this.localDateFieldWithPastOrPresentValue = localDateFieldWithPastOrPresentValue;
	}

	/**
	 * @return the localDateFieldWithPastValue
	 */
	public LocalDate getLocalDateFieldWithPastValue() {
		return localDateFieldWithPastValue;
	}

	/**
	 * @param localDateFieldWithPastValue
	 *            the localDateFieldWithPastValue to set
	 */
	public void setLocalDateFieldWithPastValue(
			LocalDate localDateFieldWithPastValue) {
		this.localDateFieldWithPastValue = localDateFieldWithPastValue;
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
	
	    return new StringBuilder().append("LocalDateRangeValuesPojo ( ")
	        	.append("localDateFieldWithFutureValue = ")
				.append(this.localDateFieldWithFutureValue)
				.append(TAB)
	        	.append("localDateFieldWithFutureOrPresentValue = ")
				.append(this.localDateFieldWithFutureOrPresentValue)
				.append(TAB)
	        	.append("localDateFieldWithPastOrPresentValue = ")
				.append(this.localDateFieldWithPastOrPresentValue)
				.append(TAB)
	        	.append("localDateFieldWithPastValue = ")
				.append(this.localDateFieldWithPastValue)
				.append(TAB)
	        	.append(" )")
	    		.toString();
	}

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes
}
