/**
 *
 */
package uk.co.jemos.podam.test.dto.issue323;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;

import java.io.Serializable;
import java.time.YearMonth;

/**
 * POJO to test the {@link Future},
 * {@link FutureOrPresent},
 * {@link PastOrPresent} or
 * {@link Past} annotations
 *
 * @author liam on 02/01/2024.
 * @since 8.0.1.RELEASE
 */
public class YearMonthValuePojo implements Serializable {

	// ------------------->> Constants

	private static final long serialVersionUID = 1L;

	// ------------------->> Instance / Static variables

	@Future
	private YearMonth yearMonthFieldWithFutureValue;

	@FutureOrPresent
	private YearMonth yearMonthFieldWithFutureOrPresentValue;

	@PastOrPresent
	private YearMonth yearMonthFieldWithPastOrPresentValue;

	@Past
	private YearMonth yearMonthFieldWithPastValue;

	// ------------------->> Constructors

	// ------------------->> Public methods

	// ------------------->> Getters / Setters

	/**
	 * @return the floatFieldWithMinValueOnly
	 */
	public YearMonth getYearMonthFieldWithFutureValue() {
		return yearMonthFieldWithFutureValue;
	}

	/**
	 * @param yearMonthFieldWithFutureValue
	 *            the yearMonthFieldWithFutureValue to set
	 */
	public void setYearMonthFieldWithFutureValue(YearMonth yearMonthFieldWithFutureValue) {
		this.yearMonthFieldWithFutureValue = yearMonthFieldWithFutureValue;
	}

	/**
	 * @return the yearMonthFieldWithFutureOrPresentValue
	 */
	public YearMonth getYearMonthFieldWithFutureOrPresentValue() {
		return yearMonthFieldWithFutureOrPresentValue;
	}

	/**
	 * @param yearMonthFieldWithFutureOrPresentValue
	 *            the yearMonthFieldWithFutureOrPresentValue to set
	 */
	public void setYearMonthFieldWithFutureOrPresentValue(YearMonth yearMonthFieldWithFutureOrPresentValue) {
		this.yearMonthFieldWithFutureOrPresentValue = yearMonthFieldWithFutureOrPresentValue;
	}

	/**
	 * @return the yearMonthFieldWithPastOrPresentValue
	 */
	public YearMonth getYearMonthFieldWithPastOrPresentValue() {
		return yearMonthFieldWithPastOrPresentValue;
	}

	/**
	 * @param yearMonthFieldWithPastOrPresentValue
	 *            the yearMonthFieldWithPastOrPresentValue to set
	 */
	public void setYearMonthFieldWithPastOrPresentValue(
			YearMonth yearMonthFieldWithPastOrPresentValue) {
		this.yearMonthFieldWithPastOrPresentValue = yearMonthFieldWithPastOrPresentValue;
	}

	/**
	 * @return the yearMonthFieldWithPastValue
	 */
	public YearMonth getYearMonthFieldWithPastValue() {
		return yearMonthFieldWithPastValue;
	}

	/**
	 * @param yearMonthFieldWithPastValue
	 *            the yearMonthFieldWithPastValue to set
	 */
	public void setYearMonthFieldWithPastValue(
			YearMonth yearMonthFieldWithPastValue) {
		this.yearMonthFieldWithPastValue = yearMonthFieldWithPastValue;
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
	
	    return new StringBuilder().append("YearMonthRangeValuesPojo ( ")
	        	.append("yearMonthFieldWithFutureValue = ")
				.append(this.yearMonthFieldWithFutureValue)
				.append(TAB)
	        	.append("yearMonthFieldWithFutureOrPresentValue = ")
				.append(this.yearMonthFieldWithFutureOrPresentValue)
				.append(TAB)
	        	.append("yearMonthFieldWithPastOrPresentValue = ")
				.append(this.yearMonthFieldWithPastOrPresentValue)
				.append(TAB)
	        	.append("yearMonthFieldWithPastValue = ")
				.append(this.yearMonthFieldWithPastValue)
				.append(TAB)
	        	.append(" )")
	    		.toString();
	}

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes
}
