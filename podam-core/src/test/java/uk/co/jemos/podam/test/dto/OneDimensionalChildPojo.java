/**
 * 
 */
package uk.co.jemos.podam.test.dto;

/**
 * Child class to test inheritance
 * 
 * @author mtedone
 * 
 */
public class OneDimensionalChildPojo extends AbstractOneDimensionalPojo {

	// ------------------->> Constants

	// ------------------->> Instance / Static variables

	/** An int field */
	private int intField;

	/** A String field */
	private String strField;

	// ------------------->> Constructors

	/**
	 * No-args constructor
	 */
	public OneDimensionalChildPojo() {
		super();
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

	/**
	 * @return the strField
	 */
	public String getStrField() {
		return strField;
	}

	/**
	 * @param strField
	 *            the strField to set
	 */
	public void setStrField(String strField) {
		this.strField = strField;
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
		retValue.append(super.toString()).append(TAB);

		retValue.append("OneDimensionalChildPojo ( ").append("intField = ")
				.append(intField).append(TAB).append("strField = ")
				.append(strField).append(TAB).append(" )");

		return retValue.toString();
	}

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes

}
