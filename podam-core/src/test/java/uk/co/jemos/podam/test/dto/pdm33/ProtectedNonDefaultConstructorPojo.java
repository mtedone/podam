/**
 * 
 */
package uk.co.jemos.podam.test.dto.pdm33;

import java.io.Serializable;

/**
 * @author mtedone
 * 
 */
public class ProtectedNonDefaultConstructorPojo implements Serializable {

	//------------------->> Constants
	private static final long serialVersionUID = 1L;

	//------------------->> Instance / Static variables

	private String firstName;

	private int intField;

	//------------------->> Constructors

	/**
	 * Full, non-default, constructor.
	 * 
	 * @param firstName
	 *            The first name
	 * @param intField
	 *            The last name
	 */
	protected ProtectedNonDefaultConstructorPojo(String firstName, int intField) {
		this.firstName = firstName;
		this.intField = intField;
	}

	//------------------->> Public methods

	// ------------------->> Getters / Setters

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

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

	//------------------->> Private methods

	//------------------->> equals() / hashcode() / toString()

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

		retValue.append("ProtectedNonDefaultConstructorPojo ( ").append("firstName = ")
				.append(firstName).append(TAB).append("intField = ").append(intField).append(TAB)
				.append(" )");

		return retValue.toString();
	}

	//------------------->> Inner classes

}
