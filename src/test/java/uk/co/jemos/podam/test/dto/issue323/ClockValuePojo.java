/**
 * 
 */
package uk.co.jemos.podam.test.dto.issue323;

import java.io.Serializable;
import java.time.Clock;

/**
 * POJO to test the annotations
 *
 * @author liam on 02/01/2024.
 * @since 8.0.1.RELEASE
 */
public class ClockValuePojo implements Serializable {

	// ------------------->> Constants

	private static final long serialVersionUID = 1L;

	// ------------------->> Instance / Static variables

	private Clock clockFieldValue;

	// ------------------->> Constructors

	// ------------------->> Public methods

	// ------------------->> Getters / Setters

	/**
	 * @return the floatFieldWithMinValueOnly
	 */
	public Clock getClockFieldValue() {
		return clockFieldValue;
	}

	/**
	 * @param clockFieldValue
	 *            the clockFieldValue to set
	 */
	public void setClockFieldValue(Clock clockFieldValue) {
		this.clockFieldValue = clockFieldValue;
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
	
	    return new StringBuilder().append("ClockRangeValuesPojo ( ")
	        	.append("clockFieldValue = ")
				.append(this.clockFieldValue)
				.append(TAB)
	        	.append(" )")
	    		.toString();
	}

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes
}
