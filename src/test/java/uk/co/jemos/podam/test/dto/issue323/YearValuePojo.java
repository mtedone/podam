/**
 *
 */
package uk.co.jemos.podam.test.dto.issue323;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;

import java.io.Serializable;
import java.time.Year;

/**
 * POJO to test the {@link Future},
 * {@link FutureOrPresent},
 * {@link PastOrPresent} or
 * {@link Past} annotations
 *
 * @author liam on 03/01/2024.
 * @since 8.0.1.RELEASE
 */
public class YearValuePojo implements Serializable {

	// ------------------->> Constants

	private static final long serialVersionUID = 1L;

	// ------------------->> Instance / Static variables

	@Future
	private Year yearFieldWithFutureValue;

	@FutureOrPresent
	private Year yearFieldWithFutureOrPresentValue;

	@PastOrPresent
	private Year yearFieldWithPastOrPresentValue;

	@Past
	private Year yearFieldWithPastValue;

	// ------------------->> Constructors

	// ------------------->> Public methods

	// ------------------->> Getters / Setters

	/**
	 * @return the floatFieldWithMinValueOnly
	 */
	public Year getYearFieldWithFutureValue() {
		return yearFieldWithFutureValue;
	}

	/**
	 * @param yearFieldWithFutureValue
	 *            the yearFieldWithFutureValue to set
	 */
	public void setYearFieldWithFutureValue(Year yearFieldWithFutureValue) {
		this.yearFieldWithFutureValue = yearFieldWithFutureValue;
	}

	/**
	 * @return the yearFieldWithFutureOrPresentValue
	 */
	public Year getYearFieldWithFutureOrPresentValue() {
		return yearFieldWithFutureOrPresentValue;
	}

	/**
	 * @param yearFieldWithFutureOrPresentValue
	 *            the yearFieldWithFutureOrPresentValue to set
	 */
	public void setYearFieldWithFutureOrPresentValue(Year yearFieldWithFutureOrPresentValue) {
		this.yearFieldWithFutureOrPresentValue = yearFieldWithFutureOrPresentValue;
	}

	/**
	 * @return the yearFieldWithPastOrPresentValue
	 */
	public Year getYearFieldWithPastOrPresentValue() {
		return yearFieldWithPastOrPresentValue;
	}

	/**
	 * @param yearFieldWithPastOrPresentValue
	 *            the yearFieldWithPastOrPresentValue to set
	 */
	public void setYearFieldWithPastOrPresentValue(
			Year yearFieldWithPastOrPresentValue) {
		this.yearFieldWithPastOrPresentValue = yearFieldWithPastOrPresentValue;
	}

	/**
	 * @return the yearFieldWithPastValue
	 */
	public Year getYearFieldWithPastValue() {
		return yearFieldWithPastValue;
	}

	/**
	 * @param yearFieldWithPastValue
	 *            the yearFieldWithPastValue to set
	 */
	public void setYearFieldWithPastValue(
			Year yearFieldWithPastValue) {
		this.yearFieldWithPastValue = yearFieldWithPastValue;
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
	
	    return new StringBuilder().append("YearRangeValuesPojo ( ")
	        	.append("yearFieldWithFutureValue = ")
				.append(this.yearFieldWithFutureValue)
				.append(TAB)
	        	.append("yearFieldWithFutureOrPresentValue = ")
				.append(this.yearFieldWithFutureOrPresentValue)
				.append(TAB)
	        	.append("yearFieldWithPastOrPresentValue = ")
				.append(this.yearFieldWithPastOrPresentValue)
				.append(TAB)
	        	.append("yearFieldWithPastValue = ")
				.append(this.yearFieldWithPastValue)
				.append(TAB)
	        	.append(" )")
	    		.toString();
	}

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes
}
