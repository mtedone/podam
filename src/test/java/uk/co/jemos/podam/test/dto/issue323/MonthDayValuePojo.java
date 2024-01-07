/**
 *
 */
package uk.co.jemos.podam.test.dto.issue323;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;

import java.io.Serializable;
import java.time.MonthDay;

/**
 * POJO to test the {@link Future},
 * {@link FutureOrPresent},
 * {@link PastOrPresent} or
 * {@link Past} annotations
 *
 * @author liam on 02/01/2024.
 * @since 8.0.1.RELEASE
 */
public class MonthDayValuePojo implements Serializable {

	// ------------------->> Constants

	private static final long serialVersionUID = 1L;

	// ------------------->> Instance / Static variables

	private MonthDay monthDayFieldValue;

	// ------------------->> Constructors

	// ------------------->> Public methods

	// ------------------->> Getters / Setters

	/**
	 * @return the floatFieldWithMinValueOnly
	 */
	public MonthDay getMonthDayFieldValue() {
		return monthDayFieldValue;
	}

	/**
	 * @param monthDayFieldValue
	 *            the monthDayFieldValue to set
	 */
	public void setMonthDayFieldValue(MonthDay monthDayFieldValue) {
		this.monthDayFieldValue = monthDayFieldValue;
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
	
	    return new StringBuilder().append("MonthDayRangeValuesPojo ( ")
	        	.append("monthDayFieldValue = ")
				.append(this.monthDayFieldValue)
				.append(TAB)
	        	.append(" )")
	    		.toString();
	}

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes
}
