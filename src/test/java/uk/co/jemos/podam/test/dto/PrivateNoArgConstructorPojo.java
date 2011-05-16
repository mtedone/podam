/**
 * 
 */
package uk.co.jemos.podam.test.dto;

import java.io.Serializable;

/**
 * @author mtedone
 * 
 */
public class PrivateNoArgConstructorPojo implements Serializable {

	// ------------------->> Constants

	private static final long serialVersionUID = 1L;

	// ------------------->> Instance / Static variables

	private int intField;

	// ------------------->> Constructors

	private PrivateNoArgConstructorPojo() {

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

	// ------------------->> Inner classes

}
