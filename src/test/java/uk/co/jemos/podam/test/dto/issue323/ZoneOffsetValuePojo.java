/**
 *
 */
package uk.co.jemos.podam.test.dto.issue323;

import java.io.Serializable;
import java.time.ZoneOffset;

/**
 * POJO to test the annotations
 *
 * @author liam on 02/01/2024.
 * @since 8.0.1.RELEASE
 */
public class ZoneOffsetValuePojo implements Serializable {

	// ------------------->> Constants

	private static final long serialVersionUID = 1L;

	// ------------------->> Instance / Static variables

	private ZoneOffset zoneOffsetFieldValue;


	// ------------------->> Constructors

	// ------------------->> Public methods

	// ------------------->> Getters / Setters

	/**
	 * @return the floatFieldWithMinValueOnly
	 */
	public ZoneOffset getZoneOffsetFieldValue() {
		return zoneOffsetFieldValue;
	}

	/**
	 * @param zoneOffsetFieldValue
	 *            the zoneOffsetFieldValue to set
	 */
	public void setZoneOffsetFieldValue(ZoneOffset zoneOffsetFieldValue) {
		this.zoneOffsetFieldValue = zoneOffsetFieldValue;
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
	
	    return new StringBuilder().append("ZoneOffsetRangeValuesPojo ( ")
	        	.append("zoneOffsetFieldValue = ")
				.append(this.zoneOffsetFieldValue)
				.append(TAB)
	        	.append(" )")
	    		.toString();
	}

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes
}
