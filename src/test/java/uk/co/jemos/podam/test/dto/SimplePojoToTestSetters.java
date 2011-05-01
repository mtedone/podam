/**
 * 
 */
package uk.co.jemos.podam.test.dto;

import java.io.Serializable;

/**
 * @author mtedone
 * 
 */
public class SimplePojoToTestSetters implements Serializable {

	private static final long serialVersionUID = 1L;

	// ------------------->> Constants

	// ------------------->> Instance / Static variables

	/** A string field */
	private String stringField;

	/** An int field */
	private int intField;

	// ------------------->> Constructors

	/**
	 * No args-constructor
	 */
	public SimplePojoToTestSetters() {
	}

	// ------------------->> Public methods

	// ------------------->> Getters / Setters

	/**
	 * @return the stringField
	 */
	public String getStringField() {
		return stringField;
	}

	/**
	 * @param stringField
	 *            the stringField to set
	 */
	public void setStringField(String stringField) {
		this.stringField = stringField;
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

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + intField;
		result = (prime * result)
				+ ((stringField == null) ? 0 : stringField.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		SimplePojoToTestSetters other = (SimplePojoToTestSetters) obj;
		if (intField != other.intField) {
			return false;
		}
		if (stringField == null) {
			if (other.stringField != null) {
				return false;
			}
		} else if (!stringField.equals(other.stringField)) {
			return false;
		}
		return true;
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

		retValue.append("SimplePojoToTestSetters ( ").append("stringField = ")
				.append(stringField).append(TAB).append("intField = ")
				.append(intField).append(TAB).append(" )");

		return retValue.toString();
	}

	// ------------------->> Inner classes

}
