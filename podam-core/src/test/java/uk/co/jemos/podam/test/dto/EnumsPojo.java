/**
 * 
 */
package uk.co.jemos.podam.test.dto;

import java.io.Serializable;

import uk.co.jemos.podam.test.enums.ExternalRatePodamEnum;

/**
 * A Pojo using enums for instance attributes
 * 
 * @author mtedone
 * 
 */
public class EnumsPojo implements Serializable {

	// ------------------->> Constants

	private static final long serialVersionUID = 1L;

	// ------------------->> Instance / Static variables

	private RatePodamInternal ratePodamInternal;

	private ExternalRatePodamEnum ratePodamExternal;

	// ------------------->> Constructors

	// ------------------->> Public methods

	// ------------------->> Getters / Setters

	/**
	 * @return the ratePodamInternal
	 */
	public RatePodamInternal getRatePodamInternal() {
		return ratePodamInternal;
	}

	/**
	 * @param ratePodamInternal
	 *            the ratePodamInternal to set
	 */
	public void setRatePodamInternal(RatePodamInternal ratePodamInternal) {
		this.ratePodamInternal = ratePodamInternal;
	}

	/**
	 * @return the ratePodamExternal
	 */
	public ExternalRatePodamEnum getRatePodamExternal() {
		return ratePodamExternal;
	}

	/**
	 * @param ratePodamExternal
	 *            the ratePodamExternal to set
	 */
	public void setRatePodamExternal(ExternalRatePodamEnum ratePodamExternal) {
		this.ratePodamExternal = ratePodamExternal;
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

		retValue.append("EnumsPojo ( ").append("ratePodamInternal = ")
				.append(ratePodamInternal).append(TAB)
				.append("ratePodamExternal = ").append(ratePodamExternal)
				.append(TAB).append(" )");

		return retValue.toString();
	}

	// ------------------->> Inner classes

	/**
	 * Some internal enum
	 * 
	 * @author mtedone
	 * 
	 */
	public enum RatePodamInternal {
		COOL, ROCKS, SUPERCOOL
	}

}
