/**
 * 
 */
package uk.co.jemos.podam.test.dto;

import java.io.Serializable;

/**
 * @author mtedone
 * 
 */
public class NoDefaultConstructorPojo implements Serializable {

	// ------------------->> Constants

	private static final long serialVersionUID = 1L;

	// ------------------->> Instance / Static variables

	/** An int field */
	private int intField;

	// ------------------->> Constructors

	/**
	 * @param intField
	 */
	public NoDefaultConstructorPojo(int intField) {
		super();
		this.intField = intField;
	}

	// ------------------->> Public methods

	// ------------------->> Getters / Setters

	/**
	 * @return the intField
	 */
	public int getIntField() {
		return intField;
	}

	/**
	 * @param intField
	 *            the intField to set
	 */
	public void setIntField(int intField) {
		this.intField = intField;
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

		retValue.append("NoDefaultConstructorPojo ( ").append("intField = ")
				.append(intField).append(TAB).append(" )");

		return retValue.toString();
	}

	// ------------------->> Inner classes

}
