/**
 * 
 */
package uk.co.jemos.podam.test.dto.annotations;

import java.io.Serializable;

import uk.co.jemos.podam.common.PodamBooleanValue;

/**
 * POJO to test {@link PodamBooleanValue} annotation.
 * 
 * @author mtedone
 * 
 */
public class BooleanValuePojo implements Serializable {

	// ------------------->> Constants

	private static final long serialVersionUID = 1L;

	// ------------------->> Instance / Static variables

	/** A boolean field with value forced to true */
	@PodamBooleanValue(boolValue = true)
	private boolean boolDefaultToTrue;

	/** A boolean field with value forced to false */
	@PodamBooleanValue(boolValue = false)
	private boolean boolDefaultToFalse = true;

	/** A boolean object field with value forced to true */
	@PodamBooleanValue(boolValue = true)
	private Boolean boolObjectDefaultToTrue;

	/** A boolean object field with value forced to false */
	@PodamBooleanValue(boolValue = false)
	private Boolean boolObjectDefaultToFalse = true;

	// ------------------->> Constructors

	// ------------------->> Public methods

	// ------------------->> Getters / Setters

	/**
	 * @return the boolDefaultToTrue
	 */
	public boolean isBoolDefaultToTrue() {
		return boolDefaultToTrue;
	}

	/**
	 * @param boolDefaultToTrue
	 *            the boolDefaultToTrue to set
	 */
	public void setBoolDefaultToTrue(boolean boolDefaultToTrue) {
		this.boolDefaultToTrue = boolDefaultToTrue;
	}

	/**
	 * @return the boolDefaultToFalse
	 */
	public boolean isBoolDefaultToFalse() {
		return boolDefaultToFalse;
	}

	/**
	 * @param boolDefaultToFalse
	 *            the boolDefaultToFalse to set
	 */
	public void setBoolDefaultToFalse(boolean boolDefaultToFalse) {
		this.boolDefaultToFalse = boolDefaultToFalse;
	}

	/**
	 * @return the boolObjectDefaultToTrue
	 */
	public Boolean getBoolObjectDefaultToTrue() {
		return boolObjectDefaultToTrue;
	}

	/**
	 * @param boolObjectDefaultToTrue
	 *            the boolObjectDefaultToTrue to set
	 */
	public void setBoolObjectDefaultToTrue(Boolean boolObjectDefaultToTrue) {
		this.boolObjectDefaultToTrue = boolObjectDefaultToTrue;
	}

	/**
	 * @return the boolObjectDefaultToFalse
	 */
	public Boolean getBoolObjectDefaultToFalse() {
		return boolObjectDefaultToFalse;
	}

	/**
	 * @param boolObjectDefaultToFalse
	 *            the boolObjectDefaultToFalse to set
	 */
	public void setBoolObjectDefaultToFalse(Boolean boolObjectDefaultToFalse) {
		this.boolObjectDefaultToFalse = boolObjectDefaultToFalse;
	}

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

		retValue.append("BooleanValuePojo ( ").append("boolDefaultToTrue = ")
				.append(boolDefaultToTrue).append(TAB)
				.append("boolDefaultToFalse = ").append(boolDefaultToFalse)
				.append(TAB).append("boolObjectDefaultToTrue = ")
				.append(boolObjectDefaultToTrue).append(TAB)
				.append("boolObjectDefaultToFalse = ")
				.append(boolObjectDefaultToFalse).append(TAB).append(" )");

		return retValue.toString();
	}

	// ------------------->> Inner classes

}
