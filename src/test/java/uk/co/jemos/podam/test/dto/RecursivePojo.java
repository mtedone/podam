/**
 * 
 */
package uk.co.jemos.podam.test.dto;

import java.io.Serializable;

/**
 * POJO to test a recursive hierarchy, like the one between parent and child
 * 
 * @author mtedone
 * 
 */
public class RecursivePojo implements Serializable {

	private static final long serialVersionUID = 1L;

	// ------------------->> Constants

	// ------------------->> Instance / Static variables

	/** An int field */
	private int intField;

	/** An instance to itself in a hierarchical structure */
	private RecursivePojo parent;

	// ------------------->> Constructors

	// ------------------->> Public methods

	/**
	 * No-args constructor
	 */
	public RecursivePojo() {
	}

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
	 * @return the parent
	 */
	public RecursivePojo getParent() {
		return parent;
	}

	/**
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(RecursivePojo parent) {
		this.parent = parent;
	}

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	/**
	 * Constructs a <code>String</code> with all attributes in name = value
	 * format.
	 *
	 * <p>Disabled for Serenity Issue: https://github.com/serenity-bdd/serenity-core/issues/66</p>
	 *
	 * @return a <code>String</code> representation of this object.

	@Override
	public String toString() {
		final String TAB = "    ";

		StringBuilder retValue = new StringBuilder();

		retValue.append("RecursivePojo ( ").append("intField = ")
				.append(intField).append(TAB).append("parent = ")
				.append(parent).append(TAB).append(" )");

		return retValue.toString();
	} */

	// ------------------->> Inner classes

}
