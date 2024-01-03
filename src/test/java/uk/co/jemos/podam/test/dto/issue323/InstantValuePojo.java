/**
 *
 */
package uk.co.jemos.podam.test.dto.issue323;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;

import java.io.Serializable;
import java.time.Instant;

/**
 * POJO to test the {@link Future},
 * {@link FutureOrPresent},
 * {@link PastOrPresent} or
 * {@link Past} annotations
 *
 * @author liam on 02/01/2024.
 * @since 8.0.1.RELEASE
 */
public class InstantValuePojo implements Serializable {

	// ------------------->> Constants

	private static final long serialVersionUID = 1L;

	// ------------------->> Instance / Static variables

	@Future
	private Instant instantFieldWithFutureValue;

	@FutureOrPresent
	private Instant instantFieldWithFutureOrPresentValue;

	@PastOrPresent
	private Instant instantFieldWithPastOrPresentValue;

	@Past
	private Instant instantFieldWithPastValue;

	// ------------------->> Constructors

	// ------------------->> Public methods

	// ------------------->> Getters / Setters

	/**
	 * @return the floatFieldWithMinValueOnly
	 */
	public Instant getInstantFieldWithFutureValue() {
		return instantFieldWithFutureValue;
	}

	/**
	 * @param instantFieldWithFutureValue
	 *            the instantFieldWithFutureValue to set
	 */
	public void setInstantFieldWithFutureValue(Instant instantFieldWithFutureValue) {
		this.instantFieldWithFutureValue = instantFieldWithFutureValue;
	}

	/**
	 * @return the instantFieldWithFutureOrPresentValue
	 */
	public Instant getInstantFieldWithFutureOrPresentValue() {
		return instantFieldWithFutureOrPresentValue;
	}

	/**
	 * @param instantFieldWithFutureOrPresentValue
	 *            the instantFieldWithFutureOrPresentValue to set
	 */
	public void setInstantFieldWithFutureOrPresentValue(Instant instantFieldWithFutureOrPresentValue) {
		this.instantFieldWithFutureOrPresentValue = instantFieldWithFutureOrPresentValue;
	}

	/**
	 * @return the instantFieldWithPastOrPresentValue
	 */
	public Instant getInstantFieldWithPastOrPresentValue() {
		return instantFieldWithPastOrPresentValue;
	}

	/**
	 * @param instantFieldWithPastOrPresentValue
	 *            the instantFieldWithPastOrPresentValue to set
	 */
	public void setInstantFieldWithPastOrPresentValue(
			Instant instantFieldWithPastOrPresentValue) {
		this.instantFieldWithPastOrPresentValue = instantFieldWithPastOrPresentValue;
	}

	/**
	 * @return the instantFieldWithPastValue
	 */
	public Instant getInstantFieldWithPastValue() {
		return instantFieldWithPastValue;
	}

	/**
	 * @param instantFieldWithPastValue
	 *            the instantFieldWithPastValue to set
	 */
	public void setInstantFieldWithPastValue(
			Instant instantFieldWithPastValue) {
		this.instantFieldWithPastValue = instantFieldWithPastValue;
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
	
	    return new StringBuilder().append("InstantRangeValuesPojo ( ")
	        	.append("instantFieldWithFutureValue = ")
				.append(this.instantFieldWithFutureValue)
				.append(TAB)
	        	.append("instantFieldWithFutureOrPresentValue = ")
				.append(this.instantFieldWithFutureOrPresentValue)
				.append(TAB)
	        	.append("instantFieldWithPastOrPresentValue = ")
				.append(this.instantFieldWithPastOrPresentValue)
				.append(TAB)
	        	.append("instantFieldWithPastValue = ")
				.append(this.instantFieldWithPastValue)
				.append(TAB)
	        	.append(" )")
	    		.toString();
	}

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes
}
