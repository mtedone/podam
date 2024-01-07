/**
 *
 */
package uk.co.jemos.podam.test.dto.issue323;

import java.io.Serializable;
import java.time.Period;

/**
 * POJO to test the annotations
 *
 * @author liam on 04/01/2024.
 * @since 8.0.1.RELEASE
 */
public class PeriodValuePojo implements Serializable {

	// ------------------->> Constants

	private static final long serialVersionUID = 1L;

	// ------------------->> Instance / Static variables

	private Period periodFieldValue;

	// ------------------->> Constructors

	// ------------------->> Public methods

	// ------------------->> Getters / Setters

	/**
	 * @return the floatFieldWithMinValueOnly
	 */
	public Period getPeriodFieldValue() {
		return periodFieldValue;
	}

	/**
	 * @param periodFieldValue
	 *            the periodFieldValue to set
	 */
	public void setPeriodFieldValue(Period periodFieldValue) {
		this.periodFieldValue = periodFieldValue;
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
	
	    return new StringBuilder().append("PeriodRangeValuesPojo ( ")
	        	.append("periodFieldValue = ")
				.append(this.periodFieldValue)
				.append(TAB)
	        	.append(" )")
	    		.toString();
	}

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes
}
