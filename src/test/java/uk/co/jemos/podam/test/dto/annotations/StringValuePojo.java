/**
 * 
 */
package uk.co.jemos.podam.test.dto.annotations;

import java.io.Serializable;

import uk.co.jemos.podam.common.PodamStringValue;
import uk.co.jemos.podam.test.utils.PodamTestConstants;

/**
 * POJO to test the {@link PodamStringValue}
 * 
 * @author mtedone
 * 
 */
public class StringValuePojo implements Serializable {

	// ------------------->> Constants

	private static final long serialVersionUID = 1L;

	// ------------------->> Instance / Static variables

	@PodamStringValue(length = PodamTestConstants.STR_ANNOTATION_TWENTY_LENGTH)
	/** A String attribute with length 20 */
	private String twentyLengthString;

	@PodamStringValue(strValue = PodamTestConstants.STR_ANNOTATION_PRECISE_VALUE)
	private String preciseValueString;

	// ------------------->> Constructors

	// ------------------->> Public methods

	// ------------------->> Getters / Setters

	/**
	 * @return the twentyLengthString
	 */
	public String getTwentyLengthString() {
		return twentyLengthString;
	}

	/**
	 * @param twentyLengthString
	 *            the twentyLengthString to set
	 */
	public void setTwentyLengthString(String twentyLengthString) {
		this.twentyLengthString = twentyLengthString;
	}

	/**
	 * @return the preciseValueString
	 */
	public String getPreciseValueString() {
		return preciseValueString;
	}

	/**
	 * @param preciseValueString
	 *            the preciseValueString to set
	 */
	public void setPreciseValueString(String preciseValueString) {
		this.preciseValueString = preciseValueString;
	}

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	/**
	 * Constructs a <code>String</code> with all attributes in name = value
	 * format.
	 * 
	 * @return a <code>String</code> representation of this object.
	 */
	@Override
	public String toString() {
		final String TAB = "    ";

		StringBuilder retValue = new StringBuilder();

		retValue.append("StringValuesPojo ( ").append("twentyLengthString = ")
				.append(twentyLengthString).append(TAB)
				.append("preciseValueString = ").append(preciseValueString)
				.append(TAB).append(" )");

		return retValue.toString();
	}

	// ------------------->> Inner classes

}
