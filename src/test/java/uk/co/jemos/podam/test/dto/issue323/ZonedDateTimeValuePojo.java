/**
 *
 */
package uk.co.jemos.podam.test.dto.issue323;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * POJO to test the {@link Future},
 * {@link FutureOrPresent},
 * {@link PastOrPresent} or
 * {@link Past} annotations
 *
 * @author liam on 02/01/2024.
 * @since 8.0.1.RELEASE
 */
public class ZonedDateTimeValuePojo implements Serializable {

	// ------------------->> Constants

	private static final long serialVersionUID = 1L;

	// ------------------->> Instance / Static variables

	@Future
	private ZonedDateTime zonedDateTimeFieldWithFutureValue;

	@FutureOrPresent
	private ZonedDateTime zonedDateTimeFieldWithFutureOrPresentValue;

	@PastOrPresent
	private ZonedDateTime zonedDateTimeFieldWithPastOrPresentValue;

	@Past
	private ZonedDateTime zonedDateTimeFieldWithPastValue;

	// ------------------->> Constructors

	// ------------------->> Public methods

	// ------------------->> Getters / Setters

	/**
	 * @return the floatFieldWithMinValueOnly
	 */
	public ZonedDateTime getZonedDateTimeFieldWithFutureValue() {
		return zonedDateTimeFieldWithFutureValue;
	}

	/**
	 * @param zonedDateTimeFieldWithFutureValue
	 *            the zonedDateTimeFieldWithFutureValue to set
	 */
	public void setZonedDateTimeFieldWithFutureValue(ZonedDateTime zonedDateTimeFieldWithFutureValue) {
		this.zonedDateTimeFieldWithFutureValue = zonedDateTimeFieldWithFutureValue;
	}

	/**
	 * @return the zonedDateTimeFieldWithFutureOrPresentValue
	 */
	public ZonedDateTime getZonedDateTimeFieldWithFutureOrPresentValue() {
		return zonedDateTimeFieldWithFutureOrPresentValue;
	}

	/**
	 * @param zonedDateTimeFieldWithFutureOrPresentValue
	 *            the zonedDateTimeFieldWithFutureOrPresentValue to set
	 */
	public void setZonedDateTimeFieldWithFutureOrPresentValue(ZonedDateTime zonedDateTimeFieldWithFutureOrPresentValue) {
		this.zonedDateTimeFieldWithFutureOrPresentValue = zonedDateTimeFieldWithFutureOrPresentValue;
	}

	/**
	 * @return the zonedDateTimeFieldWithPastOrPresentValue
	 */
	public ZonedDateTime getZonedDateTimeFieldWithPastOrPresentValue() {
		return zonedDateTimeFieldWithPastOrPresentValue;
	}

	/**
	 * @param zonedDateTimeFieldWithPastOrPresentValue
	 *            the zonedDateTimeFieldWithPastOrPresentValue to set
	 */
	public void setZonedDateTimeFieldWithPastOrPresentValue(
			ZonedDateTime zonedDateTimeFieldWithPastOrPresentValue) {
		this.zonedDateTimeFieldWithPastOrPresentValue = zonedDateTimeFieldWithPastOrPresentValue;
	}

	/**
	 * @return the zonedDateTimeFieldWithPastValue
	 */
	public ZonedDateTime getZonedDateTimeFieldWithPastValue() {
		return zonedDateTimeFieldWithPastValue;
	}

	/**
	 * @param zonedDateTimeFieldWithPastValue
	 *            the zonedDateTimeFieldWithPastValue to set
	 */
	public void setZonedDateTimeFieldWithPastValue(
			ZonedDateTime zonedDateTimeFieldWithPastValue) {
		this.zonedDateTimeFieldWithPastValue = zonedDateTimeFieldWithPastValue;
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
	
	    return new StringBuilder().append("ZonedDateTimeRangeValuesPojo ( ")
	        	.append("zonedDateTimeFieldWithFutureValue = ")
				.append(this.zonedDateTimeFieldWithFutureValue)
				.append(TAB)
	        	.append("zonedDateTimeFieldWithFutureOrPresentValue = ")
				.append(this.zonedDateTimeFieldWithFutureOrPresentValue)
				.append(TAB)
	        	.append("zonedDateTimeFieldWithPastOrPresentValue = ")
				.append(this.zonedDateTimeFieldWithPastOrPresentValue)
				.append(TAB)
	        	.append("zonedDateTimeFieldWithPastValue = ")
				.append(this.zonedDateTimeFieldWithPastValue)
				.append(TAB)
	        	.append(" )")
	    		.toString();
	}

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes
}
